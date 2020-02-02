package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.lekariterminiDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Cenovnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Zahtev;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.CenovnikRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.LekarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LekarService {

    @Autowired
    private LekarRepository lekarRepository;

    @Autowired
    private CenovnikRepository cenovnikRepository;

    public List<Lekar> findAll() {
    return lekarRepository.findAll();
    }

    public Page<Lekar> findAll(Pageable page) {
    return lekarRepository.findAll(page);
    }

    public List<Lekar> findAllByIme(String ime) {
    return lekarRepository.findAllByIme(ime);
    }

    public List<Lekar> findAllByPrezime(String prezime) {
    return lekarRepository.findAllByPrezime(prezime);
    }

    public List<Lekar> findAllByKlinika(Klinika k){return lekarRepository.findByKlinika(k);}

    public List<Lekar> findByImeAndPrezime(String ime, String prezime) {
    return lekarRepository.findByImeAndPrezimeAllIgnoringCase(ime, prezime);
    }

    @Transactional
    public Lekar update(Lekar lekar) {
        Lekar p = lekarRepository.findByJbo(lekar.getJbo());
        if (p == null) {
            return null;
        }
        p.setAdresa(lekar.getAdresa());
        p.setDrzava(lekar.getDrzava());
        p.setEmail(lekar.getEmail());
        p.setGrad(lekar.getGrad());
        p.setIme(lekar.getIme());
        p.setPrezime(lekar.getPrezime());
        p.setKontaktTelefon(lekar.getKontaktTelefon());
        p.setLozinka(lekar.getLozinka());
        lekarRepository.save(p);
        return p;
    }

    @Transactional
    public Lekar add(Lekar lekar){
        return lekarRepository.save(lekar);
    }

    @Transactional
    public Lekar remove(Long id){
        lekarRepository.deleteById(id);

        if(!lekarRepository.findById(id).isPresent()) {
            return null;
        }
        return lekarRepository.findById(id).get();
    }

    public List<Lekar> getSlobodniLekari(Zahtev zahtev){

        String vremeZakazivanja = "";

        int osamSati = 8 * 60;
        int sesnaestSati = 16 * 60;
        int dvanaestSati = 24 * 60;

        int satPocetak = Integer.parseInt(zahtev.getPocetak().split(":")[0]) * 60;
        int minutPocetak = Integer.parseInt(zahtev.getPocetak().split(":")[1]);

        int satKraj = Integer.parseInt(zahtev.getKraj().split(":")[0]) * 60;
        int minutKraj = Integer.parseInt(zahtev.getKraj().split(":")[1]);

        int pocetak = satPocetak + minutPocetak;
        int kraj  = satKraj + minutKraj;

        if (pocetak == 0){
            vremeZakazivanja = "Treca smena od 00:00 do 8:00";
        }
        else if (kraj == 0) {
            vremeZakazivanja = "Druga smena od 16:00 do 00:00";
        }
        else if (pocetak >= osamSati && kraj <= sesnaestSati){
            vremeZakazivanja = "Prva smena od 8:00 do 16:00";
        }
        else if (pocetak >= 0 && kraj <= osamSati){
            vremeZakazivanja = "Treca smena od 00:00 do 8:00";
        }
        else if (pocetak >= sesnaestSati){
            vremeZakazivanja = "Druga smena od 16:00 do 00:00";
        }

        String specijalizacija = cenovnikRepository.findById(zahtev.getIdStavke()).get().getSpecijalizacija();
        List<Long> radeUToVreme = new ArrayList<>();

        if (zahtev.getTipPosete() == "Operacija") {
            radeUToVreme = lekarRepository.daLiJeRadnoVremeOperacija(zahtev.getIdKlinike(), vremeZakazivanja, specijalizacija);
        }else {
            radeUToVreme = lekarRepository.daLiJeRadnoVremePregled(zahtev.getIdKlinike(), vremeZakazivanja, specijalizacija);
        }

        HashMap<Long, Long> slobodni = new HashMap<Long, Long>();
        for (Long id : radeUToVreme){
            if (lekarRepository.imaOperacije(id, zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()
                && lekarRepository.imaPreglede(id, zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()){
                slobodni.put(id, id);
            }
        }

        List<Lekar> sviLekari = lekarRepository.findAll();
        List<Lekar> odredjeni = new ArrayList<>();
        for (Lekar l : sviLekari){
            if(slobodni.containsKey(l.getId())){
                odredjeni.add(l);
            }
        }

        return odredjeni;
    }

    public boolean lekarSlobodan(Zahtev zahtev){
        Lekar lekar = lekarRepository.findByJbo(zahtev.getPosiljalacJbo());
        if (lekarRepository.imaOperacije(lekar.getId(), zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()
                && lekarRepository.imaPreglede(lekar.getId(), zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()){
            return true;
        }
        return false;
    }


    public HashMap<String,Lekar> getSlobodniLekariTermini(lekariterminiDTO zahtev) throws ParseException {

        DateFormat dateFormat= new SimpleDateFormat("hh:mm");

        String datum=zahtev.getDatum().replace('/','.');
        System.out.println(datum);

        Cenovnik cen = cenovnikRepository.findByNaziv(zahtev.getSpecijalizacija());
        String specijalizacija=cen.getSpecijalizacija();

        System.out.print(specijalizacija);
        System.out.println();

        List<Lekar> sviLekari = lekarRepository.findAll();
        System.out.print(sviLekari);
        System.out.println();

        List<Lekar> lekariKlinike=new ArrayList<>();
        for (Lekar lekar: sviLekari){
            System.out.println(lekar.getSpecijalizacija());
            if (lekar.getKlinika().getId().equals(zahtev.getIdKlinike()) && lekar.getSpecijalizacija().equals(specijalizacija)){
                lekariKlinike.add(lekar);
            }
        }


        HashMap<String,Lekar> lekartermin=new HashMap<String, Lekar>();
        List<String> zauzetiTermini=new ArrayList<String>();
        int interval=15;

        for (Lekar l : lekariKlinike){
            List<String> found=(lekarRepository.zauzetiTermini(l.getId(),datum));
            List<String> found2=(lekarRepository.zauzetiTermini2(l.getId(),datum));
            found.addAll(found2);
            List<String> zauzetiterminipocetak=new ArrayList<>();
            List<String> zauzetiterminikraj=new ArrayList<>();
            for (String f : found){
                String[] foundparse=f.split(",");
                zauzetiterminipocetak.add(foundparse[0]);
                zauzetiterminikraj.add(foundparse[3]);
            }
                for (int i =0;i<24;i++){
                    for (int j=0;j<60;j+=interval){
                        String i2;
                        String j2;


                        i2=Integer.toString(i);
                        if(i2.length()==1)
                            i2='0'+i2;
                        j2=Integer.toString(j);
                        if(j2.length()==1)
                            j2='0'+j2;

                        String termin=i2+":"+j2;


                        boolean exists=false;
                        for (int m =0;m<zauzetiterminipocetak.size();m++){
                            if (dateFormat.parse(zauzetiterminipocetak.get(m)).compareTo(dateFormat.parse(termin))>0 || dateFormat.parse(termin).compareTo(dateFormat.parse(zauzetiterminikraj.get(m)))<0){
                                exists=true;
                                break;
                            }
                        }
                        if (!exists)
                            lekartermin.put(termin,l);
                    }
                }
                found.clear();
                zauzetiterminipocetak.clear();
                zauzetiterminikraj.clear();
            }


        System.out.println(lekariKlinike);

        System.out.println("Pronadjeni lekari su");
        System.out.println(lekartermin);

        return lekartermin;

    }}

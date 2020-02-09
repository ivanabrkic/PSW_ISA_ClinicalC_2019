package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.CenovnikRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.GodisnjiOdmorLekarRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.LekarRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.PacijentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class LekarService {

    @Autowired
    private LekarRepository lekarRepository;

    @Autowired
    private CenovnikRepository cenovnikRepository;

    @Autowired
    private PacijentRepository pacijentRepository;
    
    @Autowired
    private GodisnjiOdmorLekarRepository godOdmorRepo;

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
    
    public Lekar findByJbo(String jbo) {
    	return lekarRepository.findByJbo(jbo);
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
        p.setPrvoLogovanje(lekar.isPrvoLogovanje());
        lekarRepository.save(p);
        return p;
    }

    @Transactional
    public Message add(Lekar lekar){
        if (lekarRepository.findByJbo(lekar.getJbo()) == null && lekarRepository.findByEmail(lekar.getEmail()) == null){
            lekarRepository.save(lekar);
            return new Message("Uspešno ste registrovali lekara!");
        }

        return new Message("Ne možete registrovati lekara! JBO ili email već postoje!");
    }

    @Transactional
    public Message remove(Long id){

        if(!lekarRepository.findById(id).isPresent()) {
            return new Message("Lekar ne postoji!");
        }

        if(lekarRepository.imaOperacije(id).isEmpty() && lekarRepository.imaPreglede(id).isEmpty()){
            lekarRepository.deleteById(id);
            return new Message("Lekar uspešno obrisan!");
        }

        return new Message("Ne možete obrisati lekara koji ima zakazane operacije ili preglede!");
    }
    ////////////////////////// IVANA ZA ZAHTEVE ////////////////////////////////////////////////////////////////////
//    public List<Lekar> getSlobodniLekari(Zahtev zahtev){
//
//        String vremeZakazivanja = "";
//
//        int osamSati = 8 * 60;
//        int sesnaestSati = 16 * 60;
//        int dvanaestSati = 24 * 60;
//
//        int satPocetak = Integer.parseInt(zahtev.getPocetak().split(":")[0]) * 60;
//        int minutPocetak = Integer.parseInt(zahtev.getPocetak().split(":")[1]);
//
//        int satKraj = Integer.parseInt(zahtev.getKraj().split(":")[0]) * 60;
//        int minutKraj = Integer.parseInt(zahtev.getKraj().split(":")[1]);
//
//        int pocetak = satPocetak + minutPocetak;
//        int kraj  = satKraj + minutKraj;
//
//        if (pocetak == 0){
//            vremeZakazivanja = "Treca smena od 00:00 do 8:00";
//        }
//        else if (kraj == 0) {
//            vremeZakazivanja = "Druga smena od 16:00 do 00:00";
//        }
//        else if (pocetak >= osamSati && kraj <= sesnaestSati){
//            vremeZakazivanja = "Prva smena od 8:00 do 16:00";
//        }
//        else if (pocetak >= 0 && kraj <= osamSati){
//            vremeZakazivanja = "Treca smena od 00:00 do 8:00";
//        }
//        else if (pocetak >= sesnaestSati){
//            vremeZakazivanja = "Druga smena od 16:00 do 00:00";
//        }
//
//        String specijalizacija = cenovnikRepository.findById(zahtev.getIdStavke()).get().getSpecijalizacija();
//        List<Long> radeUToVreme = new ArrayList<>();
//
//        if (zahtev.getTipPosete().equals("Operacija")) {
//            radeUToVreme = lekarRepository.radnoVremeSpecOperacija(zahtev.getIdKlinike(), vremeZakazivanja, specijalizacija);
//        }else {
//            radeUToVreme = lekarRepository.radnoVremeSpecPregled(zahtev.getIdKlinike(), vremeZakazivanja, specijalizacija);
//        }
//
//        HashMap<Long, Long> slobodni = new HashMap<Long, Long>();
//        for (Long id : radeUToVreme){
//            if (lekarRepository.imaOperacije(id, zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()
//                && lekarRepository.imaPreglede(id, zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()){
//                slobodni.put(id, id);
//            }
//        }
//
//        List<Lekar> sviLekari = lekarRepository.findAll();
//        List<Lekar> odredjeni = new ArrayList<>();
//        for (Lekar l : sviLekari){
//            if(slobodni.containsKey(l.getId())){
//                odredjeni.add(l);
//            }
//        }
//
//        return odredjeni;
//    }
//
//    public boolean lekarSlobodan(Zahtev zahtev){
//        Lekar lekar = lekarRepository.findByJbo(zahtev.getPosiljalacJbo());
//        if (lekarRepository.imaOperacije(lekar.getId(), zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()
//                && lekarRepository.imaPreglede(lekar.getId(), zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()){
//            return true;
//        }
//        return false;
//    }

    //////////////////////////////// TESLA LEKARI TERMINI METODA ////////////////////////////////////////////
    public HashMap<String,Lekar> getSlobodniLekariTermini(lekariterminiDTO zahtev) throws ParseException {

        DateFormat dateFormat= new SimpleDateFormat("d.M.yyyy.");

       List< Cenovnik> cen = cenovnikRepository.findByNaziv(zahtev.getSpecijalizacija());
        String specijalizacija=cen.get(0).getSpecijalizacija();

        List<Lekar> sviLekari = lekarRepository.findAll();

        List<Lekar> lekariKlinike=new ArrayList<>();
        for (Lekar lekar: sviLekari){
         //   System.out.println(lekar.getSpecijalizacija());
            if (lekar.getKlinika().getId().equals(zahtev.getIdKlinike()) && lekar.getSpecijalizacija().equals(specijalizacija)){
            	
            	boolean naOdmoru=false;
            	List<GodisnjiOdmorLekar> godisnjiOdmor=godOdmorRepo.findByLekarId(lekar.getId());
            	System.out.println(godisnjiOdmor);
            	if(!godisnjiOdmor.isEmpty()) {
	            	for (GodisnjiOdmorLekar g:godisnjiOdmor) {
	            		System.out.println(java.sql.Date.valueOf(g.getDatumOd()));
	            		System.out.println(dateFormat.parse(zahtev.getDatum()));
	            		System.out.println(java.sql.Date.valueOf(g.getDatumOd()).compareTo(dateFormat.parse(zahtev.getDatum())));
	            		if(java.sql.Date.valueOf(g.getDatumOd()).compareTo(dateFormat.parse(zahtev.getDatum()))<=0 &&  java.sql.Date.valueOf(g.getDatumDo()).compareTo(dateFormat.parse(zahtev.getDatum()))>=0)
	            			naOdmoru=true;
	            	}
            	}
            	
            	if(!naOdmoru)
            		lekariKlinike.add(lekar);
            }
        }


        HashMap<String,Lekar> lekartermin=new HashMap<String, Lekar>();
        List<String> zauzetiTermini=new ArrayList<String>();
        int interval=15;

        for (Lekar l : lekariKlinike){
            List<String> found=(lekarRepository.zauzetiTermini(l.getId(),zahtev.getDatum()));
            List<String> found2=(lekarRepository.zauzetiTermini2(l.getId(),zahtev.getDatum()));
            found.addAll(found2);
            List<String> zauzetiterminipocetak=new ArrayList<>();
            List<String> zauzetiterminikraj=new ArrayList<>();
            System.out.println(zahtev.getDatum());
            
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

    }

    ///////////////////////////////// ZAKAZIVANJE LEKARA /////////////////////////////////////////////////////
    public List<TerminDTO> getSlobodniTerminiLekar(LekarPacijentTrajanjeDTO lekarPacijentTrajanjeDTO){

        Long idLekara = lekarPacijentTrajanjeDTO.getIdLekara();
        Long idPacijenta = lekarPacijentTrajanjeDTO.getIdPacijenta();
        int trajanje = lekarPacijentTrajanjeDTO.getTrajanje();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);

        start = start.plusDays(1);

        LocalDateTime end = start.plusMonths(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.YYYY.");

        List<TerminDTO> listaTermina = new ArrayList<>();

        for (LocalDateTime date = start; date.isBefore(end); date = date.plusMinutes(15)) {
            String datum = formatter.format(date.toLocalDate());
            String pocetak = date.toLocalTime().toString();
            String kraj = date.plusMinutes(trajanje).toLocalTime().toString();

            if (isLekarRadnoVreme(idLekara, pocetak, kraj) && lekarSlobodan(idLekara, datum, pocetak, kraj) && pacijentSlobodan(idPacijenta, datum, pocetak, kraj)){
                TerminDTO terminDTO = new TerminDTO();
                terminDTO.setDatum(datum);
                terminDTO.setPocetak(pocetak);
                terminDTO.setKraj(kraj);

                List<Sala> listaSlobodnihSala = new ArrayList<Sala>();
                terminDTO.setSale(listaSlobodnihSala);

                listaTermina.add(terminDTO);
            }

        }

        return listaTermina;
    }

    public boolean lekarSlobodan(Long lekarId, String datum, String pocetak, String kraj){
        if (lekarRepository.imaOperacije(lekarId, datum, pocetak, kraj).isEmpty()
                && lekarRepository.imaPreglede(lekarId, datum, pocetak, kraj).isEmpty()){
            return true;
        }
        return false;
    }

    public boolean isLekarRadnoVreme(Long idLekara, String pocetakString, String krajString){
        String vremeZakazivanja = "";

        int osamSati = 8 * 60;
        int sesnaestSati = 16 * 60;
        int dvanaestSati = 24 * 60;

        int satPocetak = Integer.parseInt(pocetakString.split(":")[0]) * 60;
        int minutPocetak = Integer.parseInt(pocetakString.split(":")[1]);

        int satKraj = Integer.parseInt(krajString.split(":")[0]) * 60;
        int minutKraj = Integer.parseInt(krajString.split(":")[1]);

        int pocetak = satPocetak + minutPocetak;
        int kraj = satKraj + minutKraj;

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

        Lekar lekar = lekarRepository.findById(idLekara).get();

        return lekar.getRadnoVreme().equals(vremeZakazivanja);
    }

    public boolean pacijentSlobodan(Long pacijentId, String datum, String pocetak, String kraj) {

        List<Long> operacije = pacijentRepository.ifPacijentSlobodanOperacije(pacijentId, datum, pocetak, kraj);
        List<Long> pregledi = pacijentRepository.ifPacijentSlobodanPregledi(pacijentId, datum, pocetak, kraj);

        if (operacije.isEmpty() && pregledi.isEmpty()){
            return true;
        }

        return false;
    }

    public Lekar findLekarByJbo(String jbo) {
        return lekarRepository.findByJbo(jbo);
    }

    public Lekar findBySpecijalizacija(String spec) {
        return lekarRepository.findBySpecijalizacija(spec);
    }

    public List<Cenovnik> findBySpecAndKlinika(Lekar lekar) {
        return cenovnikRepository.findBySpecAndKlinika(lekar.getKlinika().getId(), lekar.getSpecijalizacija());
    }
}

package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.OperacijaDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefInfoDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import javax.transaction.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class KlinikaService {

    @Autowired
    private PacijentRepository pacijentRepository;

    @Autowired
    private ZahtevRepository zahtevRepository;

    @Autowired
    private OperacijaRepository operacijaRepository;

    @Autowired
    private PregledRepository pregledRepository;

    @Autowired
    private KlinikaRepository klinikaRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private MedicinskaSestraRepository medicinskaSestraRepository;

    @Autowired
    private CenovnikRepository cenovnikRepository;

    @Autowired
    private LekarRepository lekarRepository;

    public Klinika findByNaziv(String naziv) { return klinikaRepository.findByNaziv(naziv); }

    public Klinika findById(int id) { return klinikaRepository.findById(id); }

    public List<Klinika> findAll() { return klinikaRepository.findAll(); }


    @Transactional
    public Klinika add(Klinika klinika){
        return klinikaRepository.save(klinika);
    }

    @Transactional
    public Klinika update(Klinika klinika) {
        Klinika p = klinikaRepository.findById(klinika.getId()).get();
        if (p == null) {
            return null;
        }
        p.setAdresa(klinika.getAdresa());
        p.setDrzava(klinika.getDrzava());
        p.setEmail(klinika.getEmail());
        p.setGrad(klinika.getGrad());
        p.setNaziv(klinika.getNaziv());
        p.setKontaktTelefon(klinika.getKontaktTelefon());
        p.setOcena(klinika.getOcena());
        klinikaRepository.save(p);
        return p;
    }

    public List<MedicinskaSestra> findMedSestre(Long id){
        Klinika k = klinikaRepository.findById(id).get();

        medicinskaSestraRepository.findByKlinika(k);

        if(medicinskaSestraRepository.findByKlinika(k) == null) {
            return null;
        }
        return medicinskaSestraRepository.findByKlinika(k);
    }

    public List<Lekar> findLekari(Long id){
        Klinika k = klinikaRepository.findById(id).get();

        lekarRepository.findByKlinika(k);

        if(lekarRepository.findByKlinika(k) == null) {
            return null;
        }
        return lekarRepository.findByKlinika(k);
    }

    public List<OperacijaDTO> getOperacije(Long id) {
        List<OperacijaDTO> operacijeDTO = operacijaRepository.findBySalaId(id);

        for (OperacijaDTO opDTO:operacijeDTO
             ) {
            List<Lekar> lekari = findLekariOperacije(opDTO.getDatum(), opDTO.getPocetak(), opDTO.getKraj(), id);
            List<String> jboLekara = new ArrayList<String>();
            for (Lekar lekar: lekari
                 ) {
                jboLekara.add(lekar.getJbo());
            }
            opDTO.setJboLekara(jboLekara);
        }

        return operacijeDTO;
    }

    public List<PregledDTO> getPregledi(Long id) {
        List<PregledDTO> predef = pregledRepository.findBySalaIdPredef(id);
        List<PregledDTO> obicni = pregledRepository.findBySalaId(id);

        predef.addAll(obicni);

        return predef;
    }
    
    public List<predefInfoDTO> getPreglediPredef(Long id,String s,Long pacId) throws ParseException {
    	
    	
        List<predefInfoDTO> predef = pregledRepository.findByKlinikaIdPredef(id,s);
        List<Pregled> pacZauzet=pregledRepository.findByPacijentId(pacId);
        
        List<predefInfoDTO> odgovarajuci=new ArrayList<>();
        
        SimpleDateFormat sdf = new SimpleDateFormat("d.m.yyyy.");
        
        for (predefInfoDTO p:predef) {
        	boolean found=false;
        	for (Pregled z:pacZauzet) {
        		if(sdf.parse(p.getDatum()).compareTo(sdf.parse(z.getDatum()))==0) {//ako se datumi poklapaju
        			//(StartA <= EndB) and (EndA >= StartB) proveri poklapanje vremena
        			if( LocalTime.parse(p.getPocetak(), DateTimeFormatter.ofPattern("HH:mm")).compareTo(LocalTime.parse(z.getKraj(), DateTimeFormatter.ofPattern("HH:mm")))<=0)	{
        				if(LocalTime.parse(p.getKraj(), DateTimeFormatter.ofPattern("HH:mm")).compareTo(LocalTime.parse(z.getPocetak(), DateTimeFormatter.ofPattern("HH:mm")))>=0) {
        					found=true;
        				}
        				
        			}
        		}
        	}
        	if (!found){
        		odgovarajuci.add(p);
        	}
        }

        return odgovarajuci;
    }

    public List<Lekar> findLekariOperacije(String datum, String pocetak, String kraj, Long id){
        return operacijaRepository.findLekareOperacije(datum, pocetak, kraj, id);
    }

    public List<Zahtev> getZahtevi(Long idKlinike) {
        return zahtevRepository.findByIdKlinike(idKlinike);
    }

    public Boolean removeZahtev(Long idZahteva) {
        Long idKlinike = zahtevRepository.findById(idZahteva).get().getIdKlinike();
        zahtevRepository.deleteById(idZahteva);
        return true;
    }

    public String zakaziPregled(PregledDTO pregled) {

        Pregled p = new Pregled();

        p.setCenovnik(cenovnikRepository.findById(pregled.getTipId()).get());

        p.setDatum(pregled.getDatum());
        p.setPocetak(pregled.getPocetak());
        p.setKraj(pregled.getKraj());

        p.setPacijent(pacijentRepository.findByJbo(pregled.getJboPacijenta()));
        p.setLekar(lekarRepository.findByJbo(pregled.getJboLekara()));
        p.setSala(salaRepository.findById(pregled.getSalaId()).get());

        pregledRepository.saveAndFlush(p);

        return "Uspesno zakazan pregled!";
    }

    public String zakaziOperaciju(OperacijaDTO operacija) {

        for (String lekarJbo:operacija.getJboLekara()) {
            Operacija o = new Operacija();
            o.setCenovnik(cenovnikRepository.findById(operacija.getTipId()).get());

            o.setDatum(operacija.getDatum());
            o.setPocetak(operacija.getPocetak());
            o.setKraj(operacija.getKraj());

            Pacijent p = pacijentRepository.findByJbo(operacija.getJboPacijenta());
            o.setPacijent(pacijentRepository.findById(p.getId()).get());

            Lekar l = lekarRepository.findByJbo(lekarJbo);
            o.setLekari(lekarRepository.findById(l.getId()).get());

            o.setSala(salaRepository.findById(operacija.getSalaId()).get());

            operacijaRepository.saveAndFlush(o);

        }

        return "Uspesno zakazana operacija!";
    }

    public List<Sala> findSaleSlobodneOd(Zahtev zahtev) {
        if (pacijentSlobodan(zahtev) && !getSlobodniLekari(zahtev).isEmpty()) {
            List<Long> saleNeO = operacijaRepository.findByKlinikaIdAndVreme(zahtev.getIdKlinike(), zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj());
            List<Long> saleNeP = pregledRepository.findByKlinikaIdAndVreme(zahtev.getIdKlinike(), zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj());

            HashMap<Long, Long> uniqueIdSale = new HashMap<Long, Long>();

            for (Long s : saleNeO) {
                uniqueIdSale.put(s, s);
            }

            for (Long s : saleNeP) {
                uniqueIdSale.put(s, s);
            }

            List<Sala> sve = salaRepository.findByKlinikaId(zahtev.getIdKlinike());
            List<Sala> prolaze = new ArrayList<Sala>();
            for (Sala s : sve) {
                if (!uniqueIdSale.containsKey(s.getId())) {
                    prolaze.add(s);
                }
            }

            return prolaze;
        }
        return new ArrayList<Sala>();
    }

    public boolean pacijentSlobodan(Zahtev zahtev) {
        Pacijent p = pacijentRepository.findByJbo(zahtev.getJboPacijenta());

        List<Long> operacije = pacijentRepository.ifPacijentSlobodanOperacije(p.getId(), zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj());
        List<Long> pregledi = pacijentRepository.ifPacijentSlobodanPregledi(p.getId(), zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj());

        if (operacije.isEmpty() && pregledi.isEmpty()){
            return true;
        }

        return false;
    }

    public List<Long> getSlobodniLekari(Zahtev zahtev){
        String vremeZakazivanja = "";

        if(zahtev.getTipPosete() == "Operacija") {
            if (lekarSlobodan(zahtev)) {

                int osamSati = 8 * 60;
                int sesnaestSati = 16 * 60;
                int dvanaestSati = 24 * 60;

                int satPocetak = Integer.parseInt(zahtev.getPocetak().split(":")[0]) * 60;
                int minutPocetak = Integer.parseInt(zahtev.getPocetak().split(":")[1]);

                int satKraj = Integer.parseInt(zahtev.getKraj().split(":")[0]) * 60;
                int minutKraj = Integer.parseInt(zahtev.getKraj().split(":")[1]);

                int pocetak = satPocetak + minutPocetak;
                int kraj = satKraj + minutKraj;

                if (pocetak == 0) {
                    vremeZakazivanja = "Treca smena od 00:00 do 8:00";
                } else if (kraj == 0) {
                    vremeZakazivanja = "Druga smena od 16:00 do 00:00";
                } else if (pocetak >= osamSati && kraj <= sesnaestSati) {
                    vremeZakazivanja = "Prva smena od 8:00 do 16:00";
                } else if (pocetak >= 0 && kraj <= osamSati) {
                    vremeZakazivanja = "Treca smena od 00:00 do 8:00";
                } else if (pocetak >= sesnaestSati) {
                    vremeZakazivanja = "Druga smena od 16:00 do 00:00";
                }

                String specijalizacija = cenovnikRepository.findById(zahtev.getIdStavke()).get().getSpecijalizacija();

                List<Long> radeUToVreme = lekarRepository.daLiJeRadnoVremeOperacija(zahtev.getIdKlinike(), vremeZakazivanja, specijalizacija);

                List<Long> slobodni = new ArrayList<>();
                for (Long id : radeUToVreme) {
                    if (lekarRepository.imaOperacije(id, zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()
                            && lekarRepository.imaPreglede(id, zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()) {
                        slobodni.add(id);
                    }
                }
                return slobodni;
            }
            else {
                return new ArrayList<Long>();
            }
        }
        int osamSati = 8 * 60;
        int sesnaestSati = 16 * 60;
        int dvanaestSati = 24 * 60;

        int satPocetak = Integer.parseInt(zahtev.getPocetak().split(":")[0]) * 60;
        int minutPocetak = Integer.parseInt(zahtev.getPocetak().split(":")[1]);

        int satKraj = Integer.parseInt(zahtev.getKraj().split(":")[0]) * 60;
        int minutKraj = Integer.parseInt(zahtev.getKraj().split(":")[1]);

        int pocetak = satPocetak + minutPocetak;
        int kraj = satKraj + minutKraj;

        if (pocetak == 0) {
            vremeZakazivanja = "Treca smena od 00:00 do 8:00";
        } else if (kraj == 0) {
            vremeZakazivanja = "Druga smena od 16:00 do 00:00";
        } else if (pocetak >= osamSati && kraj <= sesnaestSati) {
            vremeZakazivanja = "Prva smena od 8:00 do 16:00";
        } else if (pocetak >= 0 && kraj <= osamSati) {
            vremeZakazivanja = "Treca smena od 00:00 do 8:00";
        } else if (pocetak >= sesnaestSati) {
            vremeZakazivanja = "Druga smena od 16:00 do 00:00";
        }

        String specijalizacija = cenovnikRepository.findById(zahtev.getIdStavke()).get().getSpecijalizacija();

        List<Long> radeUToVreme = lekarRepository.daLiJeRadnoVremePregled(zahtev.getIdKlinike(), vremeZakazivanja, specijalizacija);

        List<Long> slobodni = new ArrayList<>();
        for (Long id : radeUToVreme) {
            if (lekarRepository.imaOperacije(id, zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()
                    && lekarRepository.imaPreglede(id, zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()) {
                slobodni.add(id);
            }
        }
        return slobodni;
    }

    public boolean lekarSlobodan(Zahtev zahtev){
        Lekar lekar = lekarRepository.findByJbo(zahtev.getPosiljalacJbo());
        if (lekarRepository.imaOperacije(lekar.getId(), zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()
                && lekarRepository.imaPreglede(lekar.getId(), zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()){
            return true;
        }
        return false;
    }

}

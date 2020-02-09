package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    @Autowired
    private MailSenderService mailSenderService;

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
    
 public List<predefInfoDTO> getPreglediPredefKlinPac(Long id,Long pacId) throws ParseException {
    	
    	
        List<predefInfoDTO> predef = pregledRepository.findByKlinikaIdPredef2(id);
        List<Pregled> pacZauzet=pregledRepository.findByPacijentId(pacId);
        List<predefInfoDTO> predefUBuducnosti=new ArrayList();
        
        System.out.print(predef);
        
        List<predefInfoDTO> odgovarajuci=new ArrayList<>();
        
        SimpleDateFormat sdf = new SimpleDateFormat("d.M.yyyy.");
        for(predefInfoDTO pred:predef) {
        	if(sdf.parse(pred.getDatum()).compareTo(new Date())>0) {
        		predefUBuducnosti.add(pred);
        	}
        }
        
        //proveera poklapanja sa pacijentovim terminima
        for (predefInfoDTO p:predefUBuducnosti) {
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

        return odgovarajuci; //termini koji se ne poklapaju s pacijentovim
    }
    ////////////////////// ONA KOJA MENI TREBA  ////////////////////////////////////////////
    public List<predefInfoDTO> getPreglediPredef(Long id) {
        List<predefInfoDTO> predef = pregledRepository.findByKlinikaIdPredef(id);

        return predef;
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
    public List<SalaDTO> findDrugiTermin(Zahtev zahtev) {

        int satPocetak = Integer.parseInt(zahtev.getPocetak().split(":")[0]) * 60;
        int satKraj = Integer.parseInt(zahtev.getKraj().split(":")[0]) * 60;
        int minutKraj = Integer.parseInt(zahtev.getKraj().split(":")[1]);
        int minutPocetak = Integer.parseInt(zahtev.getPocetak().split(":")[1]);

        int trajanje = (satKraj + minutKraj) - (satPocetak + minutPocetak);

        int godina = Integer.parseInt(zahtev.getDatum().split("\\.")[2]);
        int mesec = Integer.parseInt(zahtev.getDatum().split("\\.")[1]);
        int dan = Integer.parseInt(zahtev.getDatum().split("\\.")[0]);

        LocalDateTime start = LocalDateTime.of(godina, mesec, dan, 0, 0, 0);

        LocalDateTime end = start.plusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.YYYY.");

        HashMap<Long, SalaDTO> uniqueSale = new HashMap<>();

        List<Sala> sveSale = salaRepository.findByKlinikaId(zahtev.getIdKlinike());

        while (sveSale.size() != uniqueSale.size()) {
            for (LocalDateTime date = start; date.isBefore(end); date = date.plusMinutes(15)) {

                if (date.toLocalDate() == date.plusMinutes(trajanje).toLocalDate()) {
                    String datum = formatter.format(date.toLocalDate());
                    String pocetak = date.toLocalTime().toString();
                    String kraj = date.plusMinutes(trajanje).toLocalTime().toString();

                    zahtev.setDatum(datum);
                    zahtev.setPocetak(pocetak);
                    zahtev.setKraj(kraj);

                    if (pacijentSlobodan(zahtev) && !getSlobodniLekari(zahtev).isEmpty()) {

                        for (Sala s : sveSale) {
                          if (salaRepository.checkIfSalaZauzeta(s.getId(), datum, pocetak, kraj).isEmpty()) {

                                if (!uniqueSale.containsKey(s.getId())) {
                                    SalaDTO salaDTO = new SalaDTO();

                                    salaDTO.setDatumSlobodna(datum);
                                    salaDTO.setPocetakSlobodna(pocetak);
                                    salaDTO.setKrajSlobodna(kraj);

                                    salaDTO.setNaziv(s.getNaziv());
                                    salaDTO.setBroj(s.getBroj());
                                    salaDTO.setKlinika(s.getKlinika());
                                    salaDTO.setId(s.getId());
                                    uniqueSale.put(s.getId(), salaDTO);
                                }
                            }
                        }
                    }

                }

            }
        }

        List<SalaDTO> listaSalaDTO = new ArrayList<>();

        for (SalaDTO sala : uniqueSale.values()){
            listaSalaDTO.add(sala);
        }

        return listaSalaDTO;
    }

    public List<SalaDTO> findSlobodneSale(Zahtev zahtev) {

        List<Sala> sveSale = salaRepository.findByKlinikaId(zahtev.getIdKlinike());

        HashMap<Long, SalaDTO> uniqueSale = new HashMap<>();
        if (pacijentSlobodan(zahtev) && lekarSlobodan(zahtev)){
            for (Sala s : sveSale) {
                if (salaRepository.checkIfSalaZauzeta(s.getId(), zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()) {

                    if(!uniqueSale.containsKey(s.getId())){
                        SalaDTO salaDTO = new SalaDTO();

                        salaDTO.setDatumSlobodna(zahtev.getDatum());
                        salaDTO.setPocetakSlobodna(zahtev.getPocetak());
                        salaDTO.setKrajSlobodna(zahtev.getKraj());

                        salaDTO.setNaziv(s.getNaziv());
                        salaDTO.setBroj(s.getBroj());
                        salaDTO.setKlinika(s.getKlinika());
                        salaDTO.setId(s.getId());
                        uniqueSale.put(s.getId(), salaDTO);
                    }
                }
            }
        }
        List<SalaDTO> listaSalaDTO = new ArrayList<>();

        for (SalaDTO sala : uniqueSale.values()){
            listaSalaDTO.add(sala);
        }

        return listaSalaDTO;
    }

    public boolean pacijentSlobodan(Zahtev zahtev) {

        List<Long> operacije = pacijentRepository.ifPacijentSlobodanOperacije(pacijentRepository.findByJbo(zahtev.getJboPacijenta()).getId(), zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj());
        List<Long> pregledi = pacijentRepository.ifPacijentSlobodanPregledi(pacijentRepository.findByJbo(zahtev.getJboPacijenta()).getId(), zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj());

        if (operacije.isEmpty() && pregledi.isEmpty()){
            return true;
        }

        return false;
    }

    public List<Long> getSlobodniLekari(Zahtev zahtev){
        String vremeZakazivanja = getVremeZakazivanja(zahtev);

        if(zahtev.getTipPosete().equals("Operacija")) {

            if (lekarSlobodan(zahtev)) {

                String specijalizacija = cenovnikRepository.findById(zahtev.getIdStavke()).get().getSpecijalizacija();

                List<Long> radeUToVreme = lekarRepository.radnoVremeSpecOperacija(zahtev.getIdKlinike(), vremeZakazivanja, specijalizacija);

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

        // AKO JE PREGLED LEKAR KOJI JE POSLAO ZAHTEV NIJE OBAVEZAN I DA OBAVLJA PREGLED
        String specijalizacija = cenovnikRepository.findById(zahtev.getIdStavke()).get().getSpecijalizacija();

        List<Long> radeUToVreme = lekarRepository.radnoVremeSpecPregled(zahtev.getIdKlinike(), vremeZakazivanja, specijalizacija);

        List<Long> slobodni = new ArrayList<>();
        for (Long id : radeUToVreme) {
            if (lekarRepository.imaOperacije(id, zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()
                    && lekarRepository.imaPreglede(id, zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()) {
                slobodni.add(id);
            }
        }
        return slobodni;
    }

    public List<Lekar> getSlobodniLekariCeoLekar(Zahtev zahtev){
        String vremeZakazivanja = getVremeZakazivanja(zahtev);

        if(zahtev.getTipPosete().equals("Operacija")) {

            if (lekarSlobodan(zahtev)) {

                String specijalizacija = cenovnikRepository.findById(zahtev.getIdStavke()).get().getSpecijalizacija();
                List<Long> radeUToVreme = lekarRepository.radnoVremeSpecOperacija(zahtev.getIdKlinike(), vremeZakazivanja, specijalizacija);

                List<Lekar> slobodni = new ArrayList<>();
                for (Long id : radeUToVreme) {
                    if (lekarRepository.imaOperacije(id, zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()
                            && lekarRepository.imaPreglede(id, zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()) {
                        slobodni.add(lekarRepository.findById(id).get());
                    }
                }
                return slobodni;
            }
            else {
                return new ArrayList<Lekar>();
            }
        }

        // AKO JE PREGLED LEKAR KOJI JE POSLAO ZAHTEV NIJE OBAVEZAN I DA OBAVLJA PREGLED
        String specijalizacija = cenovnikRepository.findById(zahtev.getIdStavke()).get().getSpecijalizacija();
        List<Long> radeUToVreme = lekarRepository.radnoVremeSpecPregled(zahtev.getIdKlinike(), vremeZakazivanja, specijalizacija);

        List<Lekar> slobodni = new ArrayList<>();
        for (Long id : radeUToVreme) {
            if (lekarRepository.imaOperacije(id, zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()
                    && lekarRepository.imaPreglede(id, zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()) {
                slobodni.add(lekarRepository.findById(id).get());
            }
        }
        return slobodni;
    }

    public boolean lekarSlobodan(Zahtev zahtev){
        Lekar lekar = lekarRepository.findByJbo(zahtev.getJboLekara());
        if (lekarRepository.imaOperacije(lekar.getId(), zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()
                && lekarRepository.imaPreglede(lekar.getId(), zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()){
            return true;
        }
        return false;
    }

    public String getVremeZakazivanja(Zahtev zahtev){

        String vremeZakazivanja = "";

        int osamSati = 8 * 60;
        int sesnaestSati = 16 * 60;

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

        return vremeZakazivanja;
    }
    ///////////////////////// ALGORITAM NE DIRAJ!!! ///////////////////////////////////////////
    public void algoritamOperacijaDrugiTermin(Zahtev zahtev){

        List<SalaDTO> listaDrugiTermin = findDrugiTermin(zahtev);
        boolean nasaoSaDva = false;

        for(SalaDTO sala : listaDrugiTermin){
            /// NOVA VREDNOST TERMINA!
            zahtev.setPocetak(sala.getPocetakSlobodna());
            zahtev.setKraj(sala.getKrajSlobodna());
            zahtev.setDatum(sala.getDatumSlobodna());

            List<Long> slobodniDrugiTermin = getSlobodniLekari(zahtev);

            if (slobodniDrugiTermin.size() >= 2){

                nasaoSaDva = true;

                for(Long lekarId : slobodniDrugiTermin) {
                    ///// TRAZIM LEKARA KOJI NIJE LEKAR KOJI JE GLAVNI NA OPERACIJI
                    if (lekarId != lekarRepository.findByJbo(zahtev.getJboLekara()).getId()) {
                        Operacija operacija = new Operacija();
                        operacija.setDatum(zahtev.getDatum());
                        operacija.setPocetak(zahtev.getPocetak());
                        operacija.setKraj(zahtev.getKraj());
                        operacija.setLekari(lekarRepository.findById(lekarId).get());
                        operacija.setSala(salaRepository.findById(sala.getId()).get());
                        operacija.setPacijent(pacijentRepository.findByJbo(zahtev.getJboPacijenta()));
                        operacija.setCenovnik(cenovnikRepository.findById(zahtev.getIdStavke()).get());
                        operacija.setStatus("Zakazan");

                        operacijaRepository.save(operacija);

                        break;
                    }
                }
                /// GLAVNI LEKAR
                Operacija operacija = new Operacija();
                operacija.setDatum(zahtev.getDatum());
                operacija.setPocetak(zahtev.getPocetak());
                operacija.setKraj(zahtev.getKraj());
                operacija.setLekari(lekarRepository.findByJbo(zahtev.getJboLekara()));
                operacija.setSala(salaRepository.findById(sala.getId()).get());
                operacija.setPacijent(pacijentRepository.findByJbo(zahtev.getJboPacijenta()));
                operacija.setCenovnik(cenovnikRepository.findById(zahtev.getIdStavke()).get());
                operacija.setStatus("Zakazan");

                operacijaRepository.save(operacija);
                posaljiEmailOperacija(pacijentRepository.findByJbo(zahtev.getJboPacijenta()), operacija);

            }

        }
        /// U SLUCAJU DA NIJE NASAO NIJEDNU SA DVA SLOBODNA LEKARA
        /// POSTO SVAKI TERMIN IMA UVEK GLAVNOG LEKARA MOGU DA DODELIM BILO KOJU SALU
        /// NAJBOLJE PRVU JER JE "STO PRE"

        if (!nasaoSaDva) {
            Operacija operacija = new Operacija();
            operacija.setDatum(listaDrugiTermin.get(0).getDatumSlobodna());
            operacija.setPocetak(listaDrugiTermin.get(0).getPocetakSlobodna());
            operacija.setKraj(listaDrugiTermin.get(0).getKrajSlobodna());
            operacija.setLekari(lekarRepository.findByJbo(zahtev.getJboLekara()));
            operacija.setSala(salaRepository.findById(listaDrugiTermin.get(0).getId()).get());
            operacija.setPacijent(pacijentRepository.findByJbo(zahtev.getJboPacijenta()));
            operacija.setCenovnik(cenovnikRepository.findById(zahtev.getIdStavke()).get());
            operacija.setStatus("Zakazan");

            operacijaRepository.save(operacija);
            posaljiEmailOperacija(pacijentRepository.findByJbo(zahtev.getJboPacijenta()), operacija);

        }
    }

    public void algoritamPregledDrugiTermin(Zahtev zahtev, List<SalaDTO> listaDrugiTermin){

        boolean nasaoLekara = false;

        for (SalaDTO salaDTO : listaDrugiTermin) {

            zahtev.setDatum(salaDTO.getDatumSlobodna());
            zahtev.setPocetak(salaDTO.getPocetakSlobodna());
            zahtev.setKraj(salaDTO.getKrajSlobodna());

            List<Long> slobodniLekari = getSlobodniLekari(zahtev);

            for (Long lekarId : slobodniLekari) {
                if (lekarId == lekarRepository.findByJbo(zahtev.getJboLekara()).getId()) {
                    Pregled pregled = new Pregled();
                    pregled.setDatum(zahtev.getDatum());
                    pregled.setPocetak(zahtev.getPocetak());
                    pregled.setKraj(zahtev.getKraj());
                    pregled.setSala(salaRepository.findById(salaDTO.getId()).get());
                    pregled.setLekar(lekarRepository.findById(lekarId).get());
                    pregled.setPacijent(pacijentRepository.findByJbo(zahtev.getJboPacijenta()));
                    pregled.setCenovnik(cenovnikRepository.findById(zahtev.getIdStavke()).get());
                    pregled.setPopust(0);
                    pregled.setStatus("Zakazan");

                    nasaoLekara = true;
                    pregledRepository.save(pregled);
                    posaljiEmailPregled(pacijentRepository.findByJbo(zahtev.getJboPacijenta()), lekarRepository.findById(lekarId).get(), pregled);

                    break;
                }
            }
        }
        //// AKO U PRVIM SLOBODNIM TERMINIMA BAS NIGDE NE POSTOJI ZELJENI LEKAR ZAMENI
        if (!nasaoLekara){
            zahtev.setDatum(listaDrugiTermin.get(0).getDatumSlobodna());
            zahtev.setPocetak(listaDrugiTermin.get(0).getPocetakSlobodna());
            zahtev.setKraj(listaDrugiTermin.get(0).getKrajSlobodna());

            List<Long> slobodniLekari = getSlobodniLekari(zahtev);

            Pregled pregled = new Pregled();
            pregled.setDatum(zahtev.getDatum());
            pregled.setPocetak(zahtev.getPocetak());
            pregled.setKraj(zahtev.getKraj());
            pregled.setSala(salaRepository.findById(listaDrugiTermin.get(0).getId()).get());
            pregled.setLekar(lekarRepository.findById(slobodniLekari.get(0)).get());
            pregled.setPacijent(pacijentRepository.findByJbo(zahtev.getJboPacijenta()));
            pregled.setCenovnik(cenovnikRepository.findById(zahtev.getIdStavke()).get());
            pregled.setPopust(0);
            pregled.setStatus("Zakazan");

            pregledRepository.save(pregled);
            posaljiEmailPregled(pacijentRepository.findByJbo(zahtev.getJboPacijenta()), lekarRepository.findById(slobodniLekari.get(0)).get(), pregled);
        }
    }

    public void algoritamPregledSlobodanTermin(Zahtev zahtev, SalaDTO salaDTO){

        Pregled pregled = new Pregled();
        pregled.setDatum(zahtev.getDatum());
        pregled.setPocetak(zahtev.getPocetak());
        pregled.setKraj(zahtev.getKraj());
        pregled.setLekar(lekarRepository.findByJbo(zahtev.getJboLekara()));
        pregled.setPacijent(pacijentRepository.findByJbo(zahtev.getJboPacijenta()));
        pregled.setCenovnik(cenovnikRepository.findById(zahtev.getIdStavke()).get());
        pregled.setSala(salaRepository.findById(salaDTO.getId()).get());
        pregled.setPopust(0);
        pregled.setStatus("Zakazan");

        pregledRepository.save(pregled);
        posaljiEmailPregled(pacijentRepository.findByJbo(zahtev.getJboPacijenta()), lekarRepository.findByJbo(zahtev.getJboLekara()), pregled);
    }

    public void algoritamOperacijaSlobodanTermin(Zahtev zahtev, List<Long> slobodniLekari, SalaDTO salaDTO) throws InterruptedException {

        for(Long lekarId : slobodniLekari) {
            if (lekarId != lekarRepository.findByJbo(zahtev.getJboLekara()).getId()) {
                Operacija operacija = new Operacija();
                operacija.setDatum(zahtev.getDatum());
                operacija.setPocetak(zahtev.getPocetak());
                operacija.setKraj(zahtev.getKraj());
                operacija.setLekari(lekarRepository.findById(lekarId).get());
                operacija.setSala(salaRepository.findById(salaDTO.getId()).get());
                operacija.setPacijent(pacijentRepository.findByJbo(zahtev.getJboPacijenta()));
                operacija.setCenovnik(cenovnikRepository.findById(zahtev.getIdStavke()).get());
                operacija.setStatus("Zakazan");

                operacijaRepository.save(operacija);

                break;
            }
        }
        Operacija operacija = new Operacija();
        operacija.setDatum(zahtev.getDatum());
        operacija.setPocetak(zahtev.getPocetak());
        operacija.setKraj(zahtev.getKraj());
        operacija.setLekari(lekarRepository.findByJbo(zahtev.getJboLekara()));
        operacija.setSala(salaRepository.findById(salaDTO.getId()).get());
        operacija.setPacijent(pacijentRepository.findByJbo(zahtev.getJboPacijenta()));
        operacija.setCenovnik(cenovnikRepository.findById(zahtev.getIdStavke()).get());
        operacija.setStatus("Zakazan");

        operacijaRepository.save(operacija);
        posaljiEmailOperacija(pacijentRepository.findByJbo(zahtev.getJboPacijenta()), operacija);
    }

    public void posaljiEmailPregled (Pacijent pacijent, Lekar lekar, Pregled pregled){
        String emailPacijenta = pacijent.getEmail();
        String subject = "Obaveštavamo Vas o uspešnom zakazivanju pregleda!";
        String text = "Poštovani " + pacijent.getIme() + " " + pacijent.getPrezime() + " uspešno ste zakazali pregled "
                + pregled.getCenovnik().getNaziv() + " za datum " + pregled.getDatum() + " od "
                + pregled.getPocetak() + " do " + pregled.getKraj() + " u sali " + pregled.getSala().getNaziv() + " " + pregled.getSala().getBroj()
                + " kod lekara " + lekar.getJbo() + " za pacijenta " + pacijent.getJbo() + "!";

        mailSenderService.sendSimpleMessage(emailPacijenta, subject, text);

        String emailLekara = lekar.getEmail();
        text = "Poštovani " + lekar.getIme() + " " + lekar.getPrezime() + " uspešno ste zakazali pregled "
                + pregled.getCenovnik().getNaziv() + " za datum " + pregled.getDatum() + " od "
                + pregled.getPocetak() + " do " + pregled.getKraj() + " u sali " + pregled.getSala().getNaziv() + " " + pregled.getSala().getBroj()
                + " kod lekara " + lekar.getJbo() + " za pacijenta " + pacijent.getJbo() + "!";

        mailSenderService.sendSimpleMessage(emailLekara, subject, text);
    }

    public void posaljiEmailOperacija (Pacijent pacijent, Operacija operacija){

        List<Lekar> lekariOperacije = operacijaRepository.findLekareOperacije(operacija.getDatum(), operacija.getPocetak(), operacija.getKraj(), operacija.getSala().getId());

        String lekari = "";
        for(int i = 0; i < lekariOperacije.size(); i++){
            if (i == (lekariOperacije.size() - 1)){
                lekari += lekariOperacije.get(i).getJbo();
            }
            else{
                lekari += lekariOperacije.get(i).getJbo() + ", ";
            }
        }

        String emailPacijenta = pacijent.getEmail();
        String subject = "Obaveštavamo Vas o uspešnom zakazivanju operacije!";
        String text = "Poštovani " + pacijent.getIme() + " " + pacijent.getPrezime() + " uspešno ste zakazali pregled "
                + operacija.getCenovnik().getNaziv() + " za datum " + operacija.getDatum() + " od "
                + operacija.getPocetak() + " do " + operacija.getKraj() + " u sali " + operacija.getSala().getNaziv() + " " + operacija.getSala().getBroj()
                + " kod lekara " + lekari + " za pacijenta " + pacijent.getJbo() + "!";

        mailSenderService.sendSimpleMessage(emailPacijenta, subject, text);

        for(Lekar l : lekariOperacije) {
            String emailLekara = l.getEmail();
            text = "Poštovani " + l.getIme() + " " + l.getPrezime() + " uspešno ste zakazali pregled "
                    + operacija.getCenovnik().getNaziv() + " za datum " + operacija.getDatum() + " od "
                    + operacija.getPocetak() + " do " + operacija.getKraj() + " u sali " + operacija.getSala().getNaziv() + " " + operacija.getSala().getBroj()
                    + " kod lekara " + lekari + " za pacijenta " + pacijent.getJbo() + "!";

            mailSenderService.sendSimpleMessage(emailLekara, subject, text);
        }
    }

    @Transactional
    public void pokreniAlgoritam() throws InterruptedException {
        // DOBAVI SVE ZAHTEVE
        List<Zahtev> listaZahteva = zahtevRepository.findAll();

        boolean obrisiZahtev = false;

        for (Zahtev zahtev : listaZahteva) {
            //////// DA LI IMA SLOBODNIH ZA SVE KRITERIJUME ZAHTEVA ///////////////////////////
            List<SalaDTO> listaSlobodnih = findSlobodneSale(zahtev);

            if (!listaSlobodnih.isEmpty()){

                SalaDTO salaDTO =  listaSlobodnih.get(0);

                if (zahtev.getTipPosete().equals("Pregled")){
                    algoritamPregledSlobodanTermin(zahtev, salaDTO);
                    obrisiZahtev = true;
                }
                else{
                    /////// ADMINU OSTAVLJAM DA IZABERE JEDNOG LEKARA AKO HOCE ALI PRILIKOM
                    /////// AUTOMATSKOG DODELJIVANJA BOLJE JE DA BUDU BAR DVA LEKARA PRISUTNA!
                    /// DA LI IMA SLOBODNIH LEKARA ///////////////////////////////////////////
                    List<Long> slobodniLekari = getSlobodniLekari(zahtev);
                    ///////////////////////////////////////////////////////////////////////////
                    if (slobodniLekari.size() >= 2) {
                        algoritamOperacijaSlobodanTermin(zahtev, slobodniLekari, salaDTO);
                        obrisiZahtev = true;
                    }
                    else{ ////////////// AKO NEMA BAR 2 SLOBODNA NADJI DRUGI TERMIN ///////////
                        //// OVDE NE TREBA PROVERA AKO NE NADJE JER CE SE VRATITI SVAKAKO NA TERMIN
                        //// IZ ZAHTEVA NA KRAJU KRAJEVA
                        algoritamOperacijaDrugiTermin(zahtev);
                        obrisiZahtev = true;
                    }
                }
                ////////////////////////////////////////////////////////////////////////////////
            }
            else{ /////// AKO NEMA SLOBODNIH ZA TAJ TERMIN TRAZENI
                List<SalaDTO> listaDrugiTermin = findDrugiTermin(zahtev);
                ///// UOPSTE NEMA TERMINA? OSTAVI ZAHTEV... NEKA ADMIN ODLUCI STA CE DA URADI POVODOM TOGA
                if (listaDrugiTermin.isEmpty()){
                    obrisiZahtev = false;
                }

                if (zahtev.getTipPosete().equals("Pregled")){
                    algoritamPregledDrugiTermin(zahtev, listaDrugiTermin);
                    obrisiZahtev = true;
                }
                else{
                    algoritamOperacijaDrugiTermin(zahtev);
                    obrisiZahtev = true;
                }
            }
            if (obrisiZahtev && zahtevRepository.findById(zahtev.getId()).isPresent()){
                    zahtevRepository.deleteById(zahtev.getId());
            }
        }

    }
    /////////////////////////////////////////////////////////////////////////////////////////////
	public Optional<Klinika> findById(Long id) {
		return klinikaRepository.findById(id);
	}


    public List<OperacijaKalendarDTO> findOperacijeByLekar(Lekar lekar) {
        System.out.println(lekar.getJbo());
        return operacijaRepository.findByLekari(lekar.getId());
    }

    public List<OperacijaKalendarDTO> getZakazaneOperacije() { return operacijaRepository.getZakazaneOperacije(); }
}



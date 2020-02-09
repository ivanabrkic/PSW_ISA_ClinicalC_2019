package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.PswIsaClinicalC2019Application;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.OperacijaDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PswIsaClinicalC2019Application.class)
public class KlinikaServiceTest2 {

    @InjectMocks
    @Autowired
    private KlinikaService klinikaService;

    @MockBean
    private CenovnikRepository cenovnikRepository;

    @MockBean
    private LekarRepository lekarRepository;

    @MockBean
    private PacijentRepository pacijentRepository;

    @MockBean
    private OperacijaRepository operacijaRepository;

    @MockBean
    private SalaRepository salaRepository;

    @MockBean
    private ZahtevRepository zahtevRepository;
//String tipPosete, Long idStavke, String stavkaCenovnika, String tipPosiljaoca, String posiljalacJbo, String posiljalacImePrezime, String jboPacijenta, String jboLekara, String datum, String pocetak, String kraj, String dodatneInformacije, Long idKlinike
    List<Zahtev> zahtevList = new ArrayList<Zahtev>();
    List<Zahtev> zahtevListEmpty = new ArrayList<Zahtev>();
    private Korisnik k2 = new Korisnik("1231233k", "mal@gmail.com", "08951234234", "Ime2", "Prezime2", "123532231", true,
            "Novi Sad", "Vojvode Stepe 25", "Srbija", "Lekar");
    private Klinika klinika1=new Klinika("Klinika1","Adresa1","Grad1","Drzava1","Email1","0640000001",5,"Opis1");
    private Cenovnik c1 = new Cenovnik("cenovnik1", "Zubar", 2342, klinika1);

    private Lekar l1 = new Lekar(k2);
    Zahtev zahtev1 = new Zahtev("Pregled", (long) 1, "cenovnik1", "posiljaoc1", "12314143124124", "Ime Prezime",
            "0981244775842", "123532231", "9.2.2020", "12:30", "13:00", "Jos informacija jer ovo nije dosta", (long) 1);
    Zahtev zahtev3 = new Zahtev("Pregled", (long) 2, "stavka3", "posiljaoc3", "12314141124124", "Staro Prezime",
            "0981244577842", "1267986378375", "9.2.2020", "13:30", "14:00", "Jos previse informacija jer ovo nije dosta", (long) 1);

    private Klinika klinika2=new Klinika("Klinika2","Adresa2","Grad2","Drzava2","Email2","0640000002",4,"Opis2");
    private Klinika klinika3=new Klinika("Klinika3","Adresa3","Grad3","Drzava3","Email3","0640000003",5,"Opis3");

    private Cenovnik c2 = new Cenovnik("cenovnik2", "Zubar",242, klinika2);
    private Cenovnik c3 = new Cenovnik("cenovnik3", "Zubar", 259, klinika3);

    private Korisnik k1 = new Korisnik("123123123k", "mail@gmail.com", "08958474234", "Ime1", "Prezime1", "123531231", true,
            "Novi Sad", "Vojvode Stepe 30", "Srbija", "Pacijent");
    private Korisnik k3 = new Korisnik("1231233k", "mal@gmail.com", "08951234234", "Ime2", "Prezime2", "0981244775842", true,
            "Novi Sad", "Vojvode Stepe 25", "Srbija", "Lekar");

    private Pacijent p1 = new Pacijent(k1);
    private Pacijent p2 = new Pacijent(k3);


    private Sala sala1 = new Sala("sala1", "234", klinika1);
    private Sala sala2 = new Sala("sala2", "214", klinika1);

    Optional<Zahtev> zahtev11 = Optional.of(zahtev1);
    Optional<Pacijent> pacijent11 = Optional.of(p1);
    Optional<Lekar> lekar11 = Optional.of(l1);
    Optional<Sala> sala11 = Optional.of(sala1);
    Optional<Cenovnik> c11 = Optional.of(c1);

    Optional<Pacijent> pacijent21 = Optional.of(p1);
    Optional<Lekar> lekar21 = Optional.of(l1);
    Optional<Sala> sala21 = Optional.of(sala2);
    Optional<Cenovnik> c21 = Optional.of(c2);

    List<String> jboLekara = new ArrayList<>();
    OperacijaDTO operacijaDTO = new OperacijaDTO("123531231", "23.06.2020.", "13:30", "14:45", jboLekara, "operacija krajnika", (long) 1, (long) 1);

    PregledDTO pregledDTO = new PregledDTO("123531231", "23.06.2020.", "13:30", "14:45", "123532231", "provera vida", (long) 1, (long) 1, 20, "zakazan");

    List<Long> lekari = new ArrayList<Long>();
    List<Long> lekari2 = new ArrayList<Long>();
    List<Long> lekariPregled = new ArrayList<Long>();

    /////////////////////////////////////////////////////////
    Korisnik kl =  new Korisnik("1231233k", "maaaa@gmail.com", "08951234234", "Ime2", "Prezime2", "1234567890765", true,
            "Novi Sad", "Vojvode Stepe 25", "Srbija", "Lekar");
    Lekar lekar = new Lekar(kl);
    private Cenovnik c4 = new Cenovnik("cenovnik4", "Zubar", 2342, klinika1);
    Zahtev zahtevPregled = new Zahtev("Pregled", (long) 4, "cenovnik4", "posiljaoc1", "1233210989876", "Ime Prezime",
            "0981244775842", "1234567890765", "9.2.2020", "12:30", "13:00", "Jos informacija jer ovo nije dosta", (long) 1);

    Optional<Cenovnik> cPregled = Optional.of(c4);

    /////////////////////////////////////1268775378375
    private Korisnik kp = new Korisnik("1231233k", "mal@gmail.com", "08951234234", "Ime2", "Prezime2", "1268775378375", true,
            "Novi Sad", "Vojvode Stepe 25", "Srbija", "Lekar");
    Lekar lekarOperacija = new Lekar(kp);
    private Cenovnik c5 = new Cenovnik("cenovnik5", "Kardiolog", 2342, klinika1);
    Zahtev zahtev2 = new Zahtev("Operacija", (long) 5, "cenovnik5", "posiljaoc2", "12314121224124", "Novo Ime",
            "0981244757842", "1268775378375", "9.2.2020", "14:30", "15:00", "Jos mnogo informacija jer ovo nije dosta", (long) 1);

    Optional<Cenovnik> cOperacija = Optional.of(c5);
    List<Long> lekariOperacija = new ArrayList<Long>();
    Optional<Zahtev> zahtev21 = Optional.of(zahtev2);
    @Before
    public void priprema(){
        l1.setId((long) 1);

        sala1.setId((long) 1);
        sala2.setId((long) 2);
        lekari.add((long) 1);
        l1.setSpecijalizacija("Zubar");
        l1.setRadnoVreme("Prva smena od 8:00 do 16:00");
        k1.setId((long) 1);
        k2.setId((long) 2);
        k3.setId((long) 3);
        kl.setId((long) 4);


        klinika1.setId((long) 1);
        klinika2.setId((long) 2);
        klinika3.setId((long) 3);

        p1.setId((long) 1);
        p2.setId((long) 2);


        c1.setId((long) 1);
        c2.setId((long) 2);
        c3.setId((long) 3);

        zahtev1.setId((long) 1);
        zahtev2.setId((long) 2);
        zahtev3.setId((long) 3);

        zahtevList.add(zahtev1);
        zahtevList.add(zahtev2);
        zahtevList.add(zahtev3);

        ////////////////////////////////////////
        lekar.setId((long) 3);
        lekar.setSpecijalizacija("Zubar");
        lekar.setRadnoVreme("Prva smena od 8:00 do 16:00");
        lekar.setKlinika(klinika1);
        c4.setId((long) 4);
        zahtevPregled.setId((long) 4);
        lekariPregled.add((long) 2);

        ////////////////////////////////////////
        kp.setId((long) 5);
        lekarOperacija.setId((long) 4);
        lekar.setSpecijalizacija("Kardiolog");
        lekar.setRadnoVreme("Prva smena od 8:00 do 16:00");
        lekar.setKlinika(klinika1);
        lekariOperacija.add((long) 3);
    }

    ////////////////////3.18  Postupak rezervisanja sala za preglede - Getovanje zahteva po klinici/////////////////////
    @Test
    public void findByKlinikeId_Pass() {
        Mockito.when(this.zahtevRepository.findByIdKlinike((long) 1)).thenReturn(zahtevList);
        Mockito.when(this.zahtevRepository.findByIdKlinike((long) 2)).thenReturn(zahtevListEmpty);

        List<Zahtev> zahteviPoKlinici = klinikaService.getZahtevi((long) 1);
        List<Zahtev> zahteviPrazniPoKlinici = klinikaService.getZahtevi((long) 2);

        assertEquals(0, zahteviPrazniPoKlinici.size());
        assertEquals(3, zahteviPoKlinici.size());
        assertEquals("Pregled", zahteviPoKlinici.get(2).getTipPosete());
        assertEquals("1268775378375", zahteviPoKlinici.get(1).getJboLekara());
    }

    ///find by id i delete zahtev

    @Test
    public void findById_Pass() {
        Mockito.when(this.zahtevRepository.findById((long) 1)).thenReturn(zahtev11);

        Boolean zahtev = klinikaService.removeZahtev((long) 1);

        assertEquals(true, zahtev);
    }

    @Test
    public void zakaziOperaciju_Pass(){
        Mockito.when(this.cenovnikRepository.findById((long) 1)).thenReturn(c11);
        Mockito.when(this.pacijentRepository.findById((long) 1)).thenReturn(pacijent11);
        Mockito.when(this.pacijentRepository.findByJbo("123531231")).thenReturn(p1);
        Mockito.when(this.lekarRepository.findById((long) 1)).thenReturn(lekar11);
        Mockito.when(this.salaRepository.findById((long) 1)).thenReturn(sala11);

        String odgovor = klinikaService.zakaziOperaciju(operacijaDTO);

        assertEquals("Uspesno zakazana operacija!", odgovor);
    }

    @Test
    public void zakaziPregled_Pass(){
        Mockito.when(this.cenovnikRepository.findById((long) 1)).thenReturn(c21);
        Mockito.when(this.pacijentRepository.findByJbo("2222222222222")).thenReturn(p2);
        Mockito.when(this.lekarRepository.findByJbo("123532231")).thenReturn(l1);
        Mockito.when(this.salaRepository.findById((long) 1)).thenReturn(sala21);

        String odgovor = klinikaService.zakaziPregled(pregledDTO);

        assertEquals("Uspesno zakazan pregled!", odgovor);
    }

    List<Long> prazniLekari = new ArrayList<Long>();
    Optional<Lekar> lekarPregled = Optional.of(lekar);
    Optional<Lekar> lekarOpOptional = Optional.of(lekarOperacija);
    @Test
    public void getSlobodniLekariCeoLekar_Pass() {
        Mockito.when(this.lekarRepository.findById((long) 2)).thenReturn(lekarPregled);
        Mockito.when(this.lekarRepository.findByJbo("1234567890765")).thenReturn(lekar);
        Mockito.when(this.cenovnikRepository.findById((long) 4)).thenReturn(cPregled);
        Mockito.when(this.lekarRepository.radnoVremeSpecPregled((long) 1, "Prva smena od 8:00 do 16:00",
                "Zubar")).thenReturn(lekariPregled);
        Mockito.when(this.lekarRepository.radnoVremeSpecOperacija((long) 1,
                "Prva smena od 8:00 do 16:00", "Zubar")).thenReturn(lekariPregled);
        Mockito.when(this.lekarRepository.imaOperacije((long) 2, "9.2.2020", "12:30", "13:00")).thenReturn(prazniLekari);
        Mockito.when(this.lekarRepository.imaPreglede((long) 2, "9.2.2020", "12:30", "13:00" )).thenReturn(prazniLekari);

        //////////////////////////
        Mockito.when(this.lekarRepository.findById((long) 3)).thenReturn(lekarOpOptional);
        Mockito.when(this.lekarRepository.findByJbo("1268775378375")).thenReturn(lekarOperacija);
        Mockito.when(this.cenovnikRepository.findById((long) 5)).thenReturn(cOperacija);
        Mockito.when(this.lekarRepository.radnoVremeSpecPregled((long) 1, "Prva smena od 8:00 do 16:00",
                "Zubar")).thenReturn(lekariPregled);
        Mockito.when(this.lekarRepository.radnoVremeSpecOperacija((long) 1,
                "Prva smena od 8:00 do 16:00", "Kardiolog")).thenReturn(lekariOperacija);
        Mockito.when(this.lekarRepository.imaOperacije((long) 3, "9.2.2020", "14:30", "15:00")).thenReturn(prazniLekari);
        Mockito.when(this.lekarRepository.imaPreglede((long) 3, "9.2.2020", "14:30", "15:00" )).thenReturn(prazniLekari);

        //////////////////////////

        List<Lekar> lekari1 = klinikaService.getSlobodniLekariCeoLekar(zahtevPregled);
        List<Lekar> lekari2 = klinikaService.getSlobodniLekariCeoLekar(zahtev2);

        assertEquals(lekar, lekari1.get(0));
        assertEquals(lekarOperacija, lekari2.get(0));
    }
}

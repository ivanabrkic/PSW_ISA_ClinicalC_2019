package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.PswIsaClinicalC2019Application;
import isaps.tim18.PSW_ISA_ClinicalC_2019.TestUtil;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.OperacijaDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.KlinikaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PswIsaClinicalC2019Application.class)
public class KlinikaControllerTest {

    private MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private KlinikaService klinikaService;

    @MockBean
    private ZahtevRepository zahtevRepository;

    @MockBean
    private PacijentRepository pacijentRepository;

    @MockBean
    private LekarRepository lekarRepository;

    @MockBean
    private SalaRepository salaRepository;

    @MockBean
    private CenovnikRepository cenovnikRepository;

    private Klinika klinika1 = new Klinika("Klinika1","Adresa1","Grad1","Drzava1","Email1","0640000001",5,"Opis1");

    Zahtev zahtev1 = new Zahtev("Pregled", (long) 1, "cenovnik1", "posiljaoc1", "12314143124124", "Ime Prezime",
            "123531231", "123532231", "9.2.2020", "12:30", "13:00", "Jos informacija jer ovo nije dosta", (long) 1);
    private Cenovnik c1 = new Cenovnik("cenovnik1", "Zubar", 2342, klinika1);

    private Korisnik k1 = new Korisnik("123123123k", "mail@gmail.com", "08958474234", "Ime1", "Prezime1", "123531231", true,
            "Novi Sad", "Vojvode Stepe 30", "Srbija", "Pacijent");
    private Korisnik kl =  new Korisnik("1231233k", "maaaa@gmail.com", "08951234234", "Ime2", "Prezime2", "1234567890765", true,
            "Novi Sad", "Vojvode Stepe 25", "Srbija", "Lekar");
    private Pacijent p1 = new Pacijent(k1);
    private Lekar l1 = new Lekar(kl);

    private Sala sala1 = new Sala("sala1", "234", klinika1);

    Optional<Sala> s11 = Optional.of(sala1);

    List<Zahtev> zahtevi = new ArrayList<Zahtev>();

    Optional<Cenovnik> c11 = Optional.of(c1);

    ////////////////////////////////////////////////////////
    Zahtev zahtev2 = new Zahtev("Operacija", (long) 5, "cenovnik5", "posiljaoc2", "12314121224124", "Novo Ime",
            "0981244757842", "1268775378375", "9.2.2020", "14:30", "15:00", "Jos mnogo informacija jer ovo nije dosta", (long) 1);
    private Cenovnik c2 = new Cenovnik("cenovnik2", "Kardiolog", 2342, klinika1);
    private Korisnik ko =  new Korisnik("1231233k", "maaaa@gmail.com", "08951234234", "Ime2", "Prezime2", "1268775378375", true,
            "Novi Sad", "Vojvode Stepe 25", "Srbija", "Lekar");
    private Korisnik k2 =  new Korisnik("1231233k", "maaaa@gmail.com", "08951234234", "Ime2", "Prezime2", "1268775378375", true,
            "Novi Sad", "Vojvode Stepe 25", "Srbija", "Lekar");
    Pacijent p2 = new Pacijent(k2);
    private Lekar lekarOperacija = new Lekar(ko);
    Optional<Cenovnik> c21 = Optional.of(c2);

    //////////////////////////////
    Zahtev zahtevPregled = new Zahtev("Pregled", (long) 4, "cenovnik4", "posiljaoc1", "1233210989876", "Ime Prezime",
            "0981244775842", "1234567890765", "9.2.2020", "12:30", "13:00", "Jos informacija jer ovo nije dosta", (long) 1);
    private Korisnik kp = new Korisnik("1231233k", "mal@gmail.com", "08951234234", "Ime2", "Prezime2", "1268775378375", true,
            "Novi Sad", "Vojvode Stepe 25", "Srbija", "Lekar");
    Lekar lekarOp = new Lekar(kp);
    private Cenovnik c5 = new Cenovnik("cenovnik5", "Kardiolog", 2342, klinika1);
    Optional<Cenovnik> cOperacija = Optional.of(c5);


    Optional<Lekar> lekarOpOptional = Optional.of(lekarOp);
    //////////////////////////////


    @PostConstruct
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }
    ////////////////3.18 Rezervisanje sala za pregledu//////////////////////
   @Before
    public void login(){
       zahtevi.add(zahtev1);
       zahtevi.add(zahtev2);
       sala1.setId((long) 1);
       k1.setId((long) 1);
       kl.setId((long) 2);

       l1.setId((long) 1);
       p1.setId((long) 1);

       c1.setId((long) 1);

       lekarOp.setId((long) 2);
       lekarOp.setSpecijalizacija("Kardiolog");
       lekarOp.setKlinika(klinika1);
       jboLekara.add("1234567890765");

       lekariLong.add((long) 2);

       lekarOperacija.setSpecijalizacija("Kardiolog");

       ResponseEntity<Korisnik> responseEntity =
                restTemplate.postForEntity("/login/loginSubmit", new Korisnik("12345678b", "anamarija@mailsac.com", "0652754579", "Ana-Marija"
                        , "Buhmiler", "1233211231111", true, "Novi Sad", "Srbija", "Mise Dimitrijevica 51", "Admninistrator klinike"),
                        Korisnik.class);
    }

    @Test
    public void getZahteve() throws Exception {
        Klinika klinika1 = new Klinika("Klinika1","Adresa1","Grad1","Drzava1","Email1","0640000001",5,"Opis1");
        klinika1.setId((long) 1);

        String id = klinika1.getId().toString();

        Mockito.when(this.klinikaService.getZahtevi((long) 1)).thenReturn(zahtevi);
        Mockito.when(this.zahtevRepository.findByIdKlinike((long) 1)).thenReturn(zahtevi);

        mockMvc.perform(post("/klinika/getZahtevi").contentType(contentType).content(id)).andExpect(status().isOk());
    }

    @Test
    public void ZakaziPregled() throws Exception {
        PregledDTO pregledDTO = new PregledDTO("123531231", "23.06.2020.", "13:30",
                "14:45", "1234567890765", "provera vida", (long) 1, (long) 1, 20,
                "Zakazan");
        Mockito.when(this.cenovnikRepository.findById((long) 1)).thenReturn(c11);
        Mockito.when(this.pacijentRepository.findByJbo("123531231")).thenReturn(p1);
        Mockito.when(this.lekarRepository.findByJbo("1234567890765")).thenReturn(l1);
        Mockito.when(this.salaRepository.findById((long) 1)).thenReturn(s11);

        String json = TestUtil.json(pregledDTO);

        mockMvc.perform(post("/klinika/zakaziPregled").contentType(contentType).content(json)).andExpect(status().isOk());
    }
    List<String> jboLekara = new ArrayList<String>();
    Optional<Pacijent> p21 = Optional.of(p2);


    @Test
    public void ZakaziOperaciju() throws Exception {
        OperacijaDTO operacijaDto = new OperacijaDTO("0981244757842", "23.06.2020.", "13:30",
                "14:45", jboLekara, "provera vida", (long) 2, (long) 1);

        Mockito.when(this.cenovnikRepository.findById((long) 1)).thenReturn(c21);
        Mockito.when(this.pacijentRepository.findById((long) 1)).thenReturn(p21);
        Mockito.when(this.pacijentRepository.findByJbo("123531231")).thenReturn(p2);
        Mockito.when(this.lekarRepository.findByJbo("1268775378375")).thenReturn(lekarOperacija);
        Mockito.when(this.salaRepository.findById((long) 1)).thenReturn(s11);

        String json = TestUtil.json(operacijaDto);
//String jboPacijenta, String datum, String pocetak, String kraj, List<String> jboLekara, String tipPregleda, Long tipId, Long salaId
        mockMvc.perform(post("/klinika/zakaziOperaciju").contentType(contentType).content(json)).andExpect(status().isOk());
    }

    List<Long> lekariLong = new ArrayList<Long>();
    List<Long> prazniLekari = new ArrayList<Long>();
    @Test
    public void getSlobodniLekariCeoLekar_Pass() throws Exception {
        Mockito.when(this.lekarRepository.findById((long) 3)).thenReturn(lekarOpOptional);
        Mockito.when(this.lekarRepository.findByJbo("1268775378375")).thenReturn(lekarOperacija);
        Mockito.when(this.cenovnikRepository.findById((long) 5)).thenReturn(cOperacija);
        Mockito.when(this.lekarRepository.radnoVremeSpecPregled((long) 1, "Prva smena od 8:00 do 16:00",
                "Zubar")).thenReturn(lekariLong);
        Mockito.when(this.lekarRepository.radnoVremeSpecOperacija((long) 1,
                "Prva smena od 8:00 do 16:00", "Kardiolog")).thenReturn(lekariLong);
        Mockito.when(this.lekarRepository.imaOperacije((long) 3, "9.2.2020", "14:30", "15:00")).thenReturn(prazniLekari);
        Mockito.when(this.lekarRepository.imaPreglede((long) 3, "9.2.2020", "14:30", "15:00" )).thenReturn(prazniLekari);

        String json = TestUtil.json(zahtev1);

        mockMvc.perform(post("/klinika/getSlobodniLekari").contentType(contentType).content(json)).andExpect(status().isOk());
    }
}

package Service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.PswIsaClinicalC2019Application;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.KlinikaService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PswIsaClinicalC2019Application.class)
public class ZahteviPreglediTest {

    @InjectMocks
    @Autowired
    private KlinikaService klinikaService;

    @MockBean
    private PacijentRepository pacijentRepository;

    @MockBean
    private ZahtevRepository zahtevRepository;

    @MockBean
    private PregledRepository pregledRepository;

    @MockBean
    private KlinikaRepository klinikaRepository;

    @MockBean
    private SalaRepository salaRepository;

    @MockBean
    private LekarRepository lekarRepository;

    @MockBean
    private CenovnikRepository cenovnikRepository;

    private Klinika klinika1 = new Klinika("Laza Lazarevic", "Adresa 1",
            "Grad", "Drzava", "klinika1@gmail.com", "021/754-579",3,"Opis");
    private Klinika klinika2 = new Klinika("Milan Milanovic", "Adresa 1",
            "Grad", "Drzava", "klinika2@gmail.com", "021/222-579",5,"Opis");

    private Lekar lekar = new Lekar("12345678b","lekar@gmail.com","063555555",
            "Kosta", "Doca", "1234567891234", true, "grad", "drzava",
            "adresa", "Lekar", 30, "Prva smena od 8:00 do 16:00", 3,
            "Zubar", klinika1);

    private Pacijent pacijent1 = new Pacijent("12345678b","pacijent1@gmail.com","065777777",
            "Laza", "Pacijent", "1114567891234", true, "grad", "drzava",
            "adresa", "Pacijent");
    private Pacijent pacijent2 = new Pacijent("12345678b","pacijent2@gmail.com","065727777",
            "Moca", "Pacijent", "1224567891234", true, "grad", "drzava",
            "adresa", "Pacijent");

    private Sala sala1 = new Sala("Sala", "1A", klinika1);
    private Sala sala2 = new Sala("Sala", "1A", klinika2);

    private Cenovnik cenovnik = new Cenovnik("Popravka zuba", 5000, klinika1, "Zubar");

    private Pregled pregled1 = new Pregled(pacijent1, lekar, sala1, "10.2.2020.", "11:00", "12:00",
            cenovnik, 30, "Zakazan");
    private Pregled pregled2 = new Pregled(pacijent1, lekar, sala1, "10.2.2020.", "10:00", "10:45",
            cenovnik, 30, "Zakazan");

    private Pregled pregled3 = new Pregled();

    private Zahtev zahtev = new Zahtev(String tipPosete, Long idStavke, String stavkaCenovnika, String tipPosiljaoca, String posiljalacJbo, String posiljalacImePrezime, String jboPacijenta, String jboLekara, String datum, String pocetak, String kraj, String dodatneInformacije, Long idKlinike);

    @Before
    public void priprema () {


    }

    ////////// 3.18 PRIHVATANJE ZAHTEVA ZA PREGLEDE - PRETRAGA SLOBODNIH SALA - DODELJIVANJE LEKARA ////////////////////

    //// OSNOVNO - VRACA SVE ZAHTEVE ODREDJENE KLINIKE ////





}

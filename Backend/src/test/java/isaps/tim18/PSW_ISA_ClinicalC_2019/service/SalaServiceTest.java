package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.PswIsaClinicalC2019Application;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Sala;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.KlinikaRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.SalaRepository;
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
public class SalaServiceTest {

    @InjectMocks
    @Autowired
    private SalaService salaService;

    @MockBean
    private KlinikaRepository klinikaRepository;

    @MockBean
    private SalaRepository salaRepository;

    List<Klinika> klinike = new ArrayList<Klinika>();
    List<Sala> saleKlinike1 = new ArrayList<Sala>();
    private Klinika klinika1=new Klinika("Klinika1","Adresa1","Grad1","Drzava1","Email1","0640000001",5,"Opis1");
    private Klinika klinika2=new Klinika("Klinika2","Adresa2","Grad2","Drzava2","Email2","0640000002",5,"Opis2");
    private Klinika klinika3=new Klinika("Klinika3","Adresa3","Grad3","Drzava3","Email3","0640000003",5,"Opis3");

    List<Sala> sale = new ArrayList<Sala>();
    private Sala sala1 = new Sala("sala1", "234", klinika1);
    private Sala sala2 = new Sala("sala2", "214", klinika1);
    private Sala sala3 = new Sala("sala1", "211", klinika1);
    private Sala sala4 = new Sala("sala1", "123", klinika1);
    private Sala sala5 = new Sala("sala1", "321", klinika2);
    private Sala sala6 = new Sala("sala1", "837", klinika2);
    private Sala sala7 = new Sala("sala1", "108", klinika2);
    private Sala sala8 = new Sala("sala1", "561", klinika3);

    Optional<Klinika> klinika11;
    Optional<Sala> sala11;
    @Before
    public void priprema () {
        klinika1.setId((long) 1);
        klinika2.setId((long) 2);
        klinika3.setId((long) 3);

        sala1.setId((long) 1);
        sala2.setId((long) 2);
        sala3.setId((long) 3);
        sala4.setId((long) 4);
        sala5.setId((long) 5);
        sala6.setId((long) 6);
        sala7.setId((long) 7);
        sala8.setId((long) 8);

        sale.add(sala1);
        sale.add(sala2);
        sale.add(sala3);
        sale.add(sala4);
        sale.add(sala5);
        sale.add(sala6);
        sale.add(sala7);
        sale.add(sala8);

        saleKlinike1.add(sala1);
        saleKlinike1.add(sala2);
        saleKlinike1.add(sala3);
        saleKlinike1.add(sala4);

        klinike.add(klinika1);
        klinike.add(klinika2);
        klinike.add(klinika3);
    }

    ///////////////////////3.18 Postupak rezervisanja sala za preglede//////////////////////////////

    @Test
    public void findSala_Pass() {

        Mockito.when(this.klinikaRepository.findById((long) 1)).thenReturn(klinika11);
        Mockito.when(this.salaRepository.findByKlinikaId((long) 1)).thenReturn(saleKlinike1);

        List<Sala> sale = salaRepository.findByKlinikaId((long) 1);

        assertEquals(4, sale.size());
        assertEquals("sala1", sale.get(0).getNaziv());
        assertEquals("234", sale.get(0).getBroj());
        assertEquals(klinika1, sale.get(0).getKlinika());
    }

}

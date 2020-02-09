package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import isaps.tim18.PSW_ISA_ClinicalC_2019.PswIsaClinicalC2019Application;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefInfoDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pregled;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.KlinikaRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.PregledRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PswIsaClinicalC2019Application.class)
public class KlinikaServiceTest {
	
	@InjectMocks
	@Autowired
	private KlinikaService klinikaService;
	
	@MockBean
	private PregledRepository pregledRepo;
	
	@MockBean 
	private KlinikaRepository klinikaRepo;
	
	private List<Klinika> listaKlinika=new ArrayList<Klinika>();
	private Klinika klinika1=new Klinika("Klinika1","Adresa1","Grad1","Drzava1","Email1","0640000001",5,"Opis1");
	private Klinika klinika2=new Klinika("Klinika2","Adresa2","Grad2","Drzava2","Email2","0640000002",5,"Opis2");
	private Klinika klinika3=new Klinika("Klinika3","Adresa3","Grad3","Drzava3","Email3","0640000003",5,"Opis3");
	
	private List<predefInfoDTO> listaPredef=new ArrayList<predefInfoDTO>();
	private predefInfoDTO predef1=new predefInfoDTO(Long.valueOf(1),"Predef1","29.5.2020.","11:15","11:45","A","2","Klinika1","ImeL","PrezimeC",1000,20,Long.valueOf(1),Long.valueOf(1),"1111111111111",Long.valueOf(1));
	private predefInfoDTO predef2=new predefInfoDTO(Long.valueOf(2),"Predef2","22.5.2019.","1:15","1:45","B","3","Klinika1","ImeL","PrezimeC",2000,30,Long.valueOf(1),Long.valueOf(2),"1111111111111",Long.valueOf(1));
	
	private Korisnik korisnik1=new Korisnik("Lozinka1","Email1","0611111111","ImeK1","PrezimeK1","1111111111111",true,"GradK1","Drzava1","AdresaK1","Pacijent");
	private Pacijent pacijent1=new Pacijent(korisnik1);
	
	private Pregled pregled1=new Pregled();
	private List<Pregled> listaPregleda=new ArrayList<Pregled>();
	
	@Before 
	public void priprema () {
		klinika1.setId((long) 1);
		klinika2.setId((long) 2);
		klinika3.setId((long)3);
		listaKlinika.add(klinika1);
		listaKlinika.add(klinika2);
		listaKlinika.add(klinika3);
		
		listaPredef.add(predef1);
		listaPredef.add(predef2);
		
		korisnik1.setId((long)1);
		pacijent1.setId((long)1);
		
		pregled1.setId((long)3);
		pregled1.setPacijent(pacijent1);
		pregled1.setDatum("1.1.2020.");
		listaPregleda.add(pregled1);
	}
	
	//----------------------------------------3.12 ZAKAZIVANJE PREDEFINISANIH TERMINA--------------------------------------------
	
	
	// Osnovno -> vraca kliniku
	@Test
	public void findAllClinics_Pass() {
		
		Mockito.when(this.klinikaRepo.findAll()).thenReturn(listaKlinika);
		
		List<Klinika> stvarnaLista=klinikaService.findAll();
		
		assertEquals(3,stvarnaLista.size());
		assertEquals("Klinika1",stvarnaLista.get(0).getNaziv());
		assertEquals("Grad2",stvarnaLista.get(1).getGrad());
		assertEquals("Email3",stvarnaLista.get(2).getEmail());
	}
	
	// Osnovno -> vraca sve predefinisane termine klinike
	@Test
	public void findAllPredef_Pass() {
		
		Mockito.when(this.pregledRepo.findByKlinikaIdPredef(klinika1.getId())).thenReturn(listaPredef);
		
		List<predefInfoDTO> stvarnaLista=klinikaService.getPreglediPredef(klinika1.getId());
		
		assertEquals(2,stvarnaLista.size());
		assertEquals(20,stvarnaLista.get(0).getPopust());
		assertEquals("22.5.2019.",stvarnaLista.get(1).getDatum());
		
	}
	
	//Napredno -> vraca preglede u buducnosti koji se ne poklapaju sa pacijentovim rasporedom
	@Test
	public void findAllPredefPacFreeFuture_Pass() throws ParseException {
		
		Mockito.when(this.pregledRepo.findByKlinikaIdPredef2(klinika1.getId())).thenReturn(listaPredef);
		Mockito.when(this.pregledRepo.findByPacijentId(pacijent1.getId())).thenReturn(listaPregleda);
		
		List<predefInfoDTO> stvarnaLista=klinikaService.getPreglediPredefKlinPac(klinika1.getId(),pacijent1.getId());
		
		assertEquals(1,stvarnaLista.size()); //Ne sme da vrati 1 termin iz proslosti
		
		
		Pregled pregled2=new Pregled();
		pregled2.setId((long)4);
		pregled2.setPacijent(pacijent1);
		pregled2.setDatum("29.5.2020.");
		pregled2.setPocetak("11:00");
		pregled2.setKraj("11:30");
		listaPregleda.add(pregled2);
		
		Mockito.when(this.pregledRepo.findByPacijentId(pacijent1.getId())).thenReturn(listaPregleda);
		
		stvarnaLista=klinikaService.getPreglediPredefKlinPac(klinika1.getId(),pacijent1.getId());
		
		assertEquals(0,stvarnaLista.size()); //Ne sme da vrati 1 termin iz proslosti ni termin koji se preklapa za 15 min
		
	}

	
}

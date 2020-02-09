package Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.lekariterminiDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefInfoDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Cenovnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.GodisnjiOdmorLekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.CenovnikRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.GodisnjiOdmorLekarRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.KlinikaRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.LekarRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.PregledRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.LekarService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.PregledService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PswIsaClinicalC2019Application.class)
public class LekarServiceTest {
	
	@InjectMocks
	@Autowired
	private LekarService lekarService;
	
	@MockBean
	private LekarRepository lekarRepo;
	
	@MockBean 
	private GodisnjiOdmorLekarRepository godisnjiOdmorRepo;
	
	@MockBean 
	private CenovnikRepository cenovnikRepo;
	
	private List<Klinika> listaKlinika=new ArrayList<Klinika>();
	private Klinika klinika1=new Klinika("Klinika1","Adresa1","Grad1","Drzava1","Email1","0640000001",5,"Opis1");
	private Klinika klinika2=new Klinika("Klinika2","Adresa2","Grad2","Drzava2","Email2","0640000002",5,"Opis2");
	private Klinika klinika3=new Klinika("Klinika3","Adresa3","Grad3","Drzava3","Email3","0640000003",5,"Opis3");
	
	List<Cenovnik> listaCenovnik=new ArrayList<Cenovnik>();
	private Cenovnik cenovnik1=new Cenovnik("EKG", 1000, klinika1, "Kardiolog");
	
	
	List<Lekar> listaLekara=new ArrayList<Lekar>();
	private Korisnik korisnik1=new Korisnik("Lozinka1","Email1","0611111111","ImeK1","PrezimeK1","1111111111111",true,"GradK1","Drzava1","AdresaK1","Lekar");
	private Korisnik korisnik2=new Korisnik("Lozinka2","Email2","0611111112","ImeK2","PrezimeK2","1111111111112",true,"GradK2","Drzava2","AdresaK2","Lekar");
	private Lekar lekar1=new Lekar(korisnik1);
	private Lekar lekar2=new Lekar(korisnik2);
	
	List<GodisnjiOdmorLekar> godisnjiOdmorLista=new ArrayList<GodisnjiOdmorLekar>();
	GodisnjiOdmorLekar godisnjiOdmor=new GodisnjiOdmorLekar((long)1,lekar1,LocalDate.of(2020, Month.MARCH, 1),LocalDate.of(2020, Month.MARCH, 10));
	
	private lekariterminiDTO upit1;
			
	@Before 
	public void priprema () {
		
		cenovnik1.setId((long)1);
		listaCenovnik.add(cenovnik1);
		lekar1.setId((long)1);
		lekar1.setKlinika(klinika1);
		lekar1.setSpecijalizacija("Kardiolog");
		lekar2.setSpecijalizacija("Zubar");
		lekar2.setKlinika(klinika1);
		lekar2.setId((long)2);
		listaLekara.add(lekar1);
		listaLekara.add(lekar2);
		godisnjiOdmorLista.add(godisnjiOdmor);
		klinika1.setId((long)1);
		
		
	}
	
	
	//----------------------------------------3.13 PRETRAGA I FILTRIRANJE KLINIKA--------------------------------------------
	
	//Napredno -> pronalazak termina slobodnih lekara za specijalizaciju i datum
	@Test
	public void slobodniLekariITermini() throws ParseException {
		
		Mockito.when(this.cenovnikRepo.findByNaziv("EKG")).thenReturn(listaCenovnik);
		Mockito.when(this.lekarRepo.findAll()).thenReturn(listaLekara);
		Mockito.when(this.godisnjiOdmorRepo.findByLekarId(lekar1.getId())).thenReturn(godisnjiOdmorLista);
		
		//Nece biti slobodnih termina jer je lekar na godisnjem odmoru a drugi nema odgovarajucu specijalizaciju
		upit1=new lekariterminiDTO("1.3.2020.","00:00","23:59","EKG",(long)1);
		HashMap<String,Lekar> lekariTermini=new HashMap<String,Lekar>();
		lekariTermini=this.lekarService.getSlobodniLekariTermini(upit1);
		assertEquals(0,lekariTermini.size());
		
		//Prvi lekar je slobodan ceo dan, drugi nije dobre specijalizacije, bice rezultata
		upit1=new lekariterminiDTO("1.4.2020.","00:00","23:59","EKG",(long)1);
		lekariTermini=new HashMap<String,Lekar>();
		lekariTermini=this.lekarService.getSlobodniLekariTermini(upit1);
		assertNotEquals(0,lekariTermini.size());
		assertEquals(lekar1,lekariTermini.values().iterator().next());
		assertEquals(24*4,lekariTermini.size()); //Potpuno slobodan lekar ima 96 termina u danu (na 15 min)
		
	}
}

package Service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.PswIsaClinicalC2019Application;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Cenovnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.CenovnikRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.CenovnikService;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PswIsaClinicalC2019Application.class)
public class CenovnikServiceTest {
	
	@InjectMocks
	@Autowired
	private CenovnikService cenovnikService;
	
	@MockBean
	private CenovnikRepository cenovnikRepo;
	
	private Klinika klinika1=new Klinika("Klinika1","Adresa1","Grad1","Drzava1","Email1","0640000001",5,"Opis1");
	private Klinika klinika2=new Klinika("Klinika2","Adresa2","Grad2","Drzava2","Email2","0640000002",5,"Opis2");
	private Klinika klinika3=new Klinika("Klinika3","Adresa3","Grad3","Drzava3","Email3","0640000003",5,"Opis3");
	private List<Cenovnik> listaCenovnik=new ArrayList<Cenovnik>();
	private Cenovnik cenovnik1=new Cenovnik("EKG", 1000, klinika1, "Kardiolog");
	
		
	@Before 
	public void priprema () {
	
		cenovnik1.setId((long)1);
		listaCenovnik.add(cenovnik1);
		klinika1.setId((long)1);
		klinika2.setId((long)2);
		klinika3.setId((long)3);
		
	
	}
	
	
	//----------------------------------------3.13 PRETRAGA I FILTRIRANJE KLINIKA--------------------------------------------
	
	@Test 
	public void findAll_PASS() {
		
		Mockito.when(this.cenovnikRepo.findAll()).thenReturn(listaCenovnik);
		
		List<Cenovnik> pravaLista=this.cenovnikService.findAll();
		
		assertEquals(1,pravaLista.size());
		assertEquals("Kardiolog",pravaLista.get(0).getSpecijalizacija());
	}
	
	@Test
	public void findByNazivTipaAndIDKlinike() {
		Mockito.when(this.cenovnikRepo.findByNazivAndKlinikaId(cenovnik1.getNaziv(),klinika1.getId())).thenReturn(cenovnik1);
		
		float pravaCena=cenovnikService.findByNazivAndKlinikaId("EKG", (long)1);
		
		assertEquals(1000,pravaCena,2);
		
	}
	


}

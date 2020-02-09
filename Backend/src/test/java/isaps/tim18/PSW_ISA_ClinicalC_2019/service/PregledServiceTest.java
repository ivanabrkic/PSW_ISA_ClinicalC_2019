package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pregled;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.PacijentRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.PregledRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PswIsaClinicalC2019Application.class)
public class PregledServiceTest {
	
	@InjectMocks
	@Autowired
	private PregledService pregledService;
	
	@MockBean
	private PregledRepository pregledRepo;
	
	@MockBean 
	private PacijentRepository pacijentRepo;
	
	private Pregled pregled1=new Pregled();
	private List<Pregled> listaPregleda=new ArrayList<Pregled>();
	private Korisnik korisnik1=new Korisnik("Lozinka1","Email1","0611111111","ImeK1","PrezimeK1","1111111111111",true,"GradK1","Drzava1","AdresaK1","Pacijent");
	private Pacijent pacijent1=new Pacijent(korisnik1);
	private Optional<Pregled> opregled1;
	private Optional<Pacijent> opacijent1;
	private predefDTO pregledDTO1=new predefDTO((long)3,(long)1); //id pregleda 3, id pacijenta 1
	
	@Before 
	public void priprema () {
		
		korisnik1.setId((long)1);
		pacijent1.setId((long)1);
		pregled1.setId((long)3);
		pregled1.setPacijent(pacijent1);
		pregled1.setDatum("1.1.2020.");
		pregled1.setStatus("Neaktivan");
		listaPregleda.add(pregled1);
		opregled1=Optional.of(pregled1);
		opacijent1=Optional.of(pacijent1);
	}
	
	
	//----------------------------------------3.12 ZAKAZIVANJE PREDEFINISANIH TERMINA--------------------------------------------
	
	@Test
	public void zakaziPredef_PASS() {
		
		//zakazivanje regularnog termina
		Mockito.when(this.pregledRepo.findById(pregled1.getId())).thenReturn(opregled1);
		Mockito.when(this.pacijentRepo.findById(pacijent1.getId())).thenReturn(opacijent1);
		Pregled p=this.pregledService.update(pregledDTO1);
		assertEquals("Zakazan",p.getStatus());
		
		//zakazivanje termina koji ne postoji u bazi
		Mockito.when(this.pregledRepo.findById((long)5)).thenReturn(null);
		pregledDTO1.setPacijent_id((long)5);
		Pregled p2=this.pregledService.update(pregledDTO1);
		assertEquals(null,p2);
		
		//zakazivanje vec zakazanog termina
		pregledDTO1.setPacijent_id(pacijent1.getId());
		pregled1.setStatus("Zakazan");
		opregled1=Optional.of(pregled1);
		Mockito.when(this.pacijentRepo.findById(pacijent1.getId())).thenReturn(opacijent1);
		Pregled p3=this.pregledService.update(pregledDTO1);
		assertEquals(null,p3);
		
	
	}

}

package isaps.tim18.PSW_ISA_ClinicalC_2019.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.annotation.PostConstruct;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import isaps.tim18.PSW_ISA_ClinicalC_2019.PswIsaClinicalC2019Application;
import isaps.tim18.PSW_ISA_ClinicalC_2019.TestUtil;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.klinikaPacDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.lekariterminiDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Zahtev;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT, classes = PswIsaClinicalC2019Application.class)
public class KlinikaControllerIntegrationTest {
	
	private MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());
	

	private MockMvc mockMvc;

	
	@Autowired
    private TestRestTemplate restTemplate;
	
	 @Autowired
	    private WebApplicationContext webApplicationContext;
	 
	 private lekariterminiDTO upit1;
	 private klinikaPacDTO upit2;
	 private  predefDTO pregled;
	 private Zahtev zahtev1 = new Zahtev("Operacija", (long) 1, "cenovnik1", "posiljaoc1", "1234567891111", "Ime Prezime",
	            "0981244775842", "1234567891111", "9.2.2020", "12:30", "13:00", "Jos informacija jer ovo nije dosta", (long) 1);
	 private Zahtev zahtev2 = new Zahtev("Pregled", (long) 5, "cenovnik5", "posiljaoc2", "1234567891111", "Novo Ime",
	            "0981244757842", "1234567891111", "9.2.2020", "14:30", "15:00", "Jos mnogo informacija jer ovo nije dosta", (long) 1);
	 private PregledDTO pregled1;
	 
	 @PostConstruct
	    public void setUp() {
	        this.mockMvc = MockMvcBuilders
	                .webAppContextSetup(webApplicationContext)
	                .build();
	    }
	 
	 @Before
		public void login() {
			ResponseEntity<Korisnik> responseEntity = 
					restTemplate.postForEntity("/login/loginSubmit", 
							new Korisnik("tamaralazarevic@mailsac.com","12345678b"), 
							Korisnik.class);
		
		}
	 
		//----------------------------------------3.13 PRETRAGA I FILTRIRANJE KLINIKA--------------------------------------------
	 
	 @Test 
	 public void getSlobodneKlinike_PASS() throws Exception {
		 
		 upit1=new lekariterminiDTO("1.3.2020.","00:00","23:59","EKG",(long)1);
		 String json = TestUtil.json(upit1);
		 
		 mockMvc.perform(post("/klinika/slobodneKlinike")
					.contentType(contentType)
					.content(json))
		 			//.andExpect(content().string(containsString("id")))
		 			.andExpect(status().isOk());
					
					
	 }
	 
 //----------------------------------------3.12 ZAKAZIVANJE PREDEFINISANIH TERMINA--------------------------------------------
	 @Test 
	 public void getPredefinisaniTermini_PASS() throws Exception {
		 
		 upit2=new klinikaPacDTO((long)1,(long)1);
		 String json = TestUtil.json(upit2);
		 
		 mockMvc.perform(post("/klinika/getPreglediPredef")
					.contentType(contentType)
					.content(json))
		 			.andExpect(status().isOk());
	 }
	 
	 @Test 
	 public void zakaziPredefTermin_PASS() throws Exception {
		 
		 pregled=new predefDTO((long)1,(long)3);
		 String json = TestUtil.json(pregled);
		 
		 mockMvc.perform(post("/klinika/zakaziPredefTerminn")
					.contentType(contentType)
					.content(json))
		 			.andExpect(status().isOk());
	 }
	 
	 @Test 
	 public void zakaziPredefTerminBadRequest_PASS() throws Exception {
		 
		 pregled=new predefDTO((long)100,(long)100); //ne postoji pacijent
		 String json = TestUtil.json(pregled);
		 
		 mockMvc.perform(post("/klinika/zakaziPredefTerminn")
					.contentType(contentType)
					.content(json))
		 			.andExpect(status().isBadRequest());
	 }
	 
//---------------------------------3.18 REZERVISANJE SALA ZA PREGLEDE---------------------
	 
//	 @Test
//	 public void getZahteviBadRequest_PASS() throws Exception{
//		 
//		 String json = TestUtil.json((long)10000);
//		 mockMvc.perform(post("/klinika/getZahtevi")
//					.contentType(contentType)
//					.content(json))
//		 			.andExpect(status().isBadRequest());
//	 }
	 
	 @Test
	 public void getZahtevi_PASS() throws Exception{
		 
		 String json = TestUtil.json((long)1);
		 mockMvc.perform(post("/klinika/getZahtevi")
					.contentType(contentType)
					.content(json))
		 			.andExpect(status().isOk());
	 }
	 
	 @Test
	 public void getSlobodniLekari_PASS() throws Exception {
		 
		 String json=TestUtil.json(zahtev1); //operacija
		 mockMvc.perform(post("/klinika/getSlobodniLekari")
					.contentType(contentType)
					.content(json))
		 			.andExpect(status().isOk());
		 
		 TestUtil.json(zahtev2); //pregled
		 mockMvc.perform(post("/klinika/getSlobodniLekari")
					.contentType(contentType)
					.content(json))
		 			.andExpect(status().isOk());
	 }
	 
	 @Test
	 public void getSlobodniLekariNepostojeciJBO_PASS() throws Exception {
		 
		 zahtev1.setJboLekara("1111444444444");
		 String json=TestUtil.json(zahtev1); //operacija
		 mockMvc.perform(post("/klinika/getSlobodniLekari")
					.contentType(contentType)
					.content(json))
		 			.andExpect(status().isBadRequest());
		 
	 }
	 
	 @Test
	 public void zakaziPregled_PASS() throws Exception {
		 
		 pregled1= new PregledDTO("EKG","3423784911111","1234567891111","29.3.2020.","11:30","12:30");
		 pregled1.setTipId((long)1);
		 pregled1.setSalaId((long)1);
		 String json= TestUtil.json(pregled1); 
		 mockMvc.perform(post("/klinika/zakaziPregled")
					.contentType(contentType)
					.content(json))
		 			.andExpect(status().isOk());
	 }
	
	
	

}

package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.Message;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.OperacijaDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.klinikaPacDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.lekariterminiDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefInfoDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.CenovnikRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.AdministratorKlinikeService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.KlinikaService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.LekarService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.PregledService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.ZahtevService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.swing.text.DateFormatter;

@RestController
@RequestMapping(value = "klinika")
public class KlinikaController {

    @Autowired
    private KlinikaService klinikaService;

    @Autowired
    private LekarService lekarService;

    @Autowired
    private ZahtevService zahtevService;

    @Autowired
    private PregledService pregledService;

    @Autowired
    private AdministratorKlinikeService adminklService;

    @Autowired
    private CenovnikRepository cenovnikRepository;

    @PostMapping(value = "/registracijaKlinike", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public String Register(@RequestBody Klinika klinika){
        float ocena = (float)1.0;
        Klinika novaKlinika = klinika;
        klinika.setOcena(ocena);

        try {
            klinikaService.add(novaKlinika);
        } catch(Exception e){
            klinikaService.update(novaKlinika);
        }
        return "Uspešno registrovana klinika!";
    }

    @GetMapping(value = "/all", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Klinika>> getAllKlinike(){

        List<Klinika> listaKlinika =  klinikaService.findAll();

        return new ResponseEntity<>(listaKlinika, HttpStatus.OK);
    }

    @PostMapping(value = "/getMedSestre", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MedicinskaSestra>> getMedSestre(@RequestBody Long id){

        List<MedicinskaSestra> listaMedSestara =  klinikaService.findMedSestre(id);

        return new ResponseEntity<>(listaMedSestara, HttpStatus.OK);
    }

    @PostMapping(value = "/getLekari", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Lekar>> getLekari(@RequestBody Long id){

        List<Lekar> listaLekara =  klinikaService.findLekari(id);

        return new ResponseEntity<>(listaLekara, HttpStatus.OK);
    }

    @PostMapping(value = "/update", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Klinika> update(@RequestBody Klinika klinika) throws Exception {

        Klinika l = klinikaService.update(klinika);

        if (l != null){
            return new ResponseEntity<>(klinika, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/getOperacije", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OperacijaDTO>> operacije(@RequestBody Long id){

        List<OperacijaDTO> operacije = klinikaService.getOperacije(id);

        return new ResponseEntity<>(operacije, HttpStatus.OK);
    }

    @PostMapping(value = "/getPregledi", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PregledDTO>> pregled(@RequestBody Long id) {

        List<PregledDTO> pregledi = klinikaService.getPregledi(id);

        return new ResponseEntity<>(pregledi, HttpStatus.OK);
    }

    @PostMapping(value = "/getPreglediPredef", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<predefInfoDTO>> pregledpredef(@RequestBody klinikaPacDTO k) throws Exception {



        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("d.m.yyyy.");
        String date=sdf.format(cal.getTime());


        List<predefInfoDTO> pregledi = klinikaService.getPreglediPredef(k.getIdKlin(),date,k.getIdPac()); //Prosli termini se ne izlistavaju.

        return new ResponseEntity<>(pregledi, HttpStatus.OK);
    }

    @PostMapping(value = "/getZahtevi", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Zahtev>> getZahtevi(@RequestBody Long idKlinike) {

        List<Zahtev> zahtevi = klinikaService.getZahtevi(idKlinike);

        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
    }



    @PostMapping(value = "/getSaleSlobodneOd", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sala>> getSaleSlobodneOd(@RequestBody Zahtev zahtev){

        List<Sala> listaSala =  klinikaService.findSaleSlobodneOd(zahtev);

        return new ResponseEntity<>(listaSala, HttpStatus.OK);
    }

    @PostMapping(value = "/zakaziPregled", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> zakaziPregled(@RequestBody PregledDTO pregled){

        String zakazi =  klinikaService.zakaziPregled(pregled);
        Message m = new Message(zakazi);

        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @PostMapping(value = "/zakaziOperaciju", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> zakaziOperaciju(@RequestBody OperacijaDTO operacija){

        String zakazi =  klinikaService.zakaziOperaciju(operacija);
        Message m = new Message(zakazi);

        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @PostMapping(value = "/removeZahtev", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> removeZahtev(@RequestBody Long idZahteva){

        Boolean zahtevi =  klinikaService.removeZahtev(idZahteva);

        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
    }

    @PostMapping(value="/slobodneKlinike", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Klinika>> slobodneKlinike(@RequestBody lekariterminiDTO zahtev) throws ParseException {


        List<Klinika> slobodneKlinike=new ArrayList<>();
        HashMap<String,Lekar> lekari;

        List<Klinika> listaKlinika =  klinikaService.findAll(); //sve klinike
        for (Klinika k : listaKlinika){  //filtriranje zauzetih
            zahtev.setIdKlinike(k.getId());
            lekari=lekarService.getSlobodniLekariTermini(zahtev);
            if(!lekari.isEmpty()){
                slobodneKlinike.add(k);
            }
        }

        return new ResponseEntity<>(slobodneKlinike, HttpStatus.OK); //vracanje slobodnih
    }

    @PostMapping(value = "/zakaziTermin", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Zahtev> zakaziTermin(@RequestBody Zahtev zahtev){

        System.out.print("Zahtev primljen");

        zahtevService.add(zahtev);
        List<AdministratorKlinike> administratori=adminklService.findAllByKlinikaId(zahtev.getIdKlinike());
        MailSenderController m=new MailSenderController();
        for (AdministratorKlinike a : administratori) {
            m.sendSimpleMessage(a.getEmail(),"Novi zahtev",
                    "Pristigao je novi zahtev za zakazivanje termina"+
                            " Za datum: "+zahtev.getDatum()+" i vreme: "+zahtev.getPocetak()+"-"+zahtev.getKraj()+
                            " za posetu: "+ zahtev.getTipPosete()+" tipa: "
                            + ""+zahtev.getStavkaCenovnika()+" od strane: "+zahtev.getTipPosiljaoca()+
                            ". Ulogujte se na vas nalog kako biste prihvatili ili odbili zahtev."
                            + ""
                            + "Ovaj mejl je automatski generisan i na njega nemojte odgovarati.");
        }
        return new ResponseEntity<>(zahtev, HttpStatus.OK); //vracanje slobodnih

    }


    @PostMapping(value = "/zakaziPredefTerminn", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pregled> zakaziPredefTermin(@RequestBody predefDTO pregled)throws Exception{

        System.out.print(pregled.getId());
        Optional<Pregled> pronadjenpregled=pregledService.update(pregled);

        return new ResponseEntity<>(pronadjenpregled.get(), HttpStatus.OK);

    }

    @PostMapping(value = "/getCena", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public float NadjiCenu(@RequestBody lekariterminiDTO zahtev)throws Exception{

        Cenovnik cen=cenovnikRepository.findByNazivAndKlinikaId(zahtev.getSpecijalizacija(),zahtev.getIdKlinike() );

        return cen.getCena();

    }
}
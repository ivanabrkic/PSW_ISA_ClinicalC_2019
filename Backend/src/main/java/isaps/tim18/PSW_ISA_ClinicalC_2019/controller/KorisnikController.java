package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.KorisnikRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "korisnik")
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Korisnik>> getAllKorisnik() {

        List<Korisnik> korisnik = korisnikService.findAll();
        for(int i = 0;i < korisnik.size(); i++){
            if(korisnik.get(i).getEmail().equals("n.milosevic0111@gmail.com")){
                MailSenderController mailSender = new MailSenderController();

                mailSender.sendSimpleMessage(korisnik.get(i).getEmail(), "Registracija na servis kliničkog centra",
                        "Uspešno ste se registrovali na servis!");
            }
        }
        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }

    @PostMapping(value = "/update", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> update(@RequestBody Korisnik korisnik) throws Exception {
        korisnikService.update(korisnik);

        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }

    @PostMapping(value = "/updateAktivnost", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> updateAktivnost(@RequestBody Korisnik korisnik) throws Exception {
        korisnikService.updateAktivnost(korisnik);

        MailSenderController mailSender = new MailSenderController();

        mailSender.sendSimpleMessage(korisnik.getEmail(), "Registracija na servis kliničkog centra",
                "Uspešno ste se registrovali na servis!");

        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }
}

package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.HelperRejectedMail;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.KorisnikService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.List;

@RestController
@RequestMapping(value = "korisnik")
public class KorisnikController {

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private MailSenderService mailSenderService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Korisnik>> getAllKorisnik() {

        List<Korisnik> korisnik = korisnikService.findAll();

        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }

    @PostMapping(value = "/update", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> update(@RequestBody Korisnik korisnik) throws Exception {
        korisnikService.update(korisnik);

        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }

    @PostMapping(value = "/updateAktivnost", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> updateAktivnost(@RequestBody Korisnik korisnik, WebRequest request) throws Exception {
        korisnikService.updateAktivnost(korisnik);

        mailSenderService.sendSimpleMessage(korisnik.getEmail(), "Registracija na servis kliničkog centra",
                 korisnik.getIme() + ", uspešno ste se registrovali na servis!");
        try {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                    (korisnik, appUrl));
        } catch (Exception me) {
            me.printStackTrace();
        }

        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }

    @PostMapping(value = "/rejected", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> odbijanjeRegistracije(@RequestBody HelperRejectedMail helperRejectedMail) throws Exception {

        HelperRejectedMail hrm = helperRejectedMail;
        korisnikService.removeByJbo(helperRejectedMail.getJbo());

        mailSenderService.sendSimpleMessage(helperRejectedMail.getKorisnikovEmail(), "Registracija na servis kliničkog centra",
                "Zahtev za registraciju je odbijen.\n\nRazlog:\n" + helperRejectedMail.getPoruka());

        String jboPretraga = helperRejectedMail.getJbo();
        Korisnik korisnik = korisnikService.findByJbo(jboPretraga);

        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }

    @GetMapping(value = "/ulogovanKorisnik", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> getUlogovanKorisnik(@Context HttpServletRequest request) {

        String jbo = (String) request.getSession().getAttribute("ulogovanKorisnik");
        Korisnik korisnik = korisnikService.findByJbo(jbo);

        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }

    @GetMapping(value = "/neregistrovani", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Korisnik>> getNeregistrovane() {

        List<Korisnik> korisnici = korisnikService.findNeregistrovane();

        return new ResponseEntity<>(korisnici, HttpStatus.OK);
    }
}

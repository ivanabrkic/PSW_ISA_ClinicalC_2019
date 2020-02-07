package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private IUserService service;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event){
        Korisnik korisnik = event.getKorisnik();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(korisnik, token);

        String adresa = korisnik.getEmail();
        String subject = "Aktivacija naloga";
        String url = event.getAppUrl() + "/registrationConfirm?token=" + token;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(adresa);
        email.setSubject(subject);
        email.setText(korisnik.getIme() + ", uspešno ste se registrovali na na servis! \nKliknite na link ispod da " +
                "bi aktivirali vas nalog!\n" + "http://localhost:4200" + url);
        mailSender.send(email);
    }
}

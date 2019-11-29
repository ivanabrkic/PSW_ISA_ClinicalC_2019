package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.AdministratorKlinike;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.AdministratorKlinikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "administrator_k")
public class AdministratorKlinikeController {

    @Autowired
    private AdministratorKlinikeService adminKlinikeService;

    @GetMapping(value = "/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdministratorKlinike>> getAllAdmineKlinike() {

        List<AdministratorKlinike> adminK = adminKlinikeService.findAll();

        return new ResponseEntity<>(adminK, HttpStatus.OK);
    }

    @RequestMapping(value = "/registrationSubmitAdmin", method = RequestMethod.POST)
    public String Register(@RequestBody AdministratorKlinike admin){

        AdministratorKlinike adminUsername = adminKlinikeService.findByKorIme(admin.getKorIme());
        if(adminUsername != null){
            return "Korisničko ime je zauzeto";
        }

        AdministratorKlinike adminEmail = adminKlinikeService.findByEmail(admin.getEmail());
        if(adminEmail != null){
            return "Email je već u upotrebi!";
        }

        AdministratorKlinike noviAdmin = new AdministratorKlinike();
        noviAdmin.setKorIme(admin.getKorIme());
        noviAdmin.setEmail(admin.getEmail());
        noviAdmin.setLozinka(admin.getLozinka());
        noviAdmin.setIme(admin.getIme());
        noviAdmin.setPrezime(admin.getPrezime());
        noviAdmin.setAdresa(admin.getAdresa());
        noviAdmin.setGrad(admin.getGrad());
        noviAdmin.setDrzava(admin.getDrzava());
        noviAdmin.setJbo(admin.getJbo());
        noviAdmin.setKontaktTelefon(admin.getKontaktTelefon());
        noviAdmin.setAktivnostNaloga(true);
        noviAdmin.setTipKorisnika("Administrator klinike");

        adminKlinikeService.addAdminKlinike(noviAdmin);

        System.out.println(noviAdmin.getKorIme());

        return "Uspešno registrovan administrator!";
    }

    @Transactional
    @PostMapping(value = "/update", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdministratorKlinike> update(@RequestBody AdministratorKlinike administratorKlinike) throws Exception {

        adminKlinikeService.update(administratorKlinike);

        return new ResponseEntity<>(administratorKlinike, HttpStatus.OK);
    }
}

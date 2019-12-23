package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private KorisnikService korisnikService;

    @PostMapping(value = "/loginSubmit", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> Login(@RequestBody Korisnik kor, @Context HttpServletRequest request){

        //provera email-a i username-a:
        Korisnik korisnik = (Korisnik) korisnikService.findByEmailAndLozinka(kor.getEmail(), kor.getLozinka());
        if(korisnik == null){
            System.out.println("Wrong username or password");
            return new ResponseEntity<>(kor, HttpStatus.BAD_REQUEST);
        }

        System.out.println("Account with email " + korisnik.getEmail() + "has been logged in");

        //Sacuvati korisnika u sesiji
        request.getSession().setAttribute("ulogovanKorisnik", korisnik.getJbo());
        return new ResponseEntity<>(korisnik, HttpStatus.OK);

    }
    @PostMapping(value="/logoutSubmit")
    public void logout(@Context HttpServletRequest request){
        request.getSession().invalidate();
        System.out.println("logged out");
    }
}

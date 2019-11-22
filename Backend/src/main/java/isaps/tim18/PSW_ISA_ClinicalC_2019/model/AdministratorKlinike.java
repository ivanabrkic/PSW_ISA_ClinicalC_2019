package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="administrator_klinike")
public class AdministratorKlinike extends Korisnik {

    public AdministratorKlinike() {
    }

    public AdministratorKlinike(String grad, String drzava, String adresa, String korIme, String lozinka, String email, String kontaktTelefon, String ime, String prezime, String jbo, Boolean aktivnostNaloga) {
        super(grad, drzava, adresa, korIme, lozinka, email, kontaktTelefon, ime, prezime, jbo, aktivnostNaloga);
    }
}

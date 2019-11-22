package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pacijent")
public class Pacijent extends Korisnik{
    public Pacijent() {
    }

    public Pacijent(String grad, String drzava, String adresa, String korIme, String lozinka, String email, String kontaktTelefon, String ime, String prezime, String jbo, Boolean aktivnostNaloga) {
        super(grad, drzava, adresa, korIme, lozinka, email, kontaktTelefon, ime, prezime, jbo, aktivnostNaloga);
    }
}

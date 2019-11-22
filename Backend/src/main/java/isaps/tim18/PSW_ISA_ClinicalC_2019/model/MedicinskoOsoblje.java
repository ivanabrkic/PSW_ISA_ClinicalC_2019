package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MedicinskoOsoblje extends Korisnik{

    @Column
    private int brSlobodnihDana;

    public MedicinskoOsoblje() {
    }

    public MedicinskoOsoblje(int brSlobodnihDana) {
        this.brSlobodnihDana = brSlobodnihDana;
    }

    public MedicinskoOsoblje(String grad, String drzava, String adresa, String korIme, String lozinka, String email, String kontaktTelefon, String ime, String prezime, String jbo, Boolean aktivnostNaloga, int brSlobodnihDana) {
        super(grad, drzava, adresa, korIme, lozinka, email, kontaktTelefon, ime, prezime, jbo, aktivnostNaloga);
        this.brSlobodnihDana = brSlobodnihDana;
    }
}

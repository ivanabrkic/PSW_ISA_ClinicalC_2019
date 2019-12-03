package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
@Table(name="lekar")
@PrimaryKeyJoinColumn(name = "lekar_id")
public class Lekar extends Korisnik{

    @Column
    private int brSlobodnihDana;

    public int getBrSlobodnihDana() {
        return brSlobodnihDana;
    }

    public void setBrSlobodnihDana(int brSlobodnihDana) {
        this.brSlobodnihDana = brSlobodnihDana;
    }

    public Lekar() {
    }

    public Lekar(Korisnik korisnik) {
        super(korisnik);
    }
}

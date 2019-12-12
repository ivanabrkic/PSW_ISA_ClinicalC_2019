package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
@Table(name="medicinska_sestra")
@PrimaryKeyJoinColumn(name = "meds_id")
public class MedicinskaSestra extends Korisnik{

    @Column
    private int brSlobodnihDana;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="klinika_id")
    private Klinika klinika;

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
    }

    public int getBrSlobodnihDana() {
        return brSlobodnihDana;
    }

    public void setBrSlobodnihDana(int brSlobodnihDana) {
        this.brSlobodnihDana = brSlobodnihDana;
    }

    public MedicinskaSestra() {

    }
}

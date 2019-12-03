package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
@Table(name="medicinska_sestra")
@PrimaryKeyJoinColumn(name = "meds_id")
public class MedicinskaSestra extends Korisnik{

    @Column
    private int brSlobodnihDana;

    public int getBrSlobodnihDana() {
        return brSlobodnihDana;
    }

    public void setBrSlobodnihDana(int brSlobodnihDana) {
        this.brSlobodnihDana = brSlobodnihDana;
    }

    public MedicinskaSestra() {

    }
}

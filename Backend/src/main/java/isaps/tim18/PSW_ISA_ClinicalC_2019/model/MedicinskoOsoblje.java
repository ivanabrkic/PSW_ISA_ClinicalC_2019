package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MedicinskoOsoblje extends Korisnik{

    @Column
    private int brSlobodnihDana;

    public MedicinskoOsoblje() {
    }

    public MedicinskoOsoblje(int brSlobodnihDana) {
        this.brSlobodnihDana = brSlobodnihDana;
    }

}

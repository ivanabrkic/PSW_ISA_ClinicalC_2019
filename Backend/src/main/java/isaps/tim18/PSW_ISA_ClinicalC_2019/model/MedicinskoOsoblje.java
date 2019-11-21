package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MedicinskoOsoblje extends Korisnik{

    @Column
    private int brSlobodnihDana;

}

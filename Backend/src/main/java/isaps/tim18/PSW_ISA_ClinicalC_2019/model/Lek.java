package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

//@Entity
//@Table(name="lek")
public class Lek {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    String naziv;
}

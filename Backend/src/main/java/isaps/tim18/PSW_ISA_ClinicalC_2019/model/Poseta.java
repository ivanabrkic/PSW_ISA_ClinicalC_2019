package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;
import java.util.*;

//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Poseta {

    private String tipPosete;
    private int datum;
    private int vremeOd;
    private int vremeDo;
    private List<Lekar> listaLekara;
    private Izvestaj izvestaj;
    private Sala sala;
}

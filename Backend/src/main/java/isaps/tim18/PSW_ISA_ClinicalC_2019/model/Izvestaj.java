package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

//@Entity
//@Table(name="izvestaj")
public class Izvestaj {
    private List<Dijagnoza> dijagnoze;
    private List<Lek> lekovi;
    private Poseta sledecaPoseta;
    private String dodatniTekst;
}

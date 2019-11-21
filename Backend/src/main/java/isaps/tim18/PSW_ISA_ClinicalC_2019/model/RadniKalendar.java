package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

//@Entity
//@Table(name="radniKalendar")
public class RadniKalendar {

    private int datum;
    private int vremeOd;
    private int vremeDo;
    private List<Poseta> listaPoseta;
}

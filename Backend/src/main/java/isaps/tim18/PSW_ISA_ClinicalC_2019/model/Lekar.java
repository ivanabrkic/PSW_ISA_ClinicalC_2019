package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="lekar")
public class Lekar extends MedicinskoOsoblje{

    List<Poseta> zakazanePosete;

}

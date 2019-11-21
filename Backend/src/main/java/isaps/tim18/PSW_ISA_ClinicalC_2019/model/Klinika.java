package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

//@Entity
//@Table(name="klinika")
public class Klinika {

    List<AdministratorKlinike> listaAdministratora;
    List<MedicinskoOsoblje> listaOsoblja;
    List<Pacijent> listaPacijenata;
    List<Sala> listaSala;
    List<Poseta> zakazanePosete;
    List<Poseta> slobodniTermini;


}

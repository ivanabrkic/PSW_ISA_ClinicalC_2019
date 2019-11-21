package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

public class KlinickiCentar {

    AdministratorKlinickogCentra administratorKlinickogCentra;
    List<Klinika> listaKlinika;
    List<Lek> sifarnikLekova;
    List<Dijagnoza> sifarnikDijagnoza;

}

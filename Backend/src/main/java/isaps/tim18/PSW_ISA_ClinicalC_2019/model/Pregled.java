package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name="pregled")
public class Pregled extends Poseta{

    public Pregled() {
    }

    public Pregled(String tipPosete, String datum, String pocetak, String kraj, Sala sala, Izvestaj izvestaj, ArrayList<Lekar> lekari) {
        super(tipPosete, datum, pocetak, kraj, sala, izvestaj, lekari);
    }

}

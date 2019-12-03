package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
@Table(name="pacijent")
@PrimaryKeyJoinColumn(name = "pacijent_id")
public class Pacijent extends Korisnik{

    public Pacijent() {
    }

    public Pacijent(Korisnik korisnik) {
        super(korisnik);
    }


}

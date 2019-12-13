package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pacijent")
@PrimaryKeyJoinColumn(name = "pacijent_id")
public class Pacijent extends Korisnik{

    @OneToMany(mappedBy = "pacijent")
    private List<PacijentiKlinike> pacijentiKlinike = new ArrayList<>();

    public List<PacijentiKlinike> getPacijentiKlinike() {
        return pacijentiKlinike;
    }

    public void setPacijentiKlinike(List<PacijentiKlinike> pacijentiKlinike) {
        this.pacijentiKlinike = pacijentiKlinike;
    }

    public Pacijent() {
    }

    public Pacijent(Korisnik korisnik) {
        super(korisnik);
    }


}

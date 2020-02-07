package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="pacijent")
@PrimaryKeyJoinColumn(name = "pacijent_id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Pacijent extends Korisnik{

	@JsonIgnore
    @OneToMany(mappedBy = "pacijent")
    private List<PacijentiKlinike> pacijentiKlinike = new ArrayList<>();



    @JsonIgnore
    @OneToMany(mappedBy = "pacijent")
    private Set<oceneLekari> ocenelekara;


    @JsonIgnore
    @OneToMany(mappedBy="klinika")
    private Set<oceneKlinike> oceneklinika;

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

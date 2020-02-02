package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="zkarton_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ZdravstveniKarton zdravstveniKarton;


    public void setPacijentiKlinike(List<PacijentiKlinike> pacijentiKlinike) {
        this.pacijentiKlinike = pacijentiKlinike;
    }

    public Pacijent() {
    }

    public Pacijent(Korisnik korisnik, ZdravstveniKarton zdravstveniKarton) {
        super(korisnik);
        this.zdravstveniKarton = zdravstveniKarton;
    }

    public ZdravstveniKarton getZdravstveniKarton() {
        return zdravstveniKarton;
    }

    public void setZdravstveniKarton(ZdravstveniKarton zdravstveniKarton) {
        this.zdravstveniKarton = zdravstveniKarton;
    }
}

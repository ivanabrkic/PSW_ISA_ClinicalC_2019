package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
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

    public Pacijent(String lozinka, String email, String kontaktTelefon, String ime, String prezime, String jbo, boolean aktivnostNaloga, String grad, String drzava, String adresa, String tipKorisnika) {
        super(lozinka, email, kontaktTelefon, ime, prezime, jbo, aktivnostNaloga, grad, drzava, adresa, tipKorisnika);
    }

    public Pacijent(Korisnik korisnik) {
        super(korisnik);
    }

}

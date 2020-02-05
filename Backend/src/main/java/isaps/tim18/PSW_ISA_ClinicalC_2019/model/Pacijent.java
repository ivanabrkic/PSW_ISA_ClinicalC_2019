package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="pacijent")
@PrimaryKeyJoinColumn(name = "pacijent_id")
public class Pacijent extends Korisnik{

    @OneToMany(mappedBy = "pacijent")
    private List<PacijentiKlinike> pacijentiKlinike = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="oceneLekari",
            joinColumns = @JoinColumn(name="pacijent_id"),
            inverseJoinColumns = @JoinColumn(name="lekar_id")
    )
    Set<Lekar> ocenjeniLekariPacijenta;

    @OneToMany(mappedBy = "pacijent")
    Set<oceneLekari> ocenelekara;

    @ManyToMany@JoinTable(
            name="oceneKlinike",
            joinColumns=@JoinColumn(name="pacijent_id"),
            inverseJoinColumns=@JoinColumn(name="klinika_id")
    )
    Set<Klinika> ocenjeneKlinikePacijenta;

    @OneToMany(mappedBy="klinika")
    Set<oceneKlinike> oceneklinika;

    public List<PacijentiKlinike> getPacijentiKlinike() {
        return pacijentiKlinike;
    }

    public void setPacijentiKlinike(List<PacijentiKlinike> pacijentiKlinike) {
        this.pacijentiKlinike = pacijentiKlinike;
    }

    public Set<Lekar> getLekariPacijenta() {
        return ocenjeniLekariPacijenta;
    }

    public void setLekariPacijenta(Set<Lekar> lekariPacijenta) {
        this.ocenjeniLekariPacijenta = lekariPacijenta;
    }


    public Pacijent() {
    }

    public Pacijent(Korisnik korisnik) {
        super(korisnik);
    }


}

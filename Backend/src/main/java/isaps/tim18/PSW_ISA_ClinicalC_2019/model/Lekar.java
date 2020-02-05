package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="lekar")
@PrimaryKeyJoinColumn(name = "lekar_id")
public class Lekar extends Korisnik{

    @Column
    private int brSlobodnihDana;

    @Column (nullable = false)
    private String radnoVreme;

    @Column
    private float ocena;

    @Column
    private String specijalizacija;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="klinika_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Klinika klinika;


    @ManyToMany(mappedBy = "ocenjeniLekariPacijenta")
    Set<Pacijent> ocenePacijenta;


    @OneToMany(mappedBy = "lekar")
    Set<oceneLekari> ocene;

    public Set<Pacijent> getPacijenti() {
        return ocenePacijenta;
    }

    public void setPacijenti(Set<Pacijent> pacijenti) {
        this.ocenePacijenta = pacijenti;
    }


    public Lekar() {
    }

    public String getSpecijalizacija() {
        return specijalizacija;
    }

    public void setSpecijalizacija(String specijalizacija) {
        this.specijalizacija = specijalizacija;
    }

    public Lekar(Korisnik korisnik) {
        super(korisnik);
    }

    public String getRadnoVreme() {
        return radnoVreme;
    }

    public void setRadnoVreme(String radnoVreme) {
        this.radnoVreme = radnoVreme;
    }

    public float getOcena() {
        return ocena;
    }

    public void setOcena(float ocena) {
        this.ocena = ocena;
    }

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
    }

    public int getBrSlobodnihDana() {
        return brSlobodnihDana;
    }

    public void setBrSlobodnihDana(int brSlobodnihDana) {
        this.brSlobodnihDana = brSlobodnihDana;
    }

    @Override
    public String toString() {
        return "Lekar{" +
                "brSlobodnihDana=" + brSlobodnihDana +
                ", radnoVreme='" + radnoVreme + '\'' +
                ", ocena=" + ocena +
                ", klinika=" + klinika +
                '}';
    }
}

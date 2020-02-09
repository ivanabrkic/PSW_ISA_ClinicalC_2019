package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
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

    @Column(name="specijalizacija")
    private String specijalizacija;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="klinika_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Klinika klinika;


    @JsonIgnore
    @OneToMany(mappedBy = "lekar")
    private Set<oceneLekari> ocene;


    public Lekar() {
    }

    public Lekar(String lozinka, String email, String kontaktTelefon, String ime, String prezime, String jbo, boolean aktivnostNaloga, String grad, String drzava, String adresa, String tipKorisnika, int brSlobodnihDana, String radnoVreme, float ocena, String specijalizacija, Klinika klinika) {
        super(lozinka, email, kontaktTelefon, ime, prezime, jbo, aktivnostNaloga, grad, drzava, adresa, tipKorisnika);
        this.brSlobodnihDana = brSlobodnihDana;
        this.radnoVreme = radnoVreme;
        this.ocena = ocena;
        this.specijalizacija = specijalizacija;
        this.klinika = klinika;
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

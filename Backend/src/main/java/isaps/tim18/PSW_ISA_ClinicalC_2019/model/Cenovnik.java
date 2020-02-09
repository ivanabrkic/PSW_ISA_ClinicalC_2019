package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
public class Cenovnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="naziv",nullable = false)
    private String naziv;

    @Column(name="specijalizacija",nullable = false)
    private String specijalizacija;

    @Column(name="cena",nullable = false)
    private float cena;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "klinika_id", referencedColumnName = "id")
    private Klinika klinika;

    public Cenovnik() {
    }

    public Cenovnik(String naziv, float cena, Klinika klinika, String specijalizacija) {
        this.specijalizacija = specijalizacija;
        this.naziv = naziv;
        this.cena = cena;
        this.klinika = klinika;
    }

    public Cenovnik(String naziv, float cena, Klinika klinika) {
        this.naziv = naziv;
        this.cena = cena;
        this.klinika = klinika;
    }

    public Cenovnik(String naziv, String specijalizacija, float cena, Klinika klinika) {
        this.naziv = naziv;
        this.specijalizacija = specijalizacija;
        this.cena = cena;
        this.klinika = klinika;
    }

    public String getSpecijalizacija() {
        return specijalizacija;
    }

    public void setSpecijalizacija(String specijalizacija) {
        this.specijalizacija = specijalizacija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
    }

}

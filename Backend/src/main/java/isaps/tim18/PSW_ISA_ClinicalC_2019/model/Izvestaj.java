package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "izvestaj")
public class Izvestaj {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "izvestaj")
    private String izvestaj;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "zkarton", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ZdravstveniKarton zdravstveniKarton;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "recept", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Recept recept;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "lekar", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Lekar lekar;

    @Column(name = "datum_pregleda")
    private String datum;

    public Izvestaj(){

    }

    public Izvestaj(String izvestaj, ZdravstveniKarton zdravstveniKarton, Recept recept, Lekar lekar, String datum) {
        this.izvestaj = izvestaj;
        this.zdravstveniKarton = zdravstveniKarton;
        this.recept = recept;
        this.lekar = lekar;
        this.datum = datum;
    }

    public String getIzvestaj() {
        return izvestaj;
    }

    public void setIzvestaj(String izvestaj) {
        this.izvestaj = izvestaj;
    }

    public ZdravstveniKarton getZdravstveniKarton() {
        return zdravstveniKarton;
    }

    public void setZdravstveniKarton(ZdravstveniKarton zdravstveniKarton) {
        this.zdravstveniKarton = zdravstveniKarton;
    }

    public Recept getRecept() {
        return recept;
    }

    public void setRecept(Recept recept) {
        this.recept = recept;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
}

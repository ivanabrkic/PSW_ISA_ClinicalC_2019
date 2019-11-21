package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;
import java.util.*;

//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Poseta {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @Column(name="tipPosete", nullable = false)
    private String tipPosete;
    @Column(name="datum", nullable = false)
    private String datum;
    @Column(name="pocetak", nullable = false)
    private String pocetak;
    @Column(name="kraj", nullable = false)
    private String kraj;
    @Column(name="sala", nullable = false)
    private Sala sala;
    @Column(name="izvestaj")
    private Izvestaj izvestaj;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private ArrayList<Lekar> lekari = new ArrayList<Lekar>();

    @Override
    public String toString() {
        return "Poseta{" +
                "tipPosete='" + tipPosete + '\'' +
                ", datum='" + datum + '\'' +
                ", pocetak='" + pocetak + '\'' +
                ", kraj='" + kraj + '\'' +
                ", sala=" + sala +
                ", izvestaj=" + izvestaj +
                ", lekari=" + lekari +
                '}';
    }

    public Poseta() {
    }

    public Poseta(String tipPosete, String datum, String pocetak, String kraj, Sala sala, Izvestaj izvestaj, ArrayList<Lekar> lekari) {
        this.tipPosete = tipPosete;
        this.datum = datum;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.sala = sala;
        this.izvestaj = izvestaj;
        this.lekari = lekari;
    }

    public String getTipPosete() {
        return tipPosete;
    }

    public String getDatum() {
        return datum;
    }

    public String getPocetak() {
        return pocetak;
    }

    public String getKraj() {
        return kraj;
    }

    public Sala getSala() {
        return sala;
    }

    public Izvestaj getIzvestaj() {
        return izvestaj;
    }

    public void setTipPosete(String tipPosete) {
        this.tipPosete = tipPosete;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setPocetak(String pocetak) {
        this.pocetak = pocetak;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void setIzvestaj(Izvestaj izvestaj) {
        this.izvestaj = izvestaj;
    }
}

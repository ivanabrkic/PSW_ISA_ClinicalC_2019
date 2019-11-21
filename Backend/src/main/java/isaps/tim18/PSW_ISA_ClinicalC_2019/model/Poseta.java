package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Poseta {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @Column(name = "tipPosete", unique = false, nullable = false)
    private String tipPosete;

    @Column(name = "datum", unique = false, nullable = false)
    private String datum;

    @Column(name = "pocetak", unique = false, nullable = false)
    private String pocetak;

    @Column(name = "kraj", unique = false, nullable = false)
    private String kraj;

    public Poseta() {
    }

    public Poseta(String tipPosete, String datum, String pocetak, String kraj) {
        this.tipPosete = tipPosete;
        this.datum = datum;
        this.pocetak = pocetak;
        this.kraj = kraj;
    }

    public Integer getId() {
        return id;
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

    public void setId(Integer id) {
        this.id = id;
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
}
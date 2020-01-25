package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="posete")
@PrimaryKeyJoinColumn(name = "poseta_id")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Poseta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pacijent_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Pacijent pacijent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lekar_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Lekar lekar;

    @Column(name="datum",unique=false)
    private String datum;

    @Column(name="pocetak",unique=false)
    private String pocetak;

    @Column(name="kraj",unique=false)
    private String kraj;

    @Column(name="tipposete",unique=false)
    private String tipPosete;

    public Poseta(String d,String p, String k, String tipPosete){
        this.datum=d;
        this.pocetak=p;
        this.kraj=k;
        this.tipPosete=tipPosete;
    }

    public Poseta(){}

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getPocetak() {
        return pocetak;
    }

    public void setPocetak(String pocetak) {
        this.pocetak = pocetak;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public String getTipPosete() {
        return tipPosete;
    }

    public void setTipPosete(String tipPosete) {
        this.tipPosete = tipPosete;
    }

    public Long getPoseta_id() {
        return id;
    }

    public void setPoseta_id(Long poseta_id) {
        this.id = poseta_id;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }


}

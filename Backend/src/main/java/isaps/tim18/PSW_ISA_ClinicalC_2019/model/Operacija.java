package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Operacija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pacijent_id", referencedColumnName = "pacijent_id")
    Pacijent pacijent;

    @ManyToOne
    @JoinColumn(name = "lekar_id", referencedColumnName = "lekar_id")
    Lekar lekari;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "sala_id", referencedColumnName = "id")
    Sala sala;

    @Column(name="datum",unique=false)
    private String datum;

    @Column(name="pocetak",unique=false)
    private String pocetak;

    @Column(name="kraj",unique=false)
    private String kraj;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_stavke", referencedColumnName = "id")
    private Cenovnik cenovnik;

    @Column
    private String status;

    public Operacija() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Cenovnik getCenovnik() {
        return cenovnik;
    }

    public void setCenovnik(Cenovnik cenovnik) {
        this.cenovnik = cenovnik;
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

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public Lekar getLekari() {
        return lekari;
    }

    public void setLekari(Lekar lekari) {
        this.lekari = lekari;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public String toString() {
        return "Operacija{" +
                "operacijaId=" + id +
                ", pacijent=" + pacijent +
                ", sala=" + sala +
                '}';
    }
}

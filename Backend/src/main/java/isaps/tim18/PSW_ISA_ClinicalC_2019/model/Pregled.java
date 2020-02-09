package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Pregled {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "pacijent_id", referencedColumnName = "pacijent_id")
    Pacijent pacijent;

    @ManyToOne
    @JoinColumn(name = "lekar_id", referencedColumnName = "lekar_id")
    Lekar lekar;

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
    private Integer popust;

    @Column
    private String status;

    public Pregled(Long id, Pacijent pacijent, Lekar lekar, Sala sala, String datum, String pocetak, String kraj, Cenovnik cenovnik, Integer popust, String status) {
        this.id = id;
        this.pacijent = pacijent;
        this.lekar = lekar;
        this.sala = sala;
        this.datum = datum;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.cenovnik = cenovnik;
        this.popust = popust;
        this.status = status;
    }

    public Pregled(Pacijent pacijent, Lekar lekar, Sala sala, String datum, String pocetak, String kraj, Cenovnik cenovnik, Integer popust, String status) {
        this.pacijent = pacijent;
        this.lekar = lekar;
        this.sala = sala;
        this.datum = datum;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.cenovnik = cenovnik;
        this.popust = popust;
        this.status = status;
    }

    public Pregled() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPopust() {
        return popust;
    }

    public void setPopust(Integer popust) {
        this.popust = popust;
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

    public Cenovnik getCenovnik() {
        return cenovnik;
    }

    public void setCenovnik(Cenovnik cenovnik) {
        this.cenovnik = cenovnik;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public String toString() {
        return "Pregled{" +
                "pregledId=" + id +
//                ", lekarVremeId=" + lekarVreme +
//                ", pacijentVremeId=" + pacijentVreme +
                ", pacijent=" + pacijent +
                ", lekar=" + lekar +
                ", sala=" + sala +
                '}';
    }
}

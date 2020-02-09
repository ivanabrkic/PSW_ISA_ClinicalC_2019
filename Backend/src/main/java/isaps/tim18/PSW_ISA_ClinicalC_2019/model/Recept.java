package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recept")
public class Recept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pacijent_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Pacijent pacijent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="med_sestra", nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MedicinskaSestra medicinskaSestra;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, targetEntity = Lekovi.class)
    @JoinTable(name = "recept_lekovi", joinColumns = @JoinColumn(name = "recept_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "lek_id", referencedColumnName = "id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Lekovi> lekovi = new ArrayList<Lekovi>();
//
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "izvestaj", referencedColumnName = "id")
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    private Izvestaj izvestaj;

    @Column(name = "overen", nullable = false)
    private boolean overen;

    public Recept(){

    }

    public Recept(boolean overen, Pacijent pacijent, ArrayList<Lekovi> lekovi, MedicinskaSestra medicinskaSestra) {
        this.overen = overen;
        this.pacijent = pacijent;
        this.lekovi = lekovi;
        this.medicinskaSestra = medicinskaSestra;
        //this.izvestaj = izvestaj;
    }

    public boolean isOveren() {
        return overen;
    }

    public void setOveren(boolean overen) {
        this.overen = overen;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Lekovi> getLekovi() {
        return lekovi;
    }

    public void setLekovi(List<Lekovi> lekovi) {
        this.lekovi = lekovi;
    }

    public MedicinskaSestra getMedicinskaSestra() {
        return medicinskaSestra;
    }

    public void setMedicinskaSestra(MedicinskaSestra medicinskaSestra) {
        this.medicinskaSestra = medicinskaSestra;
    }

//    public Izvestaj getIzvestaj() {
//        return izvestaj;
//    }
//
//    public void setIzvestaj(Izvestaj izvestaj) {
//        this.izvestaj = izvestaj;
//    }
}

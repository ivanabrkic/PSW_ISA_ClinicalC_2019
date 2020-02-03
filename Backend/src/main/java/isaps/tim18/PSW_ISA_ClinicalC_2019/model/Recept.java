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

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Lekovi> lekovi = new ArrayList<Lekovi>();

    @Column(name = "overen", nullable = false)
    private boolean overen;

    public Recept(){

    }

    public Recept(boolean overen, Pacijent pacijent, ArrayList<Lekovi> lekovi, int broj) {
        this.overen = overen;
        this.pacijent = pacijent;
        this.lekovi = lekovi;
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

    public void setLekovi(ArrayList<Lekovi> lekovi) {
        this.lekovi = lekovi;
    }
}

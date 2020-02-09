package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="zkarton")
public class ZdravstveniKarton {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pacijent_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Pacijent pacijent;

    @JsonIgnore
    @OneToMany(mappedBy="zkarton")
    private Set<ZkartonDijagnoze> dijagnoze;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(name = "zkartoni_opsti_izvestaji", joinColumns = @JoinColumn(name = "zkarton"),
            inverseJoinColumns = @JoinColumn(name = "opsti_izvestaj_id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private OpstiIzvestaj opstiIzvestaj;

    @Column(name = "broj_zk")
    private int broj_zk;

    public ZdravstveniKarton(){

    }

    public ZdravstveniKarton(Pacijent pacijent, Set<ZkartonDijagnoze> dijagnoze, OpstiIzvestaj opstiIzvestaj, int broj_zk) {
        this.pacijent = pacijent;
        this.dijagnoze = dijagnoze;
        this.opstiIzvestaj = opstiIzvestaj;
        this.broj_zk = broj_zk;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ZkartonDijagnoze> getDijagnoze() {
        return dijagnoze;
    }

    public void setDijagnoze(Set<ZkartonDijagnoze> dijagnoze) {
        this.dijagnoze = dijagnoze;
    }

    public OpstiIzvestaj getOpstiIzvestaj() {
        return opstiIzvestaj;
    }

    public void setOpstiIzvestaj(OpstiIzvestaj opstiIzvestaj) {
        this.opstiIzvestaj = opstiIzvestaj;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public int getBroj_zk() {
        return broj_zk;
    }

    public void setBroj_zk(int broj_zk) {
        this.broj_zk = broj_zk;
    }
}

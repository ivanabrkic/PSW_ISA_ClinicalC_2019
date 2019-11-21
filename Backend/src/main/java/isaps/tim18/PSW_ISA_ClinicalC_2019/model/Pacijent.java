package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

//@Entity
//@Table(name="pacijent")
public class Pacijent extends Korisnik{

    @Column(name="zdravstveniKarton",unique = true,nullable = false)
    private ZdravstveniKarton zdravstveniKarton;
    @OneToMany(mappedBy="pacijent", cascade=ALL) //sa izmenom i brisanjem korisnika menjaju se i pregledi
    private List<Pregled> zakazaniPregledi;

    public Pacijent(){

    }

    public Pacijent(ZdravstveniKarton zdravstveniKarton, List<Pregled> zakazaniPregledi){
        this.zdravstveniKarton=zdravstveniKarton;
        this.zakazaniPregledi=zakazaniPregledi;
    }

    public ZdravstveniKarton getZdravstveniKarton() {
        return zdravstveniKarton;
    }

    public void setZdravstveniKarton(ZdravstveniKarton zdravstveniKarton) {
        this.zdravstveniKarton = zdravstveniKarton;
    }

    public List<Pregled> getZakazaniPregledi() {
        return zakazaniPregledi;
    }

    public void setZakazaniPregledi(List<Pregled> zakazaniPregledi) {
        this.zakazaniPregledi = zakazaniPregledi;
    }
}

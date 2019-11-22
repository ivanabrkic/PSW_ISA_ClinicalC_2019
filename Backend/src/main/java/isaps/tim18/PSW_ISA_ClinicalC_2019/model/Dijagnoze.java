package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="dijagnoze")
public class Dijagnoze {

    @Column(name="sifra", nullable = false)
    private int sifra;

    @Column(name="nazivDijagnoze", nullable = false)
    private String nazivDijagnoze;

    public Dijagnoze(){

    }

    public Dijagnoze(int sifra, String nazivDijagnoze) {
        this.sifra = sifra;
        this.nazivDijagnoze = nazivDijagnoze;
    }

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public String getNazivDijagnoze() {
        return nazivDijagnoze;
    }

    public void setNazivDijagnoze(String nazivDijagnoze) {
        this.nazivDijagnoze = nazivDijagnoze;
    }
}

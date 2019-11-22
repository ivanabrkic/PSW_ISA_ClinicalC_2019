package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="lekovi")
public class Lekovi {

    @Column(name="sifra", nullable = false)
    private int sifra;

    @Column(name="nazivLeka", nullable = false)
    private String nazivLeka;

    public Lekovi(){
    }

    public Lekovi(int sifra, String naziv) {
        this.sifra = sifra;
        this.nazivLeka = naziv;
    }

    public int getSifra() {
        return sifra;
    }

    public String getLek() {
        return nazivLeka;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public void setLek(String nazivLeka) {
        this.nazivLeka = nazivLeka;
    }
}

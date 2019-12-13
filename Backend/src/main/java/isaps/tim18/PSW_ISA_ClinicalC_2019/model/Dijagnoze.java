package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
@Embeddable
@Table(name="dijagnoze")
public class Dijagnoze {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sifra;

    @Column(name="naziv", nullable = false)
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

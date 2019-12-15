package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
@Table(name="lekovi")
public class Lekovi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="sifra", nullable = false)
    private String sifra;

    @Column(name="naziv", nullable = false)
    private String naziv;

    public Lekovi(){
    }

    public Lekovi(String sifra, String naziv) {
        this.sifra = sifra;
        this.naziv = naziv;
    }

    public String getSifra() {
        return sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public void setNaziv(String nazivLeka) {
        this.naziv = nazivLeka;
    }
}

package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
@Table(name = "ZahtevOdsustvo")
public class ZahtevOdsustvo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lekar", nullable = false)
    private Lekar lekar;

    @Column(name = "odDatum", nullable = false)
    private String datumOd;

    @Column(name = "doDatum", nullable = false)
    private String datumDo;

    @Column(name = "opis", nullable = true)
    private String opis;

    @Column(name = "broj_dana", nullable = false)
    private int brojDana;

    @Column(name = "overen", nullable = false)
    private boolean overen;

    public ZahtevOdsustvo(Lekar lekar, String datumOd, String datumDo, String opis, int brojDana, boolean overen) {
        this.lekar = lekar;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.opis = opis;
        this.brojDana = brojDana;
        this.overen = overen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public String getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(String datumOd) {
        this.datumOd = datumOd;
    }

    public String getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(String datumDo) {
        this.datumDo = datumDo;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getBrojDana() {
        return brojDana;
    }

    public void setBrojDana(int brojDana) {
        this.brojDana = brojDana;
    }

    public boolean isOveren() {
        return overen;
    }

    public void setOveren(boolean overen) {
        this.overen = overen;
    }
}

package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
@Table(name = "ZahtevOdsustvo")
public class ZahtevOdsustvo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jbo", nullable = false)
    private String korisnikJbo;

    @Column(name = "ime", nullable = false)
    private String korisnikIme;

    @Column(name = "prezime", nullable = false)
    private String korisnikPrezime;

    @Column(name = "uloga", nullable = false)
    private String korisnikUloga;

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

    public ZahtevOdsustvo(String korisnikJbo, String korisnikIme, String korisnikPrezime, String korisnikUloga, String datumOd, String datumDo, String opis, int brojDana, boolean overen) {
        this.korisnikJbo = korisnikJbo;
        this.korisnikIme = korisnikIme;
        this.korisnikPrezime = korisnikPrezime;
        this.korisnikUloga = korisnikUloga;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.opis = opis;
        this.brojDana = brojDana;
        this.overen = overen;
    }

    public String getKorisnikJbo() {
        return korisnikJbo;
    }

    public void setKorisnikJbo(String korisnikJbo) {
        this.korisnikJbo = korisnikJbo;
    }

    public String getKorisnikIme() {
        return korisnikIme;
    }

    public void setKorisnikIme(String korisnikIme) {
        this.korisnikIme = korisnikIme;
    }

    public String getKorisnikPrezime() {
        return korisnikPrezime;
    }

    public void setKorisnikPrezime(String korisnikPrezime) {
        this.korisnikPrezime = korisnikPrezime;
    }

    public String getKorisnikUloga() {
        return korisnikUloga;
    }

    public void setKorisnikUloga(String korisnikUloga) {
        this.korisnikUloga = korisnikUloga;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

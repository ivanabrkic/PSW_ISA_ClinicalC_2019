package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
@Table(name = "zahtev_odsustvo")
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

    @Column(name = "overen")
    private boolean overen;

    @Column(name = "klinika_id")
    private Long klinikaId;

    @Column(name = "razlog")
    private String razlog;

    public ZahtevOdsustvo() {
    }

    public Long getKlinikaId() {
        return klinikaId;
    }

    public void setKlinikaId(Long klinikaId) {
        this.klinikaId = klinikaId;
    }

    public String getRazlog() {
        return razlog;
    }

    public void setRazlog(String razlog) {
        this.razlog = razlog;
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

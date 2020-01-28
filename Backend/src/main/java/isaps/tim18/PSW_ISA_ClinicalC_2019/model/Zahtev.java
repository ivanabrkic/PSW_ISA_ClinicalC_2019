package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
public class Zahtev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="tipPosete",nullable = false)
    private String tipPosete;

    @Column(name="tipPosiljaoca",nullable = false)
    private String tipPosiljaoca;

    @Column(name="posiljalacJbo",nullable = false)
    private String posiljalacJbo;

    @Column(name="posiljalacImePrezime",nullable = false)
    private String posiljalacImePrezime;

    @Column(name="jboPacijenta",nullable = false)
    private String jboPacijenta;

    @Column(name="jboLekara",nullable = false)
    private String jboLekara;

    @Column(name="datum",nullable = false)
    private String datum;

    @Column(name="pocetak",nullable = false)
    private String pocetak;

    @Column(name="kraj",nullable = false)
    private String kraj;

    @Column(name="dodatneInformacije",nullable = false)
    private String dodatneInformacije;

    @Column(name="id_klinike",nullable = false)
    private Long idKlinike;

    public Zahtev() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdKlinike() {
        return idKlinike;
    }

    public void setIdKlinike(Long idKlinike) {
        this.idKlinike = idKlinike;
    }

    public String getPosiljalacImePrezime() {
        return posiljalacImePrezime;
    }

    public void setPosiljalacImePrezime(String posiljalacImePrezime) {
        this.posiljalacImePrezime = posiljalacImePrezime;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getPocetak() {
        return pocetak;
    }

    public void setPocetak(String pocetak) {
        this.pocetak = pocetak;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public String getTipPosete() {
        return tipPosete;
    }

    public void setTipPosete(String tipPosete) {
        this.tipPosete = tipPosete;
    }

    public String getTipPosiljaoca() {
        return tipPosiljaoca;
    }

    public void setTipPosiljaoca(String tipPosiljaoca) {
        this.tipPosiljaoca = tipPosiljaoca;
    }

    public String getPosiljalacJbo() {
        return posiljalacJbo;
    }

    public void setPosiljalacJbo(String posiljalacJbo) {
        this.posiljalacJbo = posiljalacJbo;
    }

    public String getJboPacijenta() {
        return jboPacijenta;
    }

    public void setJboPacijenta(String jboPacijenta) {
        this.jboPacijenta = jboPacijenta;
    }

    public String getJboLekara() {
        return jboLekara;
    }

    public void setJboLekara(String jboLekara) {
        this.jboLekara = jboLekara;
    }

    public String getDodatneInformacije() {
        return dodatneInformacije;
    }

    public void setDodatneInformacije(String dodatneInformacije) {
        this.dodatneInformacije = dodatneInformacije;
    }
}

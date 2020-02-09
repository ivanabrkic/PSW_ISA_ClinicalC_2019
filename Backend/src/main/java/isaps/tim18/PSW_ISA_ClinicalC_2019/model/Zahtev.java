  
package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
public class Zahtev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="tipPosete",nullable = false)
    private String tipPosete;

    @Column(name="id_stavke",nullable = false)
    private Long idStavke;

    @Column(name="stavka",nullable = false)
    private String stavkaCenovnika;

    @Column(name="tip_posiljaoca",nullable = false)
    private String tipPosiljaoca;

    @Column(name="posiljalacJbo",nullable = false)
    private String posiljalacJbo;

    @Column(name="posiljalacImePrezime",nullable = false)
    private String posiljalacImePrezime;

    @Column(name="jboPacijenta",nullable = false)
    private String jboPacijenta;

    @Column(name="jbo_lekara",nullable = false)
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


    //Konstruktor za test//

    public Zahtev(String tipPosete, Long idStavke, String stavkaCenovnika, String tipPosiljaoca, String posiljalacJbo, String posiljalacImePrezime, String jboPacijenta, String jboLekara, String datum, String pocetak, String kraj, String dodatneInformacije, Long idKlinike) {
        this.tipPosete = tipPosete;
        this.idStavke = idStavke;
        this.stavkaCenovnika = stavkaCenovnika;
        this.tipPosiljaoca = tipPosiljaoca;
        this.posiljalacJbo = posiljalacJbo;
        this.posiljalacImePrezime = posiljalacImePrezime;
        this.jboPacijenta = jboPacijenta;
        this.jboLekara = jboLekara;
        this.datum = datum;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.dodatneInformacije = dodatneInformacije;
        this.idKlinike = idKlinike;
    }
    //


    public String getTipPosiljaoca() {
        return tipPosiljaoca;
    }

    public void setTipPosiljaoca(String tipPosiljaoca) {
        this.tipPosiljaoca = tipPosiljaoca;
    }

    public String getJboLekara() {
        return jboLekara;
    }

    public void setJboLekara(String jboLekara) {
        this.jboLekara = jboLekara;
    }

    public Long getIdStavke() {
        return idStavke;
    }

    public void setIdStavke(Long idStavke) {
        this.idStavke = idStavke;
    }

    public String getStavkaCenovnika() {
        return stavkaCenovnika;
    }

    public void setStavkaCenovnika(String stavkaCenovnika) {
        this.stavkaCenovnika = stavkaCenovnika;
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

    public String getDodatneInformacije() {
        return dodatneInformacije;
    }

    public void setDodatneInformacije(String dodatneInformacije) {
        this.dodatneInformacije = dodatneInformacije;
    }

}

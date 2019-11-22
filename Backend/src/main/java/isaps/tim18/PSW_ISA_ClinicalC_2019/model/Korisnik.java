package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @Column(name="grad",unique=false)
    private String grad;

    @Column(name="drzava",unique = false)
    private String drzava;

    @Column(name="adresa",unique = false)
    private String adresa;

    @Column(name="korIme", unique=true, nullable = false)
    private String korIme;

    @Column(name="lozinka", nullable = false)
    private String lozinka;

    @Column(name="email",unique = true,nullable = false)
    private String email;

    @Column(name="kontaktTelefon")
    private String kontaktTelefon;

    @Column(name="ime", nullable = false)
    private String ime;

    @Column(name="prezime", nullable = false)
    private String prezime;

    @Column(name="jbo",nullable = false)
    private String jbo;

    @Column(name="aktivnostNaloga",nullable = false)
    private Boolean aktivnostNaloga;

    public Korisnik() {
    }

    public Korisnik(String korIme, String lozinka, String email, String kontaktTelefon, String ime, String prezime,String jbo, boolean aktivnostNaloga,String grad,String drzava,String adresa) {
        this.korIme = korIme;
        this.lozinka = lozinka;
        this.email = email;
        this.kontaktTelefon = kontaktTelefon;
        this.ime = ime;
        this.prezime = prezime;
        this.jbo=jbo;
        this.adresa=adresa;
        this.drzava=drzava;
        this.aktivnostNaloga=aktivnostNaloga;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKorIme() {
        return korIme;
    }

    public void setKorIme(String korIme) {
        this.korIme = korIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKontaktTelefon() {
        return kontaktTelefon;
    }

    public Boolean getAktivnostNaloga() {
        return aktivnostNaloga;
    }

    public void setAktivnostNaloga(Boolean aktivnostNaloga){
        this.aktivnostNaloga=aktivnostNaloga;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getJbo() {
        return jbo;
    }

    public void setJbo(String jbo) {
        this.jbo = jbo;
    }

    public void setKontaktTelefon(String kontaktTelefon) {
        this.kontaktTelefon = kontaktTelefon;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}

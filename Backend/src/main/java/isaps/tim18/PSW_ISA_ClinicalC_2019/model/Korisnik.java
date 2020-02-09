package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Korisnik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="grad",unique=false)
    private String grad;

    @Column(name="drzava",unique = false)
    private String drzava;

    @Column(name="adresa",unique = false)
    private String adresa;

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

    @Column(name="jbo",nullable = false, unique = true, length = 13)
    private String jbo;

    @Column(name="aktivnostNaloga",nullable = false)
    private Boolean aktivnostNaloga;

    @Column(name="tipKorisnika",nullable = false)
    private String tipKorisnika;

    @Column(name = "prvoLogovanje")
    private Boolean prvoLogovanje;

    public Korisnik() {
        super();
    }

    public Korisnik(String lozinka, String email, String kontaktTelefon, String ime, String prezime,String jbo, boolean aktivnostNaloga,String grad,String drzava,String adresa, String tipKorisnika) {
        super();
        this.lozinka = lozinka;
        this.email = email;
        this.kontaktTelefon = kontaktTelefon;
        this.ime = ime;
        this.prezime = prezime;
        this.jbo=jbo;
        this.grad = grad;
        this.adresa=adresa;
        this.drzava=drzava;
        this.aktivnostNaloga=aktivnostNaloga;
        this.grad=grad;
        this.tipKorisnika = tipKorisnika;
        this.prvoLogovanje = true;
    }

    public Korisnik(Korisnik korisnik){
        super();
        this.lozinka = korisnik.lozinka;
        this.email = korisnik.email;
        this.kontaktTelefon = korisnik.kontaktTelefon;
        this.ime = korisnik.ime;
        this.prezime = korisnik.prezime;
        this.jbo=korisnik.jbo;
        this.grad = korisnik.grad;
        this.adresa=korisnik.adresa;
        this.drzava=korisnik.drzava;
        this.aktivnostNaloga=korisnik.aktivnostNaloga;
        this.grad=korisnik.grad;
        this.tipKorisnika = korisnik.tipKorisnika;
        this.prvoLogovanje = korisnik.prvoLogovanje;
    }

    public Korisnik(String string, String string2) {
		this.setEmail(string);
		this.setLozinka(string2);
	}

	public String getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(String tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean isPrvoLogovanje() {
        return prvoLogovanje;
    }

    public void setPrvoLogovanje(Boolean prvoLogovanje) {
        this.prvoLogovanje = prvoLogovanje;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
                ", grad='" + grad + '\'' +
                ", drzava='" + drzava + '\'' +
                ", adresa='" + adresa + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", email='" + email + '\'' +
                ", kontaktTelefon='" + kontaktTelefon + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", jbo='" + jbo + '\'' +
                ", aktivnostNaloga=" + aktivnostNaloga +
                ", tipKorisnika='" + tipKorisnika + '\'' +
                '}';
    }

    public static KorisnikBuilder builder() {
        return new KorisnikBuilder();
    }
}

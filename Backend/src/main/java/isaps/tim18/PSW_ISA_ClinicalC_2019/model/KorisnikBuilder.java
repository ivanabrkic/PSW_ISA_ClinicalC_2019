package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

public class KorisnikBuilder {

    private String grad;
    private String drzava;
    private String adresa;
    private String korIme;
    private String lozinka;
    private String email;
    private String kontaktTelefon;
    private String ime;
    private String prezime;
    private String jbo;
    private Boolean aktivnostNaloga;
    private String tipKorisnika;


    public KorisnikBuilder() {
    }

    public KorisnikBuilder(Korisnik korisnik) {
        this.grad = korisnik.getGrad();
        this.drzava = korisnik.getDrzava();
        this.adresa = korisnik.getAdresa();
        this.korIme = korisnik.getKorIme();
        this.lozinka = korisnik.getLozinka();
        this.email = korisnik.getEmail();
        this.kontaktTelefon = korisnik.getKontaktTelefon();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.jbo = korisnik.getJbo();
        this.aktivnostNaloga = korisnik.getAktivnostNaloga();
        this.tipKorisnika = korisnik.getTipKorisnika();

    }

    public KorisnikBuilder grad(String grad) {
        this.grad = grad;
        return this;
    }

    public KorisnikBuilder drzava(String drzava) {
        this.drzava = drzava;
        return this;
    }

    public KorisnikBuilder adresa(String adresa) {
        this.adresa = adresa;
        return this;
    }

    public KorisnikBuilder korIme(String korIme) {
        this.korIme = korIme;
        return this;
    }

    public KorisnikBuilder lozinka(String lozinka) {
        this.lozinka = lozinka;
        return this;
    }

    public KorisnikBuilder email(String email) {
        this.email = email;
        return this;
    }

    public KorisnikBuilder kontaktTelefon(String kontaktTelefon) {
        this.kontaktTelefon = kontaktTelefon;
        return this;
    }

    public KorisnikBuilder ime(String ime) {
        this.ime = ime;
        return this;
    }

    public KorisnikBuilder prezime(String prezime) {
        this.prezime = prezime;
        return this;
    }

    public KorisnikBuilder aktivnostNaloga(boolean aktivnostNaloga) {
        this.aktivnostNaloga = aktivnostNaloga;
        return this;
    }

    public KorisnikBuilder tipKorisnika(String tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
        return this;
    }

    public KorisnikBuilder jbo(String jbo) {
        this.jbo = jbo;
        return this;
    }

    public Korisnik build() {
        Korisnik korisnik = new Korisnik();
        korisnik.setGrad(grad);
        korisnik.setDrzava(drzava);
        korisnik.setAdresa(adresa);
        korisnik.setIme(ime);
        korisnik.setPrezime(prezime);
        korisnik.setKontaktTelefon(kontaktTelefon);
        korisnik.setEmail(email);
        korisnik.setKorIme(korIme);
        korisnik.setLozinka(lozinka);
        korisnik.setJbo(jbo);
        korisnik.setTipKorisnika(tipKorisnika);
        korisnik.setAktivnostNaloga(aktivnostNaloga);

        return korisnik;
    }
}


package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

public class HelperUserClass {
    private String grad;
    private String drzava;
    private String adresa;
    private String lozinka;
    private String email;
    private String kontaktTelefon;
    private String ime;
    private String prezime;
    private String jbo;
    private Boolean aktivnostNaloga;
    private String tipKorisnika;
    private String klinika;

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

    public String getJbo() {
        return jbo;
    }

    public void setJbo(String jbo) {
        this.jbo = jbo;
    }

    public Boolean getAktivnostNaloga() {
        return aktivnostNaloga;
    }

    public void setAktivnostNaloga(Boolean aktivnostNaloga) {
        this.aktivnostNaloga = aktivnostNaloga;
    }

    public String getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(String tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }

    public String getKlinika() {
        return klinika;
    }

    public void setKlinika(String klinika) {
        this.klinika = klinika;
    }

    @Override
    public String toString() {
        return "HelperUserClass{" +
                "grad='" + grad + '\'' +
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
                ", klinika='" + klinika + '\'' +
                '}';
    }
}

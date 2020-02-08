package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name="klinika")
public class Klinika implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="naziv",unique=false, nullable = false)
    private String naziv;

    @Column(name="adresa",unique=false)
    private String adresa;

    @Column(name="grad",unique=false)
    private String grad;

    @Column(name="drzava",unique=false)
    private String drzava;

    @Column(name="email",unique=true)
    private String email;

    @Column(name="kontaktTelefon",unique=false)
    private String kontaktTelefon;

    @Column(name="ocena",unique=false)
    private float ocena;
    
    @Column(name="opis",unique=false)
    private String opis;

	@JsonIgnore
    @OneToMany(mappedBy = "klinika")
    Set<oceneKlinike> ocene;
    
    public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Set<oceneKlinike> getOcene() {
		return ocene;
	}

	public void setOcene(Set<oceneKlinike> ocene) {
		this.ocene = ocene;
	}



    public Klinika(){

    }

    public Klinika(String naziv, String adresa, String grad, String drzava, String email, String kontaktTelefon, float ocena, String opis) {
        this.naziv = naziv;
        this.adresa = adresa;
        this.grad = grad;
        this.drzava = drzava;
        this.email = email;
        this.kontaktTelefon = kontaktTelefon;
        this.ocena = ocena;
        this.opis=opis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
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

    public float getOcena() {
        return ocena;
    }

    public void setOcena(float ocena) {
        this.ocena = ocena;
    }

    @Override
    public String toString() {
        return "Klinika{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", adresa='" + adresa + '\'' +
                ", grad='" + grad + '\'' +
                ", drzava='" + drzava + '\'' +
                ", email='" + email + '\'' +
                ", kontaktTelefon='" + kontaktTelefon + '\'' +
                ", ocena=" + ocena +
                '}';
    }
}

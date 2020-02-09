package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "token")
    private String token;

    @OneToOne(targetEntity = Korisnik.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "korisnik")
    private Korisnik korisnik;

    public VerificationToken(String token, Korisnik korisnik) {
        this.token = token;
        this.korisnik = korisnik;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
}

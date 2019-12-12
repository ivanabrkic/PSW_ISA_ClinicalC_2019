package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
@Table(name="administrator_klinike")
@PrimaryKeyJoinColumn(name = "admink_id")
public class AdministratorKlinike extends Korisnik{

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="klinika_id")
    private Klinika klinika;

    public AdministratorKlinike() {
    }

    public AdministratorKlinike(Korisnik korisnik) {
        super(korisnik);
    }

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
    }
}

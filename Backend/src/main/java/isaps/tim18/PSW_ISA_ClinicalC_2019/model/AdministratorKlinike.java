package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
@Table(name="administrator_klinike")
@PrimaryKeyJoinColumn(name = "admink_id")
public class AdministratorKlinike extends Korisnik{

    public AdministratorKlinike() {
    }

    public AdministratorKlinike(Korisnik korisnik) {
        super(korisnik);
    }

}

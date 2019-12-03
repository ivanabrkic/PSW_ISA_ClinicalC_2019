package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
@Table(name="administrator_klinickog_centra")
@PrimaryKeyJoinColumn(name = "adminkc_id")
public class AdministratorKlinickogCentra extends Korisnik{

    public AdministratorKlinickogCentra() {
    }

}

package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "meds_id")
public class GodisnjiOdmorMedSestra extends MedicinskaSestra {

    @Column
    private LocalDate datumOd;

    @Column
    private LocalDate datumDo;

    public GodisnjiOdmorMedSestra() {
    }

    public LocalDate getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(LocalDate datumOd) {
        this.datumOd = datumOd;
    }

    public LocalDate getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(LocalDate datumDo) {
        this.datumDo = datumDo;
    }
}

package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "lekar_id")
public class GodisnjiOdmorLekar extends Lekar{

    @Column
    private LocalDate datumOd;

    @Column
    private LocalDate datumDo;

    public GodisnjiOdmorLekar() {
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

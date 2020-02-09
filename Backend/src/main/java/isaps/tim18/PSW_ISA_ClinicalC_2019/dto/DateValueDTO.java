package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

import java.time.LocalDateTime;

public class DateValueDTO {

    private LocalDateTime vreme;
    private float vrednost;

    public DateValueDTO() {
    }

    public DateValueDTO(LocalDateTime vreme, float vrednost) {
        this.vreme = vreme;
        this.vrednost = vrednost;
    }

    public LocalDateTime getVreme() {
        return vreme;
    }

    public void setVreme(LocalDateTime vreme) {
        this.vreme = vreme;
    }

    public float getVrednost() {
        return vrednost;
    }

    public void setVrednost(float vrednost) {
        this.vrednost = vrednost;
    }
}

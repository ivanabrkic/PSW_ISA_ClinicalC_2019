package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

public class DateValueDTO {

    private String vreme;
    private float vrednost;

    public DateValueDTO() {
    }

    public DateValueDTO(String vreme, float vrednost) {
        this.vreme = vreme;
        this.vrednost = vrednost;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    public float getVrednost() {
        return vrednost;
    }

    public void setVrednost(float vrednost) {
        this.vrednost = vrednost;
    }
}

package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

public class LekarTrajanjeDTO {

    private Long idLekara;

    private Integer trajanje;

    public LekarTrajanjeDTO() {
    }

    public LekarTrajanjeDTO(Long idLekara, Integer trajanje) {
        this.idLekara = idLekara;
        this.trajanje = trajanje;
    }

    public Long getIdLekara() {
        return idLekara;
    }

    public void setIdLekara(Long idLekara) {
        this.idLekara = idLekara;
    }

    public Integer getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(Integer trajanje) {
        this.trajanje = trajanje;
    }
}

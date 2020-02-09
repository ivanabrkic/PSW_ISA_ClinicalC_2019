package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

public class posetaIdTipDTO {

    private Long idPosete;
    private String tipPosete;

    public posetaIdTipDTO(Long idPosete, String tipPosete) {
        this.idPosete = idPosete;
        this.tipPosete = tipPosete;
    }

    public Long getIdPosete() {
        return idPosete;
    }

    public void setIdPosete(Long idPosete) {
        this.idPosete = idPosete;
    }

    public String getTipPosete() {
        return tipPosete;
    }

    public void setTipPosete(String tipPosete) {
        this.tipPosete = tipPosete;
    }
}

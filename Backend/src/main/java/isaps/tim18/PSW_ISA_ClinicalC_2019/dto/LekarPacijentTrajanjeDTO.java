package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

public class LekarPacijentTrajanjeDTO {

    Long idLekara;
    Long idPacijenta;
    int trajanje;

    public LekarPacijentTrajanjeDTO() {
    }

    public Long getIdLekara() {
        return idLekara;
    }

    public void setIdLekara(Long idLekara) {
        this.idLekara = idLekara;
    }

    public Long getIdPacijenta() {
        return idPacijenta;
    }

    public void setIdPacijenta(Long idPacijenta) {
        this.idPacijenta = idPacijenta;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }
}

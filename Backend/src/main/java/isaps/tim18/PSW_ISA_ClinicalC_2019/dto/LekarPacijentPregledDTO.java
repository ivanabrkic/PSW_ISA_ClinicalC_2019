package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

public class LekarPacijentPregledDTO {
    String jboLekara;
    String jboPacijenta;

    public LekarPacijentPregledDTO(String jboLekara, String jboPacijenta) {
        this.jboLekara = jboLekara;
        this.jboPacijenta = jboPacijenta;
    }

    public String getJboLekara() {
        return jboLekara;
    }

    public void setJboLekara(String jboLekara) {
        this.jboLekara = jboLekara;
    }

    public String getJboPacijenta() {
        return jboPacijenta;
    }

    public void setJboPacijenta(String jboPacijenta) {
        this.jboPacijenta = jboPacijenta;
    }
}

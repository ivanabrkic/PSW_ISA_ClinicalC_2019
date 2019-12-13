package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

public class HelperRejectedMail {
    private String email;
    private String jbo;
    private String poruka;

    public String getKorisnikovEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJbo() {
        return jbo;
    }

    public void setJbo(String jbo) {
        this.jbo = jbo;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String message) {
        this.poruka = message;
    }
}

package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

public class EmailDTO {
    private String subject;
    private String email;
    private String text;

    public EmailDTO() {
    }

    public EmailDTO(String subject, String email, String text) {
        this.subject = subject;
        this.email = email;
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

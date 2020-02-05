package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

public class IzvestajDTO {

    private Long id;
    private Long idLekara;
    private String izvestaj;
    private Long idZdravKart;
    private Long idRecept;
    private String datum;

    public IzvestajDTO(Long id, Long idLekara, String izvestaj, Long idZdravKart, Long idRecept, String datum) {
        this.id = id;
        this.idLekara = idLekara;
        this.izvestaj = izvestaj;
        this.idZdravKart = idZdravKart;
        this.idRecept = idRecept;
        this.datum = datum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdLekara() {
        return idLekara;
    }

    public void setidLekara(Long jboLekara) {
        this.idLekara = jboLekara;
    }

    public String getIzvestaj() {
        return izvestaj;
    }

    public void setIzvestaj(String izvestaj) {
        this.izvestaj = izvestaj;
    }

    public Long getIdZdravKart() {
        return idZdravKart;
    }

    public void setIdZdravKart(Long idZdravKart) {
        this.idZdravKart = idZdravKart;
    }

    public Long getIdRecept() {
        return idRecept;
    }

    public void setIdRecept(Long idRecept) {
        this.idRecept = idRecept;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
}

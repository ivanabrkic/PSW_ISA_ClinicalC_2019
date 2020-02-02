package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

public class lekariterminiDTO {
    private String datum;
    private String start;
    private String finis;
    private String specijalizacija;
    private Long idKlinike;

    public lekariterminiDTO(String datum,String start,String finis,String specijalizacija,Long idKlinike){
        this.datum=datum;
        this.start=start;
        this.finis=finis;
        this.specijalizacija=specijalizacija;
        this.idKlinike=idKlinike;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinis() {
        return finis;
    }

    public void setFinis(String finis) {
        this.finis = finis;
    }

    public String getSpecijalizacija() {
        return specijalizacija;
    }

    public void setSpecijalizacija(Long tip) {
        this.specijalizacija = specijalizacija;
    }

    public Long getIdKlinike() {
        return idKlinike;
    }

    public void setIdKlinike(Long idKlinike) {
        this.idKlinike = idKlinike;
    }


}

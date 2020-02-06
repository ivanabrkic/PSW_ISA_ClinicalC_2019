package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Sala;

import java.util.List;

public class TerminDTO {

    private String datum;
    private String pocetak;
    private String kraj;
    private List<Sala> sale;

    public TerminDTO() {
    }

    public TerminDTO(String datum, String pocetak, String kraj, String nazivSale) {
        this.datum = datum;
        this.pocetak = pocetak;
        this.kraj = kraj;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getPocetak() {
        return pocetak;
    }

    public void setPocetak(String pocetak) {
        this.pocetak = pocetak;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public List<Sala> getSale() {
        return sale;
    }

    public void setSale(List<Sala> sale) {
        this.sale = sale;
    }
}

package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

public class OperacijaKalendarDTO {
    private String jboPacijenta;

    private String datum;

    private String pocetak;

    private String kraj;

    private String imePacijenta;

    private String prezimePacijenta;

    public OperacijaKalendarDTO(String jboPacijenta, String datum, String pocetak, String kraj, String imePacijenta, String prezimePacijenta) {
        this.jboPacijenta = jboPacijenta;
        this.datum = datum;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.imePacijenta = imePacijenta;
        this.prezimePacijenta = prezimePacijenta;
    }

    public String getJboPacijenta() {
        return jboPacijenta;
    }

    public void setJboPacijenta(String jboPacijenta) {
        this.jboPacijenta = jboPacijenta;
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

    public String getImePacijenta() {
        return imePacijenta;
    }

    public void setImePacijenta(String imePacijenta) {
        this.imePacijenta = imePacijenta;
    }

    public String getPrezimePacijenta() {
        return prezimePacijenta;
    }

    public void setPrezimePacijenta(String prezimePacijenta) {
        this.prezimePacijenta = prezimePacijenta;
    }
}

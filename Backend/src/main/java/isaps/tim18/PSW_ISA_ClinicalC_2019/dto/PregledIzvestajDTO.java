package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

public class PregledIzvestajDTO {
    private Long id;

    private String jboPacijenta;

    private String datum;

    private String pocetak;

    private String kraj;

    private String jboLekara;

    private String imeLekara;

    private String prezimeLekara;

    public PregledIzvestajDTO(Long id, String jboPacijenta, String datum, String pocetak, String kraj, String jboLekara, String imeLekara, String prezimeLekara) {
        this.id = id;
        this.jboPacijenta = jboPacijenta;
        this.datum = datum;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.jboLekara = jboLekara;
        this.imeLekara = imeLekara;
        this.prezimeLekara = prezimeLekara;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getJboLekara() {
        return jboLekara;
    }

    public void setJboLekara(String jboLekara) {
        this.jboLekara = jboLekara;
    }

    public String getImeLekara() {
        return imeLekara;
    }

    public void setImeLekara(String imeLekara) {
        this.imeLekara = imeLekara;
    }

    public String getPrezimeLekara() {
        return prezimeLekara;
    }

    public void setPrezimeLekara(String prezimeLekara) {
        this.prezimeLekara = prezimeLekara;
    }
}

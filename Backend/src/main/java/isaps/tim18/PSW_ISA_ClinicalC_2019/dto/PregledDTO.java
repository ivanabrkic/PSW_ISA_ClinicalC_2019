package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

public class PregledDTO {

    private String jboPacijenta;

    private String datum;

    private String pocetak;

    private String kraj;

    private String jboLekara;

    private String tipPregleda;

    private Long tipId;

    private Long salaId;

    private Integer popust;

    private String status;

    public PregledDTO() {
    }

    public PregledDTO(String tipPregleda, String jboPacijenta, String jboLekara, String datum, String pocetak, String kraj) {
        this.tipPregleda = tipPregleda;
        this.jboPacijenta = jboPacijenta;
        this.datum = datum;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.jboLekara = jboLekara;
    }

    public PregledDTO(String jboPacijenta, String datum, String pocetak, String kraj, String jboLekara, String tipPregleda, Long tipId, Long salaId, Integer popust, String status) {
        this.jboPacijenta = jboPacijenta;
        this.datum = datum;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.jboLekara = jboLekara;
        this.tipPregleda = tipPregleda;
        this.tipId = tipId;
        this.salaId = salaId;
        this.popust = popust;
        this.status = status;
    }

    public PregledDTO(String tipPregleda, String jboLekara, String datum, String pocetak, String kraj) {
        this.tipPregleda = tipPregleda;
        this.datum = datum;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.jboLekara = jboLekara;
    }

    public Integer getPopust() {
        return popust;
    }

    public void setPopust(Integer popust) {
        this.popust = popust;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSalaId() {
        return salaId;
    }

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
    }

    public String getTipPregleda() {
        return tipPregleda;
    }

    public void setTipPregleda(String tipPregleda) {
        this.tipPregleda = tipPregleda;
    }

    public Long getTipId() {
        return tipId;
    }

    public void setTipId(Long tipId) {
        this.tipId = tipId;
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
}

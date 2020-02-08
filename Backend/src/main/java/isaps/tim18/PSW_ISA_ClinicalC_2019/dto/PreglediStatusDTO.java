package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

public class PreglediStatusDTO {
    private Long id;

    private String jboPacijenta;

    private String datum;

    private String pocetak;

    private String kraj;

    private String status;

    public PreglediStatusDTO(Long id, String jboPacijenta, String datum, String pocetak, String kraj, String status) {
        this.id = id;
        this.jboPacijenta = jboPacijenta;
        this.datum = datum;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.status = status;
    }

    public PreglediStatusDTO(Long id, String datum, String pocetak, String kraj, String status) {
        this.id = id;
        this.datum = datum;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

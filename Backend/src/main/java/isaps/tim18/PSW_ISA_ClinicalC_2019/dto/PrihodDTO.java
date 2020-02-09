package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

public class PrihodDTO {

    private String pocetak;
    private String kraj;
    private Long idKlinike;
    private float prihod;

    public PrihodDTO() {
    }

    public PrihodDTO(String pocetak, String kraj, Long idKlinike, float prihod) {
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.idKlinike = idKlinike;
        this.prihod = prihod;
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

    public Long getIdKlinike() {
        return idKlinike;
    }

    public void setIdKlinike(Long idKlinike) {
        this.idKlinike = idKlinike;
    }

    public float getPrihod() {
        return prihod;
    }

    public void setPrihod(float prihod) {
        this.prihod = prihod;
    }
}

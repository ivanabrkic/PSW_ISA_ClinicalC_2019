package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

import java.util.ArrayList;
import java.util.List;

public class OperacijaDTO {

    private String jboPacijenta;

    private String datum;

    private String pocetak;

    private String kraj;

    private List<String> jboLekara;

    private String tipPregleda;

    private Long tipId;

    private Long salaId;

    public OperacijaDTO() {
    }

    public OperacijaDTO(String tipPregleda, String jboPacijenta, String datum, String pocetak, String kraj) {
        this.tipPregleda = tipPregleda;
        this.jboPacijenta = jboPacijenta;
        this.datum = datum;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.jboLekara = new ArrayList<String>();
    }

    public OperacijaDTO(String jboPacijenta, String datum, String pocetak, String kraj, List<String> jboLekara, String tipPregleda, Long tipId, Long salaId) {
        this.jboPacijenta = jboPacijenta;
        this.datum = datum;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.jboLekara = jboLekara;
        this.tipPregleda = tipPregleda;
        this.tipId = tipId;
        this.salaId = salaId;
    }

    public Long getSalaId() {
        return salaId;
    }

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
    }

    public Long getTipId() {
        return tipId;
    }

    public void setTipId(Long tipId) {
        this.tipId = tipId;
    }

    public String getTipPregleda() {
        return tipPregleda;
    }

    public void setTipPregleda(String tipPregleda) {
        this.tipPregleda = tipPregleda;
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

    public List<String> getJboLekara() {
        return jboLekara;
    }

    public void setJboLekara(List<String> jboLekara) {
        this.jboLekara = jboLekara;
    }
}

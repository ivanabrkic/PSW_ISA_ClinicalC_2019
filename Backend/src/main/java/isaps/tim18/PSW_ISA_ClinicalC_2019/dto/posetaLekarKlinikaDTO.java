package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;

import java.util.ArrayList;
import java.util.List;

public class posetaLekarKlinikaDTO {

    Long id;
    String tipPosete;
    Lekar lekar;
    Long klinikaId;
    String klinikaNaziv;
    Float klinikaOcena;
    Float lekarOcena;

    public posetaLekarKlinikaDTO( Lekar lekar, Long klinikaId, String klinikaNaziv, Float klinikaOcena, Float lekarOcena) {
        this.id = id;
        this.tipPosete = tipPosete;
        this.klinikaId = klinikaId;
        this.klinikaNaziv = klinikaNaziv;
        this.klinikaOcena = klinikaOcena;
        this.lekarOcena = lekarOcena;
        this.lekar=lekar;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public posetaLekarKlinikaDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipPosete() {
        return tipPosete;
    }

    public void setTipPosete(String tipPosete) {
        this.tipPosete = tipPosete;
    }

    public Long getKlinikaId() {
        return klinikaId;
    }

    public void setKlinikaId(Long klinikaId) {
        this.klinikaId = klinikaId;
    }

    public String getKlinikaNaziv() {
        return klinikaNaziv;
    }

    public void setKlinikaNaziv(String klinikaNaziv) {
        this.klinikaNaziv = klinikaNaziv;
    }

    public Float getKlinikaOcena() {
        return klinikaOcena;
    }

    public void setKlinikaOcena(Float klinikaOcena) {
        this.klinikaOcena = klinikaOcena;
    }

    public Float getLekarOcena() {
        return lekarOcena;
    }

    public void setLekarOcena(Float lekarOcena) {
        this.lekarOcena = lekarOcena;
    }


}

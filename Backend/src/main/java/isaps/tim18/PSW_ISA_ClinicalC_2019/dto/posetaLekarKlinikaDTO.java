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

    String datum;
    String pocetak;
    String kraj;

    public posetaLekarKlinikaDTO(Long id, Lekar lekar, Long klinikaId, String klinikaNaziv, Float klinikaOcena, Float lekarOcena,String stavka,String datum,String pocetak,String kraj) {
        this.klinikaId = klinikaId;
        this.klinikaNaziv = klinikaNaziv;
        this.klinikaOcena = klinikaOcena;
        this.lekarOcena = lekarOcena;
        this.lekar=lekar;
        this.tipPosete=stavka;
        this.datum=datum;
        this.pocetak=pocetak;
        this.kraj=kraj;
        this.id=id;
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


	public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public posetaLekarKlinikaDTO(Long id){this.id=id;}

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

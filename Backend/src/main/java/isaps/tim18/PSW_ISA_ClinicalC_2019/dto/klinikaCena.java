package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;

public class klinikaCena {
	
	private Klinika klinika;
	public klinikaCena(Klinika klinika, Float cena) {
		super();
		this.klinika = klinika;
		this.cena = cena;
	}
	public Klinika getKlinika() {
		return klinika;
	}
	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}
	public Float getCena() {
		return cena;
	}
	public void setCena(Float cena) {
		this.cena = cena;
	}
	private Float cena;

}

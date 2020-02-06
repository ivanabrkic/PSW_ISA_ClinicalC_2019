package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;

public class SalaDTO {
    private Long id;
    private String naziv;
    private String broj;
    private Klinika klinika;
    private String datumSlobodna;
    private String pocetakSlobodna;
    private String krajSlobodna;


    public SalaDTO() {
    }

    public SalaDTO(Long id, String naziv, String broj, Klinika klinika, String datumSlobodna, String pocetakSlobodna, String krajSlobodna) {
        this.id = id;
        this.naziv = naziv;
        this.broj = broj;
        this.klinika = klinika;
        this.datumSlobodna = datumSlobodna;
        this.pocetakSlobodna = pocetakSlobodna;
        this.krajSlobodna = krajSlobodna;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
    }

    public String getDatumSlobodna() {
        return datumSlobodna;
    }

    public void setDatumSlobodna(String datumSlobodna) {
        this.datumSlobodna = datumSlobodna;
    }

    public String getPocetakSlobodna() {
        return pocetakSlobodna;
    }

    public void setPocetakSlobodna(String pocetakSlobodna) {
        this.pocetakSlobodna = pocetakSlobodna;
    }

    public String getKrajSlobodna() {
        return krajSlobodna;
    }

    public void setKrajSlobodna(String krajSlobodna) {
        this.krajSlobodna = krajSlobodna;
    }
}

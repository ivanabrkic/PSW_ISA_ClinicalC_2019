package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.Embeddable;

@Embeddable
public class Dijagnoza {
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    private String naziv;
}

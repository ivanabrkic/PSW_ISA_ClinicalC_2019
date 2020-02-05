package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

@Entity
public class oceneKlinike {

    @EmbeddedId
    oceneKlinikeKljuc id;

    @ManyToOne
    @MapsId("pacijent_id")
    @JoinColumn(name="pacijent_id")
    Pacijent pacijent;

    @ManyToOne
    @MapsId("klinika_id")
    @JoinColumn(name="klinika_id")
    Klinika klinika;

    int ocena;

    public oceneKlinike(oceneKlinikeKljuc id, Pacijent pacijent, Klinika klinika, int ocena) {
        this.id = id;
        this.pacijent = pacijent;
        this.klinika = klinika;
        this.ocena = ocena;
    }

    public oceneKlinikeKljuc getId() {
        return id;
    }

    public void setId(oceneKlinikeKljuc id) {
        this.id = id;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}

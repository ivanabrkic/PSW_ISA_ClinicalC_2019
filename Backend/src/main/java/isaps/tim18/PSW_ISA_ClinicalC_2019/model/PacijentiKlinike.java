package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class PacijentiKlinike {

    @EmbeddedId
    PacijentiKlinikeKey id;

    @JsonIgnore
    @ManyToOne
    @MapsId("pacijent_id")
    @JoinColumn(name = "pacijent_id")
    Pacijent pacijent;

    @JsonIgnore
    @ManyToOne
    @MapsId("klinika_id")
    @JoinColumn(name = "klinika_id")
    Klinika klinika;

    public PacijentiKlinike() {
    }

    public PacijentiKlinike(PacijentiKlinikeKey id, Pacijent pacijent, Klinika klinika) {
        this.id = id;
        this.pacijent = pacijent;
        this.klinika = klinika;
    }

    public PacijentiKlinikeKey getId() {
        return id;
    }

    public void setId(PacijentiKlinikeKey id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PacijentiKlinike that = (PacijentiKlinike) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(pacijent, that.pacijent) &&
                Objects.equals(klinika, that.klinika);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pacijent, klinika);
    }

    @Override
    public String toString() {
        return "PacijentiKlinike{" +
                "id=" + id +
                ", pacijent=" + pacijent +
                ", klinika=" + klinika +
                '}';
    }
}

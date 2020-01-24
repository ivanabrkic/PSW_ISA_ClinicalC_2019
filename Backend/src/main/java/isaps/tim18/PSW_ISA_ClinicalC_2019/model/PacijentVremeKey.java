package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PacijentVremeKey implements Serializable {

    @Column(name="datum",nullable = false, length = 11)
    private String datum;

    @Column(name="sat",nullable = false, length = 2)
    private String sat;

    @Column(name="minut",nullable = false, length = 2)
    private String minut;

    @Column(name="pacijent_id",nullable = false)
    private Long pacijentId;

    public PacijentVremeKey() {
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getSat() {
        return sat;
    }

    public void setSat(String sat) {
        this.sat = sat;
    }

    public String getMinut() {
        return minut;
    }

    public void setMinut(String minut) {
        this.minut = minut;
    }

    public Long getPacijentId() {
        return pacijentId;
    }

    public void setPacijentId(Long pacijentId) {
        this.pacijentId = pacijentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PacijentVremeKey that = (PacijentVremeKey) o;
        return Objects.equals(datum, that.datum) &&
                Objects.equals(sat, that.sat) &&
                Objects.equals(minut, that.minut) &&
                Objects.equals(pacijentId, that.pacijentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datum, sat, minut, pacijentId);
    }

    @Override
    public String toString() {
        return "PacijentVremeKey{" +
                "datum='" + datum + '\'' +
                ", sat='" + sat + '\'' +
                ", minut='" + minut + '\'' +
                ", pacijentId=" + pacijentId +
                '}';
    }
}

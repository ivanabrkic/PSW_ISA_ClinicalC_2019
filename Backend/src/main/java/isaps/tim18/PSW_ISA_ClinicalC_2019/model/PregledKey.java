package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PregledKey implements Serializable{

    @Column(name="sala_id",nullable = false)
    private Long salaId;

    @Column(name="datum",nullable = false, length = 11)
    private String datum;

    @Column(name="sat",nullable = false, length = 2)
    private String sat;

    @Column(name="minut",nullable = false, length = 2)
    private String minut;

    public PregledKey() {
    }

    public Long getSalaId() {
        return salaId;
    }

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PregledKey that = (PregledKey) o;
        return Objects.equals(salaId, that.salaId) &&
                Objects.equals(datum, that.datum) &&
                Objects.equals(sat, that.sat) &&
                Objects.equals(minut, that.minut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salaId, datum, sat, minut);
    }

    @Override
    public String toString() {
        return "PregledKey{" +
                "salaId=" + salaId +
                ", datum='" + datum + '\'' +
                ", sat='" + sat + '\'' +
                ", minut='" + minut + '\'' +
                '}';
    }
}

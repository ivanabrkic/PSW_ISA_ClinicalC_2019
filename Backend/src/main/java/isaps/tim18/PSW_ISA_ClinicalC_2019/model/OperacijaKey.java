package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OperacijaKey implements Serializable {

    @Column(name="sala_id",nullable = false)
    private Long salaId;

    @Column(name="datum_op",nullable = false, length = 11)
    private String datum;

    @Column(name="sat_op",nullable = false, length = 2)
    private String sat;

    @Column(name="minut_op",nullable = false, length = 2)
    private String minut;

    @Column(name="lekar_id",nullable = false)
    private Long lekarId;

    public OperacijaKey() {
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

    public Long getLekarId() {
        return lekarId;
    }

    public void setLekarId(Long lekarId) {
        this.lekarId = lekarId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperacijaKey that = (OperacijaKey) o;
        return
                Objects.equals(datum, that.datum) &&
                Objects.equals(sat, that.sat) &&
                Objects.equals(minut, that.minut) &&
                Objects.equals(lekarId, that.lekarId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datum, sat, minut, lekarId);
    }

    @Override
    public String toString() {
        return "OperacijaKey{" +

                ", datum='" + datum + '\'' +
                ", sat='" + sat + '\'' +
                ", minut='" + minut + '\'' +
                ", lekarId=" + lekarId +
                '}';
    }
}

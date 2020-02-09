package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PacijentiKlinikeKey implements Serializable {

	
    @Column(name = "pacijent_id")
    Long pacijentId;

    @Column(name = "klinika_id")
    Long klinikaId;

    public PacijentiKlinikeKey() {
    }

    public PacijentiKlinikeKey(Long pacijentId, Long klinikaId) {
        this.pacijentId = pacijentId;
        this.klinikaId = klinikaId;
    }

    public Long getPacijentId() {
        return pacijentId;
    }

    public void setPacijentId(Long pacijentId) {
        this.pacijentId = pacijentId;
    }

    public Long getKlinikaId() {
        return klinikaId;
    }

    public void setKlinikaId(Long klinikaId) {
        this.klinikaId = klinikaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PacijentiKlinikeKey that = (PacijentiKlinikeKey) o;
        return Objects.equals(pacijentId, that.pacijentId) &&
                Objects.equals(klinikaId, that.klinikaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pacijentId, klinikaId);
    }

    @Override
    public String toString() {
        return "PacijentiKlinikeKey{" +
                "pacijentId=" + pacijentId +
                ", klinikaId=" + klinikaId +
                '}';
    }
}

package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class oceneKlinikeKljuc  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4258572701809223623L;
	
    @Column(name = "pacijent_id")
    Long pacijentId;

    @Column(name="klinika_id")
    Long klinikaId;
    

	public oceneKlinikeKljuc(Long pacijentId, Long klinikaId) {
        this.pacijentId = pacijentId;
        this.klinikaId = klinikaId;
    }

    public oceneKlinikeKljuc() {}

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
        oceneKlinikeKljuc that = (oceneKlinikeKljuc) o;
        return pacijentId.equals(that.pacijentId) &&
                klinikaId.equals(that.klinikaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pacijentId, klinikaId);
    }

}

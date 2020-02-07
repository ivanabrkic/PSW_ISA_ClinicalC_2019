package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class oceneLekariKljuc implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8996446703802611699L;

	@Column(name = "pacijent_id")
    Long pacijentId;

    @Column(name="lekar_id")
    Long lekarId;
    
    public oceneLekariKljuc() {}

    public oceneLekariKljuc(Long pacijentId, Long lekarId) {
        this.pacijentId = pacijentId;
        this.lekarId = lekarId;
    }

    public Long getPacijentId() {
        return pacijentId;
    }

    public void setPacijentId(Long pacijentId) {
        this.pacijentId = pacijentId;
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
        oceneLekariKljuc that = (oceneLekariKljuc) o;
        return pacijentId.equals(that.pacijentId) &&
                lekarId.equals(that.lekarId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pacijentId, lekarId);
    }
}

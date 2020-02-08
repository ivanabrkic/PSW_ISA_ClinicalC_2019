package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Embeddable
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class ZkartonDijagnozeKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5057722098631206886L;

	@Column(name = "dijagnoze_id")
    Long dijagnozeId;

    @Column(name="zkarton_id")
    Long zKartonId;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dijagnozeId == null) ? 0 : dijagnozeId.hashCode());
		result = prime * result + ((zKartonId == null) ? 0 : zKartonId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ZkartonDijagnozeKey other = (ZkartonDijagnozeKey) obj;
		if (dijagnozeId == null) {
			if (other.dijagnozeId != null)
				return false;
		} else if (!dijagnozeId.equals(other.dijagnozeId))
			return false;
		if (zKartonId == null) {
			if (other.zKartonId != null)
				return false;
		} else if (!zKartonId.equals(other.zKartonId))
			return false;
		return true;
	}

	public ZkartonDijagnozeKey(Long dijagnozeId, Long zKartonId) {
		super();
		this.dijagnozeId = dijagnozeId;
		this.zKartonId = zKartonId;
	}

	public Long getDijagnozeId() {
		return dijagnozeId;
	}

	public void setDijagnozeId(Long dijagnozeId) {
		this.dijagnozeId = dijagnozeId;
	}

	public Long getzKartonId() {
		return zKartonId;
	}

	public void setzKartonId(Long zKartonId) {
		this.zKartonId = zKartonId;
	}
}

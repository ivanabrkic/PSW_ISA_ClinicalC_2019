package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
public class ZkartonDijagnoze {

	@EmbeddedId
	ZkartonDijagnozeKey id;
	
	@JsonIgnore
    @ManyToOne
    @MapsId("dijagnoze_id")
    @JoinColumn(name="dijagnoze_id")
    Dijagnoze dijagnoze;
	
	@JsonIgnore
    @ManyToOne
    @MapsId("zkarton_id")
    @JoinColumn(name="zkarton_id")
	ZdravstveniKarton zkarton;

	public ZkartonDijagnozeKey getId() {
		return id;
	}

	public void setId(ZkartonDijagnozeKey id) {
		this.id = id;
	}

	public Dijagnoze getDijagnoza() {
		return dijagnoze;
	}

	public void setDijagnoza(Dijagnoze dijagnoza) {
		this.dijagnoze = dijagnoza;
	}

	public ZdravstveniKarton getZkarton() {
		return zkarton;
	}

	public void setZkarton(ZdravstveniKarton zkarton) {
		this.zkarton = zkarton;
	}

	public ZkartonDijagnoze(ZkartonDijagnozeKey id, Dijagnoze dijagnoza, ZdravstveniKarton zkarton) {
		super();
		this.id = id;
		this.dijagnoze = dijagnoza;
		this.zkarton = zkarton;
	}
	
	
}

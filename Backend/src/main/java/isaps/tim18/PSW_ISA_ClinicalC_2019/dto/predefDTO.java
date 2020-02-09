package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

public class predefDTO {
	
	Long id;
	
	Long pacijent_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPacijent_id() {
		return pacijent_id;
	}

	public void setPacijent_id(Long pacijent_id) {
		this.pacijent_id = pacijent_id;
	}
	
	public predefDTO(Long id,Long pacId){
		this.id=id;
		this.pacijent_id=pacId;
	}

	public predefDTO() {
		// TODO Auto-generated constructor stub
	}

}

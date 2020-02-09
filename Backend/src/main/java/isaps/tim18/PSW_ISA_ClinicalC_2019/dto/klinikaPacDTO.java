package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

public class klinikaPacDTO {
	
	Long idPac;
	Long idKlin;
	
	public klinikaPacDTO(Long k,Long p){
		this.idPac=p;
		this.idKlin=k;
		
	}

	public Long getIdPac() {
		return idPac;
	}

	public void setIdPac(Long idPac) {
		this.idPac = idPac;
	}

	public Long getIdKlin() {
		return idKlin;
	}

	public void setIdKlin(Long idKlin) {
		this.idKlin = idKlin;
	}

}

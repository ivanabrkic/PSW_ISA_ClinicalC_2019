package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.oceneKlinike;

@Repository 

public interface KlinikeOceneRepository extends JpaRepository<oceneKlinike,Long>{
	
	List<oceneKlinike> findAll();
	
	oceneKlinike findByPacijentIdAndKlinikaId(Long id,Long idk);

	List<oceneKlinike> findByKlinikaId(Long idKlinike);
}

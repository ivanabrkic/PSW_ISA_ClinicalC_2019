package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.oceneLekari;

@Repository
public interface LekariOceneRepository extends JpaRepository<oceneLekari, Long>{
	
	List<oceneLekari> findAll();
	
	List<oceneLekari> findByLekarId(Long id);
	
	oceneLekari findByPacijentIdAndLekarId(Long id,Long idk);

}

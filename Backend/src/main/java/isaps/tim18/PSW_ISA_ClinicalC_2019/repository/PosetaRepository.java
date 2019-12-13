package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Poseta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosetaRepository  extends JpaRepository<Poseta, Long> {

    List<Poseta> findAll();

    Poseta findById(int id);

    List<Poseta> findByPacijent_id(int id);
}

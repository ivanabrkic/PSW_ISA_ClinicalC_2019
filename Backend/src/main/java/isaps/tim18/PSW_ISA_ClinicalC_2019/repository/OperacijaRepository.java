package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Operacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperacijaRepository  extends JpaRepository<Operacija, Long> {

    void deleteBySalaId(Long id);

    List<Operacija> findBySalaId(Long id);
}

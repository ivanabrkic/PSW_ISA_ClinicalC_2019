package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.OpstiIzvestaj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpstiIzvestajRepository extends JpaRepository<OpstiIzvestaj, Long> {

    public List<OpstiIzvestaj> findAll();

    public Optional<OpstiIzvestaj> findById(Long id);
}

package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.OpstiIzvestaj;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.ZdravstveniKarton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpstiIzvestajRepository extends JpaRepository<OpstiIzvestaj, Long> {

    public List<OpstiIzvestaj> findAll();

    public Optional<OpstiIzvestaj> findById(Long id);

    @Query(nativeQuery = true, value = "SELECT opsti_izvestaj_id FROM zkarton JOIN zkartoni_opsti_izvestaji " +
            "ON zkarton.id = zkartoni_opsti_izvestaji.zkarton WHERE zkarton.id = id")
    public Long findByZkarton(Long id);
}

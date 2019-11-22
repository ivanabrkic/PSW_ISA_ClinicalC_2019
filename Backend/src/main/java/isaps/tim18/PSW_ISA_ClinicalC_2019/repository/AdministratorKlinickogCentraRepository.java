package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.AdministratorKlinickogCentra;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.AdministratorKlinike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdministratorKlinickogCentraRepository extends JpaRepository<AdministratorKlinickogCentra, Long> {

    Page<AdministratorKlinickogCentra> findAll(Pageable pageable);

    List<AdministratorKlinickogCentra> findAllByIme(String ime);

    List<AdministratorKlinickogCentra> findAllByPrezime(String prezime);

    List<AdministratorKlinickogCentra> findByImeAndPrezimeAllIgnoringCase(String ime, String prezime);

}

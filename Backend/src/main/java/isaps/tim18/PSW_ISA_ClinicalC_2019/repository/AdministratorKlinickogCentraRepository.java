package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.AdministratorKlinickogCentra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministratorKlinickogCentraRepository extends JpaRepository<AdministratorKlinickogCentra, Long> {

    Page<AdministratorKlinickogCentra> findAll(Pageable pageable);

    List<AdministratorKlinickogCentra> findAllByIme(String ime);

    List<AdministratorKlinickogCentra> findAllByPrezime(String prezime);

    List<AdministratorKlinickogCentra> findByImeAndPrezimeAllIgnoringCase(String ime, String prezime);

    AdministratorKlinickogCentra findByJbo(String jbo);

    AdministratorKlinickogCentra findByEmail(String email);

    AdministratorKlinickogCentra findByEmailAndLozinka(String email, String lozinka);

}

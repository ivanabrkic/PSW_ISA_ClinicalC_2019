package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.AdministratorKlinike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministratorKlinikeRepository extends JpaRepository<AdministratorKlinike, Long> {

    Page<AdministratorKlinike> findAll(Pageable pageable);

    AdministratorKlinike findAllByKorIme(String korisnickoIme);

    List<AdministratorKlinike> findAllByIme(String ime);

    List<AdministratorKlinike> findAllByPrezime(String prezime);

    List<AdministratorKlinike> findByImeAndPrezimeAllIgnoringCase(String ime, String prezime);

    AdministratorKlinike findByEmail(String email);

    AdministratorKlinike findByJbo(String jbo);


}

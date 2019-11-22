package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.AdministratorKlinike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdministratorKlinikeRepository extends JpaRepository<AdministratorKlinike, Long> {

    Page<AdministratorKlinike> findAll(Pageable pageable);

    Page<AdministratorKlinike> findAll(Pageable page);

    AdministratorKlinike findAllByKorIme(String korisnickoIme);

    List<AdministratorKlinike> findAllByIme(String ime);

    List<AdministratorKlinike> findAllByPrezime(String prezime);

    List<AdministratorKlinike> findByImeAndPrezimeAllIgnoringCase(String ime, String prezime);

    AdministratorKlinike findByEmail(String email);

}

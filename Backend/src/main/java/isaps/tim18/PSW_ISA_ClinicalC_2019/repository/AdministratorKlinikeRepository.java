package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.AdministratorKlinike;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdministratorKlinikeRepository extends JpaRepository<AdministratorKlinike, Long> {

    Page<AdministratorKlinike> findAll(Pageable pageable);

    List<AdministratorKlinike> findAllByIme(String ime);

    List<AdministratorKlinike> findAllByPrezime(String prezime);

    List<AdministratorKlinike> findByImeAndPrezimeAllIgnoringCase(String ime, String prezime);

}

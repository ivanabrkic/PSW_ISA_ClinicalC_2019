package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LekarRepository extends JpaRepository<Lekar, Long> {

    Page<Lekar> findAll(Pageable pageable);

    List<Lekar> findAllByIme(String ime);

    List<Lekar> findAllByPrezime(String prezime);

    List<Lekar> findByImeAndPrezimeAllIgnoringCase(String ime, String prezime);

    Lekar findByJbo(String jbo);

    Lekar findByEmail(String email);

    Lekar findByEmailAndLozinka(String email, String lozinka);

    List<Lekar> findByKlinika(Klinika k);

}

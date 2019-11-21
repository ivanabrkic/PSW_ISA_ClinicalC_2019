package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LekarRepository extends JpaRepository<Lekar, Long> {

    Page<Lekar> findAll(Pageable pageable);

    List<Lekar> findAllByIme(String ime);

    List<Lekar> findAllByPrezime(String prezime);

    List<Lekar> findByImeAndPrezimeAllIgnoringCase(String ime, String prezime);
}

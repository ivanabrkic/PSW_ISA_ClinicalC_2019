package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;


import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacijentRepository extends JpaRepository<Pacijent, Long> {

    Page<Pacijent> findAll(Pageable pageable);

    List<Pacijent> findAllByIme(String ime);

    List<Pacijent> findAllByPrezime(String prezime);

    List<Pacijent> findByImeAndPrezimeAllIgnoringCase(String ime, String prezime);

    Pacijent findByJbo(String jbo);

    Pacijent findByEmail(String email);

    Pacijent findByEmailAndLozinka(String email, String lozinka);

    List<Pacijent> findByAktivnostNaloga(boolean aktivnost);

    Integer removePacijentByJbo(String jbo);

}

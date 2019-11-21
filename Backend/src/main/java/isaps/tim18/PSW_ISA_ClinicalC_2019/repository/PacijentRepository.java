package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;


import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacijentRepository extends JpaRepository<Pacijent, Long> {

    Page<Pacijent> findAll(Pageable pageable);

    List<Pacijent> findAllByKorisnickoIme(String kime);

    List<Pacijent> findAllByEmail(String email);
}

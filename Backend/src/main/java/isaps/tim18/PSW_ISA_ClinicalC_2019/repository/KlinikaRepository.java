package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KlinikaRepository extends JpaRepository<Klinika, Long> {
    Page<Klinika> findAll(Pageable page);

    List<Klinika> findAll();

    Klinika findByNaziv(String nazivKlinike);

    Klinika findById(int id);
}

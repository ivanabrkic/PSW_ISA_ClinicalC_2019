package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.MedicinskaSestra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicinskaSestraRepository extends JpaRepository<MedicinskaSestra, Long> {

    Page<MedicinskaSestra> findAll(Pageable pageable);

    List<MedicinskaSestra> findAllByIme(String ime);

    List<MedicinskaSestra> findAllByPrezime(String prezime);

    List<MedicinskaSestra> findByImeAndPrezimeAllIgnoringCase(String ime, String prezime);

    MedicinskaSestra findByJbo(String jbo);

    MedicinskaSestra findByEmail(String email);

    MedicinskaSestra findByEmailAndLozinka(String email, String lozinka);

    List<MedicinskaSestra> findByKlinika(Klinika klinika);

}

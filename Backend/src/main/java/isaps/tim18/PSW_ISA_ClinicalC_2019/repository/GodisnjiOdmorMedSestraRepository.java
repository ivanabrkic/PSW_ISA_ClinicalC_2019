package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.GodisnjiOdmorMedSestra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GodisnjiOdmorMedSestraRepository extends JpaRepository<GodisnjiOdmorMedSestra,Long> {

    @Query("SELECT g FROM GodisnjiOdmorMedSestra g WHERE g.medicinskaSestra.id = ?1")
    List<GodisnjiOdmorMedSestra> findBySestraId(Long id);
}

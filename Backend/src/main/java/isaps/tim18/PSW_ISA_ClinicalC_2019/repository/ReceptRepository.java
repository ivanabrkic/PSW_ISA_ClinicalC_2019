package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Izvestaj;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Recept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceptRepository extends JpaRepository<Recept, Long> {
    Optional<Recept> findById(Long id);
    List<Recept> findAll();
    List<Recept> findByPacijent(Pacijent p);
    List<Recept> findByOveren(Boolean overen);
    //Recept findByIzvestaj(Izvestaj izvestaj);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE  FROM recept_lekovi WHERE recept_id = recept_id")
    void obrisiLekove(@Param("recept_id") Long id);
}

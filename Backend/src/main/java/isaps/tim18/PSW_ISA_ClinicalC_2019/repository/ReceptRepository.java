package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Recept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceptRepository extends JpaRepository<Recept, Long> {
    Recept findById(int id);
    List<Recept> findAll();
    List<Recept> findByPacijent(Pacijent p);
    List<Recept> findByOveren(Boolean overen);
    Recept findByBrojAndPacijent(int broj, Pacijent p);
}

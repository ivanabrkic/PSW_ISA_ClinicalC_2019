package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Cenovnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CenovnikRepository extends JpaRepository<Cenovnik, Long> {

    Cenovnik findByNaziv(String ime);

    List<Cenovnik> findAll();
}

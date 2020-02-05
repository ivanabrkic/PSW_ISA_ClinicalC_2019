package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.ZdravstveniKarton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface ZdravstveniKartonRepository extends JpaRepository<ZdravstveniKarton, Long>{

   ZdravstveniKarton findById(int id);

   List<ZdravstveniKarton> findAll();

   ZdravstveniKarton findByPacijent(Pacijent pacijent);

}
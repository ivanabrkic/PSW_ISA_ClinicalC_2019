package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.IzvestajDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Izvestaj;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.ZdravstveniKarton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IzvestajRepository extends JpaRepository<Izvestaj, Long> {

    @Query(value = "SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.IzvestajDTO(i.id, i.lekar.id,  i.izvestaj, i.zdravstveniKarton.id, i.datum) " +
            "FROM Izvestaj i WHERE i.zdravstveniKarton.id = ?1")
    public List<IzvestajDTO> findIzvestajByZdravstveniKartonDTO(Long idZk);
}

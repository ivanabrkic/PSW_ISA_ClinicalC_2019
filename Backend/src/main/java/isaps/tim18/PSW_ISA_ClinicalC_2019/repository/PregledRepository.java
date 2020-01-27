package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pregled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PregledRepository  extends JpaRepository<Pregled, Long> {

    void deleteBySalaId(Long id);

    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO(p.pacijent.jbo, p.lekar.jbo, p.id.datum, p.pocetak, p.kraj) FROM Pregled p " +
            "WHERE p.pocetak is not NULL AND p.kraj is not NULL AND p.sala.id = ?1" +
            " GROUP BY p.id.datum, p.pocetak, p.kraj, p.pacijent.jbo, p.lekar.jbo")
    List<PregledDTO> findBySalaId(Long id);
}

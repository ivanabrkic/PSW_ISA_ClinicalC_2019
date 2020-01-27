package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Operacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperacijaRepository  extends JpaRepository<Operacija, Long> {

    void deleteBySalaId(Long id);

    @Query("SELECT o.id.datum, o.pocetak, o.kraj, o.pacijent.jbo FROM Operacija o " +
            "WHERE o.pocetak is not NULL AND o.kraj is not NULL AND o.sala.id = ?1" +
            " GROUP BY o.id.datum, o.pocetak, o.kraj, o.pacijent.jbo")
    List<String> findBySalaId(Long id);

    @Query("SELECT o.lekari FROM Operacija o WHERE o.id.datum = ?1 " +
            "AND o.pocetak = ?2 AND o.kraj = ?3 " +
            "AND o.sala.id = ?4")
    List<Lekar> findLekareOperacije(String datum, String pocetak, String kraj, Long id);

}

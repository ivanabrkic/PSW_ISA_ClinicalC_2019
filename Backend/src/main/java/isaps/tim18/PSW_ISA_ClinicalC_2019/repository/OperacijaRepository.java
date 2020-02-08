package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.OperacijaDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.OperacijaKalendarDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Operacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperacijaRepository  extends JpaRepository<Operacija, Long> {

    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.OperacijaDTO(c.naziv, o.pacijent.jbo, o.datum, o.pocetak, o.kraj) FROM Operacija o LEFT OUTER JOIN Cenovnik c ON o.cenovnik.id = c.id " +
            "WHERE o.sala.id = ?1" +
            " GROUP BY o.datum, o.pocetak, o.kraj, o.pacijent.jbo, c.naziv")
    List<OperacijaDTO> findBySalaId(Long id);

    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.OperacijaKalendarDTO(o.pacijent.jbo, o.datum, o.pocetak, o.kraj, o.pacijent.ime, o.pacijent.prezime)" +
            "FROM Operacija o WHERE o.lekari.id = ?1")
    List<OperacijaKalendarDTO> findByLekari(Long lekarId);

    @Query("SELECT o.lekari FROM Operacija o WHERE o.datum = ?1 " +
            "AND o.pocetak = ?2 AND o.kraj = ?3 " +
            "AND o.sala.id = ?4")
    List<Lekar> findLekareOperacije(String datum, String pocetak, String kraj, Long id);

    List<Operacija> findByCenovnikId(Long idTipa);
}

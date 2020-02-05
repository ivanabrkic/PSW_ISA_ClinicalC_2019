package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.OperacijaDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.posetaLekarKlinikaDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Operacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperacijaRepository  extends JpaRepository<Operacija, Long> {

    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.OperacijaDTO(c.naziv, o.pacijent.jbo, o.datum, o.pocetak, o.kraj) FROM Operacija o LEFT OUTER JOIN Cenovnik c ON o.cenovnik.id = c.id " +
            "WHERE o.sala.id = ?1" +
            " GROUP BY o.datum, o.pocetak, o.kraj, o.pacijent.jbo, c.naziv")
    List<OperacijaDTO> findBySalaId(Long id);

    @Query("SELECT o.lekari FROM Operacija o WHERE o.datum = ?1 " +
            "AND o.pocetak = ?2 AND o.kraj = ?3 " +
            "AND o.sala.id = ?4")
    List<Lekar> findLekareOperacije(String datum, String pocetak, String kraj, Long id);

    @Query(value = "SELECT sala_id FROM Operacija AS o LEFT OUTER JOIN Sala AS s ON o.sala_id = s.id WHERE s.klinika_id = ?1 " +
            " AND (( CAST(o.pocetak AS Time) <= CAST(?3 AS Time) AND CAST(o.kraj AS Time) >= CAST(?3 AS Time) ) OR " +
            " ( CAST(o.pocetak AS Time) <= CAST(?4 AS Time) AND CAST(o.kraj AS Time) >= CAST(?4 AS Time)  )" +
            " OR ( CAST(o.pocetak AS Time) > CAST(?3 AS Time) AND CAST(o.kraj AS Time) < CAST(?4 AS Time)  ))" +
            " AND o.datum = ?2 ", nativeQuery = true)
    List<Long> findByKlinikaIdAndVreme(Long idKlinike, String datum, String pocetak, String kraj);

    List<Operacija> findByCenovnikId(Long idTipa);

    List<Operacija> findByPacijentId(Long id);

    Optional<Operacija> findById(Long id);

    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.posetaLekarKlinikaDTO(l,k.id,k.naziv,k.ocena,l.ocena) from Operacija p inner join p.lekari as l inner join p.sala as s  inner join s.klinika as k inner join p.cenovnik as c " +
            "WHERE p.id=?1")
    posetaLekarKlinikaDTO findInfo(Long id);

}

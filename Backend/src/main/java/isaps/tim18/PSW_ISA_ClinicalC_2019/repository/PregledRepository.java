package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledIzvestajDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefInfoDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pregled;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PregledRepository  extends JpaRepository<Pregled, Long> {

    @Query(value = "SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledIzvestajDTO(p.id, p.pacijent.jbo,  p.datum, p.pocetak, p.kraj, p.lekar.jbo) " +
            "FROM Pregled p WHERE p.lekar.id = id")
    List<PregledIzvestajDTO> findPregledeById(Long id);
    
    Optional<Pregled> findById(Long id);

    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO(c.naziv, p.pacijent.jbo, p.lekar.jbo, p.datum, p.pocetak, p.kraj) FROM Pregled p LEFT OUTER JOIN Cenovnik c ON p.cenovnik.id = c.id " +
            "WHERE p.sala.id = ?1 AND p.status = 'Zakazan'" +
            " GROUP BY p.datum, p.pocetak, p.kraj, p.pacijent.jbo, p.lekar.jbo, c.naziv")
    List<PregledDTO> findBySalaId(Long id);

    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO(c.naziv, p.lekar.jbo, p.datum, p.pocetak, p.kraj) FROM Pregled p LEFT OUTER JOIN Cenovnik c ON p.cenovnik.id = c.id " +
            "WHERE p.sala.id = ?1 AND p.status = 'Neaktivan'" +
            " GROUP BY p.datum, p.pocetak, p.kraj, p.lekar.jbo, c.naziv")
    List<PregledDTO> findBySalaIdPredef(Long id);

    @Query(value = "SELECT sala_id FROM Pregled AS o LEFT OUTER JOIN Sala AS s ON o.sala_id = s.id WHERE s.klinika_id = ?1 " +
            " AND (( CAST(o.pocetak AS Time) <= CAST(?3 AS Time) AND CAST(o.kraj AS Time) >= CAST(?3 AS Time) ) OR " +
            " ( CAST(o.pocetak AS Time) <= CAST(?4 AS Time) AND CAST(o.kraj AS Time) >= CAST(?4 AS Time)  )" +
            " OR ( CAST(o.pocetak AS Time) > CAST(?3 AS Time) AND CAST(o.kraj AS Time) < CAST(?4 AS Time)  ))" +
            " AND o.datum = ?2 AND NOT o.status = 'Završen'", nativeQuery = true)
    List<Long> findByKlinikaIdAndVreme(Long idKlinike, String datum, String pocetak, String kraj);
    
    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefInfoDTO(c.naziv,  p.datum, p.pocetak, p.kraj,s.naziv,s.broj,k.naziv,l.ime,l.prezime,c.cena,p.popust) FROM Pregled p INNER JOIN Cenovnik c ON p.cenovnik.id = c.id INNER JOIN p.sala as s INNER JOIN s.klinika as k INNER JOIN p.lekar as l " +
            "WHERE k.id = ?1 AND p.status = 'Neaktivan'" 
           )
    List<predefInfoDTO> findByKlinikaIdPredef(Long id);

    @Query("SELECT p FROM Pregled p WHERE p.cenovnik.id = ?1 " +
            "AND NOT p.status = 'Završen'")
    List<Pregled> findByCenovnikId(Long idTipa);

    @Query("SELECT p.sala FROM Pregled p WHERE p.sala.id = ?1 " +
            "AND NOT p.status = 'Završen'")
    List<Sala> findBySalaIdAndStatus(Long id);
}

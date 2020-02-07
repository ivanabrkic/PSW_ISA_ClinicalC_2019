package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.posetaLekarKlinikaDTO;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefInfoDTO;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pregled;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefInfoDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PregledRepository  extends JpaRepository<Pregled, Long> {

    void deleteBySalaId(Long id);
    
    List<Pregled> findByPacijentId(Long id);
    
    Optional<Pregled> findById(Long id);

    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO(c.naziv, p.pacijent.jbo, p.lekar.jbo, p.datum, p.pocetak, p.kraj) FROM Pregled p LEFT OUTER JOIN Cenovnik c ON p.cenovnik.id = c.id " +
            "WHERE p.sala.id = ?1 AND (p.status <> 'Završen')" +
            " GROUP BY p.datum, p.pocetak, p.kraj, p.pacijent.jbo, p.lekar.jbo, c.naziv")
    List<PregledDTO> findBySalaId(Long id);

    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO(c.naziv, p.lekar.jbo, p.datum, p.pocetak, p.kraj) FROM Pregled p LEFT OUTER JOIN Cenovnik c ON p.cenovnik.id = c.id " +
            "WHERE p.sala.id = ?1 AND p.status = 'Neaktivan'" +
            " GROUP BY p.datum, p.pocetak, p.kraj, p.lekar.jbo, c.naziv")
    List<PregledDTO> findBySalaIdPredef(Long id);
    
    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefInfoDTO(c.naziv,  p.datum, p.pocetak, p.kraj,s.naziv,s.broj,k.naziv,l.ime,l.prezime,c.cena,p.popust) FROM Pregled p INNER JOIN Cenovnik c ON p.cenovnik.id = c.id INNER JOIN p.sala as s INNER JOIN s.klinika as k INNER JOIN p.lekar as l " +
            "WHERE k.id = ?1 AND p.status = 'Neaktivan'")
    List<predefInfoDTO> findByKlinikaIdPredef(Long id);

    @Query(value = "SELECT sala_id FROM Pregled AS o LEFT OUTER JOIN Sala AS s ON o.sala_id = s.id WHERE s.klinika_id = ?1 " +
            " AND (( CAST(o.pocetak AS Time) <= CAST(?3 AS Time) AND CAST(o.kraj AS Time) >= CAST(?3 AS Time) ) OR " +
            " ( CAST(o.pocetak AS Time) <= CAST(?4 AS Time) AND CAST(o.kraj AS Time) >= CAST(?4 AS Time)  )" +
            " OR ( CAST(o.pocetak AS Time) > CAST(?3 AS Time) AND CAST(o.kraj AS Time) < CAST(?4 AS Time)  ))" +
            " AND o.datum = ?2 AND NOT o.status = 'Završen'", nativeQuery = true)
    List<Long> findByKlinikaIdAndVreme(Long idKlinike, String datum, String pocetak, String kraj);
    
    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefInfoDTO(p.id,c.naziv,  p.datum, p.pocetak, p.kraj,s.naziv,s.broj,k.naziv,l.ime,l.prezime,c.cena,p.popust,k.id,c.id,l.jbo,s.id) FROM Pregled p INNER JOIN Cenovnik c ON p.cenovnik.id = c.id INNER JOIN p.sala as s INNER JOIN s.klinika as k INNER JOIN p.lekar as l " +
            "WHERE k.id = ?1 AND p.status = 'Neaktivan' AND date(to_date(?2,'d.M.yyyy.'))< date(to_date(p.datum,'d.M.yyyy.'))" 
           )
    List<predefInfoDTO> findByKlinikaIdPredef(Long id,String modifiedDate);

    @Query("SELECT p FROM Pregled p WHERE p.cenovnik.id = ?1 " +
            "AND NOT p.status = 'Završen'")
    List<Pregled> findByCenovnikId(Long idTipa);

    @Query("SELECT p.sala FROM Pregled p WHERE p.sala.id = ?1 " +
            "AND p.status <> 'Završen'")
    List<Sala> findBySalaIdAndStatus(Long id);


//    @Query(value="SELECT new posetaLekarKlinikaDTO(id,l,id,naziv,ocena,ocena, c.naziv,p.datum,p.pocetak,p.kraj) from "
//    		+ " Pregled p inner join lekar as l on p.lekar.id=l.id "
//    		+ "inner join sala as s on p.sala.id=s.id "
//    		+ "inner join klinika as k on k.id=p.klinika.id "
//    		+ "inner join cenovnik as c on c.id=p.cenovnik.id " +
//            "WHERE p.pacijent.id=?1 and CURRENT_DATE >  to_timestamp(p.datum, 'D.M.YYYY.')",nativeQuery=true)
//    List<posetaLekarKlinikaDTO> findInfo(Long id);


//    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.posetaLekarKlinikaDTO(p.id,l,k.id,k.naziv,k.ocena,l.ocena, c.naziv,p.datum,p.pocetak,p.kraj) from Pregled p inner join p.lekar as l inner join p.sala as s  inner join s.klinika as k inner join p.cenovnik as c " +
//            "WHERE p.pacijent.id=?1 and p.status='Završen'")
//    List<posetaLekarKlinikaDTO> findInfo(Long id);
    
    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.posetaLekarKlinikaDTO(p.id,l,k.id,k.naziv,k.ocena,l.ocena, c.naziv,p.datum,p.pocetak,p.kraj) from Pregled p inner join p.lekar as l inner join p.sala as s  inner join s.klinika as k inner join p.cenovnik as c " +
            "WHERE p.pacijent.id=?1 ")
    List<posetaLekarKlinikaDTO> findInfo(Long id);
    
//    //Vraca samo prosle preglede
//    @Query("Select p from Pregled as p where pacijent.id=?1 and CURRENT_DATE > date(to_date(p.datum,'d.M.yyyy.'))")
//	List<Pregled> findHistoryByPacijentId(Long id);

}

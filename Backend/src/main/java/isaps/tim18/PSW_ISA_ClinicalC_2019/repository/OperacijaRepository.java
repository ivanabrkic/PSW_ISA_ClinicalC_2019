package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.OperacijaDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.OperacijaKalendarDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.posetaLekarKlinikaDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Operacija;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pregled;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OperacijaRepository  extends JpaRepository<Operacija, Long> {

    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.OperacijaDTO(c.naziv, o.pacijent.jbo, o.datum, o.pocetak, o.kraj) FROM Operacija o LEFT OUTER JOIN Cenovnik c ON o.cenovnik.id = c.id " +
            "WHERE o.sala.id = ?1" +
            " GROUP BY o.datum, o.pocetak, o.kraj, o.pacijent.jbo, c.naziv")
    List<OperacijaDTO> findBySalaId(Long id);

    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.OperacijaKalendarDTO(o.pacijent.jbo, o.datum, o.pocetak, o.kraj, o.pacijent.ime, o.pacijent.prezime)" +
            "FROM Operacija o WHERE o.lekari.id = ?1 AND o.status = 'Zakazan'")
    List<OperacijaKalendarDTO> findByLekari(Long lekarId);

    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.OperacijaKalendarDTO(o.pacijent.jbo, o.datum, o.pocetak, o.kraj, o.pacijent.ime, o.pacijent.prezime)" +
            "FROM Operacija o WHERE o.status = 'Zakazan'")
    List<OperacijaKalendarDTO> getZakazaneOperacije();

    @Query("SELECT o.lekari FROM Operacija o WHERE o.datum = ?1 " +
            "AND o.pocetak = ?2 AND o.kraj = ?3 " +
            "AND o.sala.id = ?4")
    List<Lekar> findLekareOperacije(String datum, String pocetak, String kraj, Long id);

    List<Operacija> findByCenovnikId(Long idTipa);

    List<Operacija> findByPacijentId(Long id);

    Optional<Operacija> findById(Long id);

    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.posetaLekarKlinikaDTO(p.id,l,k.id,k.naziv,k.ocena,l.ocena, c.naziv,p.datum,p.pocetak,p.kraj) from Operacija p inner join p.lekari as l inner join p.sala as s  inner join s.klinika as k inner join p.cenovnik as c " +
            "WHERE p.pacijent.id=?1 ")
    List<posetaLekarKlinikaDTO> findInfo(Long id);

    @Query("SELECT p FROM Operacija p WHERE p.datum = ?1 AND p.status = ?2 AND p.sala.klinika.id = ?3")
    List<Operacija> findByDatumAndStatusAndKlinikaId(String format, String završen, Long idKlinike);

//  //Vraca samo prosle operacije
//    @Query("Select p from Operacija as p where pacijent.id=?1 and CURRENT_DATE > to_date(p.datum,'d.m.yyyy.')")
//	List<Operacija> findHistoryByPacijentId(Long id);

}

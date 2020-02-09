package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pregled;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PregledRepository  extends JpaRepository<Pregled, Long> {

    @Query(value = "SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledIzvestajDTO(p.id, p.pacijent.jbo,  p.datum, p.pocetak, p.kraj, p.lekar.jbo, p.lekar.ime, p.lekar.prezime) " +
            "FROM Pregled p WHERE p.lekar.id = ?1")
    List<PregledIzvestajDTO> nadjiPoIdLekara(Long id);

    @Query(value = "SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PreglediStatusDTO(p.id, p.pacijent.jbo,  p.datum, p.pocetak, p.kraj, p.status, p.lekar.jbo) " +
            "FROM Pregled p WHERE p.status = 'Zakazan'")
    List<PreglediStatusDTO> findAllPreglede();

    @Query(value = "SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PreglediStatusDTO(p.id, p.datum, p.pocetak, p.kraj, p.status, p.lekar.jbo) " +
            "FROM Pregled p WHERE p.status = 'Neaktivan'")
    List<PreglediStatusDTO> findAllPregledeNezakazane();

    void deleteBySalaId(Long id);

    List<Pregled> findByPacijentId(Long id);

    @Query(value = "SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledIzvestajDTO(p.id, p.pacijent.jbo,  p.datum, p.pocetak, p.kraj, p.lekar.jbo, p.lekar.ime, p.lekar.prezime) " +
            "FROM Pregled p WHERE p.status = 'Zakazan' AND p.pacijent.jbo = ?2 AND p.lekar.jbo = ?1")
    List<PregledIzvestajDTO> getZakazaneByLekarAndPacijent(String jboLekara, String jboPacijenta);

    @Query(value = "SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledIzvestajDTO(p.id, p.pacijent.jbo,  p.datum, p.pocetak, p.kraj, p.lekar.jbo, p.lekar.ime, p.lekar.prezime) " +
            "FROM Pregled p WHERE p.status = 'Zakazan'")
    List<PregledIzvestajDTO> getZakazaneByPacijent(String jboPacijent);

    @Query(value = "SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PreglediStatusDTO(p.id, p.pacijent.jbo,  p.datum, p.pocetak, p.kraj, p.status, p.lekar.jbo)" +
            "FROM Pregled p WHERE p.lekar.jbo = ?1 AND p.status = 'Zakazan'")
    List<PreglediStatusDTO> getPregledeByLekar(String jboLekar);


    @Query(value = "SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PreglediStatusDTO(p.id, p.datum, p.pocetak, p.kraj, p.status, p.lekar.jbo)" +
            "FROM Pregled p WHERE p.lekar.jbo = ?1 AND p.status = 'Neaktivan'")
    List<PreglediStatusDTO> getNeaktivneByLekar(String jboLekar);

    Optional<Pregled> findById(Long id);

    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO(c.naziv, p.pacijent.jbo, p.lekar.jbo, p.datum, p.pocetak, p.kraj) FROM Pregled p LEFT OUTER JOIN Cenovnik c ON p.cenovnik.id = c.id " +
            "WHERE p.sala.id = ?1 AND (p.status <> 'Završen')" +
            " GROUP BY p.datum, p.pocetak, p.kraj, p.pacijent.jbo, p.lekar.jbo, c.naziv")
    List<PregledDTO> findBySalaId(Long id);

    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO(c.naziv, p.lekar.jbo, p.datum, p.pocetak, p.kraj) FROM Pregled p LEFT OUTER JOIN Cenovnik c ON p.cenovnik.id = c.id " +
            "WHERE p.sala.id = ?1 AND p.status = 'Neaktivan'" +
            " GROUP BY p.datum, p.pocetak, p.kraj, p.lekar.jbo, c.naziv")
    List<PregledDTO> findBySalaIdPredef(Long id);

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////// OVAJ MI TREBA TESLA ZA STRANICU /////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefInfoDTO(p.cenovnik.naziv,  p.datum, p.pocetak, p.kraj, p.sala.naziv, p.sala.broj, p.sala.klinika.naziv, p.lekar.ime, p.lekar.prezime, p.cenovnik.cena, p.popust) FROM Pregled p" +
            " WHERE p.sala.klinika.id = ?1 AND p.status = 'Neaktivan'")

    List<predefInfoDTO> findByKlinikaIdPredef(Long id);
    
    @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefInfoDTO(p.id,c.naziv,  p.datum, p.pocetak, p.kraj,s.naziv,s.broj,k.naziv,l.ime,l.prezime,c.cena,p.popust,k.id,c.id,l.jbo,s.id) FROM Pregled p INNER JOIN Cenovnik c ON p.cenovnik.id = c.id INNER JOIN p.sala as s INNER JOIN s.klinika as k INNER JOIN p.lekar as l " +
            "WHERE k.id = ?1 AND p.status = 'Neaktivan' ")
    List<predefInfoDTO> findByKlinikaIdPredef2(Long id);


//     @Query("SELECT new isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefInfoDTO(p.id,c.naziv,  p.datum, p.pocetak, p.kraj,s.naziv,s.broj,k.naziv,l.ime,l.prezime,c.cena,p.popust,k.id,c.id,l.jbo,s.id) FROM Pregled p INNER JOIN Cenovnik c ON p.cenovnik.id = c.id INNER JOIN p.sala as s INNER JOIN s.klinika as k INNER JOIN p.lekar as l " +
//             "WHERE k.id = ?1 AND p.status = 'Neaktivan' AND date(to_date(?2,'d.M.yyyy.'))< date(to_date(p.datum,'d.M.yyyy.'))")
//     List<predefInfoDTO> findByKlinikaIdPredef(Long id,String modifiedDate);


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

    @Query("SELECT p FROM Pregled p WHERE p.datum = ?1 AND p.status = ?2 AND p.sala.klinika.id = ?3")
    List<Pregled> findByDatumAndStatusAndKlinikaId(String datum, String status, Long klinikaId);

//    //Vraca samo prosle preglede
//    @Query("Select p from Pregled as p where pacijent.id=?1 and CURRENT_DATE > date(to_date(p.datum,'d.M.yyyy.'))")
//	List<Pregled> findHistoryByPacijentId(Long id);

}

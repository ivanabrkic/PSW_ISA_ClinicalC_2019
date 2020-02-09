package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LekarRepository extends JpaRepository<Lekar, Long> {

    Page<Lekar> findAll(Pageable pageable);

    List<Lekar> findAllByIme(String ime);

    List<Lekar> findAllByPrezime(String prezime);

    List<Lekar> findByImeAndPrezimeAllIgnoringCase(String ime, String prezime);

    Lekar findByJbo(String jbo);

    Lekar findByEmail(String email);

    List<Lekar> findByKlinika(Klinika k);

    @Query(value = "SELECT lekar_id FROM Lekar AS l WHERE  " +
            " l.radno_vreme = ?2  AND l.klinika_id = ?1 AND ( l.specijalizacija = ?3 OR l.specijalizacija = 'Anesteziolog')", nativeQuery = true)
    List<Long> radnoVremeSpecOperacija(Long idKlinike, String vremeZakazivanja, String specijalizacija);

    @Query(value = "SELECT lekar_id FROM Lekar AS l WHERE  " +
            " l.radno_vreme = ?2  AND l.klinika_id = ?1 AND l.specijalizacija = ?3", nativeQuery = true)
    List<Long> radnoVremeSpecPregled(Long idKlinike, String vremeZakazivanja, String specijalizacija);

    @Query(value = "SELECT id FROM Operacija AS o WHERE o.lekar_id = ?1 " +
            " AND (( CAST(o.pocetak AS Time) > CAST(?3 AS Time) AND CAST(o.pocetak AS Time) < CAST(?4 AS Time) AND CAST(o.kraj AS Time) > CAST(?4 AS Time))" +
            " OR ( CAST(o.pocetak AS Time) < CAST(?3 AS Time) AND CAST(o.kraj AS Time) > CAST(?3 AS Time) AND CAST(o.kraj AS Time) < CAST(?4 AS Time))" +
            " OR ( CAST(o.pocetak AS Time) < CAST(?3 AS Time) AND  CAST(o.kraj AS Time) > CAST(?3 AS Time) AND CAST(o.kraj AS Time) > CAST(?4 AS Time))" +
            " OR ( CAST(o.pocetak AS Time) >= CAST(?3 AS Time) AND CAST(o.kraj AS Time) <= CAST(?4 AS Time)))" +
            " AND o.datum = ?2 ", nativeQuery = true)
    List<Long> imaOperacije(Long idLekara, String datum, String pocetak, String kraj);

    @Query(value = "SELECT id FROM Pregled AS o WHERE o.lekar_id = ?1 " +
            " AND (( CAST(o.pocetak AS Time) > CAST(?3 AS Time) AND CAST(o.pocetak AS Time) < CAST(?4 AS Time) AND CAST(o.kraj AS Time) > CAST(?4 AS Time))" +
            " OR ( CAST(o.pocetak AS Time) < CAST(?3 AS Time) AND CAST(o.kraj AS Time) > CAST(?3 AS Time) AND CAST(o.kraj AS Time) < CAST(?4 AS Time))" +
            " OR ( CAST(o.pocetak AS Time) < CAST(?3 AS Time) AND  CAST(o.kraj AS Time) > CAST(?3 AS Time) AND CAST(o.kraj AS Time) > CAST(?4 AS Time))" +
            " OR ( CAST(o.pocetak AS Time) >= CAST(?3 AS Time) AND CAST(o.kraj AS Time) <= CAST(?4 AS Time)))" +
            " AND o.datum = ?2 AND (o.status <> 'Završen')", nativeQuery = true)
    List<Long> imaPreglede(Long idLekara, String datum, String pocetak, String kraj);

    @Query(value = "SELECT id FROM Operacija AS o WHERE o.lekar_id = ?1 AND (o.status <> 'Završen')", nativeQuery = true)
    List<Long> imaOperacije(Long idLekara);

    @Query(value = "SELECT id FROM Pregled AS o WHERE o.lekar_id = ?1 AND (o.status <> 'Završen')", nativeQuery = true)
    List<Long> imaPreglede(Long idLekara);

    // @Query(value="SELECT pocetak FROM Pregled AS o WHERE o.lekar_id=?1 AND CAST(?2 AS DATE )=CAST(o.datum AS DATE) AND o.status='Zakazan'",nativeQuery = true)
    @Query(value="SELECT pocetak, lekar_id, datum, kraj FROM Pregled AS o WHERE o.lekar_id = ?1 AND o.datum=?2 And o.status='Zakazan'",nativeQuery = true)
    List<String> zauzetiTermini(Long idLekara,String datum);

    @Query(value="SELECT pocetak, lekar_id, datum, kraj FROM Operacija AS o WHERE o.lekar_id = ?1 AND o.datum=?2 ",nativeQuery = true)
    List<String> zauzetiTermini2(Long idLekara,String datum);

    Lekar findBySpecijalizacija(String specijalizacija);

    List<Lekar> findBySpecijalizacijaAndKlinika(String specijalizacija, Klinika klinika);
}

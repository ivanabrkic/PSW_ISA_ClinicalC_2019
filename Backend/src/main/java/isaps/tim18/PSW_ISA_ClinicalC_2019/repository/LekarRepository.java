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

    Lekar findByEmailAndLozinka(String email, String lozinka);

    List<Lekar> findByKlinika(Klinika klinika);

    @Query(value = "SELECT lekar_id FROM Lekar AS l WHERE  " +
            " l.radno_vreme = ?2  AND klinika_id = ?1 AND ( specijalizacija = ?3 OR specijalizacija = 'Anesteziolog')", nativeQuery = true)
    List<Long> daLiJeRadnoVreme(Long idKlinike, String vremeZakazivanja, String specijalizacija);

    @Query(value = "SELECT id FROM Operacija AS o WHERE o.lekar_id = ?1 " +
            " AND ( ( CAST(o.pocetak AS Time) <= CAST(?3 AS Time) AND CAST(o.kraj AS Time) >= CAST(?3 AS Time) ) OR " +
            " ( CAST(o.pocetak AS Time) <= CAST(?4 AS Time) AND CAST(o.kraj AS Time) >= CAST(?4 AS Time)  )" +
            " OR ( CAST(o.pocetak AS Time) > CAST(?3 AS Time) AND CAST(o.kraj AS Time) < CAST(?4 AS Time)  ) )" +
            " AND o.datum = ?2 ", nativeQuery = true)
    List<Long> imaOperacije(Long idLekara, String datum, String pocetak, String kraj);

    @Query(value = "SELECT id FROM Pregled AS o WHERE o.lekar_id = ?1 " +
            " AND ( ( CAST(o.pocetak AS Time) <= CAST(?3 AS Time) AND CAST(o.kraj AS Time) >= CAST(?3 AS Time) ) OR " +
            " ( CAST(o.pocetak AS Time) <= CAST(?4 AS Time) AND CAST(o.kraj AS Time) >= CAST(?4 AS Time)  )" +
            " OR ( CAST(o.pocetak AS Time) > CAST(?3 AS Time) AND CAST(o.kraj AS Time) < CAST(?4 AS Time)  ) )" +
            " AND o.datum = ?2 ", nativeQuery = true)
    List<Long> imaPreglede(Long idLekara, String datum, String pocetak, String kraj);

}

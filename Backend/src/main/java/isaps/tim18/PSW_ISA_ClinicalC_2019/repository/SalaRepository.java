package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

    List<Sala> findByKlinikaId(Long id);

    Sala findByNazivAndKlinikaAndBroj(String naziv, Klinika klinika, String broj);

    @Query(value = "SELECT sala_id FROM Pregled AS p WHERE " +
            " (( CAST(p.pocetak AS Time) >= CAST(?3 AS Time) AND CAST(p.pocetak AS Time) <= CAST(?4 AS Time) AND CAST(p.kraj AS Time) >= CAST(?4 AS Time))" +
            " OR ( CAST(p.pocetak AS Time) <= CAST(?3 AS Time) AND CAST(p.kraj AS Time) >= CAST(?3 AS Time) AND CAST(p.kraj AS Time) <= CAST(?4 AS Time))" +
            " OR ( CAST(p.pocetak AS Time) < CAST(?3 AS Time) AND  CAST(p.kraj AS Time) > CAST(?3 AS Time) AND CAST(p.kraj AS Time) > CAST(?4 AS Time))" +
            " OR ( CAST(p.pocetak AS Time) > CAST(?3 AS Time) AND CAST(p.kraj AS Time) < CAST(?4 AS Time)))" +
            " AND p.datum = ?2 AND ( p.status <> 'Završen') AND p.sala_id = ?1"+
            " UNION ALL " +
            " SELECT sala_id FROM Operacija AS o WHERE " +
            " (( CAST(o.pocetak AS Time) >= CAST(?3 AS Time) AND CAST(o.pocetak AS Time) <= CAST(?4 AS Time) AND CAST(o.kraj AS Time) >= CAST(?4 AS Time))" +
            " OR ( CAST(o.pocetak AS Time) <= CAST(?3 AS Time) AND CAST(o.kraj AS Time) >= CAST(?3 AS Time) AND CAST(o.kraj AS Time) <= CAST(?4 AS Time))" +
            " OR ( CAST(o.pocetak AS Time) < CAST(?3 AS Time) AND  CAST(o.kraj AS Time) > CAST(?3 AS Time) AND CAST(o.kraj AS Time) > CAST(?4 AS Time))" +
            " OR ( CAST(o.pocetak AS Time) > CAST(?3 AS Time) AND CAST(o.kraj AS Time) < CAST(?4 AS Time)))" +
            " AND o.datum = ?2 AND o.sala_id = ?1", nativeQuery = true)
    List<Long> checkIfSalaZauzeta(Long idSale, String datum, String pocetak, String kraj);

}

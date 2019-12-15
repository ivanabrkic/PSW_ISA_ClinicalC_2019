package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekovi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LekoviRepository extends JpaRepository<Lekovi, Long> {

    Lekovi findBySifra(String sifra);

    Lekovi findByNaziv(String nazivLeka);

    List<Lekovi> findAll();

}

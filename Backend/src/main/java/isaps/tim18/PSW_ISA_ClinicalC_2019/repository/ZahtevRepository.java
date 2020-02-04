package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Zahtev;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZahtevRepository  extends JpaRepository<Zahtev, Long> {

    List<Zahtev> findByIdKlinike(Long idKlinike);
    
    Optional<Zahtev> findById(Long id);

}

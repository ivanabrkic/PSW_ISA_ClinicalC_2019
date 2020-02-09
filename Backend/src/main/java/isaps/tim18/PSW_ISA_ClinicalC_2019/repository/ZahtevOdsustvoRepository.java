package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.ZahtevOdsustvo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZahtevOdsustvoRepository extends JpaRepository<ZahtevOdsustvo, Long> {

    public List<ZahtevOdsustvo> findAllByOveren(boolean overen);

    List<ZahtevOdsustvo> findAllByKlinikaId(Long klinikaId);
}

package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    Page<Korisnik> findAll(Pageable pageable);

    List<Korisnik> findAllByKorIme(String kime);

    List<Korisnik> findAllByEmail(String email);

    List<Korisnik> findAllByPassword(String password);

}

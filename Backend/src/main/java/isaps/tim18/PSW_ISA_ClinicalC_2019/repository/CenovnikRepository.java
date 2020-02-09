package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Cenovnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CenovnikRepository extends JpaRepository<Cenovnik, Long> {

    List<Cenovnik> findByKlinikaId(Long idKlinike);

    @Query("SELECT c FROM Cenovnik c WHERE c.naziv = ?1 AND c.specijalizacija = ?2 AND c.klinika.id = ?3 AND c.cena = ?4")
    List<Cenovnik> findByNazivAndSpecijalizacijaAndKlinikaAndCena(String naziv, String specijalizacija, Long klinika, float cena);

    List<Cenovnik> findByNaziv(String ime);

    List<Cenovnik> findAll();

    Cenovnik findByNazivAndKlinikaId(String n,Long id);

    @Query("SELECT c FROM Cenovnik c WHERE c.specijalizacija = ?2 AND c.klinika.id = ?1")
    List<Cenovnik> findBySpecAndKlinika(Long id, String specijalizacija);
}

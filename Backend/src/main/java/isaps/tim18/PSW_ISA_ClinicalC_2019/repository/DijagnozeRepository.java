package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Dijagnoze;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public interface DijagnozeRepository extends JpaRepository<Dijagnoze,Long> {

    Optional<Dijagnoze> findById(Long sifra);

    Dijagnoze findBySifraDijagnoze(String sifra);

    @Query(nativeQuery=true,value= "SELECT naziv\n" +
            "FROM zkarton_dijagnoze JOIN zkarton  ON\n" +
            "zkarton_dijagnoze.zkarton_id= zkarton.id \n" +
            "JOIN dijagnoze ON\n" +
            "zkarton_dijagnoze.dijagnoze_id=dijagnoze.sifra\n" +
            "WHERE pacijent_id=(:id) \n")
    List<String> find(@Param("id") Long id);

    Dijagnoze findByNazivDijagnoze(String nazivDijagnoze);

    List<Dijagnoze> findAll();
}

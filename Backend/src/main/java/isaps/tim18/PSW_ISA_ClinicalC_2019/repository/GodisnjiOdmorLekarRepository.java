package isaps.tim18.PSW_ISA_ClinicalC_2019.repository;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.GodisnjiOdmorLekar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GodisnjiOdmorLekarRepository extends JpaRepository<GodisnjiOdmorLekar,Long> {

    @Query("SELECT g FROM GodisnjiOdmorLekar g WHERE g.lekar.id = ?1")
    List<GodisnjiOdmorLekar> findByLekarId(Long id);
}

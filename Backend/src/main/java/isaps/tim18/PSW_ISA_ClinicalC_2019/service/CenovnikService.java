package isaps.tim18.PSW_ISA_ClinicalC_2019.service;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Cenovnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.CenovnikRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CenovnikService {
    @Autowired
    CenovnikRepository cenovnikRepo;

    public String findByNaziv(String ime){
        Cenovnik found=cenovnikRepo.findByNaziv(ime);
        return found.getSpecijalizacija();
    }

    public List<Cenovnik> findAll(){
        return cenovnikRepo.findAll();
    }
}

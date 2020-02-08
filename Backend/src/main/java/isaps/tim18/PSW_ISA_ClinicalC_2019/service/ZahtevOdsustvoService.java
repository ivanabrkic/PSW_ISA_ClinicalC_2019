package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.ZahtevOdsustvo;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.ZahtevOdsustvoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZahtevOdsustvoService {

    @Autowired
    private ZahtevOdsustvoRepository zahtevOdsustvoRepository;

    public ZahtevOdsustvo add(ZahtevOdsustvo zahtevOdsustvo) {
        zahtevOdsustvo.setOveren(false);
        return zahtevOdsustvoRepository.save(zahtevOdsustvo);
    }

    public void delete(ZahtevOdsustvo zo) { zahtevOdsustvoRepository.delete(zo); }

    public List<ZahtevOdsustvo> findByNeodobren() { return zahtevOdsustvoRepository.findAllByOveren(false); }

    public ZahtevOdsustvo update(ZahtevOdsustvo zo) {
        Optional<ZahtevOdsustvo> opt = zahtevOdsustvoRepository.findById(zo.getId());

        if(opt.isPresent()){
            ZahtevOdsustvo apdejtovanZahtev = opt.get();
            apdejtovanZahtev.setOveren(true);
            return apdejtovanZahtev;
        }
        return null;
    }
}

package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.AdministratorKlinike;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.AdministratorKlinikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorKlinikeService {

    @Autowired
    private AdministratorKlinikeRepository adminKlinikeRepo;

    public List<AdministratorKlinike> findAll() { return adminKlinikeRepo.findAll(); }

    public Page<AdministratorKlinike> findAll(Pageable page) { return adminKlinikeRepo.findAll(page); }

    public List<AdministratorKlinike> findAllByIme(String ime) {
        return adminKlinikeRepo.findAllByIme(ime);
    }

    public List<AdministratorKlinike> findAllByPrezime(String prezime) {
        return adminKlinikeRepo.findAllByPrezime(prezime);
    }

    public List<AdministratorKlinike> findByImeAndPrezime(String ime, String prezime) {
        return adminKlinikeRepo.findByImeAndPrezimeAllIgnoringCase(ime, prezime);
    }

    public AdministratorKlinike findByKorisnickoIme(String korIme){
        return adminKlinikeRepo.findAllByKorisnickoIme(korIme);
    }

    public AdministratorKlinike findByEmail(String email) {
        return adminKlinikeRepo.findByEmail(email);
    }

    public AdministratorKlinike addAdminKlinike(AdministratorKlinike admin){
        return adminKlinikeRepo.save(admin);
    }
}

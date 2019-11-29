package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KorisnikService {

    @Autowired
    private PacijentRepository pacijentRepository;
    @Autowired
    private LekarRepository lekarRepository;
    @Autowired
    private MedicinskaSestraRepository medicinskaSestraRepository;
    @Autowired
    private AdministratorKlinickogCentraRepository administratorKlinickogCentraRepository;
    @Autowired
    private AdministratorKlinikeRepository administratorKlinikeRepository;

    public List<Korisnik> findAll() {

        List<Lekar> listaLekara = lekarRepository.findAll();
        List<MedicinskaSestra> listaSestra = medicinskaSestraRepository.findAll();
        List<AdministratorKlinickogCentra> listaAdminaKC = administratorKlinickogCentraRepository.findAll();
        List<AdministratorKlinike> listaAdminaK = administratorKlinikeRepository.findAll();
        List<Pacijent> listaPacijent = pacijentRepository.findAll();

        List<Korisnik> listaKorisnika = new ArrayList<Korisnik>(listaLekara);
        listaKorisnika.addAll(listaSestra);
        listaKorisnika.addAll(listaAdminaK);
        listaKorisnika.addAll(listaAdminaKC);
        listaKorisnika.addAll(listaPacijent);

        return listaKorisnika;
    }

    public Korisnik findByKorIme(String kime){

        if (lekarRepository.findByKorIme(kime) != null){
            return lekarRepository.findByKorIme(kime);
        }

        if (medicinskaSestraRepository.findByKorIme(kime) != null){
            return medicinskaSestraRepository.findByKorIme(kime);
        }

        if (pacijentRepository.findByKorIme(kime) != null){
            return pacijentRepository.findByKorIme(kime);
        }

        if (administratorKlinickogCentraRepository.findByKorIme(kime) != null){
            return administratorKlinickogCentraRepository.findByKorIme(kime);
        }

        if (administratorKlinikeRepository.findByKorIme(kime) != null){
            return administratorKlinikeRepository.findByKorIme(kime);
        }

        return null;
    }

    public Korisnik findByEmail(String email){
        if (lekarRepository.findByEmail(email) != null){
            return lekarRepository.findByEmail(email);
        }

        if (medicinskaSestraRepository.findByEmail(email) != null){
            return medicinskaSestraRepository.findByEmail(email);
        }

        if (pacijentRepository.findByEmail(email) != null){
            return pacijentRepository.findByEmail(email);
        }

        if (administratorKlinickogCentraRepository.findByEmail(email) != null){
            return administratorKlinickogCentraRepository.findByEmail(email);
        }

        if (administratorKlinikeRepository.findByEmail(email) != null){
            return administratorKlinikeRepository.findByEmail(email);
        }

        return null;
    }

    public Korisnik findByEmailAndLozinka(String email, String lozinka){
        if (lekarRepository.findByEmailAndLozinka(email, lozinka) != null){
            return lekarRepository.findByEmailAndLozinka(email, lozinka);
        }

        if (medicinskaSestraRepository.findByEmailAndLozinka(email, lozinka) != null){
            return medicinskaSestraRepository.findByEmailAndLozinka(email, lozinka);
        }

        if (pacijentRepository.findByEmailAndLozinka(email, lozinka) != null){
            return pacijentRepository.findByEmailAndLozinka(email, lozinka);
        }

        if (administratorKlinickogCentraRepository.findByEmailAndLozinka(email, lozinka) != null){
            return administratorKlinickogCentraRepository.findByEmailAndLozinka(email, lozinka);
        }

        if (administratorKlinikeRepository.findByEmailAndLozinka(email, lozinka) != null){
            return administratorKlinikeRepository.findByEmailAndLozinka(email, lozinka);
        }

        return null;
    }

    public Korisnik findByJbo(String jbo) {
        if (lekarRepository.findByJbo(jbo) != null){
            return lekarRepository.findByJbo(jbo);
        }

        if (medicinskaSestraRepository.findByJbo(jbo) != null){
            return medicinskaSestraRepository.findByJbo(jbo);
        }

        if (pacijentRepository.findByJbo(jbo) != null){
            return pacijentRepository.findByJbo(jbo);
        }

        if (administratorKlinickogCentraRepository.findByJbo(jbo) != null){
            return administratorKlinickogCentraRepository.findByJbo(jbo);
        }

        if (administratorKlinikeRepository.findByJbo(jbo) != null){
            return administratorKlinikeRepository.findByJbo(jbo);
        }

        return null;
    }
}

package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import java.util.List;
import java.util.Optional;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.LekarPacijentPregledDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledIzvestajDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pregled;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.KorisnikRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.PacijentRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.PregledRepository;

import javax.transaction.Transactional;

@Service
public class PregledService {

	@Autowired
	PregledRepository pregledRepo;

	@Autowired
	PacijentRepository pacRepo;

	public List<PregledIzvestajDTO> findPregledeById(Long idLekar) { return pregledRepo.nadjiPoIdLekara(idLekar); }

	@Transactional
	public void updateZavrsen(PregledIzvestajDTO p) {

		Optional<Pregled> pregled = pregledRepo.findById(p.getId());

		if(pregled.isPresent()){
			Pregled noviPregled = pregled.get();
			noviPregled.setStatus("Završen");
			pregledRepo.save(noviPregled);

			return;
		}

		return;
	}

	public Optional<Pregled> findById(Long id) {return pregledRepo.findById(id);}

	public Optional<Pregled> update(predefDTO pregled) {

		System.out.println("Usao si ovde");

		Optional<Pregled> pronadjen=pregledRepo.findById(pregled.getId());
		if (pronadjen!=null) {
			Pregled p=pronadjen.get();
			p.setStatus("Zakazan");
			Optional<Pacijent> pac=pacRepo.findById(pregled.getPacijent_id());
			p.setPacijent(pac.get());
			pregledRepo.save(p);
			return pronadjen;
		}

		return null;
	}

	public List<PregledIzvestajDTO> getZakazane(LekarPacijentPregledDTO lekarPacijentPregledDTO){
		return pregledRepo.getZakazaneByLekarAndPacijent(lekarPacijentPregledDTO.getJboLekara(), lekarPacijentPregledDTO.getJboPacijenta());
	}

	public List<PregledIzvestajDTO> getZakazaneSestra(String jboPacijenta){
		return pregledRepo.getZakazaneByPacijent(jboPacijenta);
	}

}

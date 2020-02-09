package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.posetaLekarKlinikaDTO;
import java.util.List;
import java.util.Optional;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.LekarPacijentPregledDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledIzvestajDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pregled;
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

	public List<PreglediStatusDTO> findAll() {
		List<PreglediStatusDTO> pregledi = pregledRepo.findAllPreglede();
		pregledi.addAll(pregledRepo.findAllPregledeNezakazane());
		return pregledi;
	}

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

	public List<Pregled> findByPacijentId(Long id) {return pregledRepo.findByPacijentId(id);}

	public List<posetaLekarKlinikaDTO> findInfo(Long id){return pregledRepo.findInfo(id);}

	public Pregled update(predefDTO pregled) {
		
		System.out.println("Usao si ovde");

		Optional<Pregled> pronadjen=pregledRepo.findById(pregled.getId());
		if (pronadjen!=null && pronadjen.get().getStatus().equals("Neaktivan")) {
			Pregled p=pronadjen.get();
			p.setStatus("Zakazan");
			Optional<Pacijent> pac=pacRepo.findById(pregled.getPacijent_id());
			p.setPacijent(pac.get());
			pregledRepo.save(p);
			return p;
		}

		return null;
	}

//	public List<Pregled> findHistoryByPacijentId(Long id) {
//		
//		Date date = new Date();
//		String modifiedDate= new SimpleDateFormat("d.M.yyyy.").format(date);
//		System.out.print(modifiedDate);
//
//		return pregledRepo.findHistoryByPacijentId(id);
//	}
	public List<PregledIzvestajDTO> getZakazane(LekarPacijentPregledDTO lekarPacijentPregledDTO){
		return pregledRepo.getZakazaneByLekarAndPacijent(lekarPacijentPregledDTO.getJboLekara(), lekarPacijentPregledDTO.getJboPacijenta());
	}

	public List<PregledIzvestajDTO> getZakazaneSestra(String jboPacijenta){
		return pregledRepo.getZakazaneByPacijent(jboPacijenta);
	}

	public List<PreglediStatusDTO> getPregledeByLekar(String jbo){
		System.out.println(jbo);
		List<PreglediStatusDTO> pregledi = pregledRepo.getPregledeByLekar(jbo);
		List<PreglediStatusDTO> neaktivni = pregledRepo.getNeaktivneByLekar(jbo);

		for(int i = 0;i < neaktivni.size();i++){
			pregledi.add(neaktivni.get(i));
		}

		return pregledi;
	}

}

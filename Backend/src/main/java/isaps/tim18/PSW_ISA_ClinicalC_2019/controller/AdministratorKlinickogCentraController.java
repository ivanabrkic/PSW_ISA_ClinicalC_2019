package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.AdministratorKlinickogCentra;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.AdministratorKlinickogCentraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("administrator_kc")
public class AdministratorKlinickogCentraController {

    @Autowired
    private AdministratorKlinickogCentraService administratorKlinickogCentraService;

    @PostMapping(value = "/update", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdministratorKlinickogCentra> update(@RequestBody AdministratorKlinickogCentra administratorKlinickogCentra) throws Exception {

        administratorKlinickogCentraService.update(administratorKlinickogCentra);

        return new ResponseEntity<>(administratorKlinickogCentra, HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdministratorKlinickogCentra>> getAllAdministratoriKC() {

        List<AdministratorKlinickogCentra> administratorKlinickogCentra = administratorKlinickogCentraService.findAll();

        return new ResponseEntity<>(administratorKlinickogCentra, HttpStatus.OK);
    }

}

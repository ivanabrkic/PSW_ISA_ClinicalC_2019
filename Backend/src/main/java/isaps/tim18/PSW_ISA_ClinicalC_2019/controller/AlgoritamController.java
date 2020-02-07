package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.service.KlinikaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class AlgoritamController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KlinikaService klinikaService;

    /*
     * Logika pocinje da se izvrsava u fiksnim intervalima sa odlozenim pocetkom  izvrsavanja prilikom pokretanja aplikacije.
     *
     * 'fixedRate' se koristi kao indikacija u kojem intervalu ce se pozivati metoda.
     * 'initialDelay' se koristi kao indikacija koliko posle pokretanja aplikacije treba da se ceka do prvog pokretanja metode.
     */
    @Scheduled(initialDelayString = "${greeting.initialdelay}", fixedRateString = "${greeting.fixedrate}")
    public void fixedRateJobWithInitialDelay() throws InterruptedException {

        logger.info("> fixedRateJobWithInitialDelay");

        System.out.println("Prošlo 5 minuta, vidi zahteve!");
        klinikaService.pokreniAlgoritam();

        logger.info("< fixedRateJobWithInitialDelay");
    }
}

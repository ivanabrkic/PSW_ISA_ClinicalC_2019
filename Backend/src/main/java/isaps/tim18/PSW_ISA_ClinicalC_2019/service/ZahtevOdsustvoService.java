package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.Message;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ZahtevOdsustvoService {

    @Autowired
    private ZahtevOdsustvoRepository zahtevOdsustvoRepository;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private LekarRepository lekarRepository;

    @Autowired
    private MedicinskaSestraRepository medicinskaSestraRepository;

    @Autowired
    private GodisnjiOdmorMedSestraRepository godisnjiOdmorMedSestraRepository;

    @Autowired
    private GodisnjiOdmorLekarRepository godisnjiOdmorLekarRepository;

    public ZahtevOdsustvo add(ZahtevOdsustvo zahtevOdsustvo) {
        zahtevOdsustvo.setOveren(false);
        return zahtevOdsustvoRepository.save(zahtevOdsustvo);
    }

    public void delete(ZahtevOdsustvo zo) {
        zahtevOdsustvoRepository.delete(zo);
    }

    public List<ZahtevOdsustvo> findByNeodobren() {
        return zahtevOdsustvoRepository.findAllByOveren(false);
    }

    public ZahtevOdsustvo update(ZahtevOdsustvo zo) {
        Optional<ZahtevOdsustvo> opt = zahtevOdsustvoRepository.findById(zo.getId());

        if (opt.isPresent()) {
            ZahtevOdsustvo apdejtovanZahtev = opt.get();
            apdejtovanZahtev.setOveren(true);
            return apdejtovanZahtev;
        }
        return null;
    }

    public boolean imaGodisnji(ZahtevOdsustvo zahtevOdsustvo){
        if (zahtevOdsustvo.getKorisnikUloga().equals("Lekar")) {
            Lekar lekar = lekarRepository.findByJbo(zahtevOdsustvo.getKorisnikJbo());
            if (godisnjiOdmorLekarRepository.findById(lekar.getId()).isPresent()){
                return true;
            }

        } else {
            MedicinskaSestra medSestra = medicinskaSestraRepository.findByJbo(zahtevOdsustvo.getKorisnikJbo());
            if (godisnjiOdmorMedSestraRepository.findById(medSestra.getId()).isPresent()){
                return true;
            }
        }
        return false;
    }

    public boolean checkIfImaOP(ZahtevOdsustvo zahtevOdsustvo){

        Lekar lekar = lekarRepository.findByJbo(zahtevOdsustvo.getKorisnikJbo());

        int godinaStart = Integer.parseInt(zahtevOdsustvo.getDatumOd().split("\\.")[2]);
        int mesecStart = Integer.parseInt(zahtevOdsustvo.getDatumOd().split("\\.")[1]);
        int danStart = Integer.parseInt(zahtevOdsustvo.getDatumOd().split("\\.")[0]);

        int godinaEnd = Integer.parseInt(zahtevOdsustvo.getDatumDo().split("\\.")[2]);
        int mesecEnd = Integer.parseInt(zahtevOdsustvo.getDatumDo().split("\\.")[1]);
        int danEnd = Integer.parseInt(zahtevOdsustvo.getDatumDo().split("\\.")[0]);

        LocalDateTime start = LocalDateTime.of(godinaStart, mesecStart, danStart, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(godinaEnd, mesecEnd, danEnd, 0, 0, 0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.YYYY.");

        for(LocalDateTime date = start; date.isBefore(end);date =date.plusDays(1))

        {
            String datum = formatter.format(date.toLocalDate());
            String pocetak = "00:00";
            String kraj = "23:59";

            if (!lekarRepository.imaPreglede(lekar.getId(), datum, pocetak, kraj).isEmpty() && !lekarRepository.imaOperacije(lekar.getId(), datum, pocetak, kraj).isEmpty()){
                return true;
            }
        }

        return false;
    }


    public Message checkIfImaDanaAndOP(ZahtevOdsustvo zahtevOdsustvo) {
        if (zahtevOdsustvo.getKorisnikUloga().equals("Lekar")) {
            Lekar lekar = lekarRepository.findByJbo(zahtevOdsustvo.getKorisnikJbo());

            if (lekar.getBrSlobodnihDana() < zahtevOdsustvo.getBrojDana()) {
                if (!checkIfImaOP(zahtevOdsustvo)) {
                    return new Message("Korisnik nema dovoljno slobodnih dana!");
                } else {
                    return new Message("Korisnik nema dovoljno slobodnih dana i ima zakazane operacije i/ili preglede u tom periodu!");
                }
            }

            if (checkIfImaOP(zahtevOdsustvo)) {
                return new Message("Korisnik ima zakazane operacije i/ili preglede u tom periodu!");
            }

        } else {
            MedicinskaSestra medSestra = medicinskaSestraRepository.findByJbo(zahtevOdsustvo.getKorisnikJbo());

            if (medSestra.getBrSlobodnihDana() < zahtevOdsustvo.getBrojDana()) {
                return new Message("Korisnik nema dovoljno slobodnih dana!");
            }
        }

        if (imaGodisnji(zahtevOdsustvo)){
            return new Message("Korisnik je već odredio dane godišnjeg odmora!");
        }

        return new Message("Korisnik ispunjava osnovne uslove za odobrenje godišnjeg odmora!");
    }

    public Message prihvatiZahtev(ZahtevOdsustvo zahtevOdsustvo) {

        int godinaStart = Integer.parseInt(zahtevOdsustvo.getDatumOd().split("\\.")[2]);
        int mesecStart = Integer.parseInt(zahtevOdsustvo.getDatumOd().split("\\.")[1]);
        int danStart = Integer.parseInt(zahtevOdsustvo.getDatumOd().split("\\.")[0]);

        int godinaEnd = Integer.parseInt(zahtevOdsustvo.getDatumDo().split("\\.")[2]);
        int mesecEnd = Integer.parseInt(zahtevOdsustvo.getDatumDo().split("\\.")[1]);
        int danEnd = Integer.parseInt(zahtevOdsustvo.getDatumDo().split("\\.")[0]);

        LocalDate start = LocalDate.of(godinaStart, mesecStart, danStart);
        LocalDate end = LocalDate.of(godinaEnd, mesecEnd, danEnd);

        if (zahtevOdsustvoRepository.findById(zahtevOdsustvo.getId()).isPresent()) {

            String email = " ";
            String subject = "Zahtev za godisnjim odmorom/odsustvom prihvacen!";
            String text = " ";

            if (zahtevOdsustvo.getKorisnikUloga().equals("Lekar")) {
                Lekar lekar = lekarRepository.findByJbo(zahtevOdsustvo.getKorisnikJbo());
                lekar.setBrSlobodnihDana(lekar.getBrSlobodnihDana() - zahtevOdsustvo.getBrojDana());
                lekarRepository.save(lekar);

                GodisnjiOdmorLekar god = new GodisnjiOdmorLekar();
                god.setId(lekar.getId());
                god.setDatumOd(start);
                god.setDatumDo(end);
                godisnjiOdmorLekarRepository.save(god);

                email = lekar.getEmail();
                text = "Vas zahtev je prihvacen za period od " + zahtevOdsustvo.getDatumOd() + " do " + zahtevOdsustvo.getDatumDo() + "."
                        + "Preostalo Vam je " + lekar.getBrSlobodnihDana() + " slobodnih dana.";
            } else {
                MedicinskaSestra medSestra = medicinskaSestraRepository.findByJbo(zahtevOdsustvo.getKorisnikJbo());
                medSestra.setBrSlobodnihDana(medSestra.getBrSlobodnihDana() - zahtevOdsustvo.getBrojDana());
                medicinskaSestraRepository.save(medSestra);

                GodisnjiOdmorMedSestra god = new GodisnjiOdmorMedSestra();
                god.setId(medSestra.getId());
                god.setDatumOd(start);
                god.setDatumDo(end);
                godisnjiOdmorMedSestraRepository.save(god);

                email = medSestra.getEmail();
                text = "Vas zahtev je prihvacen za period od " + zahtevOdsustvo.getDatumOd() + " do " + zahtevOdsustvo.getDatumDo() + "."
                        + "Preostalo Vam je " + medSestra.getBrSlobodnihDana() + " slobodnih dana.";
            }

            mailSenderService.sendSimpleMessage(email, subject, text);
            zahtevOdsustvoRepository.deleteById(zahtevOdsustvo.getId());
        }

        return new Message("Zahtev za odsustvo/godišnji odmor uspešno prihvaćen! Korisnik će biti obavešten putem mejla!");
    }

    public Message odbijZahtev(ZahtevOdsustvo zahtevOdsustvo) {
        if (zahtevOdsustvoRepository.findById(zahtevOdsustvo.getId()).isPresent()) {

            String email = " ";
            String subject = "Zahtev za godisnjim odmorom/odsustvom odbijen!";
            String text = " ";

            if (zahtevOdsustvo.getKorisnikUloga().equals("Lekar")) {
                Lekar lekar = lekarRepository.findByJbo(zahtevOdsustvo.getKorisnikJbo());
                email = lekar.getEmail();
            } else {
                MedicinskaSestra medSestra = medicinskaSestraRepository.findByJbo(zahtevOdsustvo.getKorisnikJbo());
                email = medSestra.getEmail();
            }

            text = "Vas zahtev je odbijen za period od " + zahtevOdsustvo.getDatumOd() + " do " + zahtevOdsustvo.getDatumDo() + "."
                    + "Razlog je : " + zahtevOdsustvo.getRazlog();

            mailSenderService.sendSimpleMessage(email, subject, text);
            zahtevOdsustvoRepository.deleteById(zahtevOdsustvo.getId());

            return new Message("Zahtev za odsustvo/godišnji odmor uspešno odbijen! Korisnik će biti obavešten putem mejla!");
        }
        return new Message("Zahtev ne postoji!");
    }

    public List<ZahtevOdsustvo> getAllByKlinikaId(Long klinikaId) {
        return zahtevOdsustvoRepository.findAllByKlinikaId(klinikaId);
    }
}

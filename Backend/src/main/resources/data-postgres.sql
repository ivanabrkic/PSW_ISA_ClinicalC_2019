insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika,prvo_logovanje) values ('Backa Palanka', 'Srbija', 'Save Kovacevica 47', 1234567891111, True, 'ivanabrkic@mailsac.com', 'Ivana', '0652754579', '12345678b', 'Brkic', 'Lekar',false);
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika,prvo_logovanje) values ('Indjija', 'Srbija', 'Glavna 38', 9876543211111, True,'vladimirpopovic@mailsac.com', 'Vladimir', '0652754579', '12345678b', 'Popovic', 'Lekar',false);
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika,prvo_logovanje) values ('Sabac', 'Srbija', 'Mladena Djuricica 11', 1231231231111, true, 'tamaralazarevic@mailsac.com', 'Tamara', '0652754579', '12345678b', 'Lazarevic', 'Pacijent',false);
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika,prvo_logovanje) values ('Novi Sad', 'Srbija', 'Mise Dimitrijevica 51', 1233211231111, True, 'anamarija@mailsac.com', 'Ana-Marija', '0652754579', '12345678b', 'Buhmiler', 'Administrator klinike',false);
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika,prvo_logovanje) values ('Nova Pazova', 'Srbija', 'Vojvode Misica 30', 6541236541111, True,'nikolinMejl@gmail.com', 'Nikola', '0652754579', '12345678b', 'Milosevic', 'Administrator klinike',false);
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika,prvo_logovanje) values ('Beocin', 'Srbija', 'Zlatiborska 40', 8766566541111, True ,'milanlux@mailsac.com', 'Milan', '0652754579', '12345678b', 'Lukovic', 'Pacijent',false);
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika,prvo_logovanje) values ('Brazilija', 'Srbija', 'Tarska 40', 3423784911111, FALSE, 'n.milosevic@gmail.com', 'Nikola', '0652754579', '12345678b', 'Milo', 'Pacijent',false);
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika,prvo_logovanje) values ('Backa Palanka', 'Srbija', 'Save Kovacevica 50', 6452389671111, True, 'ivan@gmail.com', 'Ivan', '0652754579', '12345678b', 'Maksimovic', 'Medicinska sestra',false);
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika,prvo_logovanje) values ('Indjija', 'Srbija', 'Glavna 38', 6459872341111, True,'vladimirpopovic2306@gmail.com', 'Vladimir', '0652754579', '12345678b', 'Popov', 'Medicinska sestra',false);
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika,prvo_logovanje) values ('Nova Pazova', 'Srbija', 'Glavna 38', 9879879871111, True,'adminkc@gmail.com', 'Nikola', '0652754579', '12345678b', 'Milosev', 'Administrator klinickog centra',false);
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika,prvo_logovanje) values ('Beograd', 'Srbija', 'Masarikova 11', 5476543211111, True,'svetaprotic@mailsac.com', 'Sveta', '0622755579', '12345678b', 'Protic', 'Lekar',false);

insert into klinika (id, naziv, adresa, grad, drzava, email, kontakt_telefon, ocena,opis) values (1,'Laza Lazarevic', 'Mise Dimitrijevica 21', 'Novi Sad', 'Srbija', 'laza@gmail.com', '021/456-435', 4.3,'Mnogo dobra klinika');
insert into klinika (id, naziv, adresa, grad, drzava, email, kontakt_telefon, ocena,opis) values (2,'Ivo Andric', 'St John 43a', 'London', 'United Kingdom', 'ivoandric@gmail.com', '021/111-435', 3.2,'Jos bolja klinika');
insert into klinika (id, naziv, adresa, grad, drzava, email, kontakt_telefon, ocena,opis) values (3,'Pera Peric', 'Sukablablu 76', 'Lenjingrad', 'Rusija', 'perapera@gmail.com', '021/456-999', 2.2,'Najbolja klinika');

insert into sala (naziv, broj, klinika_id) values ('Velika sala', '1A', 1);
insert into sala (naziv, broj, klinika_id) values ('Mala sala', '2A', 1);
insert into sala (naziv, broj, klinika_id) values ('Velika sala', '1A', 2);
insert into sala (naziv, broj, klinika_id) values ('Mala sala', '2A', 2);

insert into lekar (lekar_id, br_slobodnih_dana, klinika_id, radno_vreme, ocena, specijalizacija) values (1, 33, 1, 'Prva smena od 8:00 do 16:00', 3, 'Kardiolog');
insert into lekar (lekar_id, br_slobodnih_dana, klinika_id, radno_vreme, ocena, specijalizacija) values (2, 54, 1, 'Prva smena od 8:00 do 16:00' , 4.5, 'Anesteziolog');
insert into lekar (lekar_id, br_slobodnih_dana, klinika_id, radno_vreme, ocena, specijalizacija) values (11, 22, 1, 'Prva smena od 8:00 do 16:00' , 5, 'Zubar');

insert into pacijent (pacijent_id) values (6);
insert into pacijent (pacijent_id) values (7);
insert into pacijent (pacijent_id) values (3);

insert into zkarton(broj_zk, pacijent_id) values (1, 6);
insert into zkarton(broj_zk, pacijent_id) values (2, 7);
insert into zkarton(broj_zk, pacijent_id) values (3, 3);

insert into administrator_klinike (admink_id, klinika_id) values (4, 1);
insert into administrator_klinike (admink_id, klinika_id) values (5, 2);

insert into pacijenti_klinike (pacijent_id, klinika_id) values (6, 1);
insert into pacijenti_klinike (pacijent_id, klinika_id) values (6, 2);
insert into pacijenti_klinike (pacijent_id, klinika_id) values (7, 1);
insert into pacijenti_klinike (pacijent_id, klinika_id) values (7, 2);
insert into pacijenti_klinike (pacijent_id, klinika_id) values (7, 3);
insert into medicinska_sestra (meds_id, br_slobodnih_dana, klinika_id, radno_vreme, ocena, specijalizacija) values (9, 89, 1, 'Prva smena od 8:00 do 16:00', 1.3, 'Nema');
insert into medicinska_sestra (meds_id, br_slobodnih_dana, klinika_id, radno_vreme, ocena, specijalizacija) values (8, 56, 3, 'Prva smena od 8:00 do 16:00', 5.0, 'Nema');
insert into administrator_klinickog_centra (adminkc_id) values (10);

insert into dijagnoze (sifra, sifradijagnoze, naziv) values (100,'1sf3','depresija');
insert into dijagnoze (sifra, sifradijagnoze, naziv) values (200,'1sf124', 'anksioznost');

insert into cenovnik (naziv, cena, klinika_id, specijalizacija) values ('Popravka zuba', 3000, 1, 'Zubar');
insert into cenovnik (naziv, cena, klinika_id, specijalizacija) values ('Ugradnja stenta', 10000, 1, 'Kardiolog');
insert into cenovnik (naziv, cena, klinika_id, specijalizacija) values ('Vađenje krajnika', 1500, 1, 'Opšta praksa');
insert into cenovnik (naziv, cena, klinika_id, specijalizacija) values ('EKG', 5000, 1, 'Neurolog');
insert into cenovnik (naziv, cena, klinika_id, specijalizacija) values ('Opšti pregled', 1000, 1, 'Opšta praksa');
insert into cenovnik (naziv, cena, klinika_id, specijalizacija) values ('Ultrazvuk srca', 6000, 1, 'Kardiolog');

insert into pregled (pocetak, kraj, datum, pacijent_id, lekar_id, sala_id, id_stavke, status) values ('14:00', '14:30', '4.1.2020.', 3, 2, 1, 4, 'Završen');
insert into pregled (pocetak, kraj, datum, pacijent_id, lekar_id, sala_id, id_stavke, status) values ('16:00', '16:30', '29.1.2020.', 7, 2, 2, 4, 'Završen');
insert into pregled (pocetak, kraj, datum, pacijent_id, lekar_id, sala_id, id_stavke, status) values ('13:00', '13:30', '31.1.2020.', 3, 1, 2, 4, 'Završen');
insert into pregled (pocetak, kraj, datum, pacijent_id, lekar_id, sala_id, id_stavke, status) values ('13:00', '13:30', '1.2.2020.', 7, 1, 2, 4, 'Završen');

insert into pregled (pocetak, kraj, datum, pacijent_id, lekar_id, sala_id, id_stavke, status) values ('17:30', '23:45', '6.2.2020.', 6, 1, 3, 1, 'Završen');
insert into pregled (pocetak, kraj, datum, pacijent_id, lekar_id, sala_id, id_stavke, status) values ('02:56', '12:00', '9.2.2020.', 6, 1, 1, 1, 'Završen');
insert into pregled (pocetak, kraj, datum, pacijent_id, lekar_id, sala_id, id_stavke, status) values ('13:00', '13:30', '12.2.2020.', 7, 1, 2, 4, 'Završen');
insert into pregled (pocetak, kraj, datum, pacijent_id, lekar_id, sala_id, id_stavke, status) values ('14:00', '14:30', '14.2.2020.', 7, 1, 2, 4, 'Završen');

insert into pregled (pocetak, kraj, datum, lekar_id, sala_id, id_stavke, popust, status) values ('08:00', '08:15', '20.3.2020.', 1, 2, 6, 60, 'Neaktivan');
insert into pregled (pocetak, kraj, datum, lekar_id, sala_id, id_stavke, popust, status) values ('08:30', '08:45', '20.3.2020.',2, 2, 1, 10, 'Neaktivan');
insert into pregled (pocetak, kraj, datum, lekar_id, sala_id, id_stavke, popust, status) values ('09:00', '09:15', '1.1.2020.',2, 1, 1, 20, 'Neaktivan');

insert into operacija (pocetak, kraj, datum, pacijent_id, lekar_id, sala_id, id_stavke, status) values ('13:00', '13:15', '27.2.2020.', 3, 1, 2, 2, 'Zakazan');
insert into operacija (pocetak, kraj, datum, pacijent_id, lekar_id, sala_id, id_stavke, status) values ('13:00', '13:15', '28.2.2020.', 3, 1, 2, 2, 'Zakazan');
insert into operacija (pocetak, kraj, datum, pacijent_id, lekar_id, sala_id, id_stavke, status) values ('13:00', '13:15', '26.2.2020.', 3, 1, 2, 2, 'Zakazan');

insert into zahtev (id_stavke, stavka, tip_posete, tip_posiljaoca,  posiljalac_jbo, posiljalac_ime_prezime, jbo_pacijenta, jbo_lekara, datum, pocetak, kraj, dodatne_informacije, id_klinike) values
(6, 'Ultrazvuk srca', 'Pregled', 'Lekar', '1234567891111', 'Ivana Brkic', '8766566541111', '1234567891111','7.3.2020.', '13:00', '13:15', 'Nema dodatnih informacija.', 1);
insert into zahtev (id_stavke, stavka, tip_posete, tip_posiljaoca, posiljalac_jbo, posiljalac_ime_prezime, jbo_pacijenta, jbo_lekara, datum, pocetak, kraj, dodatne_informacije, id_klinike) values
(2, 'Ugradnja stenta', 'Operacija', 'Lekar', '1234567891111', 'Ivana Brkic', '1231231231111', '1234567891111', '7.3.2020.', '08:15', '10:00', 'U pitanju je operacija na srcu. Potreban mi je anesteziolog da izvršim operaciju.', 1);

insert into lekovi(sifra, naziv) values ('ry71h', 'bromazemam');
insert into lekovi(sifra, naziv) values ('r451h', 'sinacilin');
insert into lekovi(sifra, naziv) values ('r211h', 'gentamicin');



insert into recept(overen, pacijent_id, med_sestra) values (false, 3, null);
insert into recept(overen, pacijent_id, med_sestra) values (false, 6, null);
insert into recept(overen, pacijent_id,med_sestra) values (false, 6, null);

insert into recept_lekovi(recept_id, lek_id) values (1, 1);
insert into recept_lekovi(recept_id, lek_id) values (1, 2);
insert into recept_lekovi(recept_id, lek_id) values (1, 3);
insert into recept_lekovi(recept_id, lek_id) values (2, 2);
insert into recept_lekovi(recept_id, lek_id) values (3, 3);
insert into recept_lekovi(recept_id, lek_id) values (3, 1);

insert into ocene_lekari values (1,6,3);
insert into ocene_lekari values (2,3,5);
insert into ocene_lekari values (2,7,4);

insert into opsti_izvestaj(id, alergije_lek, dioptrija, krvna_grupa, tezina, visina) values (1, 'bromazepam', '+2', 'B+', '78', '185');
insert into opsti_izvestaj(id, alergije_lek, dioptrija, krvna_grupa, tezina, visina) values (2, '', '0.3', '0+', '69', '178');
insert into opsti_izvestaj(id, alergije_lek, dioptrija, krvna_grupa, tezina, visina) values (3, '', '0', 'A-', '65', '161');

insert into zkartoni_opsti_izvestaji(opsti_izvestaj_id, zkarton) values (2,1);

insert into zkarton_dijagnoze(zkarton_id,dijagnoze_id) values (3,100);
insert into zkarton_dijagnoze(zkarton_id,dijagnoze_id) values (1,200);

insert into izvestaj(datum_pregleda, izvestaj, lekar_id, zkarton, recept_id) values (
'23.02.2020', 'Pacijent ima problema sa disanjem.', 1, 1, 3
);
insert into izvestaj(datum_pregleda, izvestaj, lekar_id, zkarton, recept_id) values (
'24.02.2020', 'Pacijent ima ubrzan rad srca.', 2, 1, 2
);

insert into izvestaj(datum_pregleda, izvestaj, lekar_id, zkarton, recept_id) values (
'22.02.2020', 'Pacijentov oporavak je zavrsen', 2, 1, 1
);

insert into zahtev_odsustvo(jbo, ime, prezime, uloga, od_datum, do_datum, opis, broj_dana, overen, klinika_id) values (1234567891111, 'Ivana', 'Brkic', 'Lekar', '26.2.2020.', '1.3.2020.', 'Dete mi je bolesno. Molim za odsustvo 10 dana.', 5, false, 1);
insert into zahtev_odsustvo(jbo, ime, prezime, uloga, od_datum, do_datum, opis, broj_dana, overen, klinika_id) values (1234567891111, 'Ivana', 'Brkic', 'Lekar', '1.3.2020.', '1.5.2020.', 'Dete mi je bolesno. Molim za odsustvo 10 dana.', 60, false, 1);

insert into godisnji_odmor_lekar(lekar_id,datum_od,datum_do) values (11,'2020-2-28','2020-3-12');


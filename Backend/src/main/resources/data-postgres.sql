insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika) values ('Backa Palanka', 'Srbija', 'Save Kovacevica 47', 1234567891111, True, 'ivanabrkic192@gmail.com', 'Ivana', '0652754579', '12345678b', 'Brkic', 'Lekar');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika) values ('Indjija', 'Srbija', 'Glavna 38', 9876543211111, True,'vladimirpopovic@gmail.com', 'Vladimir', '0652754579', '12345678b', 'Popovic', 'Lekar');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika) values ('Sabac', 'Srbija', 'Mladena Djuricica 11', 1231231231111, FALSE , 'tamaralazarevic@gmail.com', 'Tamara', '0652754579', '12345678b', 'Lazarevic', 'Pacijent');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika) values ('Novi Sad', 'Srbija', 'Mise Dimitrijevica 51', 1233211231111, True, 'anamarija@gmail.com', 'Ana-Marija', '0652754579', '12345678b', 'Buhmiler', 'Administrator klinike');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika) values ('Nova Pazova', 'Srbija', 'Vojvode Misica 30', 6541236541111, True,'nikola.milosevic0111@gmail.com', 'Nikola', '0652754579', '12345678b', 'Milosevic', 'Administrator klinike');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika) values ('Beocin', 'Srbija', 'Zlatiborska 40', 8766566541111, True ,'milanlux@gmail.com', 'Milan', '0652754579', '12345678b', 'Lukovic', 'Pacijent');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika) values ('Brazilija', 'Srbija', 'Tarska 40', 3423784911111, FALSE, 'n.milosevic@gmail.com', 'Nikola', '0652754579', '12345678b', 'Milo', 'Pacijent');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika) values ('Backa Palanka', 'Srbija', 'Save Kovacevica 50', 6452389671111, True, 'ivan@gmail.com', 'Ivan', '0652754579', '12345678b', 'Maksimovic', 'Medicinska sestra');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika) values ('Indjija', 'Srbija', 'Glavna 38', 6459872341111, True,'vladimirpopovic2306@gmail.com', 'Vladimir', '0652754579', '12345678b', 'Popov', 'Medicinska sestra');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, lozinka, prezime, tip_korisnika) values ('Nova Pazova', 'Srbija', 'Glavna 38', 9879879871111, True,'adminkc@gmail.com', 'Nikola', '0652754579', '12345678b', 'Milosev', 'Administrator klinickog centra');

insert into klinika (id, naziv, adresa, grad, drzava, email, kontakt_telefon, ocena) values (1,'Laza Lazarevic', 'Mise Dimitrijevica 21', 'Novi Sad', 'Srbija', 'laza@gmail.com', '021/456-435', 4.3);
insert into klinika (id, naziv, adresa, grad, drzava, email, kontakt_telefon, ocena) values (2,'Ivo Andric', 'St John 43a', 'London', 'United Kingdom', 'ivoandric@gmail.com', '021/111-435', 3.2);
insert into klinika (id, naziv, adresa, grad, drzava, email, kontakt_telefon, ocena) values (3,'Pera Peric', 'Sukablablu 76', 'Lenjingrad', 'Rusija', 'perapera@gmail.com', '021/456-999', 2.2);

insert into sala (naziv, broj, klinika_id) values ('Velika sala', '1A', 1);
insert into sala (naziv, broj, klinika_id) values ('Mala sala', '2A', 1);
insert into sala (naziv, broj, klinika_id) values ('Velika sala', '1A', 2);
insert into sala (naziv, broj, klinika_id) values ('Mala sala', '2A', 2);


insert into lekar (lekar_id, br_slobodnih_dana, klinika_id, radno_vreme, ocena, specijalizacija) values (1, 33, 1, 'Prva smena od 8:00 do 16:00', 2.3, 'Kardiolog');
insert into lekar (lekar_id, br_slobodnih_dana, klinika_id, radno_vreme, ocena, specijalizacija) values (2, 54, 1, 'Prva smena od 8:00 do 16:00' , 4.5, 'Zubar');

insert into administrator_klinike (admink_id, klinika_id) values (4, 1);
insert into administrator_klinike (admink_id, klinika_id) values (5, 2);
insert into pacijent (pacijent_id) values (6);
insert into pacijent (pacijent_id) values (7);
insert into pacijent (pacijent_id) values (3);
insert into pacijenti_klinike (pacijent_id, klinika_id) values (6, 1);
insert into pacijenti_klinike (pacijent_id, klinika_id) values (6, 2);
insert into pacijenti_klinike (pacijent_id, klinika_id) values (7, 1);
insert into pacijenti_klinike (pacijent_id, klinika_id) values (7, 2);
insert into pacijenti_klinike (pacijent_id, klinika_id) values (7, 3);
insert into medicinska_sestra (meds_id, br_slobodnih_dana, klinika_id, radno_vreme, ocena, specijalizacija) values (9, 89, 1, 'Prva smena od 8:00 do 16:00', 1.3, 'Nema');
insert into medicinska_sestra (meds_id, br_slobodnih_dana, klinika_id, radno_vreme, ocena, specijalizacija) values (10, 56, 3, 'Prva smena od 8:00 do 16:00', 5.0, 'Nema');
insert into administrator_klinickog_centra (adminkc_id) values (10);

insert into posete (id, pacijent_id, datum, pocetak, kraj, tipposete) values (1, 3, '12/12/2019','10:10','11:00','pregled');
insert into posete (id, pacijent_id, datum, pocetak, kraj, tipposete) values (2, 3, '13/12/2019','11:10','13:50','operacija');
insert into posete (id, pacijent_id, datum, pocetak, kraj, tipposete) values (3, 7, '14/12/2019','11:10','11:00','pregled');

insert into dijagnoze (sifra, sifradijagnoze, naziv) values (100,'1sf3','depresija');
insert into dijagnoze (sifra, sifradijagnoze, naziv) values (200,'1sf124', 'anksioznost');

insert into zkarton(id,pacijent_id) values (1,3);

insert into zkarton_dijagnoze(zdravstveni_karton_id,dijagnoze_sifra) values (1,100);

insert into cenovnik (naziv, cena, klinika_id, specijalizacija) values ('Popravka zuba', 3000, 1, 'Zubar');
insert into cenovnik (naziv, cena, klinika_id, specijalizacija) values ('Ugradnja stenta', 10000, 1, 'Kardiolog');
insert into cenovnik (naziv, cena, klinika_id, specijalizacija) values ('Vađenje krajnika', 1500, 1, 'Opšta praksa');
insert into cenovnik (naziv, cena, klinika_id, specijalizacija) values ('EKG', 5000, 1, 'Neurolog');
insert into cenovnik (naziv, cena, klinika_id, specijalizacija) values ('Opšti pregled', 1000, 1, 'Opšta praksa');
insert into cenovnik (naziv, cena, klinika_id, specijalizacija) values ('Ultrazvuk srca', 6000, 1, 'Kardiolog');


insert into pregled (pocetak, kraj, datum, pacijent_id, lekar_id, sala_id, id_stavke, status) values ('12:00', '12:45', '30.1.2020.', 6, 1, 1, 1, 'Zakazan');
insert into pregled (pocetak, kraj, datum, pacijent_id, lekar_id, sala_id, id_stavke, status) values ('13:00', '13:30', '31.1.2020.', 7, 1, 2, 4, 'Zakazan');

insert into pregled (pocetak, kraj, datum, lekar_id, sala_id, id_stavke, popust, status) values ('08:00', '08:15', '5.2.2020.', 1, 2, 6, 60, 'Neaktivan');
insert into pregled (pocetak, kraj, datum, lekar_id, sala_id, id_stavke, popust, status) values ('08:30', '08:45', '5.2.2020.',2, 2, 1, 10, 'Neaktivan');
insert into pregled (pocetak, kraj, datum, lekar_id, sala_id, id_stavke, popust, status) values ('09:00', '09:15', '5.2.2020.',2, 1, 1, 20, 'Neaktivan');

insert into operacija (pocetak, kraj, datum, pacijent_id, lekar_id, sala_id, id_stavke) values ('13:00', '13:15', '1.2.2020.', 7, 1, 2, 2);
insert into operacija (pocetak, kraj, datum, pacijent_id, lekar_id, sala_id, id_stavke) values ('13:00', '13:15', '1.2.2020.', 7, 2, 2, 2);

insert into zahtev (id_stavke, stavka, tip_posete, posiljalac_jbo, posiljalac_ime_prezime, jbo_pacijenta, datum, pocetak, kraj, dodatne_informacije, id_klinike) values
(6, 'Ultrazvuk srca', 'Pregled', '1234567891111', 'Ivana Brkic', '8766566541111', '30.1.2020.', '13:00', '13:15', 'Nema dodatnih informacija.', 1);
insert into zahtev (id_stavke, stavka, tip_posete, posiljalac_jbo, posiljalac_ime_prezime, jbo_pacijenta, datum, pocetak, kraj, dodatne_informacije, id_klinike) values
(2, 'Ugradnja stenta', 'Operacija', '1234567891111', 'Ivana Brkic', '1231231231111', '3.2.2020.', '12:15', '16:00', 'U pitanju je operacija na srcu. Potreban mi je anesteziolog da izvršim operaciju.', 1);

insert into lekovi(sifra, naziv) values ('ry71h', 'bromazemam');
insert into lekovi(sifra, naziv) values ('r451h', 'sinacilin');
insert into lekovi(sifra, naziv) values ('r211h', 'gentamicin');

insert into recept(broj, overen, pacijent_id, med_sestra) values (23, false, 3, null);
insert into recept(broj, overen, pacijent_id, med_sestra) values (29, false, 6, null);
insert into recept(broj, overen, pacijent_id, med_sestra) values (28, false, 6, null);

insert into recept_lekovi(recept_id, lekovi_id) values (1, 1);
insert into recept_lekovi(recept_id, lekovi_id) values (1, 2);
insert into recept_lekovi(recept_id, lekovi_id) values (1, 3);
insert into recept_lekovi(recept_id, lekovi_id) values (2, 2);
insert into recept_lekovi(recept_id, lekovi_id) values (3, 3);
insert into recept_lekovi(recept_id, lekovi_id) values (3, 1);
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Backa Palanka', 'Srbija', 'Save Kovacevica 47', 123456789, True, 'ivanabrkic192@gmail.com', 'Ivana', '0652754579', 'ika', '12345678b', 'Brkic', 'Lekar');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Indjija', 'Srbija', 'Glavna 38', 987654321, True,'vladimirpopovic@gmail.com', 'Vladimir', '0652754579', 'vlada', '12345678b', 'Popovic', 'Lekar');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Sabac', 'Srbija', 'Mladena Djuricica 11', 123123123, FALSE , 'tamaralazarevic@gmail.com', 'Tamara', '0652754579', 'tesla', '12345678b', 'Lazarevic', 'Lekar');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Novi Sad', 'Srbija', 'Mise Dimitrijevica 51', 123321123, True, 'anamarija@gmail.com', 'Ana-Marija', '0652754579', 'anacrtica', '12345678b', 'Buhmiler', 'Administrator klinike');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Nova Pazova', 'Srbija', 'Vojvode Misica 30', 654123654, True,'nikola.milosevic0111@gmail.com', 'Nikola', '0652754579', 'vanelee', '12345678b', 'Milosevic', 'Administrator klinike');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Beocin', 'Srbija', 'Zlatiborska 40', 876656654, True ,'milanlux@gmail.com', 'Milan', '0652754579', 'lux', '12345678b', 'Lukovic', 'Pacijent');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Brazilija', 'Srbija', 'Tarska 40', 342378491, False, 'n.milosevic0111@gmail.com', 'Milance', '0652754579', 'mix', '12345678b', 'Lukic', 'Pacijent');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Stara Pazova', 'Srbija', 'Vojvode Misica 30', 654132654, True ,'milanlukic@gmail.com', 'Nikola', '0652754579', 'vaneleeca', '12345678b', 'Milosevic', 'Administrator klinike');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Backa Palanka', 'Srbija', 'Save Kovacevica 50', 645238967, True, 'ivanabrkic@gmail.com', 'Ivanica', '0652754579', 'maxiixa', '12345678b', 'Maksimovica', 'Medicinska sestra');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Indjija', 'Srbija', 'Glavna 38', 645987234, True,'vladimirpopovic2306@gmail.com', 'Vladimirko', '0652754579', 'popvlada', '12345678b', 'Popov', 'Medicinska sestra');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Nova Pazova', 'Srbija', 'Glavna 38', 987987987, True,'n.milosevicNN@gmail.com', 'Vladimirko', '0652754579', 'Vaneleey', '12345678b', 'Popov', 'Administrator klinickog centra');

insert into klinika (id, naziv, adresa, grad, drzava, email, kontakt_telefon, ocena) values (1,'Laza Lazarevic', 'Mise Dimitrijevica 21', 'Novi Sad', 'Srbija', 'laza@gmail.com', '021/456-435', 4.3);
insert into klinika (id, naziv, adresa, grad, drzava, email, kontakt_telefon, ocena) values (2,'Ivo Andric', 'St John 43a', 'London', 'United Kingdom', 'ivoandric@gmail.com', '021/111-435', 3.2);
insert into klinika (id, naziv, adresa, grad, drzava, email, kontakt_telefon, ocena) values (3,'Pera Peric', 'Sukablablu 76', 'Lenjingrad', 'Rusija', 'perapera@gmail.com', '021/456-999', 2.2);

insert into lekar (lekar_id, br_slobodnih_dana, klinika_id) values (1, 33, 1);
insert into lekar (lekar_id, br_slobodnih_dana, klinika_id) values (2, 54, 1);
insert into lekar (lekar_id, br_slobodnih_dana, klinika_id) values (3, 12, 2);
insert into administrator_klinike (admink_id, klinika_id) values (4, 1);
insert into administrator_klinike (admink_id, klinika_id) values (5, 2);
insert into administrator_klinike (admink_id, klinika_id) values (8, 3);
insert into pacijent (pacijent_id) values (6);
insert into pacijent (pacijent_id) values (7);
insert into pacijenti_klinike (pacijent_id, klinika_id) values (6, 1);
insert into pacijenti_klinike (pacijent_id, klinika_id) values (6, 2);
insert into pacijenti_klinike (pacijent_id, klinika_id) values (7, 1);
insert into pacijenti_klinike (pacijent_id, klinika_id) values (7, 2);
insert into pacijenti_klinike (pacijent_id, klinika_id) values (7, 3);
insert into medicinska_sestra (meds_id, br_slobodnih_dana, klinika_id) values (9, 89, 1);
insert into medicinska_sestra (meds_id, br_slobodnih_dana, klinika_id) values (10, 56, 3);
insert into administrator_klinickog_centra (adminkc_id) values (11);




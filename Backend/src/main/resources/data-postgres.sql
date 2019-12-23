insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Backa Palanka', 'Srbija', 'Save Kovacevica 47', 1234567891111, True, 'ivanabrkic192@gmail.com', 'Ivana', '0652754579', 'ika', '12345678b', 'Brkic', 'Lekar');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Indjija', 'Srbija', 'Glavna 38', 9876543211111, True,'vladimirpopovic@gmail.com', 'Vladimir', '0652754579', 'vlada', '12345678b', 'Popovic', 'Lekar');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Sabac', 'Srbija', 'Mladena Djuricica 11', 1231231231111, FALSE , 'tamaralazarevic@gmail.com', 'Tamara', '0652754579', 'tesla', '12345678b', 'Lazarevic', 'Pacijent');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Novi Sad', 'Srbija', 'Mise Dimitrijevica 51', 1233211231111, True, 'anamarija@gmail.com', 'Ana-Marija', '0652754579', 'anacrtica', '12345678b', 'Buhmiler', 'Administrator klinike');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Nova Pazova', 'Srbija', 'Vojvode Misica 30', 6541236541111, True,'nikola.milosevic0111@gmail.com', 'Nikola', '0652754579', 'vanelee', '12345678b', 'Milosevic', 'Administrator klinike');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Beocin', 'Srbija', 'Zlatiborska 40', 8766566541111, True ,'milanlux@gmail.com', 'Milan', '0652754579', 'lux', '12345678b', 'Lukovic', 'Pacijent');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Brazilija', 'Srbija', 'Tarska 40', 3423784911111, FALSE, 'n.milosevic0111@gmail.com', 'Nikola', '0652754579', 'mix', '12345678b', 'Milo', 'Pacijent');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Stara Pazova', 'Srbija', 'Vojvode Misica 30', 6541326541111, True ,'milanlukic@gmail.com', 'Milan', '0652754579', 'vaneleeca', '12345678b', 'Lukic', 'Administrator klinike');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Backa Palanka', 'Srbija', 'Save Kovacevica 50', 6452389671111, True, 'ivan@gmail.com', 'Ivan', '0652754579', 'maxiixa', '12345678b', 'Maksimovic', 'Medicinska sestra');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Indjija', 'Srbija', 'Glavna 38', 6459872341111, True,'vladimirpopovic2306@gmail.com', 'Vladimirko', '0652754579', 'popvlada', '12345678b', 'Popov', 'Medicinska sestra');
insert into korisnik (grad, drzava, adresa, jbo, aktivnost_naloga, email, ime, kontakt_telefon, kor_ime, lozinka, prezime, tip_korisnika) values ('Nova Pazova', 'Srbija', 'Glavna 38', 9879879871111, True,'n.milosevicNN@gmail.com', 'Nikola', '0652754579', 'Vaneleey', '12345678b', 'Milosev', 'Administrator klinickog centra');

insert into klinika (id, naziv, adresa, grad, drzava, email, kontakt_telefon, ocena) values (1,'Laza Lazarevic', 'Mise Dimitrijevica 21', 'Novi Sad', 'Srbija', 'laza@gmail.com', '021/456-435', 4.3);
insert into klinika (id, naziv, adresa, grad, drzava, email, kontakt_telefon, ocena) values (2,'Ivo Andric', 'St John 43a', 'London', 'United Kingdom', 'ivoandric@gmail.com', '021/111-435', 3.2);
insert into klinika (id, naziv, adresa, grad, drzava, email, kontakt_telefon, ocena) values (3,'Pera Peric', 'Sukablablu 76', 'Lenjingrad', 'Rusija', 'perapera@gmail.com', '021/456-999', 2.2);

insert into lekar (lekar_id, br_slobodnih_dana, klinika_id, radno_vreme, ocena) values (1, 33, 1, 'Ponedeljak - 10:00 - 14:00/Utorak - 7:00:16:00/Sreda - 12:00 - 20:00/Četvrtak - 19:00 - 1:00/Petak - 13:00 - 21:00', 2.3);
insert into lekar (lekar_id, br_slobodnih_dana, klinika_id, radno_vreme, ocena) values (2, 54, 1, 'Ponedeljak - 12:00 - 14:00/Utorak - 9:00:17:00/Sreda - 13:00 - 21:00/Četvrtak - 20:00 - 1:00/Petak - 8:00 - 12:00', 4.5);
insert into administrator_klinike (admink_id, klinika_id) values (4, 1);
insert into administrator_klinike (admink_id, klinika_id) values (5, 2);
insert into administrator_klinike (admink_id, klinika_id) values (8, 3);
insert into pacijent (pacijent_id) values (6);
insert into pacijent (pacijent_id) values (7);
insert into pacijent (pacijent_id) values (3);
insert into pacijenti_klinike (pacijent_id, klinika_id) values (6, 1);
insert into pacijenti_klinike (pacijent_id, klinika_id) values (6, 2);
insert into pacijenti_klinike (pacijent_id, klinika_id) values (7, 1);
insert into pacijenti_klinike (pacijent_id, klinika_id) values (7, 2);
insert into pacijenti_klinike (pacijent_id, klinika_id) values (7, 3);
insert into medicinska_sestra (meds_id, br_slobodnih_dana, klinika_id, radno_vreme, ocena) values (9, 89, 1, 'Ponedeljak - 10:00 - 14:00/Utorak - 7:00:16:00/Sreda - 12:00 - 20:00/Četvrtak - 19:00 - 1:00/Petak - 13:00 - 21:00', 1.3);
insert into medicinska_sestra (meds_id, br_slobodnih_dana, klinika_id, radno_vreme, ocena) values (10, 56, 3, 'Ponedeljak - 10:00 - 14:00/Utorak - 7:00:16:00/Sreda - 12:00 - 20:00/Četvrtak - 19:00 - 1:00/Petak - 13:00 - 21:00', 5.0);
insert into administrator_klinickog_centra (adminkc_id) values (11);

insert into posete (id, pacijent_id, datum, pocetak, kraj, tipposete) values (1, 3, '12/12/2019','10:10','11:00','pregled');
insert into posete (id, pacijent_id, datum, pocetak, kraj, tipposete) values (2, 3, '13/12/2019','11:10','13:50','operacija');
insert into posete (id, pacijent_id, datum, pocetak, kraj, tipposete) values (3, 7, '14/12/2019','11:10','11:00','pregled');

insert into dijagnoze (sifra, sifradijagnoze, naziv) values (100,'1sf3','depresija');
insert into dijagnoze (sifra, sifradijagnoze, naziv) values (200,'1sf124', 'anksioznost');

insert into zkarton(id,pacijent_id) values (1,3);

insert into zkarton_dijagnoze(zdravstveni_karton_id,dijagnoze_sifra) values (1,100);




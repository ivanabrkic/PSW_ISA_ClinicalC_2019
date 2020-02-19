# PSW_ISA_ClinicalC_2019 [![Codacy Badge](https://api.codacy.com/project/badge/Grade/4d41894bcf1740a29bcb2e837c49889c)](https://www.codacy.com/manual/ivanabrkic/PSW_ISA_ClinicalC_2019?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ivanabrkic/PSW_ISA_ClinicalC_2019&amp;utm_campaign=Badge_Grade)

### Klinički Centar Web Aplikacija implementirana korišćenjem Spring Boot, Angular, REST, Maven i Postgre

### Klinički centar "Higija"

Klinički centar "Higija" omogućava rukovanje klinikama, zakazivanje pregleda i operacija od strane pacijenata, kao i pregled medicinskog osoblja klinike i njihovih radnih kalendara . Korisnici ovog sistema su lekari, medicinske sestre, pacijenti, ali i administratori klinike i administrator kliničkog centra.

Pacijentima omogućava zakazivanje pregleda, pretragu klinika i njihovih lekara, uvid u zdravstveni karton pacijenta, rukovanje profilom i pregled istorije pregleda i operacija, kao i ocenjivanje lekara i klinika.

Lekarima omogućava rukovanje pregledima i izveštajima pregleda, uvid u raspored pregleda preko njihovog radnog kalendara, kao i podnošenje zahteva za godišnji odmor ili odsustvo.

Medicinskim sestrama omogućava rukovanje izveštajima pregleda i receptima, uvid u raspored pregleda i operacija preko njihovog radnog kalendara, podnošenje zahteva za godišnji odmor ili odsustvo. 

Administratorima klinike omogućava vođenje evidencije o salama i godišnjim odmorima lekara, dodeljivanje satnice i sala operacijama, registraciju novih lekara na klinici.

Administratoru kliničkog centra omogućava registraciju klinika i njihovih administratora, odobravanje zahteva za registraciju novog pacijenta, kreiranje šifrarnika dijagnoza i lekova i zdravstvenog kartona pacijenta.

Neautentifikovanim korisnicima nudi mogućnost slanja zahteva za registraciju i prijava na sistem.

## Uputstvo
Uslovi koje je potrebno ispuniti:
* Node JS v12.13.0
* Maven v3.6.2
* PostgreSQL v12.1
* JDK v1.8


Uz pomoć ove komande ćete preuzeti projekat sa source kodom u vaš direktorijum radi testiranja i razvoja aplikacije

```
$ git clone https://github.com/ivanabrkic/PSW_ISA_ClinicalC_2019.git
```

### Instaliranje i konfiguracija
1. Pokrenite pgAdmin i kreirajte bazu podataka pod nazivom
```jpa```
2. Otvorite Frontend direktorijum u okviru korenskog direktorijuma aplikacije i pokrenite komandu
```
npm install
```
da bi se instalirali svi neophodni paketi.

## Pokretanje
### Pokretanje backenda
Otvorite vaš IDE i otvorite ili importujte Backend direktorijum i pokrenite projekat.

### Pokretanje frontenda
Otvorite vaš IDE i u terminalu ukucajte 
```npm start```

Zatim otvorite Vaš pretraživač i posetite adresu 
```http://localhost:4200```
da biste koristili usluge kliničkog centra Higija.

#### Nikola Milošević, Tamara Lazarević i Ivana Brkić

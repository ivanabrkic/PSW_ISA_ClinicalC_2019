package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MedicinskoOsoblje extends Korisnik{

    @Column
    private int brSlobodnihDana;

//    @ElementCollection
//    @MapKeyColumn(name="datum")
//    @Column(name="poseta")
//    @CollectionTable(name="radniKalendar", joinColumns=@JoinColumn(name="id"))
//    private HashMap<Integer, ArrayList<Poseta>> radniKalendar;

    public MedicinskoOsoblje() {
    }

    public MedicinskoOsoblje(String korIme, String lozinka, String email, String kontaktTelefon, String ime, String prezime, int brSlobodnihDana) {
        super(korIme, lozinka, email, kontaktTelefon, ime, prezime);
        this.brSlobodnihDana = brSlobodnihDana;
//        this.radniKalendar = radniKalendar;
    }

    public int getBrSlobodnihDana() {
        return brSlobodnihDana;
    }

    public void setBrSlobodnihDana(int brSlobodnihDana) {
        this.brSlobodnihDana = brSlobodnihDana;
    }

//    public HashMap<Integer, ArrayList<Poseta>> getRadniKalendar() {
//        return radniKalendar;
//    }
//
//    public void setRadniKalendar(HashMap<Integer, ArrayList<Poseta>> radniKalendar) {
//        this.radniKalendar = radniKalendar;
//    }

    @Override
    public String toString() {
        return "MedicinskoOsoblje{" +
                "brSlobodnihDana=" + brSlobodnihDana +
                '}';
    }
}

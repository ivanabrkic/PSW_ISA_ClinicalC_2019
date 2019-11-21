package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MedicinskoOsoblje extends Korisnik {

    @Column(name="brojSlobDana", unique = false, nullable = false)
    private Integer brojSlobDana;

    @ElementCollection
    @CollectionTable(name="radniKalendar")
    @MapKeyColumn(name="datum")
    private HashMap<Integer, Poseta> radniKalendar;

    public MedicinskoOsoblje(){
    }

    public MedicinskoOsoblje(Integer brojSlobDana, HashMap<Integer, Poseta> radniKalendar) {
        this.brojSlobDana = brojSlobDana;
        this.radniKalendar = radniKalendar;
    }

    public Integer getBrojSlobDana() {
        return brojSlobDana;
    }

    public void setBrojSlobDana(Integer brojSlobDana) {
        this.brojSlobDana = brojSlobDana;
    }

    public HashMap<Integer, Poseta> getRadniKalendar() {
        return radniKalendar;
    }

    public void setRadniKalendar(HashMap<Integer, Poseta> radniKalendar) {
        this.radniKalendar = radniKalendar;
    }
}

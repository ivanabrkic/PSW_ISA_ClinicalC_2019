package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;


@Entity
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @MapsId("klinika_id")
    @JoinColumn(name = "klinika_id")
    Klinika klinika;

    public Sala() {
    }

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "salaId=" + id +
                ", klinika=" + klinika +
                '}';
    }
}

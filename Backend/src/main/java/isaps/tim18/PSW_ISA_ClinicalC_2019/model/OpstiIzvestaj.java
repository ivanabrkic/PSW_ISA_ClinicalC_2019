package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "opsti_izvestaj")
public class OpstiIzvestaj {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "dioptrija")
    private String dioptrija;

    @Column(name = "visina")
    private String visina;

    @Column(name = "tezina")
    private String tezina;

    @Column(name = "krvna_grupa")
    private String krvnaGrupa;

    @Column(name = "alergije_lek")
    private String alergijeNaLek;

    public OpstiIzvestaj(String dioptrija, String visina, String tezina, String krvnaGrupa, String alergijeNaLek,
                         List<Dijagnoze> nasledne_bolesti, ZdravstveniKarton zdravstveniKarton) {
        this.dioptrija = dioptrija;
        this.visina = visina;
        this.tezina = tezina;
        this.krvnaGrupa = krvnaGrupa;
        this.alergijeNaLek = alergijeNaLek;
    }

    public OpstiIzvestaj(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDioptrija() {
        return dioptrija;
    }

    public void setDioptrija(String dioptrija) {
        this.dioptrija = dioptrija;
    }

    public String getVisina() {
        return visina;
    }

    public void setVisina(String visina) {
        this.visina = visina;
    }

    public String getTezina() {
        return tezina;
    }

    public void setTezina(String tezina) {
        this.tezina = tezina;
    }

    public String getKrvnaGrupa() {
        return krvnaGrupa;
    }

    public void setKrvnaGrupa(String krvnaGrupa) {
        this.krvnaGrupa = krvnaGrupa;
    }

    public String getAlergijeNaLek() {
        return alergijeNaLek;
    }

    public void setAlergijeNaLek(String alergijeNaLek) {
        this.alergijeNaLek = alergijeNaLek;
    }
}

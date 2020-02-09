package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Embeddable
@Table(name="dijagnoze")
public class Dijagnoze {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sifra;

    @Column(name="sifradijagnoze", nullable = false)
    private String sifraDijagnoze;

    @Column(name="naziv", nullable = false)
    private String nazivDijagnoze;
    
    @JsonIgnore
    @OneToMany(mappedBy = "dijagnoze")
    Set<ZkartonDijagnoze> zkarton;

    public Dijagnoze(){

    }

    public Set<ZkartonDijagnoze> getZkarton() {
		return zkarton;
	}

	public void setZkarton(Set<ZkartonDijagnoze> zkarton) {
		this.zkarton = zkarton;
	}

	public Dijagnoze(String sifraDijagnoze, String nazivDijagnoze, Set<ZkartonDijagnoze> zkartoni) {
        this.sifraDijagnoze = sifraDijagnoze;
        this.nazivDijagnoze = nazivDijagnoze;
        this.zkarton=zkartoni;
    }

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public String getNazivDijagnoze() {
        return nazivDijagnoze;
    }

    public void setNazivDijagnoze(String nazivDijagnoze) {
        this.nazivDijagnoze = nazivDijagnoze;
    }

    public String getSifraDijagnoze() {
        return sifraDijagnoze;
    }

    public void setSifraDijagnoze(String sifraDijagnoze) {
        this.sifraDijagnoze = sifraDijagnoze;
    }
}

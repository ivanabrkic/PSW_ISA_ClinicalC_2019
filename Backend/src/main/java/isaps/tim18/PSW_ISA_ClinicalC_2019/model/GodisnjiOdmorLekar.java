package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class GodisnjiOdmorLekar{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lekar_id", referencedColumnName = "lekar_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Lekar lekar;

    public GodisnjiOdmorLekar(Long id, Lekar lekar, LocalDate datumOd, LocalDate datumDo) {
		super();
		this.id = id;
		this.lekar = lekar;
		this.datumOd = datumOd;
		this.datumDo = datumDo;
	}

	@Column
    private LocalDate datumOd;

    @Column
    private LocalDate datumDo;

    public GodisnjiOdmorLekar() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public LocalDate getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(LocalDate datumOd) {
        this.datumOd = datumOd;
    }

    public LocalDate getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(LocalDate datumDo) {
        this.datumDo = datumDo;
    }
}

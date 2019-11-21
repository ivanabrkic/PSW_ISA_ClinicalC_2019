package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import javax.persistence.*;

//@Entity
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer brSale;

    public Sala() {
    }

    public Sala(int brSale) {
        this.brSale = brSale;
    }

    public int getBrSale() {
        return brSale;
    }

    public void setBrSale(int brSale) {
        this.brSale = brSale;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "brSale=" + brSale +
                '}';
    }
}

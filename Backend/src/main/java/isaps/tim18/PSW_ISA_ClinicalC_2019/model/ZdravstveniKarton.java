package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.HashMap;

//@Entity
public class ZdravstveniKarton {

    @ElementCollection
    @MapKeyColumn(name="sifra")
    @Column(name="dijagnoza")
    @CollectionTable(name="istorijaBolesti", joinColumns=@JoinColumn(name="example_id"))
    private HashMap<Integer,String> istorijaBolesti=new HashMap<Integer, String>();
    @Column(name="beleske")
    private String beleske;

    public ZdravstveniKarton(){

    }

    public HashMap<Integer, String> getIstorijaBolesti() {
        return istorijaBolesti;
    }

    public void setIstorijaBolesti(HashMap<Integer, String> istorijaBolesti) {
        this.istorijaBolesti = istorijaBolesti;
    }

    public String getBeleske() {
        return beleske;
    }

    public void setBeleske(String beleske) {
        this.beleske = beleske;
    }

    ZdravstveniKarton(HashMap<Integer,String> istorijaBolesti, String beleske){
        this.istorijaBolesti=istorijaBolesti;
        this.beleske=beleske;
    }
}

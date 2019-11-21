package isaps.tim18.PSW_ISA_ClinicalC_2019.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

//@Entity
public class ZdravstveniKarton {

    private String istorijaBolesti;
    private List<Poseta> istorijaPoseta;
}

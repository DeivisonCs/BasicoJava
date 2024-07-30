package ifba.gsort.partohumano.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "municipios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Municipio implements Serializable {

    @Id
    private Long codigo;
    private String municipio;
    private String gentilico;

}

package ifba.gsort.partohumano.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProgramaRequest {

    private String nome;
    private Long codigoDoMunicipio;

    public ProgramaRequest(Long numeroDoPrograma, String nome, Long codigoDoMunicipio) {
        this.nome = nome;
        this.codigoDoMunicipio = codigoDoMunicipio;
    }

}

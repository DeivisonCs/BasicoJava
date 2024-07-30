package ifba.gsort.partohumano.dtos;

import ifba.gsort.partohumano.model.Municipio;
import ifba.gsort.partohumano.model.Programa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramaDTO {

	private Long numeroDoPrograma;
	private String nome;
	private Municipio municipio;

	public ProgramaDTO(Programa programa) {
		this.numeroDoPrograma = programa.getNumeroDoPrograma();
		this.nome = programa.getNome();
		this.municipio = programa.getMunicipio();
	}

}

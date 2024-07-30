package ifba.gsort.partohumano.dtos;

import ifba.gsort.partohumano.model.PerguntaGpq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PerguntaResponseDTO {
	
	private String descricao;
	private Boolean temAnexo;
	
	public PerguntaResponseDTO(PerguntaGpq pergunta) {
		this.descricao = pergunta.getDescricao();
		this.temAnexo = pergunta.getTemAnexo();
	}

}

package ifba.gsort.partohumano.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PerguntaAtualizarDTO {

	private Long idPergunta;
	private Long acaoId;
	private String descricao;
	private Boolean temAnexo;
	
}

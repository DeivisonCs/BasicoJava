package ifba.gsort.partohumano.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespostaGpqDTO {
	private Long perguntaId;
	private int quantidade;
}
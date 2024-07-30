package ifba.gsort.partohumano.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespostaGpqRequest {
	private List<RespostaGpqDTO> respostas;
			
}
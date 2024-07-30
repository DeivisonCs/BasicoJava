package ifba.gsort.partohumano.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespostaGpqResponse {
	String message;
	private List<RespostaGpqDTO> respostas;
			
}
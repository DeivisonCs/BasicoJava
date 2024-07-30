package ifba.gsort.partohumano.dtos;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GpqDetalhadoResponse {

	private UUID enfermeiraId;
	private Long numeroDoDocumento;
	private Long programaId;
	private String statusDoPrograma;
	private List<RespostaGpqDTO> respostas;
}

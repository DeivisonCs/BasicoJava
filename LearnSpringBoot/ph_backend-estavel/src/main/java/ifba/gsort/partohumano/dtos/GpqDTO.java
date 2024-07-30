package ifba.gsort.partohumano.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GpqDTO {

	private UUID enfermeiraId;
	private Long numeroDoDocumento;
	private Long programaId;
	private String statusDoPrograma;

}

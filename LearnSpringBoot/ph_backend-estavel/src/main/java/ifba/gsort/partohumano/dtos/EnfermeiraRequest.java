package ifba.gsort.partohumano.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnfermeiraRequest {
	private UsuarioRequest usuario;
    private String coren;
}

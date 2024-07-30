package ifba.gsort.partohumano.dtos;

import ifba.gsort.partohumano.mapper.UsuarioMapper;
import ifba.gsort.partohumano.model.Enfermeira;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnfermeiraResponse {
	private UsuarioResponse usuario;
    private String coren;
    
    public EnfermeiraResponse(Enfermeira enfermeira) {
    	this.usuario = UsuarioMapper.toDTO(enfermeira.getUsuario());
    	this.coren = enfermeira.getCoren();
    }
}

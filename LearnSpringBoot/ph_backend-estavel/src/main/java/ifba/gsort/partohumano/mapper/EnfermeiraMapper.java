package ifba.gsort.partohumano.mapper;

import ifba.gsort.partohumano.dtos.EnfermeiraResponse;
import ifba.gsort.partohumano.model.Enfermeira;
import ifba.gsort.partohumano.model.Usuario;

public class EnfermeiraMapper {
	public static EnfermeiraResponse toDTO(Enfermeira enfermeira) {
		return new EnfermeiraResponse(enfermeira);
	}
	
	public static Enfermeira toModel(Usuario usuario, String coren) {
		return Enfermeira.builder().usuario(usuario).coren(coren).build();
	}
}

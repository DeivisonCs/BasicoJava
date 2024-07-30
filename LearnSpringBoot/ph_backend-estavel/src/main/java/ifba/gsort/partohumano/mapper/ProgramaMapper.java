package ifba.gsort.partohumano.mapper;

import java.util.List;

import ifba.gsort.partohumano.dtos.ProgramaDTO;
import ifba.gsort.partohumano.dtos.ProgramaRequest;
import ifba.gsort.partohumano.model.Municipio;
import ifba.gsort.partohumano.model.Programa;

public class ProgramaMapper {

	public static Programa toModel(ProgramaRequest programa, Municipio municipio) {
		return Programa.builder().nome(programa.getNome()).municipio(municipio).build();
	}

	public static ProgramaDTO toDTO(Programa programa) {
		return new ProgramaDTO(programa.getNumeroDoPrograma(), programa.getNome(), programa.getMunicipio());
	}

	public static List<ProgramaDTO> toDTOList(List<Programa> programas) {
		return programas.stream().map(ProgramaDTO::new).toList();
	}
}

package ifba.gsort.partohumano.mapper;

import java.util.ArrayList;

import ifba.gsort.partohumano.dtos.GpqDTO;
import ifba.gsort.partohumano.dtos.GpqDetalhadoResponse;
import ifba.gsort.partohumano.model.Gpq;
import ifba.gsort.partohumano.model.RespostaGpq;

public class GpqMapper {

	public static Gpq toModel(GpqDTO documento) {
		return Gpq.builder()
				.respostas(new ArrayList<RespostaGpq>())
				.build();
	}
	
	public static GpqDTO toDto(Gpq gpq) {
		return new GpqDTO(gpq.getEnfermeira().getUsuario().getId(), gpq.getNumeroDoDocumento(),
				gpq.getPrograma().getNumeroDoPrograma(), gpq.getStatus().getDescricao());
	}

	public static GpqDetalhadoResponse ToDetalhadoDto(Gpq gpq) {
		return new GpqDetalhadoResponse(gpq.getEnfermeira().getUsuario().getId(),
				gpq.getNumeroDoDocumento(),
				gpq.getPrograma().getNumeroDoPrograma(),
				gpq.getStatus().getDescricao(),
				gpq.getRespostas().stream().map(RespostaGpqMapper::toDto).toList());
	}
}

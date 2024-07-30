package ifba.gsort.partohumano.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ifba.gsort.partohumano.dtos.PerguntaDTO;
import ifba.gsort.partohumano.dtos.PerguntaResponseDTO;
import ifba.gsort.partohumano.model.AcaoGpq;
import ifba.gsort.partohumano.model.PerguntaGpq;

public class PerguntaGpqMapper {
	
	public static PerguntaGpq toModel(PerguntaDTO perguntaGpq, AcaoGpq acao) {
		return new PerguntaGpq(acao, perguntaGpq.getDescricao(), perguntaGpq.getTemAnexo());
	}
	
	public static List<PerguntaResponseDTO> toListResponseDTO(List<PerguntaGpq> perguntasGpq) {
		return perguntasGpq.stream().map(PerguntaResponseDTO::new).collect(Collectors.toList());
	}
	
	public static PerguntaResponseDTO toDTO(PerguntaGpq pergunta) {
		return new PerguntaResponseDTO(pergunta);
	}

}

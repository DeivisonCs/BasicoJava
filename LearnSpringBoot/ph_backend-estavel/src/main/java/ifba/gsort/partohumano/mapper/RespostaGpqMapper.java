package ifba.gsort.partohumano.mapper;

import ifba.gsort.partohumano.dtos.RespostaGpqDTO;
import ifba.gsort.partohumano.model.Gpq;
import ifba.gsort.partohumano.model.PerguntaGpq;
import ifba.gsort.partohumano.model.RespostaGpq;

public class RespostaGpqMapper {

	public static RespostaGpq toModel(Gpq gpq, int quantidade, PerguntaGpq pergunta) {
		return RespostaGpq.builder()
				.gpq(gpq)
				.pergunta(pergunta)
				.quantidade(quantidade)
				.build();
	}

	public static RespostaGpqDTO toDto(RespostaGpq resposta) {
		return new RespostaGpqDTO(resposta.getPergunta().getId(), resposta.getQuantidade());
	}
}

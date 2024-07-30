package ifba.gsort.partohumano.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ifba.gsort.partohumano.dtos.PerguntaAtualizarDTO;
import ifba.gsort.partohumano.dtos.PerguntaDTO;
import ifba.gsort.partohumano.dtos.PerguntaResponseDTO;
import ifba.gsort.partohumano.mapper.PerguntaGpqMapper;
import ifba.gsort.partohumano.model.AcaoGpq;
import ifba.gsort.partohumano.model.PerguntaGpq;
import ifba.gsort.partohumano.repository.PerguntaGpqRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PerguntaGpqService {

	private final PerguntaGpqRepository perguntaGpqRepository;
	private final AcaoGpqService acaoGpqService;
	
	public PerguntaDTO create(PerguntaDTO perguntaDTO) {
		AcaoGpq acao= this.acaoGpqService.getAcaoGpq(perguntaDTO.getAcaoId());
		PerguntaGpq perguntaGpq = PerguntaGpqMapper.toModel(perguntaDTO, acao);
		this.perguntaGpqRepository.save(perguntaGpq);
		return perguntaDTO;
		
	}

	public List<PerguntaResponseDTO> get() {
		return PerguntaGpqMapper.toListResponseDTO(this.perguntaGpqRepository.findAll());
	}

	public PerguntaResponseDTO atualizarPergunta(PerguntaAtualizarDTO perguntaDTO) {
		PerguntaGpq perguntaGpq = this.perguntaGpqRepository.findById(perguntaDTO.getIdPergunta()).orElseThrow();
	
		//TODO: criar método que verifica cada campo e atualiza. Criar service para o Model AcaoGpq para atualizar, caso seja necessário.
		perguntaGpq.setDescricao(perguntaDTO.getDescricao() == null ? perguntaGpq.getDescricao() : perguntaDTO.getDescricao());
		perguntaGpq.setTemAnexo(perguntaDTO.getTemAnexo() == null ? perguntaGpq.getTemAnexo() : perguntaDTO.getTemAnexo());
		
		this.perguntaGpqRepository.save(perguntaGpq);
		return PerguntaGpqMapper.toDTO(perguntaGpq);
	}

}

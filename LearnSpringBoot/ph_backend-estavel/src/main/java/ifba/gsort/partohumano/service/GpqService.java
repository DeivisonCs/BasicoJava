package ifba.gsort.partohumano.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ifba.gsort.partohumano.dtos.GpqDTO;
import ifba.gsort.partohumano.dtos.GpqDetalhadoResponse;
import ifba.gsort.partohumano.dtos.RespostaGpqDTO;
import ifba.gsort.partohumano.dtos.VotoDTO;
import ifba.gsort.partohumano.mapper.GpqMapper;
import ifba.gsort.partohumano.mapper.RespostaGpqMapper;
import ifba.gsort.partohumano.model.Enfermeira;
import ifba.gsort.partohumano.model.Gpq;
import ifba.gsort.partohumano.model.HistoricoGpq;
import ifba.gsort.partohumano.model.PerguntaGpq;
import ifba.gsort.partohumano.model.Programa;
import ifba.gsort.partohumano.model.RespostaGpq;
import ifba.gsort.partohumano.model.Roles;
import ifba.gsort.partohumano.model.StatusGpq;
import ifba.gsort.partohumano.model.StatusGpqEnum;
import ifba.gsort.partohumano.model.Usuario;
import ifba.gsort.partohumano.repository.GpqRepository;
import ifba.gsort.partohumano.repository.HistoricoRepository;
import ifba.gsort.partohumano.repository.PerguntaGpqRepository;
import ifba.gsort.partohumano.repository.RespostaGpqRepository;
import ifba.gsort.partohumano.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GpqService {

	private final GpqRepository gpqRepository;
	private final HistoricoRepository historicoRepository;
	private final PerguntaGpqRepository perguntaGpqRepository;
	private final RespostaGpqRepository respostaGpqRepository;
	private final UsuarioRepository usuarioRepository;
	private final EnfermeiraService enfermeiraService;
	private final UsuarioService usuarioService;
	private final RespostaGpqService respostaGpqService;

	public List<GpqDTO> listarGpqs() {
		return gpqRepository.findAll().stream().map(GpqMapper::toDto).toList();
	}

	public GpqDetalhadoResponse listarGpqDetalhado(Long numeroGpq) {
		Gpq gpq = encontrarGpq(numeroGpq);
		return GpqMapper.ToDetalhadoDto(gpq);
	}

	public GpqDTO criarGpq(GpqDTO gpqDTO) {
		Gpq gpq = GpqMapper.toModel(gpqDTO);
		Enfermeira enfermeira = enfermeiraService.encontrarEnfermeiraPorUUID(gpqDTO.getEnfermeiraId());
		gpq.registarHistorico(gerarRegistroDeHistorico(gpq, enfermeira.getUsuario(), null, "GPQ criado"));
		gpq.setEnfermeira(enfermeira);
		StatusGpq status = new StatusGpq(StatusGpqEnum.Criado.ordinal(), StatusGpqEnum.Criado.toString());
		gpq.setStatus(status);
		gpq.setPrograma(enfermeira.getUsuario().getPrograma());
		gpqRepository.save(gpq);
		gpq.getRespostas().addAll(respostaGpqService.gerarRespostasGpq(gpq));
		return GpqMapper.toDto(gpqRepository.save(gpq));
	}

	public Gpq encontrarGpq(Long id) {
		return this.gpqRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Gpq não econtrado"));
	}

	public GpqDTO aprovarGpq(Long numeroGpq, VotoDTO votoDTO) {
		final Usuario usuario = usuarioService.encontrarUsuarioPorUUID(votoDTO.getVotanteId());
		final Gpq gpq = this.encontrarGpq(numeroGpq);
		this.validacoesDeVoto(usuario, votoDTO, gpq);
		final HistoricoGpq historico = gerarRegistroDeHistorico(gpq, usuario, votoDTO.getVoto(),
				votoDTO.getDescricao());
		// TODO mudar para descricao
		gpq.registarHistorico(historico);
		this.historicoRepository.save(historico);
		if (votoDTO.getVoto() != null && !votoDTO.getVoto()) {
			return GpqMapper.toDto(gpqRepository.save(gpq.reprovar()));
		}
		if (gpq.getStatus().equals("PendenteMunicipio") && !(this.validacoesMunicipais(gpq))) {
			return GpqMapper.toDto(gpqRepository.save(gpq));
		}
		return GpqMapper.toDto(gpqRepository.save(gpq.aprovar()));
	}

	private boolean validacoesMunicipais(Gpq gpq) {
		LocalDateTime reprovacao = this.historicoRepository.encontrarDataDaUltimaReprovacao(gpq.getNumeroDoDocumento());
		final Programa programa = gpq.getPrograma();
		if (reprovacao == null) {
			return this.teveAprovacoesMunicipaisSuficientes(gpq, programa);
		}
		Boolean teveAprovacoesSuficientes = usuarioRepository
				.quantidadeDeUsuariosDeUmPrograma(programa.getNumeroDoPrograma()) == this.historicoRepository
						.quantidadeDeAproavacoesAposAUltimaReprovacao(reprovacao, gpq.getNumeroDoDocumento());
		if (!teveAprovacoesSuficientes) {
			return false;
		}
		return true;
	}

	private boolean teveAprovacoesMunicipaisSuficientes(Gpq gpq, Programa programa) {
		int quantidade = usuarioRepository.quantidadeDeUsuariosDeUmPrograma(programa.getNumeroDoPrograma());
		return this.historicoRepository.encontrarQuantidadeDeAprovacoes(gpq.getNumeroDoDocumento()) == quantidade;
	}

	private HistoricoGpq gerarRegistroDeHistorico(Gpq gpq, Usuario usuario, Boolean voto, String descricao) {
		return HistoricoGpq.builder().gpq(gpq).usuario(usuario).voto(voto).descricao(descricao).build();
	}

	public void enviarRespostas(Long numeroDoDocumento, List<RespostaGpqDTO> respostas) {
		Gpq gpq = gpqRepository.findById(numeroDoDocumento).orElseThrow(EntityNotFoundException::new);
		
		// Mudar a exceção depois
		if (!(gpq.getStatus().getDescricao().equals(StatusGpqEnum.Criado.toString())) || gpq.getStatus().getDescricao().equals(StatusGpqEnum.Rejeitado.toString()))
			throw new EntityNotFoundException("Status do documento não permite enviar respostas");
		
		List<PerguntaGpq> perguntas = perguntaGpqRepository.findAll();
		List<RespostaGpq> respostasFinal = respostas.stream().map(
				r -> RespostaGpqMapper.toModel(gpq, r.getQuantidade(), perguntas.get(r.getPerguntaId().intValue() - 1)))
				.toList();
		respostaGpqRepository.saveAll(respostasFinal);
		gpqRepository.save(gpq);
	}

	private boolean usuarioEstaNoPrograma(Gpq gpq, Usuario usuario) {
		if (Roles.igualOuSuperior(usuario.getPermissao(), Roles.Fesf)) {
			return true;
		}
		List<UUID> identificadoresDoPrograma = this.usuarioRepository
				.listarIdsDeUsuariosDeUmPrograma(gpq.getPrograma().getNumeroDoPrograma());
		return identificadoresDoPrograma.stream().anyMatch(id -> id.equals(usuario.getId()));
	}

	private void validacoesDeVoto(Usuario usuario, VotoDTO votoDTO, Gpq gpq) {
		final boolean votoENulo = votoDTO.getVoto() == null;
		final String descricao = votoDTO.getDescricao();
		final boolean descricaoENula = descricao == null;
		if (!gpq.getEstadoDocumento().temPermissaoNecessaria(usuario.getPermissao())
				|| !this.usuarioEstaNoPrograma(gpq, usuario)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Este documento está fora da sua jurisdicao de validacao");
		}
		if (usuario.getPermissao() == Roles.Enfermeira) {
			if (!votoENulo)
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Liberacoes de documento devem vir sem voto");
			return;
		}
		if (votoENulo) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aprovacoes não podem ter valor de voto nulo");
		}
		if (!votoDTO.getVoto() && descricaoENula) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Votos de reprovacao devem vir com descricação");
		}
		if (!descricaoENula && descricao.trim().length() < 10) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"É importante que as descrições sejam informativas do motivo da reprovação");
		}
	}
}

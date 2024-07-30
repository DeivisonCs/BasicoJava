package ifba.gsort.partohumano.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import ifba.gsort.partohumano.dtos.EnfermeiraRequest;
import ifba.gsort.partohumano.dtos.EnfermeiraResponse;
import ifba.gsort.partohumano.mapper.EnfermeiraMapper;
import ifba.gsort.partohumano.model.Enfermeira;
import ifba.gsort.partohumano.model.Usuario;
import ifba.gsort.partohumano.repository.EnfermeiraRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnfermeiraService {
	final EnfermeiraRepository enfermeiraRepository;
	final UsuarioService usuarioService;
	
	public EnfermeiraResponse cadastrarEnfermeira(EnfermeiraRequest enfermeiraDto) {
		Usuario usuario = usuarioService.criarNovoPerfil(enfermeiraDto.getUsuario());
		Enfermeira enfermeira = EnfermeiraMapper.toModel(usuario, enfermeiraDto.getCoren());
		enfermeiraRepository.save(enfermeira);
		
		return EnfermeiraMapper.toDTO(enfermeira);
	}

	public void desativarEnfermeira(String coren) {
		Enfermeira enfermeira = encontrarEnfermeiraPorCoren(coren);
		usuarioService.desativarUsuario(enfermeira.getUsuario().getId());
	}
	
	public Enfermeira encontrarEnfermeiraPorCoren(String coren) throws EntityNotFoundException {
        return enfermeiraRepository.findByCoren(coren)
        		.orElseThrow(() -> new EntityNotFoundException("Enfermeira não encontrada"));
    }
	
	public Enfermeira encontrarEnfermeiraPorUUID(UUID enfermeiraId) {
		return enfermeiraRepository.findByUsuarioId(enfermeiraId)
				.orElseThrow(() -> new EntityNotFoundException("Enfermeira não encontrada"));
	}
}

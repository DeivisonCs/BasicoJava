package ifba.gsort.partohumano.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import ifba.gsort.partohumano.dtos.ProgramaDTO;
import ifba.gsort.partohumano.dtos.ProgramaRequest;
import ifba.gsort.partohumano.mapper.ProgramaMapper;
import ifba.gsort.partohumano.model.Municipio;
import ifba.gsort.partohumano.model.Programa;
import ifba.gsort.partohumano.model.Usuario;
import ifba.gsort.partohumano.repository.MunicipioRepository;
import ifba.gsort.partohumano.repository.ProgramaRepository;
import ifba.gsort.partohumano.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProgramaService {

    private final ProgramaRepository programaRepository;
    private final MunicipioRepository municipioRepository;
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    public ProgramaDTO criarPrograma(ProgramaRequest programaRequest) {
        Municipio municipio = municipioRepository.findById(programaRequest.getCodigoDoMunicipio())
                .orElseThrow(() -> new EntityNotFoundException("Municipio não encontrado"));
        Programa programa = ProgramaMapper.toModel(programaRequest, municipio);
        return ProgramaMapper.toDTO(programaRepository.save(programa));
    }

    public ProgramaDTO adicionarMembro(UUID usuarioUUID, Long numeroDoPrograma) {
        final Usuario usuario = this.usuarioService.encontrarUsuarioPorUUID(usuarioUUID);
        final Programa programa = this.programaRepository.findById(numeroDoPrograma).orElseThrow(
                () -> new EntityNotFoundException("Programa não encontrado"));
        programa.adicionarIntegrantes(usuario);
        return ProgramaMapper.toDTO(programaRepository.save(programa));
    }

}

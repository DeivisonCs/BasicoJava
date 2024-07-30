package ifba.gsort.partohumano.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ifba.gsort.partohumano.dtos.DadosPutDTO;
import ifba.gsort.partohumano.dtos.UsuarioRequest;
import ifba.gsort.partohumano.dtos.UsuarioResponse;
import ifba.gsort.partohumano.mapper.UsuarioMapper;
import ifba.gsort.partohumano.model.Municipio;
import ifba.gsort.partohumano.model.Usuario;
import ifba.gsort.partohumano.repository.MunicipioRepository;
import ifba.gsort.partohumano.repository.ProgramaRepository;
import ifba.gsort.partohumano.repository.UsuarioRepository;
import ifba.gsort.partohumano.service.Regras.Regra;
import ifba.gsort.partohumano.service.Regras.RegrasDeCriacaoDeUsuario;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final AuthenticationService authenticationService;
    private final MunicipioRepository municipioRepository;
    private final ProgramaRepository programaRepository;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public void possuiPermissaoParaCriarUsuario(String token, UsuarioRequest usuarioDTO) {
        Usuario criador = encontrarUsuario(token);
        Regra regra = new RegrasDeCriacaoDeUsuario(criador, usuarioDTO);
        regra.validar();
    }

    public Usuario criarNovoPerfil(UsuarioRequest usuarioDTO) {
        Municipio municipio = null;
        if (usuarioDTO.getCodigoDoMunicipio() != null) {
            municipio = municipioRepository.findById(usuarioDTO.getCodigoDoMunicipio())
                    .orElseThrow(() -> new EntityNotFoundException("Município não encontrado"));
        }
        Usuario usuario = UsuarioMapper.toModel(usuarioDTO, municipio);
        authenticationService.registrarNovoUsuario(usuario);
        return usuario;
    }

    public Usuario encontrarUsuario(String token) {
        var login = tokenService.tokenIsValid(token.replace("Bearer ", ""));
        return this.authenticationService.encontrarUsuario(login);
    }

    public Usuario encontrarUsuarioPorUUID(UUID uuid) {
        return this.usuarioRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    public List<UsuarioResponse> listarUsuarios() {
        return UsuarioMapper.toDTOList(usuarioRepository.findAll());
    }

    public UsuarioResponse atualizarDados(UUID uuid, DadosPutDTO dadosParaAlteracao) throws EntityNotFoundException {
        Usuario usuario = encontrarUsuarioPorUUID(uuid);

        usuario.getDadosCadastrais().setNome(dadosParaAlteracao.getNome());
        usuario.setEmail(dadosParaAlteracao.getEmail());
        usuario.getDadosCadastrais().setTelefone(dadosParaAlteracao.getTelefone());

        usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(usuario);
    }

    public void desativarUsuario(UUID uuid) {
        Usuario usuario = encontrarUsuarioPorUUID(uuid);
        usuario.desativar();
        usuarioRepository.save(usuario);
    }
}

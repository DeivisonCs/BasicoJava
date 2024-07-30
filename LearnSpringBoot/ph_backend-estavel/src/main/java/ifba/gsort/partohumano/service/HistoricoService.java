package ifba.gsort.partohumano.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ifba.gsort.partohumano.dtos.HistoricoResponse;
import ifba.gsort.partohumano.repository.HistoricoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HistoricoService {

    private final HistoricoRepository historicoRepository;
    private final UsuarioService usuarioService;

    public List<HistoricoResponse> listAll() {
        return historicoRepository.findAll().stream().map(HistoricoResponse::new).toList();
    }

    public List<HistoricoResponse> listAllActionOfAUser(UUID uuid) {
        return historicoRepository.findByUsuarioId(uuid).stream().map(HistoricoResponse::new).toList();
    }

    public List<HistoricoResponse> listAllMyActions(String token, UUID uuid) {
        UUID uuidDoDoToken = usuarioService.encontrarUsuario(token).getId();
        if (!uuidDoDoToken.equals(uuid)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "indetificacao nao confere");
        }
        return this.listAllActionOfAUser(uuid);
    }

}

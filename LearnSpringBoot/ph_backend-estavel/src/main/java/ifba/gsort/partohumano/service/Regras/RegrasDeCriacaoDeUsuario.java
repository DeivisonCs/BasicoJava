package ifba.gsort.partohumano.service.Regras;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import ifba.gsort.partohumano.dtos.UsuarioRequest;
import ifba.gsort.partohumano.model.Roles;
import ifba.gsort.partohumano.model.Usuario;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegrasDeCriacaoDeUsuario implements Regra {

    private Usuario criador;
    private UsuarioRequest novoUsuario;

    @Override
    public boolean validar() {
        return this.criadorPossuiPermissoesNecessarias() && usuarioMunicipalEstaLigadoAMunicipio();
    }

    private boolean criadorPossuiPermissoesNecessarias() {
        if (!Roles.igualOuSuperior(criador.getPermissao(), Roles.Fesf)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Nao possui permissão necessaria para realizar esta ação");
        }
        return true;
    }

    private boolean usuarioMunicipalEstaLigadoAMunicipio() {
        if (!Roles.igualOuSuperior(novoUsuario.getPermissao(), Roles.Fesf)
                && !novoUsuario.estaligadoAMunicipio()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Usuarios de nivel municipal devem estar ligados a um municipio");

        }
        return true;
    }

}

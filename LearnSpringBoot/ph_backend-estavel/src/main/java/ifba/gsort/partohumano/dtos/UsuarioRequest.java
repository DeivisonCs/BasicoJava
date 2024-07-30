package ifba.gsort.partohumano.dtos;

import ifba.gsort.partohumano.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {

    private AuthenticationDTO credenciais;
    private DadosCadastraisDTO dadosCadastrais;
    private Roles permissao;
    private Long codigoDoMunicipio;

    public String getEmail() {
        return this.credenciais.getEmail();
    }

    public String getPassword() {
        return this.credenciais.getPassword();
    }

    public Roles getPermissao() {
        return this.permissao;
    }

    public boolean estaligadoAMunicipio() {
        return this.getCodigoDoMunicipio() != null;
    }
}

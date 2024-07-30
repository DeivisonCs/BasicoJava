package ifba.gsort.partohumano.dtos;

import ifba.gsort.partohumano.mapper.DadosCadastraisMapper;
import ifba.gsort.partohumano.mapper.ProgramaMapper;
import ifba.gsort.partohumano.model.Roles;
import ifba.gsort.partohumano.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    private DadosCadastraisDTO dadosCadastraisDTO;
    private Roles permissao;
    private String municipio;
    private ProgramaDTO programas;

    public UsuarioResponse(Usuario usuario) {
        this.dadosCadastraisDTO = DadosCadastraisMapper.toDTO(usuario.getDadosCadastrais());
        this.permissao = usuario.getPermissao();
        this.municipio = usuario.getMunicipio().getMunicipio();
        if (usuario.getPrograma() != null) {
            this.programas = ProgramaMapper.toDTO(usuario.getPrograma());
        }
    }
}

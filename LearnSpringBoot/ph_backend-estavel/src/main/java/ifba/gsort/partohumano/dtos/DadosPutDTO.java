package ifba.gsort.partohumano.dtos;

import ifba.gsort.partohumano.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DadosPutDTO {

	private String nome;
	private String email;
    private String telefone;
    
    public DadosPutDTO(Usuario usuario) {
    	this.nome = usuario.getDadosCadastrais().getNome();
    	this.email = usuario.getEmail();
    	this.telefone = usuario.getDadosCadastrais().getTelefone();
    }
}

package ifba.gsort.partohumano.dtos;

import ifba.gsort.partohumano.model.DadosCadastrais;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DadosCadastraisDTO {

    private String cpf;
    private String nome;
    private String telefone;
    
    public DadosCadastraisDTO(DadosCadastrais dadosCadastrais) {
    	this.cpf = dadosCadastrais.getCpf();
    	this.nome = dadosCadastrais.getNome();
    	this.telefone = dadosCadastrais.getTelefone();
    }
}

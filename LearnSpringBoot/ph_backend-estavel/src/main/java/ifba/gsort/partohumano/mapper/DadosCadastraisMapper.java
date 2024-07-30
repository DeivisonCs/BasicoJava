package ifba.gsort.partohumano.mapper;

import ifba.gsort.partohumano.dtos.DadosCadastraisDTO;
import ifba.gsort.partohumano.model.DadosCadastrais;

public class DadosCadastraisMapper {

    public static DadosCadastrais toModel(DadosCadastraisDTO dadosCadastrais) {
        return DadosCadastrais.builder()
        		.cpf(dadosCadastrais.getCpf())
                .telefone(dadosCadastrais.getTelefone())
                .nome(dadosCadastrais.getNome()).build();
    }
    
    public static DadosCadastraisDTO toDTO(DadosCadastrais dadosCadastrais) {
    	return new DadosCadastraisDTO(dadosCadastrais);
    }
}

package ifba.gsort.partohumano.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Embeddable
public class DadosCadastrais implements Serializable {

    private String cpf;
    private String nome;
    // TODO: Alterar para lista de telefones
    private String telefone;
}

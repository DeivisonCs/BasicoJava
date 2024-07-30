package ifba.gsort.partohumano.dtos;

import java.util.UUID;

import ifba.gsort.partohumano.model.Gpq;
import ifba.gsort.partohumano.model.HistoricoGpq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HistoricoResponse {

    private Long id;
    private Long numeroGqp;
    private UUID identificador;
    private String cpf;
    private String nomeDoUsuario;
    private String descricao;
    private Boolean voto;

    public HistoricoResponse(HistoricoGpq gpq) {
        this.id = gpq.getId();
        this.identificador = gpq.getUsuario().getId();
        this.cpf = gpq.getUsuario().getDadosCadastrais().getCpf();
        this.descricao = gpq.getDescricao();
        this.voto = gpq.getVoto();
        this.numeroGqp = gpq.getId();
    }

}

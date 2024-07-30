package ifba.gsort.partohumano.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class VotoDTO {

    private UUID votanteId;
    private String descricao;
    private Boolean voto;

}

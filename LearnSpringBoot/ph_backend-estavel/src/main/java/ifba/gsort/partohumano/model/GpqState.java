package ifba.gsort.partohumano.model;

import ifba.gsort.partohumano.model.DocumentoStates.CriadoState;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public abstract class GpqState {

    private Gpq gpq;

    public GpqState reprovarDocumento() {
    	StatusGpq status = new StatusGpq(StatusGpqEnum.Criado.ordinal(), StatusGpqEnum.Criado.toString());
        gpq.setStatus(status);
        return new CriadoState(this.gpq);
    }

    public abstract GpqState aprovarDocumento();

    public Gpq getGpq() {
        return this.gpq;
    }

    public abstract boolean temPermissaoNecessaria(Roles roles);

}

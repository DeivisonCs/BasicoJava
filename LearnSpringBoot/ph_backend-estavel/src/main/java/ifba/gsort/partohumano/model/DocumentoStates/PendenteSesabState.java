package ifba.gsort.partohumano.model.DocumentoStates;

import ifba.gsort.partohumano.model.Gpq;
import ifba.gsort.partohumano.model.GpqState;
import ifba.gsort.partohumano.model.Roles;
import ifba.gsort.partohumano.model.StatusGpq;
import ifba.gsort.partohumano.model.StatusGpqEnum;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PendenteSesabState extends GpqState {

    public PendenteSesabState(Gpq gpq) {
        super(gpq);
    }

    @Override
    public GpqState aprovarDocumento() {
    	StatusGpqEnum novoStatus = StatusGpqEnum.Aprovado;
    	StatusGpq status = new StatusGpq(novoStatus.ordinal(), novoStatus.toString());
        this.getGpq().setStatus(status);
        return new AprovadoState();
    }

    @Override
    public boolean temPermissaoNecessaria(Roles roles) {
        return roles.ordinal() == Roles.Sesab.ordinal();
    }

}

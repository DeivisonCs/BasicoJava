package ifba.gsort.partohumano.model.DocumentoStates;

import ifba.gsort.partohumano.model.Gpq;
import ifba.gsort.partohumano.model.GpqState;
import ifba.gsort.partohumano.model.Roles;
import ifba.gsort.partohumano.model.StatusGpq;
import ifba.gsort.partohumano.model.StatusGpqEnum;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PendenteFesfState extends GpqState {

    public PendenteFesfState(Gpq gpq) {
        super(gpq);
    }

    @Override
    public GpqState aprovarDocumento() {
    	StatusGpqEnum novoStatus = StatusGpqEnum.PendenteSesab;
    	StatusGpq status = new StatusGpq(novoStatus.ordinal(), novoStatus.toString());
        this.getGpq().setStatus(status);
        return new PendenteSesabState();
    }

    @Override
    public boolean temPermissaoNecessaria(Roles roles) {
        return roles.ordinal() == Roles.Fesf.ordinal();
    }

}

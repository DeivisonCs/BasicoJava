package ifba.gsort.partohumano.model.DocumentoStates;

import ifba.gsort.partohumano.model.Gpq;
import ifba.gsort.partohumano.model.GpqState;
import ifba.gsort.partohumano.model.Roles;
import ifba.gsort.partohumano.model.StatusGpq;
import ifba.gsort.partohumano.model.StatusGpqEnum;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PendenteMunicipioState extends GpqState {

    public PendenteMunicipioState(Gpq gpq) {
        super(gpq);
    }

    @Override
    public GpqState aprovarDocumento() {
    	StatusGpqEnum novoStatus = StatusGpqEnum.PendenteFesf;
    	StatusGpq status = new StatusGpq(novoStatus.ordinal(), novoStatus.toString());
        this.getGpq().setStatus(status);
        return new PendenteFesfState(getGpq());
    }

    @Override
    public boolean temPermissaoNecessaria(Roles roles) {
        return roles.ordinal() == Roles.Ssm.ordinal();
    }

}

package ifba.gsort.partohumano.model.DocumentoStates;

import ifba.gsort.partohumano.model.Gpq;
import ifba.gsort.partohumano.model.GpqState;
import ifba.gsort.partohumano.model.Roles;
import ifba.gsort.partohumano.model.StatusGpq;
import ifba.gsort.partohumano.model.StatusGpqEnum;

public class CriadoState extends GpqState {

    public CriadoState(Gpq gpq) {
        super(gpq);
    }

    @Override
    public GpqState aprovarDocumento() {
    	StatusGpqEnum novoStatus = StatusGpqEnum.PendenteMunicipio;
    	StatusGpq status = new StatusGpq(novoStatus.ordinal(), novoStatus.toString());
        this.getGpq().setStatus(status);
        return new PendenteMunicipioState(this.getGpq());
    }

    @Override
    public boolean temPermissaoNecessaria(Roles roles) {
        return roles.ordinal() == Roles.Enfermeira.ordinal();
    }

}

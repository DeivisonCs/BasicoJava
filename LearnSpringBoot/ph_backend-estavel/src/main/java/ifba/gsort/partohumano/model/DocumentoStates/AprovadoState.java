package ifba.gsort.partohumano.model.DocumentoStates;

import ifba.gsort.partohumano.model.GpqState;
import ifba.gsort.partohumano.model.Roles;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AprovadoState extends GpqState {

    @Override
    public GpqState aprovarDocumento() {
        return this;
    }

    @Override
    public boolean temPermissaoNecessaria(Roles roles) {
        return true;
    }

}

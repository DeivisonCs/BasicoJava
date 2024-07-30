package ifba.gsort.partohumano.model;

import java.util.Arrays;
import java.util.List;

public enum StatusGpqEnum {
	Criado,
	PendenteMunicipio,
	PendenteFesf,
	PendenteSesab,
	Rejeitado,
	Aprovado;
	
	public static List<StatusGpqEnum> lista(){
		return Arrays.asList(StatusGpqEnum.values());
	}
}

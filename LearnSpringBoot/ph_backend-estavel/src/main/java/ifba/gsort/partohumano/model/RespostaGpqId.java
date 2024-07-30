package ifba.gsort.partohumano.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RespostaGpqId implements Serializable {

	private PerguntaGpq pergunta;
	private Gpq gpq;
}

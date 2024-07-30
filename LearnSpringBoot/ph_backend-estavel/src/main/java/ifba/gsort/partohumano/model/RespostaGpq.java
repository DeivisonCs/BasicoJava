package ifba.gsort.partohumano.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "respostas_gpq")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(RespostaGpqId.class)
public class RespostaGpq {

	@Id
	@ManyToOne
	private PerguntaGpq pergunta;
	@Id
	@ManyToOne
	private Gpq gpq;
	private int quantidade;
	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public RespostaGpq(PerguntaGpq pergunta, Gpq gpq, int quantidade) {
		this.pergunta = pergunta;
		this.gpq = gpq;
		this.quantidade = quantidade;
	}

}

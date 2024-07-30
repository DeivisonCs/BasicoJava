package ifba.gsort.partohumano.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="perguntas_gpq")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PerguntaGpq {
	 
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private AcaoGpq acao;
	private String descricao; 
	private Boolean temAnexo = false;
	
	public PerguntaGpq(AcaoGpq acao, String descricao, Boolean temAnexo) {
		this.acao = acao;
		this.descricao = descricao;
		this.temAnexo = temAnexo;
	}

	public void setAcao(AcaoGpq acao) {
		this.acao = acao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setTemAnexo(Boolean temAnexo) {
		this.temAnexo = temAnexo;
	}
	
	
	
	

}

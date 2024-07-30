package ifba.gsort.partohumano.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "status_gpq")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
public class StatusGpq implements Serializable {
	
	@Id
	private Integer codigo;
	private String descricao;
	
	public StatusGpq(String descricao) {
		this.descricao = descricao; 
	}
}

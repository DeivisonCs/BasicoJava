package ifba.gsort.partohumano.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "historico")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class HistoricoGpq implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
    private String descricao;
    private Boolean voto;
    @ManyToOne(fetch = FetchType.LAZY)
    private Gpq gpq;
    @CreationTimestamp
    private LocalDateTime createdAt;

}

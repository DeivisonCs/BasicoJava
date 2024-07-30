package ifba.gsort.partohumano.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "programas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class Programa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numeroDoPrograma;
    private String nome;
    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY, mappedBy = "id")
    private List<Usuario> integrantes;
    @OneToMany(mappedBy = "numeroDoDocumento")
    private List<Gpq> documentos;
    @OneToOne
    private Municipio municipio;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public int getQuantidadeDeIntegrantes() {
        if (this.integrantes == null) {
            return 0;
        }
        return this.integrantes.size();
    }

    public void adicionarIntegrantes(Usuario integrante) {
        if (this.integrantes == null) {
            this.integrantes = new LinkedList<Usuario>();
        }
        this.integrantes.add(integrante);
        integrante.setPrograma(this);
    }

    public void adicionarGpq(Gpq gpq) {
        if (this.documentos == null) {
            this.documentos = new LinkedList<Gpq>();
        }
        this.documentos.add(gpq);
    }

}

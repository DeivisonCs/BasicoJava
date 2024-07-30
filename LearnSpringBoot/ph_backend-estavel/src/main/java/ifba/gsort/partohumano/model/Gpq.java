package ifba.gsort.partohumano.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import ifba.gsort.partohumano.model.DocumentoStates.CriadoState;
import ifba.gsort.partohumano.model.DocumentoStates.PendenteFesfState;
import ifba.gsort.partohumano.model.DocumentoStates.PendenteMunicipioState;
import ifba.gsort.partohumano.model.DocumentoStates.PendenteSesabState;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "gpq")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Gpq implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numeroDoDocumento;
    @Transient
    private GpqState estadoDocumento;
    @ManyToOne
    private StatusGpq status;
    @Column(updatable = false)
    private LocalDate mesAno;
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HistoricoGpq> historico;
    @ManyToOne
    private Enfermeira enfermeira;
    @ManyToOne(fetch = FetchType.LAZY)
    private Programa programa;
    @OneToMany(fetch = FetchType.LAZY)
    private List<RespostaGpq> respostas;

    public Gpq aprovar() {
        this.definirEstado();
        estadoDocumento = estadoDocumento.aprovarDocumento();
        System.out.println("Meu estado atual " + this.status);
        return this;
    }

    public Gpq reprovar() {
        estadoDocumento.reprovarDocumento();
        return this;
    }

    public List<HistoricoGpq> getValidacoes() {
        return this.historico;
    }

    @PostLoad
    private void definirEstado() {
        if (status.getDescricao().equals(StatusGpqEnum.Criado.toString()) || status.getDescricao().equals(StatusGpqEnum.Rejeitado.toString())) {
            this.estadoDocumento = new CriadoState(this);
            return;
        }
        if (status.getDescricao().equals(StatusGpqEnum.PendenteMunicipio.toString())) {
            this.estadoDocumento = new PendenteMunicipioState(this);
            return;
        }
        if (status.getDescricao().equals(StatusGpqEnum.PendenteFesf.toString())) {
            this.estadoDocumento = new PendenteFesfState(this);
            return;
        }
        if (status.getDescricao().equals(StatusGpqEnum.PendenteSesab.toString())) {
            this.estadoDocumento = new PendenteSesabState(this);
            return;
        }
    }

    public void setStatus(StatusGpq status) {
        this.status = status;
        this.definirEstado();
    }

    public void registarHistorico(HistoricoGpq gpq) {
        if (this.getHistorico() == null) {
            this.historico = new LinkedList<HistoricoGpq>();
        }
        this.historico.add(gpq);
    }

    public void setEnfermeira(Enfermeira enfermeira) {
        this.enfermeira = enfermeira;
        this.estadoDocumento = new CriadoState(this);
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
        programa.adicionarGpq(this);
    }

    public GpqState getEstadoDocumento() {
        this.definirEstado();
        return estadoDocumento;
    }

}

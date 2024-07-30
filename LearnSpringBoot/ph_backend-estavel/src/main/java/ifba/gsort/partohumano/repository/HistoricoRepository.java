package ifba.gsort.partohumano.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ifba.gsort.partohumano.model.HistoricoGpq;

public interface HistoricoRepository extends JpaRepository<HistoricoGpq, Long> {

    @Query(nativeQuery = true, value = "select created_at from historico where voto = false order by created_at limit 1")
    public LocalDateTime encontrarDataDaUltimaReprovacao(Long numeroDoDocumento);

    @Query(nativeQuery = true, value = "Select count(distinct usuario_id) from historico as h where created_at > ?1 and\n"
            + " h.voto = true and gpq_numero_do_documento = ?2")
    public int quantidadeDeAproavacoesAposAUltimaReprovacao(LocalDateTime ultimaReprovacao, Long numeroDoGpq);

    @Query(nativeQuery = true, value = "Select count(distinct usuario_id) from historico where voto = true and gpq_numero_do_documento = ?1")
    public int encontrarQuantidadeDeAprovacoes(Long numeroDoGpq);

    public List<HistoricoGpq> findByUsuarioId(UUID usuarioUuid);

}

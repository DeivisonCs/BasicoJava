package ifba.gsort.partohumano.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ifba.gsort.partohumano.model.StatusGpq;

public interface StatusGpqRepository extends JpaRepository<StatusGpq, Integer>{
	public Optional<StatusGpq> findByDescricao(String descricao);
}

package ifba.gsort.partohumano.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifba.gsort.partohumano.model.Enfermeira;

@Repository
public interface EnfermeiraRepository extends JpaRepository<Enfermeira, Long> {
    public Optional<Enfermeira> findByCoren(String coren);
    
    public Optional<Enfermeira> findByUsuarioId(UUID uuid);
}

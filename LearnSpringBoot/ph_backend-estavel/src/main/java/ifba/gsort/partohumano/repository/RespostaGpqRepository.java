package ifba.gsort.partohumano.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifba.gsort.partohumano.model.RespostaGpq;
import ifba.gsort.partohumano.model.RespostaGpqId;

public interface RespostaGpqRepository extends JpaRepository<RespostaGpq, RespostaGpqId> {

}

package ifba.gsort.partohumano.service;

import org.springframework.stereotype.Service;

import ifba.gsort.partohumano.model.AcaoGpq;
import ifba.gsort.partohumano.repository.AcaoGpqRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AcaoGpqService {
	
	private final AcaoGpqRepository acaoGpqRepository;
	
	public AcaoGpq getAcaoGpq(Long id) {
		return this.acaoGpqRepository.findById(id).orElseThrow();
	}

}

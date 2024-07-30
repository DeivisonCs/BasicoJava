package ifba.gsort.partohumano.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifba.gsort.partohumano.dtos.EnfermeiraRequest;
import ifba.gsort.partohumano.dtos.EnfermeiraResponse;
import ifba.gsort.partohumano.service.EnfermeiraService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/enfermagem")
@RequiredArgsConstructor
public class EnfermeiraController {
	
	final EnfermeiraService enfermeiraService;
	
	@PostMapping
	public ResponseEntity<EnfermeiraResponse> cadastrarEnfermeira(@RequestBody EnfermeiraRequest enfermeiraDto) {
		return ResponseEntity.ok().body(enfermeiraService.cadastrarEnfermeira(enfermeiraDto));
	}
	
	@DeleteMapping("/{coren}")
	public ResponseEntity<Void> desativarEnfermeira(@PathVariable("coren") String coren) {
		enfermeiraService.desativarEnfermeira(coren);
		return ResponseEntity.ok().build();
	}
}

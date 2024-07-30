package ifba.gsort.partohumano.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifba.gsort.partohumano.dtos.PerguntaAtualizarDTO;
import ifba.gsort.partohumano.dtos.PerguntaDTO;
import ifba.gsort.partohumano.dtos.PerguntaResponseDTO;
import ifba.gsort.partohumano.service.PerguntaGpqService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/gpq/perguntas")
@AllArgsConstructor
public class PerguntaGpqController {
	
	
	private final PerguntaGpqService perguntaGpqService;
	
	
	//TODO: Verificar/Validar quem pode fazer as perguntas;
	@PostMapping
	public ResponseEntity<PerguntaDTO> create(@RequestBody PerguntaDTO perguntaDTO) {
		return ResponseEntity.ok(this.perguntaGpqService.create(perguntaDTO));
	}
	
	
	@GetMapping
	public ResponseEntity<List<PerguntaResponseDTO>> getPerguntasGpq() {
		return ResponseEntity.ok(this.perguntaGpqService.get());
	}
	
	@PutMapping
	public ResponseEntity put(@RequestBody PerguntaAtualizarDTO perguntaDTO){
		return ResponseEntity.ok(this.perguntaGpqService.atualizarPergunta(perguntaDTO));
	}

}

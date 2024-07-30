package ifba.gsort.partohumano.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ifba.gsort.partohumano.dtos.GpqDTO;
import ifba.gsort.partohumano.dtos.GpqDetalhadoResponse;
import ifba.gsort.partohumano.dtos.RespostaGpqRequest;
import ifba.gsort.partohumano.dtos.RespostaGpqResponse;
import ifba.gsort.partohumano.dtos.VotoDTO;
import ifba.gsort.partohumano.service.GpqService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/gpq")
@AllArgsConstructor
public class GpqController {

	private final GpqService gpqService;

	@GetMapping
	public ResponseEntity<List<GpqDTO>> listarDocumentos() {
		return ResponseEntity.ok(gpqService.listarGpqs());
	}

	@GetMapping("/{id}")
	public ResponseEntity<GpqDetalhadoResponse> listarGpqDetalhado(@PathVariable("id") Long numeroGpq) {
		return ResponseEntity.ok(gpqService.listarGpqDetalhado(numeroGpq));
	}

	@PostMapping
	public ResponseEntity<GpqDTO> registrarDocumento(@RequestBody GpqDTO gpqDTO) {
		return ResponseEntity.ok(gpqService.criarGpq(gpqDTO));
	}

	@PatchMapping("/aprovar/{id}")
	public ResponseEntity<GpqDTO> aprovar(@PathVariable("id") Long numeroGpq, @RequestBody VotoDTO voto) {
		return ResponseEntity.ok(this.gpqService.aprovarGpq(numeroGpq, voto));
	}

	@PutMapping("/respostas/{id}")
	public ResponseEntity<RespostaGpqResponse> enviarRespostas(@PathVariable("id") Long numeroGpq,
			@RequestBody RespostaGpqRequest respostas) {
		gpqService.enviarRespostas(numeroGpq, respostas.getRespostas());
		return ResponseEntity
				.ok(new RespostaGpqResponse("Respostas cadastradas com sucesso", respostas.getRespostas()));
	}

}

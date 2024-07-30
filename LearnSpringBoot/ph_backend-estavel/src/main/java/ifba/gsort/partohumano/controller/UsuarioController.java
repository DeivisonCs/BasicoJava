package ifba.gsort.partohumano.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifba.gsort.partohumano.dtos.DadosPutDTO;
import ifba.gsort.partohumano.dtos.UsuarioResponse;
import ifba.gsort.partohumano.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsuarioController {
	private final UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
		return ResponseEntity.ok(usuarioService.listarUsuarios());
	}

	@PutMapping("/atualizar/{uuid}")
	public ResponseEntity<UsuarioResponse> atualizarDadosCadastrais(
			@PathVariable("uuid") UUID uuid,
			@RequestBody DadosPutDTO dadosCadastraisDto) {
		UsuarioResponse usuario = usuarioService.atualizarDados(uuid, dadosCadastraisDto);
		return new ResponseEntity<UsuarioResponse>(usuario, HttpStatus.OK);
	}

	@DeleteMapping("/{uuid}")
	public ResponseEntity<Void> desativarUsuario(@PathVariable("uuid") UUID uuid) {
		usuarioService.desativarUsuario(uuid);
		return ResponseEntity.ok().build();
	}
}

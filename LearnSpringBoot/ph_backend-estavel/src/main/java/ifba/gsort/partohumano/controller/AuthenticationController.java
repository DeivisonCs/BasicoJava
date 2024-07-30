package ifba.gsort.partohumano.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifba.gsort.partohumano.dtos.AuthenticationDTO;
import ifba.gsort.partohumano.dtos.TokenDTO;
import ifba.gsort.partohumano.dtos.UsuarioRequest;
import ifba.gsort.partohumano.service.AuthenticationService;
import ifba.gsort.partohumano.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody AuthenticationDTO usuario) {
        return ResponseEntity.ok(authenticationService.login(usuario));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> novoUsuario(@RequestHeader("Authorization") String token,
            @RequestBody UsuarioRequest usuario) {
        this.usuarioService.possuiPermissaoParaCriarUsuario(token, usuario);
        usuarioService.criarNovoPerfil(usuario);
        return ResponseEntity.ok().build();
    }
}

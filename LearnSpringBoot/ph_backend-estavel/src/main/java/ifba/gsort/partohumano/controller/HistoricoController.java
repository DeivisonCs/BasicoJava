package ifba.gsort.partohumano.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifba.gsort.partohumano.dtos.HistoricoResponse;
import ifba.gsort.partohumano.service.HistoricoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/historico")
@AllArgsConstructor
public class HistoricoController {

    private final HistoricoService historicoService;

    @GetMapping
    public ResponseEntity<List<HistoricoResponse>> listAll() {
        return ResponseEntity.ok().body(historicoService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<HistoricoResponse>> listAllActionOfAUser(@PathVariable(name = "id") UUID uuid) {
        return ResponseEntity.ok().body(historicoService.listAllActionOfAUser(uuid));
    }

    @GetMapping("/minhasacoes/{id}")
    public ResponseEntity<List<HistoricoResponse>> listAllMyActions(@RequestHeader("Authorization") String token,
            @PathVariable(name = "id") UUID uuid) {
        return ResponseEntity.ok().body(historicoService.listAllMyActions(token, uuid));
    }

}

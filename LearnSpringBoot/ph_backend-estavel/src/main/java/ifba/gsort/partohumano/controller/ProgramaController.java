package ifba.gsort.partohumano.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifba.gsort.partohumano.dtos.ProgramaDTO;
import ifba.gsort.partohumano.dtos.ProgramaRequest;
import ifba.gsort.partohumano.service.ProgramaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/programas")
@AllArgsConstructor
public class ProgramaController {

    private final ProgramaService programaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ProgramaDTO> criarPrograma(@RequestBody ProgramaRequest programa) {
        return ResponseEntity.ok().body(programaService.criarPrograma(programa));
    }

    @PatchMapping("{id}/add/{uuid}")
    public ResponseEntity<ProgramaDTO> adicionarMembro(@PathVariable("id") Long id, @PathVariable("uuid") UUID uuid) {
        this.programaService.adicionarMembro(uuid, id);
        return ResponseEntity.noContent().build();
    }
}

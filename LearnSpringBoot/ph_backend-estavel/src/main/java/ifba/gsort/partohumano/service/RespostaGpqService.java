package ifba.gsort.partohumano.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ifba.gsort.partohumano.model.Gpq;
import ifba.gsort.partohumano.model.PerguntaGpq;
import ifba.gsort.partohumano.model.RespostaGpq;
import ifba.gsort.partohumano.repository.GpqRepository;
import ifba.gsort.partohumano.repository.PerguntaGpqRepository;
import ifba.gsort.partohumano.repository.RespostaGpqRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RespostaGpqService {

    private final RespostaGpqRepository respostaGpqRepository;
    private final PerguntaGpqRepository perguntaGpqRepository;
    private final GpqRepository gpqRepository;

    public List<RespostaGpq> gerarRespostasGpq(Gpq gpq) {
        List<PerguntaGpq> perguntas = perguntaGpqRepository.findAll();
        List<RespostaGpq> respostas = perguntas.stream().map(pergunta -> new RespostaGpq(pergunta, gpq, 0)).toList();
        respostaGpqRepository.saveAll(respostas);
        return respostas;
    }

}

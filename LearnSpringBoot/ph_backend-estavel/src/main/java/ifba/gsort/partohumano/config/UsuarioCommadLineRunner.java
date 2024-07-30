package ifba.gsort.partohumano.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ifba.gsort.partohumano.dtos.AuthenticationDTO;
import ifba.gsort.partohumano.dtos.DadosCadastraisDTO;
import ifba.gsort.partohumano.dtos.UsuarioRequest;
import ifba.gsort.partohumano.mapper.EnfermeiraMapper;
import ifba.gsort.partohumano.model.Enfermeira;
import ifba.gsort.partohumano.model.Municipio;
import ifba.gsort.partohumano.model.Programa;
import ifba.gsort.partohumano.model.Roles;
import ifba.gsort.partohumano.model.StatusGpq;
import ifba.gsort.partohumano.model.StatusGpqEnum;
import ifba.gsort.partohumano.model.Usuario;
import ifba.gsort.partohumano.repository.EnfermeiraRepository;
import ifba.gsort.partohumano.repository.MunicipioRepository;
import ifba.gsort.partohumano.repository.ProgramaRepository;
import ifba.gsort.partohumano.repository.StatusGpqRepository;
import ifba.gsort.partohumano.repository.UsuarioRepository;
import ifba.gsort.partohumano.service.UsuarioService;

@Component
public class UsuarioCommadLineRunner implements CommandLineRunner {

        @Autowired
        private UsuarioService usuarioService;
        @Autowired
        private UsuarioRepository usuarioRepository;
        @Autowired
        private ProgramaRepository programaRepository;
        @Autowired
        private MunicipioRepository municipioRepository;
        @Autowired
        private EnfermeiraRepository enfermeiraRepository;
        @Autowired
        private StatusGpqRepository statusGpqRepository;
        @Value("${gsort.user.admin.name}")
        private String userName;
        @Value("${gsort.user.admin.password}")
        private String password;
        private Municipio salvador;

        @Override
        public void run(String... args) throws Exception {
                this.salvador = municipioRepository.findById(Long.valueOf(2927408)).get();
                criandoUsuarioAdmin();
                criarUsuariosDoProgramaDeTestes();
        }
        
        public void criandoUsuarioAdmin() {
                AuthenticationDTO credenciais = new AuthenticationDTO(userName, password);
                DadosCadastraisDTO dadosCadastraisDTO = new DadosCadastraisDTO("00000000000", "gsort", "71");
                usuarioService.criarNovoPerfil(
                                new UsuarioRequest(credenciais, dadosCadastraisDTO, Roles.Admin,
                                                this.salvador.getCodigo()));
        }

        public Programa criarProgramaDeTestes() {
                return programaRepository
                                .save(Programa.builder().nome("Programa gsort").municipio(this.salvador).build());
        }

        public void criarUsuariosDoProgramaDeTestes() {
                Programa programa = criarProgramaDeTestes();
                Usuario usuario;
                AuthenticationDTO credenciais = new AuthenticationDTO("Enfermeira", "EnfermagemPorAmor");
                DadosCadastraisDTO dadosCadastraisDTO = new DadosCadastraisDTO("11111111111", "Valentine", "071");
                // enfermeira
                usuario = usuarioService
                                .criarNovoPerfil(
                                                new UsuarioRequest(credenciais, dadosCadastraisDTO, Roles.Enfermeira,
                                                                this.salvador.getCodigo()));
                Enfermeira enfermeira = EnfermeiraMapper.toModel(usuario, "123456");
                programa.adicionarIntegrantes(usuario);
                this.usuarioRepository.save(usuario);
                this.enfermeiraRepository.save(enfermeira);
                this.programaRepository.save(programa);
                // Pezao
                credenciais = new AuthenticationDTO("Lavagem", "voceMeDaAcademias");
                dadosCadastraisDTO = new DadosCadastraisDTO("22222222222", "Pezao", "021");

                usuario = usuarioService.criarNovoPerfil(
                                new UsuarioRequest(credenciais, dadosCadastraisDTO, Roles.Ssm,
                                                this.salvador.getCodigo()));
                programa.adicionarIntegrantes(usuario);
                this.usuarioRepository.save(usuario);
                this.programaRepository.save(programa);

                // Cabral
                credenciais = new AuthenticationDTO("Influencia", "Trafico");
                dadosCadastraisDTO = new DadosCadastraisDTO("33333333333", "Cabral", "021");

                usuario = usuarioService.criarNovoPerfil(
                                new UsuarioRequest(credenciais, dadosCadastraisDTO, Roles.Ssm,
                                                this.salvador.getCodigo()));
                programa.adicionarIntegrantes(usuario);
                this.usuarioRepository.save(usuario);
                this.programaRepository.save(programa);

                // Gedel
                credenciais = new AuthenticationDTO("apartamento", "graca");
                dadosCadastraisDTO = new DadosCadastraisDTO("51515151510", "Gedel", "51000000");

                usuario = usuarioService.criarNovoPerfil(
                                new UsuarioRequest(credenciais, dadosCadastraisDTO, Roles.Ssm,
                                                this.salvador.getCodigo()));
                programa.adicionarIntegrantes(usuario);
                System.out.println(programa.toString());
                this.usuarioRepository.save(usuario);
                programa = this.programaRepository.save(programa);

        }

}

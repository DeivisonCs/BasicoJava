package ifba.gsort.partohumano.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ifba.gsort.partohumano.dtos.AuthenticationDTO;
import ifba.gsort.partohumano.dtos.TokenDTO;
import ifba.gsort.partohumano.model.Usuario;
import ifba.gsort.partohumano.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final PasswordEncoder bCryptPasswordEncoder;
    private final UsuarioRepository usuariosRepository;
    private final TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuariosRepository.findByUserName(username);
    }

    public Usuario registrarNovoUsuario(Usuario usuario) {
        if (this.loadUserByUsername(usuario.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado");
        }
        String password = bCryptPasswordEncoder.encode(usuario.getPassword());
        usuario.setPassword(password);
        return this.usuariosRepository.save(usuario);
    }

    public TokenDTO login(AuthenticationDTO usuario) {
        UserDetails userDetails = this.loadUserByUsername(usuario.getEmail());
        final UUID uuid = ((Usuario) userDetails).getId();
        if (bCryptPasswordEncoder.matches(usuario.getPassword(), userDetails.getPassword())) {
            final String token = tokenService.generateToken((Usuario) userDetails);
            return new TokenDTO(uuid, token);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credenciais invalidas");
    }

    public Usuario encontrarUsuario(String email) {
        return this.usuariosRepository.encontrarUsuarioPorEmail(email);
    }

}

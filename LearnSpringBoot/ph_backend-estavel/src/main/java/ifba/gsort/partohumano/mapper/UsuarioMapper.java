package ifba.gsort.partohumano.mapper;

import java.util.List;

import ifba.gsort.partohumano.dtos.UsuarioRequest;
import ifba.gsort.partohumano.dtos.UsuarioResponse;
import ifba.gsort.partohumano.model.DadosCadastrais;
import ifba.gsort.partohumano.model.Municipio;
import ifba.gsort.partohumano.model.Usuario;

public class UsuarioMapper {

	public static Usuario toModel(UsuarioRequest usuarioDTO, Municipio municipio) {
		DadosCadastrais dados = DadosCadastraisMapper.toModel(usuarioDTO.getDadosCadastrais());
//		if (usuarioDTO.estaligadoAMunicipio()) {
//			return new UsuarioMunicipal(usuarioDTO.getEmail(), usuarioDTO.getPassword().toString(), dados,
//					usuarioDTO.getPermissao(), municipio);
//		}
		return Usuario.builder()
				.email(usuarioDTO.getEmail())
				.password(usuarioDTO.getPassword())
				.dadosCadastrais(dados)
				.permissao(usuarioDTO.getPermissao())
				.municipio(municipio)
				.build();
	}
	
	public static UsuarioResponse toDTO(Usuario usuario) {
		return new UsuarioResponse(usuario);
	}
	
	public static List<UsuarioResponse> toDTOList(List<Usuario> usuarios) {
		return usuarios.stream().map(UsuarioResponse::new).toList();
	}

}

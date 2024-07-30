package ifba.gsort.partohumano.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import ifba.gsort.partohumano.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    public default UserDetails findByUserName(String userName) {
        return this.findByEmail(userName);
    }

    public UserDetails findByEmail(String email);

    @Query(nativeQuery = true, value = "Select * from usuarios where email = ?1")
    public Usuario encontrarUsuarioPorEmail(String email);

    public Usuario findByDadosCadastraisCpf(String cpf);

    @Query(nativeQuery = true, value = "Select count(distinct id) from usuarios where programa_numero_do_programa = ?1 and permissao = 'Ssm'")
    public int quantidadeDeUsuariosDeUmPrograma(long numeroDeUmPrograma);

    @Query(nativeQuery = true, value = "Select id from usuarios where programa_numero_do_programa = ?1")
    public List<UUID> listarIdsDeUsuariosDeUmPrograma(long numeroDoPrograma);

}

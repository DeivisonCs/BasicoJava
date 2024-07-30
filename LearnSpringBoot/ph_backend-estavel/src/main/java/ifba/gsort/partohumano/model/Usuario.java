package ifba.gsort.partohumano.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "usuarios")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator()
    private UUID id;
    private String email; // TODO validar
    private String password;
    @Embedded
    private DadosCadastrais dadosCadastrais;
    @Enumerated(EnumType.STRING)
    @Column(name = "permissao")
    private Roles permissao;
    @ManyToOne
    private Municipio municipio;
    @ManyToOne(fetch = FetchType.LAZY)
    private Programa programa;
    private LocalDateTime ativo;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (Roles.Admin == this.permissao)
            return List.of(
                    new SimpleGrantedAuthority("ROLE_Admin"),
                    new SimpleGrantedAuthority("ROLE_Sesab"),
                    new SimpleGrantedAuthority("ROLE_Fesf"),
                    new SimpleGrantedAuthority("ROLE_Ssm"),
                    new SimpleGrantedAuthority("ROLE_Enfermeira"));
        if (Roles.Sesab == this.permissao)
            return List.of(
                    new SimpleGrantedAuthority("ROLE_Sesab"),
                    new SimpleGrantedAuthority("ROLE_Fesf"),
                    new SimpleGrantedAuthority("ROLE_Ssm"));
        if (Roles.Fesf == this.permissao)
            return List.of(
                    new SimpleGrantedAuthority("ROLE_Fesf"),
                    new SimpleGrantedAuthority("ROLE_Ssm"));

        return List.of(new SimpleGrantedAuthority("ROLE_Enfermeira"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void ativar() {
        this.ativo = null;
    }

    public void desativar() {
        this.ativo = LocalDateTime.now();
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

}

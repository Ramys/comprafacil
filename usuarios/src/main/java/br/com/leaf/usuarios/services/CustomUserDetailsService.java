package br.com.leaf.usuarios.services;

import br.com.leaf.usuarios.entity.Usuarios;
import br.com.leaf.usuarios.exceptions.NegocioException;
import br.com.leaf.usuarios.models.CustomUserDetails;
import br.com.leaf.usuarios.repositories.UsuariosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuariosRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuarios> user = repository.findByEmail(email);
        return user.map(CustomUserDetails::new)
                .orElseThrow(() -> new NegocioException("Usuário não localizado"));
    }
}

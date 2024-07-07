package br.com.leaf.usuarios.services;

import br.com.leaf.usuarios.enums.PerfisEnum;
import br.com.leaf.usuarios.exceptions.NegocioException;
import br.com.leaf.usuarios.mappers.UsuariosMapper;
import br.com.leaf.usuarios.repositories.UsuariosRepository;
import br.com.leaf.usuarios.security.JwtService;
import br.com.leaf.usuarios.vos.LoginVO;
import br.com.leaf.usuarios.vos.RegistroVO;
import br.com.leaf.usuarios.vos.TokenResponseVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuariosServices {

    private final UsuariosMapper mapper;
    private final ValidacaoComponent component;
    private final UsuariosRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UUID registrarUsuario(RegistroVO registro) {

        this.component.validacao(registro);

        var usuario = this.mapper.registroTOUsuario(registro);

        var senhaEncode = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaEncode);

        var usuarioDb = this.repository.save(usuario);

        return usuarioDb.getId();
    }

    public TokenResponseVO logar(@Valid LoginVO vo) {
        var usuario = this.repository.findByEmail(vo.email()).orElseThrow(() -> new NegocioException("Usuário não localizado"));

        if (this.passwordEncoder.matches(vo.senha(), usuario.getSenha())) {
            var tokenJwt = this.jwtService.generateToken(usuario.getEmail(), PerfisEnum.ADMIN, 1L);
            return new TokenResponseVO(tokenJwt);
        } else {
            throw new NegocioException("Credenciais incorrtetas. Por favor informe novamente");
        }
    }
}

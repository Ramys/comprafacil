package br.com.leaf.usuarios.services;

import br.com.leaf.usuarios.exceptions.NegocioException;
import br.com.leaf.usuarios.repositories.UsuariosRepository;
import br.com.leaf.usuarios.vos.RegistroVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class ValidacaoComponent {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private final UsuariosRepository repository;

    public void validacao(RegistroVO vo) {

        if (Objects.isNull(vo)) {
            throw new NegocioException("Por favor, informe Nome, Senha e Confirmação de senha");
        }

        usuarioJaCadastrado(vo);
        validarEmail(vo);
        validarSenha(vo);

    }

    private void usuarioJaCadastrado(RegistroVO vo) {
        if (Objects.nonNull(vo.getEmail())) {
            if (this.repository.findByEmail(vo.getEmail()).isPresent()) {
                throw new NegocioException("E-mail já cadastrado");
            }
        }
    }

    private void validarSenha(RegistroVO vo) {
        if (Objects.isNull(vo.getSenha()) || Objects.isNull(vo.getSenhaConfirmacao())) {
            throw new NegocioException("Por favor, informe a Senha e a Confirmação de Senha");
        }
        if (!vo.getSenha().equals(vo.getSenhaConfirmacao())) {
            throw new NegocioException("As senhas não são iguais. Tente novamente.");
        }
    }

    private void validarEmail(RegistroVO vo) {
        if (Objects.isNull(vo.getEmail())) {
            throw new NegocioException("E-mail não invoado!");
        } else if (!isValidEmail(vo.getEmail())) {
            throw new NegocioException("E-mail inválido!");
        }
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

package br.com.leaf.usuarios.vos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginVO(@Email(message = "O e-mail não é válido")
                      @NotNull(message = "O e-mail não pode ser nulo")
                      String email,
                      @NotNull(message = "Senha não pode ser nula")
                      @NotBlank(message = "Senha não pode ser vazia")
                      String senha) {
}

package br.com.leaf.usuarios.vos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroVO {

    @NotNull
    @NotBlank(message = "Nome é obrigatório")
    @Length(min = 2, max = 50, message = "O nome do usuário deve ter entre 2 e 50 caracteres.")
    private String nome;

    @Email(message = "Email deve ser válido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    @NotBlank(message = "Senha é obrigatória")
    @Length(min = 4, max = 10, message = "A senha deve ter entre 4 e 10 caracteres")
    private String senha;

    @NotBlank(message = "Confirmação de senha é obrigatória")
    @Length(min = 4, max = 10, message = "A senha deve ter entre 4 e 10 caracteres")
    private String senhaConfirmacao;

}

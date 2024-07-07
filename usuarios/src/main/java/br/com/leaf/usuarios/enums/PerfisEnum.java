package br.com.leaf.usuarios.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PerfisEnum {

    ADMIN(1, "ADM"),
    CLIENTE(2, "CLI");

    private final Integer codigo;
    private final String descricao;

    public static PerfisEnum obterPerfilPorDescricao(String descricao) {
        return Arrays.stream(PerfisEnum.values())
                .filter(perfil -> perfil.getDescricao().equals(descricao))
                .findFirst()
                .orElse(null);
    }

}

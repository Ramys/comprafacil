package br.com.leaf.usuarios.controllers;

import br.com.leaf.usuarios.services.UsuariosServices;
import br.com.leaf.usuarios.vos.RegistroVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cadastro-usuario")
@Tag(name = "1 - Login e Registro de Usuários",
        description = "A API de Usuários permite que os usuários se registrem e façam login no sistema. " +
                "Ela garante a segurança e integridade dos dados do usuário, proporcionando um controle de " +
                "acesso robusto baseado em permissões.")
public class RegistroUsuarioController {

    private final UsuariosServices service;

    @PostMapping("/registrar-usuarios")
    @Operation(summary = "O serviço de registro de usuário permite que novos usuários criem uma conta no sistema. " +
            "Os usuários fornecem informações como nome, email, senha e confirmação de senha. " +
            "O sistema valida e armazena esses dados de forma segura.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro criado com sucesso", headers = {
                    @Header(name = "Location", description = "URI do recurso criado", schema = @Schema(implementation = String.class))
            }),
            @ApiResponse(responseCode = "400", description = "Falha no cadastro do cliente"),
    })
    public ResponseEntity<UUID> registrarUsuarios(@Validated @RequestBody RegistroVO form) {
        var id = this.service.registrarUsuario(form);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/logar")
    @Operation(summary = "O serviço de login autentica usuários existentes, " +
            "permitindo que acessem suas contas. Os usuários fornecem suas credenciais (email e senha), " +
            "que são verificadas pelo sistema.")
    public String logar() {
        return "oi";
    }

    @PostMapping("/autorizar")
    @Operation(summary = "O serviço de autorização controla o acesso dos usuários a diferentes partes e funcionalidades" +
            " do sistema, com base em suas permissões e roles (ex.: usuário comum, administrador).")
    public String autorizar() {
        return "oi";
    }

    @GetMapping("/teste")
    @Operation(summary = "O serviço de autorização controla o acesso dos usuários a diferentes partes e funcionalidades" +
            " do sistema, com base em suas permissões e roles (ex.: usuário comum, administrador).")
    public String teste() {
        return "oi";
    }

}

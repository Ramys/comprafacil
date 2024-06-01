<h1 align="center" style="font-family: Arial, sans-serif; color: #FFFF00;">
  Módulo de usuários
</h1>

<p align="center">
  <img src="../images/img.png" style="width: 50px; height: auto;" alt="Símbolo de em construção"/> <br />
<span style="font-family: Arial, sans-serif; color: #FF0000;">Em construção</span>
</p>

# Módulo de Usuários - Compra Fácil

Os usuários devem ser capazes de se cadastrar e fazer login no sistema usando as ferramentas do Spring Security para autenticação e autorização.

## Funcionalidades

### Registro de Usuário

Os usuários podem se registrar no sistema fornecendo as seguintes informações:
- Nome
- Email
- Senha
- Confirmação de senha

**Detalhes:**
- Formulário de registro com validação de dados no lado do cliente e no servidor.
- Armazenamento seguro da senha utilizando hashing.

### Login de Usuário

Os usuários registrados podem fazer login no sistema utilizando:
- Email
- Senha

**Detalhes:**
- API de login que aceita email e senha.
- Autenticação de usuário usando Spring Security.
- Gestão de sessões de usuário utilizando tokens do Spring Security.

### Autorização

Controle de acesso baseado em permissões do usuário:
- Usuário comum
- Administrador

**Detalhes:**
- Controle de acesso a diferentes partes do sistema com base nas permissões do usuário.
- Middleware de verificação de permissões antes de permitir o acesso a rotas protegidas.

## Tecnologias Utilizadas

- **Spring Security:** Para autenticação e autorização.
- **Spring Boot:** Para a estrutura do aplicativo.
- **JWT (JSON Web Tokens):** Para gestão de sessões de usuário.

## Estrutura do Projeto

├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── comprafacil
│   │   │           └── usuarios
│   │   │               ├── controller
│   │   │               │   └── UserController.java
│   │   │               ├── model
│   │   │               │   └── User.java
│   │   │               ├── repository
│   │   │               │   └── UserRepository.java
│   │   │               ├── service
│   │   │               │   └── UserService.java
│   │   │               └── security
│   │   │                   ├── WebSecurityConfig.java
│   │   │                   └── JwtTokenProvider.java
│   └── resources
│       └── application.properties
└── pom.xml
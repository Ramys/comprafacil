<h1 align="center" style="font-family: Arial, sans-serif; color: #FFFF00;">
  Compra Fácil
</h1>

<p align="center">
  <img src="src/main/resources/images/img.png" style="width: 50px; height: auto;" alt="Símbolo de em construção"/> <br />
<span style="font-family: Arial, sans-serif; color: #FF0000;">Em construção</span>
</p>

O "Compra Fácil" é um software inovador de gerenciamento de carrinho de compras e gestão, projetado para oferecer uma
experiência de compra eficiente e intuitiva. Este sistema é ideal para lojas online, supermercados e qualquer
estabelecimento que necessite de uma solução robusta para facilitar o processo de compras dos seus clientes.

## Objetivo

O objetivo do "Compra Fácil" é simplificar o processo de compras para os clientes, ao mesmo tempo em que oferece aos
gestores ferramentas poderosas para controlar e otimizar as operações de vendas e estoque.

## Funcionalidades Principais

### 1. Login e Registro de Usuário

**Descrição:** Os usuários devem ser capazes de se cadastrar e fazer login no sistema usando as ferramentas do Spring
Security para autenticação e autorização.

**Funcionalidades:**

- **Registro de Usuário:**
    - Formulário de registro contendo campos como nome, email, senha e confirmação de senha.
    - Validação dos dados do usuário no lado do cliente e no servidor.
    - Armazenamento seguro da senha usando hashing.
- **Login de Usuário:**
    - API de login com variáveis para email e senha.
    - Autenticação de usuário usando Spring Security.
    - Gestão de sessões de usuário utilizando tokens do Spring Security.
- **Autorização:**
    - Controle de acesso a diferentes partes do sistema com base nas permissões do usuário (usuário comum,
      administrador).
    - Middleware de verificação de permissões antes de permitir o acesso a rotas protegidas.

### 2. Gestão de Itens

**Descrição:** Os usuários administradores terão acesso a uma tela de gestão de itens, permitindo o controle do cadastro
e manutenção de itens, bem como seus preços.

**Funcionalidades:**

- **Listagem de Itens:** (Todos)
    - Exibição de uma tabela/lista de itens cadastrados com informações básicas (nome, descrição, preço).
- **Cadastro de Novo Item:** (Administrador)
    - API para adicionar um novo item, incluindo campos como nome, descrição, categoria, preço, URL da imagem e
      quantidade.
    - Validação para saber se o usuário tem permissão de fazer essa função.
- **Edição de Item:** (Administrador)
    - Opção para editar informações de um item existente.
    - Atualização dos dados no banco de dados.
    - Validação para saber se o usuário tem permissão de fazer essa função.
- **Remoção de Item:** (Administrador)
    - Função para remover itens do sistema.
    - Validação para saber se o usuário tem permissão de fazer essa função.

### 3. Carrinho de Compras

**Descrição:** Os usuários podem adicionar e remover itens do carrinho de compras. O carrinho de compras deve ser
persistente e associado ao usuário logado.

**Funcionalidades:** (Usuário Comum)

- **Adição de Itens ao Carrinho:** (Usuário Comum)
    - Adicionar um item e quantidade a um carrinho.
- **Remoção de Itens do Carrinho:** (Usuário Comum)
    - Remoção de um item e de sua quantidade.
- **Visualização do Carrinho:** (Usuário Comum)
    - Consulta que lista de itens no carrinho do cliente.

### 4. Pagamentos (Simulação)

**Descrição:** Implementar um serviço que vai chamar uma API de pagamento passando um carrinho.

**Funcionalidades:**

- **Resumo do Carrinho:**
    - Listar todos os itens do carrinho.
    - Colocar no final um resumo de quantidade de itens e soma de valores.
- **Simulação de Transação:**
    - Envia para um endpoint mockado lista de itens, custo total e o tipo de pagamento (Cartão, Boleto, Débito, Pix).
    - O endpoint mockado vai sempre retornar "pagamento realizado com sucesso".

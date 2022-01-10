# TQI evolution backend 2021 - API REST Empresa de Empréstimos

* Esse repósitório contém todo o código utilizado como solução do desafio proposto de criar uma Api para cadastro de clientes e empréstimos, utilizando os requisitos necessários para a funcionalidade do projeto.

* Funcionalidades implementas: Sign Up e cadastro de dados do cliente e endereço; Cadastro de Empréstimo com quantidade máxima de parcelas (60x) e prazo para primeira parcela (3 meses a partir da data atual); Área de login com Email e Senha; Segurança e autenticação de usuário com Spring Security e JWT.

## Requests da API:

* A ferramenta utilizada para os testes de implementação da API foi o [Postman](https://www.postman.com/product/what-is-postman/)

* No repositório há um arquivo JSON (Requests.postman_collection.json) que contém uma Collection do Postman com as Request implementas no projeto.

* [Como Utilizar o Arquivo de Collection](https://nfe.io/docs/documentacao/nota-fiscal-produto-eletronica/importar-colecao-postman/)

## Documentação da API:

* Para o auxilio na descrição, consumo e visualização de serviços da API REST, foi utilizado o [Swagger UI](https://swagger.io/tools/swagger-ui/)

* Para acessar esse serviço basta roda o projeto na sua IDE de preferência. Utilizando o seu navegador web, digite o endereço para acessar a documentação: http://localhost:8080/swagger-ui.html

## Banco de dados:

- O banco de dados utilizado para a database do projeto foi o MySql.

- No link abaixo, você pode obter mais informações sobre como implementar o Banco de dados MySql no seu projeto: 

- [MySql Guide](https://www.javatpoint.com/example-to-connect-to-the-mysql-database)

## Segurança:

- Nesse projeto foi implementado um modelo de segurança através do Spring Security e autenticação com o Json Web Token 

- Através do link abaixo, segue um artigo como exemplo da implementação utilizada nesse projeto:

- [Spring Security e JWT](https://programadev.com.br/spring-security-jwt/)

## Acesso a funcionalidade dos endpoints

### Sign Up e Cadastro de Usuários:
Aqui o usuário pode cadastras seus dados pessoais como: nome, e-mail, CPF, RG, endereço completo, renda e senha. Aqui também será possível a escolha de perfil: Admin (perfil 1) ou Cliente (perfil 2).

- Tipo de Request: POST
- URL: localhost:8080/clientes/signup
- Exemplo de Json:

```json
 
   "nome": "Fulano Ciclano",
    "email": "usuario@email.com",
    "cpf": "38588427028",
    "rg": "12345678",
    "renda": 98765.43,
    "idPerfil": 1,
    "senha": "123456789",
    "enderecos": [{
        "logradouro": "Rua Diniz",
        "numero": "123",
        "complemento": "Casa",
        "bairro": "Santa Rosa",
        "cep": "31255590",
        "cidade": "Belo Horizonte",
        "estado": "Minas Gerais"
    }]

```

### Cadastro de clientes pelo Usuário:
Aqui é possível que o próprio usuário cadastrado como Cliente faça a requisição de um empréstimo

- Tipo de Request: POST
- URL: localhost:8080/emprestimos/create/me
- Autenticação: Bearer Token
- Exemplo de Json:

```json
        "valorEmprestimo": 10000.90,
        "quantidadeParcelas": 35,
        "primeiraParcela":"06-03-2022"

```

### Cadastro de Clientes pelo Admin: 
Aqui é possível que um Usuário logado como Admin faça um empréstimo pelo id do Cliente

- Tipo de Request: POST
- URL: localhost:8080/emprestimos/admin/{id}
- Autenticação: Bearer Token
- Exemplo de Json:

```json
        "valorEmprestimo": 10000.90,
        "quantidadeParcelas": 35,
        "primeiraParcela":"06-03-2022"

```

### Login do Usuário:
Aqui é possível que um usuário já cadastrado faça login com seu email e senha

- Tipo de Request: POST
- URL: localhost:8080/login
- Exemplo de Json:

```json
    "email": "usuario@email.com",
    "senha": "123456789"
```

### Lista de Cliente pelo Admin:
Aqui é possivel que o usuário Admin tenha acesso a lista de usuários

- Tipo de Request: GET
- Autenticação: Bearer Token
- URL: localhost:8080/clientes

### Cliente por id pelo Admin:
Aqui é possivel que o usuário Admin tenha acesso ao cliente pelo seu id

- Tipo de Request: GET
- Autenticação: Bearer Token
- URL: localhost:8080/clientes

### Lista de Empréstimos pelo Admin:
Aqui é possível ao admin consultar a lista de emprestimos cadastrados

- Tipo de Request: GET
- Autenticação: Bearer Token
- URL: localhost:8080/emprestimos

### Empréstimo por id pelo Admin:
Aqui é possivel ao Admin acessar os detalhes de um empréstimo pelo seu id

- Tipo de Request: GET
- Autenticação: Bearer Token
- URL: localhost:8080/emprestimos/admin/{id}

### Detalhes do cliente pelo cliente:
Aqui é possível que o próprio usuário busque os detalhes do seu cadastro

- Tipo de Request: GET
- Autenticação: Bearer Token
- URL: localhost:8080/clientes/me

### Lista de empréstimos pelo Cliente:
Aqui é possivel que o cliente consulte a sua lista de empréstimos.

- Tipo de Request: GET
- Autenticação: Bearer Token
- URL: localhost:8080/emprestimos/me

### Empréstimo por id pelo Cliente:
Aqui é possível ao cliente acessar os detalhes de um dos seu empréstimo pelo id

- Tipo de Request: GET
- Autenticação: Bearer Token
- URL: localhost:8080/emprestimos/{id}/me

### Empréstimo pelo Id do Cliente:
Aqui é possivel ao Admin consultar os detalhes do empréstimo de um cliente pelo seu id

- Tipo de Request: GET
- Autenticação: Bearer Token
- URL: localhost:8080/emprestimos/clientes/{id}

### Edição do Cliente por id
Aqui é possível ao Cliente ou Admin substituir as suas informações de cadastro pelo id

- Tipo de Request: PUT
- Autenticação: Bearer Token
- URL: localhost:8080/clientes/{id}
- JSON: Para essa Resquest o Json utilizado é o mesmo para a Request de Sign Up.

### Edição do Empréstimo por id
Aqui é possível ao Admin alterar informações de um empréstimo pelo o id.

- Tipo de Request: PUT
- Autenticação: Bearer Token
- URL: localhost:8080/emprestimos/{id}
- JSON: Para essa Resquest o Json utilizado é o mesmo para a Request de cadastro de empréstimos.

### Deletar empréstimos por Id
Aqui é possível ao Admin deletar um empréstimo pelo id.

- Tipo de Request: DELETE
- Autenticação: Bearer Token
- URL: localhost:8080/emprestimos/{id}

### Deletar cliente por Id
Aqui é possível ao Admin deletar um cliente pelo id.

- Tipo de Request: DELETE
- Autenticação: Bearer Token
- URL: localhost:8080/clientes/{id}


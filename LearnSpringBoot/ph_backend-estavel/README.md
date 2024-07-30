
# Parto Humanizado

Descrição do projeto




## Rodando o projeto (Docker)

Clone o projeto

```bash
  git clone https://gitlab.com/ifba/projetos/parto-humanizado/ph_backend.git
```

Entre no diretório do projeto

```bash
  cd ph_backend
```

No terminal, crie o arquivo jar da aplicação

```bash
  ./gradlew build
```

Execute o docker compose. Verifique se o docker está aberto e funcionando antes.

```bash
  docker compose up -d
```

## Rodando o projeto localmente
O projeto contém uma dependência do docker. Por isso, se você executar a aplicação spring de forma local ele ainda gerencia o arquivo docker-compose que está no projeto.  
Por isso, você pode comentar a parte do app no docker compose e ter apenas o banco sendo executado no docker, enquanto a aplicação spring está executando localmente.  


## Documentação da API

#### Registra um novo usuário

```http
  POST /auth/register
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `userName` | `string` | **Obrigatório**. O email do usuário |
| `password` | `string` | **Obrigatório**. A senha do usuário |

#### Fazer login (retorna o token do usuário na response)

```http
  POST /auth/login/
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `userName` | `string` | **Obrigatório**. O email do usuário |
| `password` | `string` | **Obrigatório**. A senha do usuário |





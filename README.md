
```markdown
# API de Pessoas

Esta API fornece endpoints para criar, atualizar, buscar e excluir pessoas.

## Endpoints da API

A API possui os seguintes endpoints:

* `/api/pessoa/adicionar`: Cria uma nova pessoa.
* `/api/pessoa/{id}/atualizar`: Atualiza uma pessoa existente.
* `/api/pessoa/{id}`: Busca uma pessoa pelo seu ID.
* `/api/pessoa/listar`: Lista todas as pessoas.
* `/api/pessoa/buscarPorNome/{nome}`: Busca pessoas pelo seu nome.

## Parâmetros e retornos dos endpoints

Os parâmetros e retornos dos endpoints são os seguintes:

### Endpoint `/api/pessoa/adicionar`

**Parâmetros:**

* `dataNascimento`: A data de nascimento da pessoa, no formato `YYYY-MM-DD`.
* `nome`: O nome da pessoa.

**Retornos:**

* `Pessoa`: Um objeto do tipo `Pessoa`, que contém os dados da pessoa criada.

### Endpoint `/api/pessoa/{id}/atualizar`

**Parâmetros:**

* `id`: O ID da pessoa a ser atualizada.
* `dataNascimento`: A data de nascimento da pessoa, no formato `YYYY-MM-DD` (opcional).
* `nome`: O nome da pessoa (opcional).

**Retornos:**

* `Pessoa`: Um objeto do tipo `Pessoa`, que contém os dados da pessoa atualizada.

### Endpoint `/api/pessoa/{id}`

**Parâmetros:**

* `id`: O ID da pessoa a ser buscada.

**Retornos:**

* `Pessoa`: Um objeto do tipo `Pessoa`, que contém os dados da pessoa buscada.

### Endpoint `/api/pessoa/listar`

**Parâmetros:**

Nenhum.

**Retornos:**

* Uma lista de objetos do tipo `Pessoa`, que contém os dados de todas as pessoas cadastradas.

### Endpoint `/api/pessoa/buscarPorNome/{nome}`

**Parâmetros:**

* `nome`: O nome da pessoa a ser buscada.

**Retornos:**

* Uma lista de objetos do tipo `Pessoa`, que contém os dados de todas as pessoas com o nome especificado.

## Erros que podem ser retornados pela API

Os erros que podem ser retornados pela API são os seguintes:

* `400 Bad Request`: Se o parâmetro `dataNascimento` for inválido.
* `404 Not Found`: Se o ID da pessoa não for encontrado.

## Exemplos

### Criar uma nova pessoa

```
POST /api/pessoa/adicionar

{
  "dataNascimento": "2023-09-29",
  "nome": "John Doe"
}

```

### Atualizar uma pessoa existente

```
PUT /api/pessoa/1

{
  "dataNascimento": "2023-10-01",
  "nome": "Jane Doe"
}

```

### Buscar uma pessoa pelo ID

```
GET /api/pessoa/1

```

### Listar todas as pessoas

```
GET /api/pessoa/listar

```

### Buscar pessoas pelo nome

```
GET /api/pessoa/buscarPorNome/John

```


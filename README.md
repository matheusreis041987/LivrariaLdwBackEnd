LivrariaLdw
===========

<!-- TOC -->
* [LivrariaLdw](#livrarialdw)
* [Descrição](#descrição)
* [Modelagem do domínio](#modelagem-do-domínio)
* [Escopo inicial](#escopo-inicial)
* [APIs](#apis)
  * [APIs de Livro](#apis-de-livro)
    * [`/cadastrar`](#cadastrar)
    * [`/atualizar_preco_de_venda`](#atualizarprecodevenda)
    * [`/buscar`](#buscar)
    * [`/detalhar/{id}`](#detalharid)
<!-- TOC -->

# Descrição

![example workflow](https://github.com/matheusreis041987/LivrariaLdwBackEnd/actions/workflows/maven.yml/badge.svg)
![framework_back](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![server_ci](https://img.shields.io/badge/Github%20Actions-282a2e?style=for-the-badge&logo=githubactions&logoColor=367cfe)

Repositório para o backend do trabalho a ser entregue na disciplina Laboratório de Desenvolvimento Web, do curso Análise e Projeto de Sistemas, da PUC-RJ.

# Modelagem do domínio

O Modelo Entidade-Relacionamento do domínio completo se dá conforme a imagem abaixo.

![Diagrama Entidade Relacionamento](https://github.com/matheusreis041987/LivrariaLdwBackEnd/blob/main/docs/modelo_entidade_relacionamento/DER%20Livraria%20LDW.png)

# Escopo inicial

Como entrega inicial da aplicação, desenvolveremos o back-end para a entidade Livro, com 4 APIs a serem consumidas pelo
front.

Como simplificação, não faremos controle de estoque, nem guardaremos histórico de preços de venda.

Nessa V0, o atributo `Autor` será substituído por `Autoria`, que guardará texto com os autores (pode haver mais de um, naturalmente).

Igualmente, colocaremos o atributo `quantidade` em `Livro`, em vez da entidade da modelagem original.

# APIs
## APIs de Livro

A raiz de todos os *endpoints*, discrimandos abaixo, será `/api/livros`.

### `/cadastrar`

Expectativa de ser utilizado somente por usuários internos de nossa livraria, responsáveis pelo estoque.

Através dessa rota, cuja implementação se dará pelo método POST, o estoquista inserirá as informações cadastrais de novo livro a ser vendido.

```json
{
  "isbn": "9786500019506",
  "titulo": "Engenharia de Software Moderna",
  "autoria": "Marco Tulio Valente",
  "editora": "Independente",
  "categoria": "INFORMATICA",
  "precoVenda": 100.90,
  "quantidade": 1
}
```

Em caso de preenchimento inadequado ou não preenchimento, a aplicação deverá informar o erro.

Como dívida técnica, será implementado algoritmo de validação de ISBN.

### `/atualizar_preco_de_venda`

Através dessa rota, cuja implementação se dará pelo método PUT, o estoquista informará novo preço de venda para um livro anteriormente cadastrado, passando `id` e `precoVenda`.

Também será feito através de tela para acesso interno apenas. 

Se `id` inexistente, retornar que o recurso não foi encontrado.

```json
{
  "id": 1,
  "precoVenda": 99.99
}
```

### `/buscar`

Esse endpoint pode ser utilizado tanto para clientes internos quanto externos.

Exibe a lista de livros em catálogo, porém não todos os campos. Somente `Titulo`, `Autoria`, `Categoria` e `Preco_Venda`.

Requisições usarão método GET, com possibilidade dos seguintes parâmetros (opcionais):

* ISBN
* Categoria
* Autoria
* Titulo
* Editora

Pesquisas para `Autor`, `Titulo` e `Editora` devem considerar a possibilidade de *matches* parciais.

### `/detalhar/{id}`

Endpoint para detalhamento de um livro.

Todos os campos são exibidos aqui.
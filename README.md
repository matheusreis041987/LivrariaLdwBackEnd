# LivrariaLdw

Repositório para o backend do trabalho a ser entregue na disciplina Laboratório de Desenvolvimento Web, do curso Análise e Projeto de Sistemas, da PUC-RJ.

## Escopo inicial

Como entrega inicial da aplicação, desenvolveremos o back-end para a entidade Livro, com 4 APIs a serem consumidas pelo
front.

Como simplificação, não faremos controle de estoque, nem guardaremos histórico de preços de venda.

Nessa V0, o atributo `Autor` será substituído por `Autoria`, que guardará texto com os autores (pode haver mais de um, naturalmente).

## APIs de Livro

Desenvolveremos os seguintes *endpoints*

### `/cadastrar_livro`

Expectativa de ser utilizado somente por usuários internos de nossa livraria, responsáveis pelo estoque.

Através dessa rota, cuja implementação se dará pelo método POST, o estoquista inserirá as informações cadastrais de novo livro a ser vendido.

```json
{
  "isbn": "9786500019506",
  "titulo": "Engenharia de Software Moderna",
  "autoria": "Marco Tulio Valente",
  "editora": "Independente",
  "categoria": "INFORMATICA",
  "precoVenda": 100.90
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

### `/livros`

Esse endpoint pode ser utilizado tanto para clientes internos quanto externos.

Exibe a lista de livros em catálogo, porém não todos os campos. Somente `Titulo`, `Autoria`, `Categoria` e `Preco_Venda`.

Requisições usarão método GET, com possibilidade dos seguintes parâmetros (opcionais):

* ISBN
* Categoria
* Autoria
* Titulo
* Editora

Pesquisas para `Autor`, `Titulo` e `Editora` devem considerar a possibilidade de *matches* parciais.

### `/livros/{id}`

Endpoint para detalhamento de um livro.

Todos os campos são exibidos aqui.
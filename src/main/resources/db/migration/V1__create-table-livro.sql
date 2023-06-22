create table livro(
    id bigint primary key auto_increment,
    isbn varchar(13) not null,
    titulo varchar(200) not null,
    autoria varchar(200) not null,
    editora varchar(100) not null,
    categoria varchar(100) not null,
    preco_venda decimal(15, 2) not null
);
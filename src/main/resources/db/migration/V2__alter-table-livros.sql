alter table "livraria".livro
    add column quantidade int not null default 1,
    add column capa bytea;
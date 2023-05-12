use dbmariposa;

create table usuarios(
    id_usuario int auto_increment primary key,
    email varchar(100),
    senha varchar(255)
);

create table perguntas(
    id_pergunta varchar(10) primary key,
    texto_pergunta varchar(500)
);

insert into perguntas (id_pergunta, texto_pergunta) values
    ('perg1', "Quantas roupas novas você costuma comprar por ano?"),
    ('perg2', "Quantas vezes você lava sua roupa na semana?"),
    ('perg3', "Você costuma usar roupas de segunda mão? Digite um dos números que mais descreva sua ação: 0- Sim. 1-Não."),
    ('perg4', "Quantas peças de jeans você possui no guarda-roupas?"),
    ('perg5', "Quantas peças de roupa (sem ser jeans) você possui no seu guarda-roupa?"),
    ('perg6', "Como você geralmente descarta as roupas que não usa mais? Digite um dos números que mais descreva sua ação: 1- Jogo no lixo. 2- Se estão em bom estado vão para a doação. 3- Outro."),
    ('perg7', "Por quanto tempo (em anos) você costuma usar uma peça de roupa nova antes de descartá-la?" );

create table respostas(
    id_pergunta varchar(10),
    id_usuario int,
    resposta int,
    foreign key (id_usuario) references usuarios (id_usuario),
    foreign    key (id_pergunta) references perguntas (id_pergunta)
);

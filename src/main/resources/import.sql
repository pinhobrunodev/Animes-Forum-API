INSERT INTO tb_role (authority,created_At) values ('ROLE_ADMIN',NOW());
INSERT INTO tb_role (authority,created_At) values ('ROLE_MODERATOR',NOW());
INSERT INTO tb_role (authority,created_At) values ('ROLE_BASIC',NOW());


INSERT  INTO tb_user (first_Name,last_Name,nickname,email,password,created_At) values ('Bruno','Pinho','pinhobrunodev','admin@gmail.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG',NOW());
INSERT  INTO tb_user (first_Name,last_Name,nickname,email,password,created_At) values ('Matheus','Oliveira','theussoliveira','matheus@gmail.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG',NOW());
INSERT  INTO tb_user (first_Name,last_Name,nickname,email,password,created_At) values ('Cleiton','Bispo','cleitin','cleiton@gmail.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG',NOW());
INSERT  INTO tb_user (first_Name,last_Name,nickname,email,password,created_At) values ('José','Carvalho','zezinho','ze@gmail.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG',NOW());


INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 3);
INSERT INTO tb_user_role (user_id, role_id) VALUES (4, 3);


INSERT INTO tb_gender(name,created_At) values ('Magic',NOW());
INSERT INTO tb_gender(name,created_At) values ('Action',NOW());
INSERT INTO tb_gender(name,created_At) values ('Drama',NOW());
INSERT INTO tb_gender(name,created_At) values ('Horror',NOW());
INSERT INTO tb_gender(name,created_At) values ('Sports',NOW());
INSERT INTO tb_gender(name,created_At) values ('Games',NOW());
INSERT INTO tb_gender(name,created_At) values ('Terror',NOW());
INSERT INTO tb_gender(name,created_At) values ('Slice of Life',NOW());
INSERT INTO tb_gender(name,created_At) values ('Romance',NOW());
INSERT INTO tb_gender(name,created_At) values ('Mecha',NOW());
INSERT INTO tb_gender(name,created_At) values ('Adventure',NOW());


INSERT INTO tb_anime(title,description,img_Url,release_Date,created_At) values ('Kyoukai Senki','No ano de 2061 DC, o Japão perdeu sua soberania. O povo japonês passa seus dias como cidadãos oprimidos depois de ser dividido e governado pelas quatro principais facções comerciais. O país se tornou a vanguarda do mundo após a implantação da AMAIM - uma arma móvel especial humanóide - por cada bloco econômico. Um dia, Amou Shiiba, um menino que ama máquinas, conhece Gai, um A.I. pensador autônomo. O encontro leva Amou a se lançar na batalha para recuperar o Japão, pilotando o AMAIM Kenbu que ele mesmo construiu.','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpv-jGuS5sUgJZmEWEMZIxlkAAr1Ke9IVg-Q&usqp=CAU','2021-10-06',NOW());


INSERT INTO tb_anime_gender(anime_id,gender_id) values (1,10);
INSERT INTO tb_anime_gender(anime_id,gender_id) values (1,11);
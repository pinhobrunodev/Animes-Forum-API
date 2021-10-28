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
INSERT INTO tb_gender(name,created_At) values ('Games',NOW());
INSERT INTO tb_gender(name,created_At) values ('Terror',NOW());
INSERT INTO tb_gender(name,created_At) values ('Slice of Life',NOW());
INSERT INTO tb_gender(name,created_At) values ('Mecha',NOW());
INSERT INTO tb_gender(name,created_At) values ('Adventure',NOW());
INSERT INTO tb_gender(name,created_At) values ('Romance',NOW());
INSERT INTO tb_gender(name,created_At) values ('Comedy',NOW());
INSERT INTO tb_gender(name,created_At) values ('Shounen',NOW());
INSERT INTO tb_gender(name,created_At) values ('Super Powers',NOW());


INSERT INTO tb_anime(title,description,img_Url,release_Date,created_At) values ('Kyoukai Senki','No ano de 2061 DC, o Japão perdeu sua soberania. O povo japonês passa seus dias como cidadãos oprimidos depois de ser dividido e governado pelas quatro principais facções comerciais. O país se tornou a vanguarda do mundo após a implantação da AMAIM - uma arma móvel especial humanóide - por cada bloco econômico. Um dia, Amou Shiiba, um menino que ama máquinas, conhece Gai, um A.I. pensador autônomo. O encontro leva Amou a se lançar na batalha para recuperar o Japão, pilotando o AMAIM Kenbu que ele mesmo construiu.','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpv-jGuS5sUgJZmEWEMZIxlkAAr1Ke9IVg-Q&usqp=CAU','2021-10-06',NOW());
/** Magic Animes**/
INSERT INTO tb_anime(title,description,img_Url,release_Date,created_At) values ('Blue Reflection Ray','Um céu claro de verão se espalha sobre o colégio feminino Hoshinomiya. Esta história começa com o início tardio da vida escolar de Hinako Shirai, que acaba de se recuperar de uma lesão na perna devido a um trágico acidente. As irmãs mágicas Yuzu e Lime concederam a ela um poder especial para se tornar uma ‘Reflector’. Hinako se transforma na forma mágica de Reflector e protege o mundo de forças devastadoras, pelo bem do mundo e de seu próprio sonho, do qual ela pensou que deveria desistir.','https://image.tmdb.org/t/p/original/nlJjX7W7qJkG1KcYooVyy0Y5Yk8.jpg','2021-10-10',NOW());
INSERT INTO tb_anime(title,description,img_Url,release_Date,created_At) values ('Mushoku Tensei: Isekai Ittara Honki Dasu','Mushoku Tensei: Isekai Ittara Honki Dasu é uma série de light novel japonesa de Rifujin na Magonote sobre um homem desempregado e desesperado que reencarna em um mundo de fantasia enquanto guarda suas memórias, determinado a viver sua nova vida sem arrependimentos','https://i2.wp.com/www.otakupt.com/wp-content/uploads/2020/12/Mushoku-Tensei-Jobless-Reincarnation-poster-scaled.jpg?resize=696%2C992&ssl=1','2021-10-04',NOW());
INSERT INTO tb_anime(title,description,img_Url,release_Date,created_At) values ('Tsuki ga Michibiku','A fantasia gira em torno de Makoto Misumi, um garoto normal do ensino médio convocado para um mundo alternativo como um bravo guerreiro. Infelizmente, a deusa do mundo disse com desdém: ''Seu rosto é feio'', despojou-o de seu título e o baniu para as periferias do deserto. Enquanto vagava pela selva, Makoto encontrou dragões, aranhas, orcs, anões e todos os tipos de outras espécies não humanas. Devido às diferenças no ambiente de seu mundo natal, Makoto agora exibe poderes extraordinários em magia e combate. Assim, ele sobrevive neste mundo ao lidar com vários encontros. A cortina sobe para a fantasia de reforma social do mundo alternativo de um menino abandonado por deuses e humanos','https://image.tmdb.org/t/p/original/9OkSps4eGuntx1e6GZtQ0vF5whM.jpg','2021-07-07',NOW());
INSERT INTO tb_anime(title,description,img_Url,release_Date,created_At) values ('Naruto','Naruto Uzumaki, um ninja hiperativo e junta-cabeças, vive em Konohagakure, o Hidden Leaf Village. Momentos antes do seu nascimento, um demônio enorme conhecida como a Kyuubi, a Raposa de Nove Caudas, atacou Konohagakure e causou estragos. A fim de colocar um fim à violência da Kyuubi, o líder da vila, o Hokage 4, sacrificou sua vida e selou a besta monstruosa dentro do Naruto recém-nascido. Evitado por causa da presença da Kyuubi dentro dele, Naruto se esforça para encontrar seu lugar na aldeia. Ele se esforça para se tornar o Hokage de Konohagakure, e ele encontra muitos amigos e inimigos ao longo do caminho.','https://img.olhardigital.com.br/wp-content/uploads/2021/07/Naruto-Classico-e-Naruto-Shippuden-fillers-1024x576.jpg','1999-09-21',NOW());


INSERT INTO tb_anime_gender(anime_id,gender_id) values (2,1);
INSERT INTO tb_anime_gender(anime_id,gender_id) values (2,8);
INSERT INTO tb_anime_gender(anime_id,gender_id) values (2,2);
INSERT INTO tb_anime_gender(anime_id,gender_id) values (2,6);
INSERT INTO tb_anime_gender(anime_id,gender_id) values (3,1);
INSERT INTO tb_anime_gender(anime_id,gender_id) values (3,8);
INSERT INTO tb_anime_gender(anime_id,gender_id) values (3,3);
INSERT INTO tb_anime_gender(anime_id,gender_id) values (4,8);
INSERT INTO tb_anime_gender(anime_id,gender_id) values (4,1);
INSERT INTO tb_anime_gender(anime_id,gender_id) values (4,9);
INSERT INTO tb_anime_gender(anime_id,gender_id) values (5,2);
INSERT INTO tb_anime_gender(anime_id,gender_id) values (5,10);
INSERT INTO tb_anime_gender(anime_id,gender_id) values (5,9);
INSERT INTO tb_anime_gender(anime_id,gender_id) values (5,11);
INSERT INTO tb_anime_gender(anime_id,gender_id) values (5,12);
INSERT INTO tb_anime_gender(anime_id,gender_id) values (1,7);
INSERT INTO tb_anime_gender(anime_id,gender_id) values (1,8);
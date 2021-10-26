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




create database if not exists clientes;

use clientes;

DROP USER IF EXISTS 'usuario_cliente'@localhost;

flush privileges;

create user 'usuario_cliente'@localhost identified by '1234';

grant ALL ON `clientes`.* TO 'usuario_cliente'@localhost;

flush privileges;

DROP TABLE IF EXISTS apolice;

DROP TABLE IF EXISTS cliente;

CREATE TABLE cliente(
   id_cliente bigint not null auto_increment primary key,
   nome_cliente varchar(255) not null,
   cpf_cliente varchar(11) not null     
) ENGINE=InnoDB;
 
CREATE TABLE apolice(
   id_apolice bigint not null auto_increment primary key,
   id_cliente bigint not null,
   cod_produto varchar(255) not null,
   FOREIGN KEY fk_cliente_apolice(id_cliente) REFERENCES cliente(id_cliente)
) ENGINE=InnoDB;
 

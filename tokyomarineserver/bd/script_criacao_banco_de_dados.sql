create database if not exists clientes;

DROP USER IF EXISTS 'usuario_cliente'@localhost;

flush privileges;

create user 'usuario_cliente'@localhost identified by '1234';

grant ALL ON `clientes`.* TO 'usuario_cliente'@localhost;

flush privileges;
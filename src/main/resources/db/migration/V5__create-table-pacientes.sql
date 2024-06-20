create table pacientes(

    id bigint not null auto_increment,
    nombre varchar(100) not null,
    documento varchar(6) not null unique,
    telefono varchar(100) not null,
    calle varchar(100) not null,
    distrito varchar(100) not null,
    complemento varchar(100),
    numero int,
    ciudad varchar(100) not null,
    activo tinyint,

    primary key(id)

);
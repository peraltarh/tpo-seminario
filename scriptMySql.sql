CREATE TABLE seminario.Usuario (
	dni int NOT NULL,
	nombre varchar(45) NOT NULL,
	apellido varchar(45)  NOT NULL,
	matricula int NULL,
	username varchar(45) NOT NULL,
	password varchar(45) NOT NULL,
	borrado bit NOT NULL,
 CONSTRAINT PK_Usuario PRIMARY KEY CLUSTERED 
(
	dni ASC
)
);

Insert into Usuario values (11111111,'Administrador','Administrador',0,'admin','admin',0);
Insert into Usuario values (31412777,'Mauro','Castro',0,'mauro','mauro',0);
Insert into Usuario values (123456,'Laura','Daunis',111111,'laura','laura',0);

CREATE TABLE permiso (
	idPermiso int  NOT NULL,
	codigo varchar(45) NOT NULL,
	descripcion varchar(45) NOT NULL,
	borrado bit NOT NULL,
PRIMARY KEY CLUSTERED 
(
	idPermiso ASC
));

insert into permiso values (0,'ADMIN_USUARIO','Administracion Usuario',0);
insert into permiso values (1,'NUEVA_CONSULTA','Alta Conuslta',0);
insert into permiso values (2,'ADMIN_PACIENTE','Administracion Paciente',0);
insert into permiso values (3,'VER_HCE','Ver HCE',0);
insert into permiso values (4,'NUEVA_CIRUGIA','Alta Cirugia',0);
insert into permiso values (5,'NUEVA_PRACTICA','Alta Practica',0);
insert into permiso values (6,'REPORTE_FACTURACION','Reporte Facturacion',0);


CREATE TABLE usuario_permiso (
	dniUsuario int NULL,
	idPermiso int NULL
);

ALTER TABLE usuario_permiso ADD  CONSTRAINT FK_dniUsuario FOREIGN KEY(dniUsuario)
REFERENCES Usuario (dni);

ALTER TABLE usuario_permiso  ADD  CONSTRAINT FK_idPermiso FOREIGN KEY(idPermiso)
REFERENCES permiso (idPermiso);

Insert into usuario_permiso values ((select dni from usuario where nombre = 'Administrador'), (select idPermiso from permiso where codigo = 'ADMIN_USUARIO'));
Insert into usuario_permiso values ((select dni from usuario where nombre = 'Administrador'), (select idPermiso from permiso where codigo = 'NUEVA_CONSULTA'));
Insert into usuario_permiso values ((select dni from usuario where nombre = 'Administrador'), (select idPermiso from permiso where codigo = 'ADMIN_PACIENTE'));
Insert into usuario_permiso values ((select dni from usuario where nombre = 'Administrador'), (select idPermiso from permiso where codigo = 'VER_HCE'));
Insert into usuario_permiso values ((select dni from usuario where nombre = 'Administrador'), (select idPermiso from permiso where codigo = 'NUEVA_CIRUGIA'));
Insert into usuario_permiso values ((select dni from usuario where nombre = 'Administrador'), (select idPermiso from permiso where codigo = 'NUEVA_PRACTICA'));
Insert into usuario_permiso values ((select dni from usuario where nombre = 'Administrador'), (select idPermiso from permiso where codigo = 'REPORTE_FACTURACION'));


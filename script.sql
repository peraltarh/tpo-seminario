CREATE TABLE Usuario(
	[idUsuario] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](45) COLLATE Modern_Spanish_CI_AS NOT NULL,
	[apellido] [varchar](45) COLLATE Modern_Spanish_CI_AS NOT NULL,
	[matricula] [int] NULL,
	[dni] [int] NOT NULL,
	[username] [varchar](45) COLLATE Modern_Spanish_CI_AS NOT NULL,
	[password] [varchar](45) COLLATE Modern_Spanish_CI_AS NOT NULL,
	[borrado] [bit] NOT NULL,
PRIMARY KEY (idUsuario)
)

Insert into Usuario values ('Mauro','Castro',null ,31412777,'admin','admin',0)
Insert into Usuario values ('Laura','Daunis',123456 ,111111,'laura','laura',0)

CREATE TABLE permiso(
	[idPermiso] [int] IDENTITY(1,1) NOT NULL,
	[codigo] [varchar](45) COLLATE Modern_Spanish_CI_AS NOT NULL,
	[descripcion] [varchar](45) COLLATE Modern_Spanish_CI_AS NOT NULL,
	[borrado] [bit] NOT NULL,
PRIMARY KEY (idPermiso)
)

insert into permiso values ('USERABM','ABM Usuarios',0)
insert into permiso values ('NEWCONSULTA','Nueva Conuslta',0)

CREATE TABLE usuarioPermiso(
	
)


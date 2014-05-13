CREATE TABLE [dbo].[Usuario](
	[dni] [int] NOT NULL,
	[nombre] [varchar](45) COLLATE Modern_Spanish_CI_AS NOT NULL,
	[apellido] [varchar](45) COLLATE Modern_Spanish_CI_AS NOT NULL,
	[matricula] [int] NULL,
	[username] [varchar](45) COLLATE Modern_Spanish_CI_AS NOT NULL,
	[password] [varchar](45) COLLATE Modern_Spanish_CI_AS NOT NULL,
	[borrado] [bit] NOT NULL,
 CONSTRAINT [PK_Usuario] PRIMARY KEY CLUSTERED 
(
	[dni] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

Insert into Usuario values (11111111,'Administrador','Administrador',0,'admin','admin',0)
Insert into Usuario values (31412777,'Mauro','Castro',0,'mauro','mauro',0)
Insert into Usuario values (123456,'Laura','Daunis',111111,'laura','laura',0)

CREATE TABLE [dbo].[permiso](
	[idPermiso] [int] IDENTITY(1,1) NOT NULL,
	[codigo] [varchar](45) COLLATE Modern_Spanish_CI_AS NOT NULL,
	[descripcion] [varchar](45) COLLATE Modern_Spanish_CI_AS NOT NULL,
	[borrado] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idPermiso] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

insert into permiso values ('ADMIN_USUARIO','Administracion Usuario',0)
insert into permiso values ('NUEVA_CONSULTA','Alta Conuslta',0)
insert into permiso values ('ADMIN_PACIENTE','Administracion Paciente',0)
insert into permiso values ('VER_HCE','Ver HCE',0)
insert into permiso values ('NUEVA_CIRUGIA','Alta Cirugia',0)
insert into permiso values ('NUEVA_PRACTICA','Alta Practica',0)
insert into permiso values ('REPORTE_FACTURACION','Reporte Facturacion',0)


CREATE TABLE [dbo].[usuario_permiso](
	[dniUsuario] [int] NULL,
	[idPermiso] [int] NULL
) ON [PRIMARY]

GO
USE [seminario]
GO
ALTER TABLE [dbo].[usuario_permiso]  WITH CHECK ADD  CONSTRAINT [FK_dniUsuario] FOREIGN KEY([dniUsuario])
REFERENCES [dbo].[Usuario] ([dni])
GO
ALTER TABLE [dbo].[usuario_permiso]  WITH CHECK ADD  CONSTRAINT [FK_idPermiso] FOREIGN KEY([idPermiso])
REFERENCES [dbo].[permiso] ([idPermiso])

Insert into usuario_permiso values (select dni from usuario where nombre = 'Administrador', select idPermiso from permiso where codigo = 'ADMIN_USUARIO')
Insert into usuario_permiso values (select dni from usuario where nombre = 'Administrador', select idPermiso from permiso where codigo = 'NUEVA_CONSULTA')
Insert into usuario_permiso values (select dni from usuario where nombre = 'Administrador', select idPermiso from permiso where codigo = 'ADMIN_PACIENTE')
Insert into usuario_permiso values (select dni from usuario where nombre = 'Administrador', select idPermiso from permiso where codigo = 'VER_HCE')
Insert into usuario_permiso values (select dni from usuario where nombre = 'Administrador', select idPermiso from permiso where codigo = 'NUEVA_CIRUGIA')
Insert into usuario_permiso values (select dni from usuario where nombre = 'Administrador', select idPermiso from permiso where codigo = 'NUEVA_PRACTICA')
Insert into usuario_permiso values (select dni from usuario where nombre = 'Administrador', select idPermiso from permiso where codigo = 'REPORTE_FACTURACION')


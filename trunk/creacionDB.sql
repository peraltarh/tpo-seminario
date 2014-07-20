--creacion de usuario seminario1 y DB seminario1

CREATE DATABASE seminario1



USE seminario1



--DROP TABLAS
/*	
drop table auditoria
drop table consulta
drop table nomenclador
drop table obrasocial
drop table practicaambulatoria
drop table practicaquirurgica
drop table prestacion
drop table prestador 
drop table usuario 
drop table hce
drop table Especialidad
drop table obrasocial 
drop table obrasocial_paciente 
drop table itemhce
drop table Usuario_Especialidad
drop table paciente 
*/

--creacion de tablas


CREATE TABLE Especialidad
	(
		idEspecialidad smallint NOT NULL IDENTITY(1,1),
		descripcion varchar(128) NOT NULL,
		CONSTRAINT PK_Especialidad PRIMARY KEY (idEspecialidad)
	)
CREATE TABLE Usuario
	(
		nro varchar(16) NOT NULL,
		tipoDoc varchar(16) NOT NULL,
		usuarioLogin varchar(16) NOT NULL,
		clave varchar(16) NOT NULL,
		nombre varchar(32) NOT NULL,
		apellido varchar(32) NOT NULL,
		matricula varchar(32),
		borrado bit,
		CONSTRAINT PK_Usuario PRIMARY KEY (nro, tipoDoc),
		CONSTRAINT PK_Usuario2 UNIQUE (usuarioLogin),
	) 

CREATE TABLE Usuario_Especialidad
	(
		idUsuario_Especialidad smallint NOT NULL IDENTITY(1,1),
		idEspecialidad smallint NOT NULL,
		nro varchar(16) NOT NULL,
		tipoDoc varchar(16) NOT NULL,
		CONSTRAINT PK_Usuario_Especialidad PRIMARY KEY (idUsuario_Especialidad),
		CONSTRAINT FK_Especialidad_Usuario FOREIGN KEY (idEspecialidad)
			REFERENCES Especialidad(idEspecialidad),
		CONSTRAINT FK_Usuario_Especialidad FOREIGN KEY (nro,tipoDoc)
			REFERENCES Usuario(nro,tipoDoc)

	)

CREATE TABLE Auditoria
	(
		idAudit smallint NOT NULL IDENTITY(1,1),
		usuarioLogin varchar(16) NOT NULL,
		fechaHs datetime NOT NULL,
		descripcion varchar(512) NOT NULL,
		CONSTRAINT PK_Auditoria PRIMARY KEY (idAudit),
		CONSTRAINT FK_Auditoria_Usuario FOREIGN KEY (usuarioLogin)
			REFERENCES Usuario(usuarioLogin)

	)

CREATE TABLE ObraSocial
	(
		idObraSocial smallint NOT NULL IDENTITY(1,1),
		razonSocial varchar(64) NOT NULL,
		CONSTRAINT PK_ObraSocial PRIMARY KEY (idObraSocial)
	)
CREATE TABLE Paciente
	(
		nro varchar(16) NOT NULL,
		tipoDoc varchar(16) NOT NULL,
		sexo char(1) NOT NULL,
		nombre varchar(32) NOT NULL,
		apellido varchar(32) NOT NULL,
		celular varchar(32) NOT NULL,
		telefono varchar(32) NOT NULL,
		fechaNacimiento datetime NOT NULL,
		email varchar(32),
		edad smallint,
		CONSTRAINT PK_Paciente PRIMARY KEY (nro, tipoDoc)
	) 

	
CREATE TABLE ObraSocial_Paciente
	(
		idOS_Paciente smallint NOT NULL IDENTITY(1,1),
		idObraSocial smallint NOT NULL,
		nro varchar(16) NOT NULL,
		tipoDoc varchar(16) NOT NULL,
		nroAfiliado int NOT NULL,
		CONSTRAINT PK_ObraSocial_Paciente PRIMARY KEY (idOS_Paciente),
		CONSTRAINT FK_ObraSocial_Paciente FOREIGN KEY (idObraSocial)
			REFERENCES ObraSocial(idObraSocial),
		CONSTRAINT FK_Paciente_ObraSocial FOREIGN KEY (nro,tipoDoc)
			REFERENCES Paciente(nro,tipoDoc)

	)
		


CREATE TABLE Prestacion
	(
		idPrestacion smallint NOT NULL IDENTITY(1,1),
		descripcion varchar(128) NOT NULL,
		CONSTRAINT PK_Prestacion PRIMARY KEY (idPrestacion)
	)


CREATE TABLE Prestador
	(
		idPrestador smallint NOT NULL IDENTITY(1,1),
		idObraSocial smallint NOT NULL,
		idPrestacion smallint NOT NULL,
		usuarioLogin varchar(16) NOT NULL,
		CONSTRAINT PK_Prestador PRIMARY KEY (idPrestador),
		CONSTRAINT FK_Prestador_Prestacion FOREIGN KEY (idPrestacion)
			REFERENCES Prestacion(idPrestacion),
		CONSTRAINT FK_Prestador_ObraSocial FOREIGN KEY (idObraSocial)
			REFERENCES ObraSocial(idObraSocial),
		CONSTRAINT FK_Prestador_Usuario FOREIGN KEY (usuarioLogin)
			REFERENCES Usuario(usuarioLogin)
	)
	



	
CREATE TABLE Nomenclador
	(
		idNomenclador smallint NOT NULL IDENTITY(1,1),
		precio float NOT NULL,
		idPrestacion smallint NOT NULL,
		idObraSocial smallint NOT NULL,
		CONSTRAINT PK_Nomenclador PRIMARY KEY (idNomenclador),
		CONSTRAINT FK_Nomenclador_ObraSocial FOREIGN KEY (idObraSocial)
			REFERENCES ObraSocial(idObraSocial),
		CONSTRAINT FK_Nomenclador_Prestacion FOREIGN KEY (idPrestacion)
			REFERENCES Prestacion(idPrestacion)
	)

CREATE TABLE Consulta
	(
		idConsulta smallint NOT NULL IDENTITY(1,1),
		idPrestacion smallint NOT NULL,
		usuarioLogin varchar(16) NOT NULL,
		observacionGeneral varchar(512) NOT NULL,
		tratamiento varchar(512) NOT NULL,
		motivo varchar(512) NOT NULL,
		observacionOjoIzq varchar(512) NOT NULL,
		observacionOjoDer varchar(512) NOT NULL,
		CONSTRAINT PK_Consulta PRIMARY KEY (idConsulta),
		CONSTRAINT FK_Consulta_Prestacion FOREIGN KEY (idPrestacion)
			REFERENCES Prestacion(idPrestacion),
		CONSTRAINT FK_Consulta_Usuario FOREIGN KEY (usuarioLogin)
			REFERENCES Usuario(usuarioLogin)
	) 
	
CREATE TABLE PracticaAmbulatoria
	(
		idPracticaAmbulatoria smallint NOT NULL IDENTITY(1,1),
		idPrestacion smallint NOT NULL,
		usuarioLogin varchar(16) NOT NULL,
		ojo char(9) NOT NULL,
		diagnostico varchar(512) NOT NULL,
		CONSTRAINT PK_PracticaAmbulatoria PRIMARY KEY (idPracticaAmbulatoria),
		CONSTRAINT FK_PracticaAmbulatoria_Prestacion FOREIGN KEY (idPrestacion)
			REFERENCES Prestacion(idPrestacion),
		CONSTRAINT FK_PracticaAmbulatoria_Usuario FOREIGN KEY (usuarioLogin)
			REFERENCES Usuario(usuarioLogin)
	) 

	
CREATE TABLE PracticaQuirurgica
	(
		idPracticaQuirurgica smallint NOT NULL IDENTITY(1,1),
		idPrestacion smallint NOT NULL,
		usuarioLogin varchar(16) NOT NULL,
		ojo char(9) NOT NULL,
		diagnostico varchar(512) NOT NULL,
		monitoreo varchar(64) NOT NULL,
		horaInicio datetime NOT NULL,
		horaFin datetime NOT NULL,
		anestesia varchar(64) NOT NULL,
		CONSTRAINT PK_PracticaQuirurgica PRIMARY KEY (idPracticaQuirurgica),
		CONSTRAINT FK_PracticaQuirurgica_Prestacion FOREIGN KEY (idPrestacion)
			REFERENCES Prestacion(idPrestacion),
		CONSTRAINT FK_PracticaQuirurgica_Usuario FOREIGN KEY (usuarioLogin)
			REFERENCES Usuario(usuarioLogin)
	) 

CREATE TABLE HCE
	(
		idHCE smallint NOT NULL IDENTITY(1,1),
		nroDoc varchar(16) NOT NULL,
		tipoDoc varchar(16) NOT NULL,
		CONSTRAINT PK_HCE PRIMARY KEY (idHCE),
		CONSTRAINT FK_HCE_Paciente FOREIGN KEY (nroDoc, tipoDoc)
			REFERENCES Paciente(nro,tipoDoc)
	) 

	CREATE TABLE ItemHCE
	(
		idItemHCE smallint NOT NULL IDENTITY(1,1),
		idHCE smallint NOT NULL,
		fecha datetime NOT NULL,
		practica varchar(32) NOT NULL,
		idPracticaAmbulatoria smallint,
		idPracticaQuirurgica smallint,
		idConsulta smallint,
		CONSTRAINT PK_ItemHCE PRIMARY KEY (idItemHCE),
		CONSTRAINT FK_HCE FOREIGN KEY (idHCE)
			REFERENCES HCE(idHCE),
		CONSTRAINT FK__HCE_PracticaAmbulatoria FOREIGN KEY (idPracticaAmbulatoria)
			REFERENCES PracticaAmbulatoria(idPracticaAmbulatoria),
		CONSTRAINT FK__HCE_PracticaQuirurgica FOREIGN KEY (idPracticaQuirurgica)
			REFERENCES PracticaQuirurgica(idPracticaQuirurgica),
		CONSTRAINT FK__HCE_Consulta FOREIGN KEY (idConsulta)
			REFERENCES Consulta(idConsulta)
	) 

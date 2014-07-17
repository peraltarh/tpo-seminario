insert into dbo.Especialidad values ('Oftalmologo General')
insert into dbo.Especialidad values ('Oftalmologo Quirurgico')
insert into dbo.Especialidad values ('Oftalmologo Practica Ambulatoria')
insert into dbo.Especialidad values ('Administrativo')


insert into dbo.Usuario values ('35234123','dni','dmoretti','123abc','Damian','Moretti','',1,0)
insert into dbo.Usuario values ('32599439','dni','rperalta','123abc','Rodrigo','Peralta','',2,0)
insert into dbo.Usuario values ('30489590','dni','mcastro','123abc','Mauro','Castro','',3,0)
insert into dbo.Usuario values ('31984875','dni','ldaunis','123abc','Laura','Daunis','',3,0)
insert into dbo.Usuario values ('36928036','dni','mburgos','123abc','Marco','Burgos','',1,0)
insert into dbo.Usuario values ('40985859','dni','sgomez','123abc','Sofia','Gomez','',4,0)


insert into dbo.ObraSocial values ('Osecac')
insert into dbo.ObraSocial values ('Osplad')
insert into dbo.ObraSocial values ('UOM')
insert into dbo.ObraSocial values ('PAMI')


insert into dbo.Paciente values ('22459884','dni','m','Gonzalo','Higuan','153039389','43456332','19840423','',40)
insert into dbo.Paciente values ('20355893','dni','m','Javier','Mascherano','159385938','5948943','19820713','',42)
insert into dbo.Paciente values ('30549865','dni','m','Leonel','Messi','523462344','1231256','19871112','',37)
insert into dbo.Paciente values ('18938389','dni','f','Josefina','Gonzalez','153039389','43456332','19840423','',40)


insert into dbo.ObraSocial_Paciente values (1,'22459884','dni',32144323)
insert into dbo.ObraSocial_Paciente values (2,'20355893','dni',12325434)
insert into dbo.ObraSocial_Paciente values (3,'30549865','dni',76345222)
insert into dbo.ObraSocial_Paciente values (4,'18938389','dni',34543521)


insert into dbo.Prestacion values ('PA- Fondo de Ojo')
insert into dbo.Prestacion values ('PQ- Catarata')
insert into dbo.Prestacion values ('PQ- Miopia')
insert into dbo.Prestacion values ('CP- Consulta General')
insert into dbo.Prestacion values ('CP- Consulta Inicial')
insert into dbo.Prestacion values ('PQ- Intervencion Laser')
insert into dbo.Prestacion values ('PA- Control Presion Ocular')


insert into dbo.Prestador values (1,1,'mcastro')
insert into dbo.Prestador values (2,2,'rperalta')
insert into dbo.Prestador values (3,3,'rperalta')
insert into dbo.Prestador values (4,4,'dmoretti')
insert into dbo.Prestador values (1,1,'rperalta')


insert into dbo.Nomenclador values (120,1,1)
insert into dbo.Nomenclador values (5000,2,1)
insert into dbo.Nomenclador values (10000,3,1)
insert into dbo.Nomenclador values (80,4,1)
insert into dbo.Nomenclador values (15000,5,1)

insert into dbo.Nomenclador values (140,1,2)
insert into dbo.Nomenclador values (4800,2,2)
insert into dbo.Nomenclador values (11000,3,2)
insert into dbo.Nomenclador values (90,4,2)
insert into dbo.Nomenclador values (14000,5,2)

insert into dbo.Nomenclador values (180,1,3)
insert into dbo.Nomenclador values (7000,2,3)
insert into dbo.Nomenclador values (14000,3,3)
insert into dbo.Nomenclador values (120,4,3)


insert into dbo.Consulta values (1,'dmoretti','Corte ojo izquierdo','N/A','Revision','Corte, sin daño permanente.','N/A')
insert into dbo.Consulta values (1,'mburgos','Inflamacion ojo derecho','hielo cada 2hs y 500mg analgesico','Consulta','N/A','Inflamacion')
insert into dbo.Consulta values (1,'dmoretti','Conjuntivitis','Medicacion correspondiente','Consulta','Conjuntivitis','N/A')
insert into dbo.Consulta values (1,'mburgos','Orzuelo','Inyeccion esteroides 10ml','Revision','Orzuelo 1mm','N/A')


insert into dbo.PracticaAmbulatoria values (3,'mcastro','Ambos','Fondo de Ojo')
insert into dbo.PracticaAmbulatoria values (2,'ldaunis','Ambos','Cataratas')
insert into dbo.PracticaAmbulatoria values (5,'mcastro','Derecho','Cirugia conducto lagrimal ojo derecho')
insert into dbo.PracticaAmbulatoria values (3,'ldaunis','Izquierdo','Cirugia camara posterior ojo izquierdo')
insert into dbo.PracticaAmbulatoria values (2,'ldaunis','Ambos','Cataratas')


insert into dbo.PracticaQuirurgica values (2,'rperalta','Izquierdo','Cirugia correctiva','Juan Perez','2014-06-12 12:12:22','2014-06-12 13:34:56','N/A')
insert into dbo.PracticaQuirurgica values (2,'rperalta','Derecho','Cirugia reconstructiva','Juan Perez','2014-06-19 12:00:05','2014-06-19 13:06:50','Local')
insert into dbo.PracticaQuirurgica values (2,'rperalta','Izquierdo','Cirugia correctiva','Juan Perez','2014-06-26 15:30:00','2014-06-26 16:15:11','N/A')
insert into dbo.PracticaQuirurgica values (2,'rperalta','Derecho','Cirugia oblicuo inferior','Juan Perez','2014-07-03 10:20:25','2014-07-03 12:22:12','Local')



insert into dbo.HCE values ('22459884','dni')
insert into dbo.HCE values ('20355893','dni')
insert into dbo.HCE values ('30549865','dni')
insert into dbo.HCE values ('18938389','dni')


insert into dbo.ItemHCE (idHCE, fecha, practica,idPracticaQuirurgica) values (1,'2014-06-13','PracticaQuirurgica',1)
insert into dbo.ItemHCE (idHCE, fecha, practica,idPracticaQuirurgica) values (2,'2014-06-20','PracticaQuirurgica',2)
insert into dbo.ItemHCE (idHCE, fecha, practica,idPracticaQuirurgica) values (3,'2014-06-26','PracticaQuirurgica',3)
insert into dbo.ItemHCE (idHCE, fecha, practica,idPracticaQuirurgica) values (4,'2014-07-04','PracticaQuirurgica',4)


insert into dbo.ItemHCE (idHCE, fecha, practica,idPracticaAmbulatoria) values (1,'2014-07-21','PracticaAmbulatoria',1)
insert into dbo.ItemHCE (idHCE, fecha, practica,idPracticaAmbulatoria) values (2,'2014-07-24','PracticaAmbulatoria',2)
insert into dbo.ItemHCE (idHCE, fecha, practica,idPracticaAmbulatoria) values (3,'2014-07-21','PracticaAmbulatoria',3)
insert into dbo.ItemHCE (idHCE, fecha, practica,idPracticaAmbulatoria) values (4,'2014-07-25','PracticaAmbulatoria',4)
insert into dbo.ItemHCE (idHCE, fecha, practica,idPracticaAmbulatoria) values (1,'2014-08-02','PracticaAmbulatoria',5)

insert into dbo.ItemHCE (idHCE, fecha, practica,idConsulta) values (1,'2014-07-13','Consulta',1)
insert into dbo.ItemHCE (idHCE, fecha, practica,idConsulta) values (2,'2014-07-15','Consulta',2)
insert into dbo.ItemHCE (idHCE, fecha, practica,idConsulta) values (3,'2014-07-22','Consulta',3)
insert into dbo.ItemHCE (idHCE, fecha, practica,idConsulta) values (4,'2014-07-22','Consulta',4)


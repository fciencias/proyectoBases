INSERT INTO usuario VALUES ('luanma', 'LUIS ANTONIO','MARTINEZ','MARTINEZ','luis@hotmail.com','1960-09-28','pxkh');
INSERT INTO usuario VALUES ('edgoca', 'EDER','GOMEZ','CASTRO','eder@hotmail.com','1978-01-07','okgp');
INSERT INTO usuario VALUES ('edmera', 'EDGAR','MEZA','ROMANO','edgar@hotmail.com','1959-05-10','huoi');
INSERT INTO usuario VALUES ('oscobl', 'OSVALDO','CORTES','BLANCO','osvaldo@hotmail.com','1989-11-10','uhkl');
INSERT INTO usuario VALUES ('sacaro', 'SANDRA','CABRERA','ROBLES','sandra@hotmail.com','1968-09-02','bjil');
INSERT INTO usuario VALUES ('romeme', 'ROBERTO','MENDEZ','MENDEZ','roberto@hotmail.com','1981-12-07','bnjo');
INSERT INTO usuario VALUES ('omsica', 'OMAR','SILVA','CASTRO','omar@hotmail.com','1966-11-04','fjol');
INSERT INTO usuario VALUES ('jemaso', 'JESUS','MARTINEZ','SOTO','jesus@hotmail.com','1956-09-09','nmkf');
INSERT INTO usuario VALUES ('aroles', 'ARTURO','OLGUIN','ESQUIVEL','arturo@hotmail.com','1971-10-10','rtik');
INSERT INTO usuario VALUES ('rumova', 'RUBEN','MONCADA','VALDEZ','ruben@hotmail.com','1988-11-04','hjur');
INSERT INTO usuario VALUES ('jusohe', 'JUAN','SOBERON','HERNANDEZ','juan@hotmail.com','1990-05-01','uhet');
INSERT INTO usuario VALUES ('loescr', 'LORENA','ESTRELLA','CRUZ','lorena@hotmail.com','1956-09-06','juyy');
INSERT INTO usuario VALUES ('maases', 'MANUEL','ASTUDILLO','ESTUDILLO','manuel@hotmail.com','1960-04-09','ghtu');
INSERT INTO usuario VALUES ('calaor', 'CARLOS','LARA','ORTIZ','carlos@hotmail.com','1993-02-08','tyej');
INSERT INTO usuario VALUES ('oypime', 'OYALA','PIMENTEL','PIZZA','oyala@hotmail.com','1978-01-06','ghti');
INSERT INTO usuario VALUES ('macoca', 'MAYRA','CORONADO','CAMARGO','mayra@hotmail.com','1912-04-04','yruv');
INSERT INTO grupo(id_responsable,nombre_grupo,fecha_creacion) VALUES ('edmera','Computologos','2012-01-03');
INSERT INTO grupo(id_responsable,nombre_grupo,fecha_creacion) VALUES ('sacaro','Porristas','2001-10-10');
INSERT INTO grupo(id_responsable,nombre_grupo,fecha_creacion) VALUES ('rumova','Gamers','2006-01-11');
INSERT INTO evento(responsable,fecha_inicio,fecha_final,hora_inicio,hora_final,descripcion,max_asistentes)
 VALUES ('edmera','2012-10-01','2012-10-10','12:00:00','19:00:00','congreso',100);
INSERT INTO evento(responsable,fecha_inicio,fecha_final,hora_inicio,hora_final,descripcion,max_asistentes)
 VALUES ('maases','2012-11-01','2012-12-10','09:00:00','20:00:00','video juegos',50);
INSERT INTO evento(responsable,fecha_inicio,fecha_final,hora_inicio,hora_final,descripcion,max_asistentes)
 VALUES ('sacaro','2012-09-06','2012-11-02','11:00:00','20:00:00','concurso',200);
INSERT INTO usuarioAsistente VALUES ('edmera',1);
INSERT INTO usuarioAsistente VALUES ('luanma',1);
INSERT INTO usuarioAsistente VALUES ('edgoca',1);
INSERT INTO usuarioAsistente VALUES ('oscobl',1);
INSERT INTO usuarioAsistente VALUES ('sacaro',2);
INSERT INTO usuarioAsistente VALUES ('oypime',2);
INSERT INTO usuarioAsistente VALUES ('macoca',2);
INSERT INTO usuarioAsistente VALUES ('romeme',3);
INSERT INTO usuarioAsistente VALUES ('omsica',3);
INSERT INTO usuarioAsistente VALUES ('rumova',3);
INSERT INTO usuarioAsistente VALUES ('jemaso',3);
INSERT INTO usuarioAsistente VALUES ('aroles',3);
INSERT INTO recurso(nombre,descripcion,cantidad) VALUES ('Sillas','sillas para eventos',50);
INSERT INTO recurso(nombre,descripcion,cantidad) VALUES ('Mesas','mesas para 4 personas',100);
INSERT INTO recurso(nombre,descripcion,cantidad) VALUES ('Auditores','lugar para eventos',4);
INSERT INTO recurso(nombre,descripcion,cantidad) VALUES ('Computadoras','computadoras',30);
INSERT INTO asignacion_recursos VALUES (1,1,25);
INSERT INTO asignacion_recursos VALUES (2,2,10);
INSERT INTO asignacion_recursos VALUES (3,3,2);


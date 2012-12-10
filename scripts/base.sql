CREATE TABLE usuario
(
  id_usuario character varying(50) NOT NULL,
  nombre character varying(50) NOT NULL,
  ap_paterno character varying(50) NOT NULL,
  ap_materno character varying(50) NOT NULL,
  email character varying(50) NOT NULL,
  fecha_nacimiento date,
  id_password varchar(10) NOT NULL, 
  CONSTRAINT pkey_usuario PRIMARY KEY (id_usuario),
  CONSTRAINT fechaNac_check CHECK (fecha_nacimiento < CURRENT_DATE)
);


CREATE TABLE grupo
(
  id_grupo serial ,
  id_responsable character varying(50) NOT NULL,
  nombre_grupo character varying(50) NOT NULL,
  fecha_creacion date,
  grupo_padre integer, 
  CONSTRAINT pkey_grupo PRIMARY KEY (id_grupo),
  CONSTRAINT fkey_grupo_padre FOREIGN KEY (grupo_padre)
      REFERENCES grupo(id_grupo) MATCH FULL
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fkey_responsable FOREIGN KEY (id_responsable)
      REFERENCES usuario(id_usuario) MATCH FULL
      ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE evento
(
  id_evento serial,
  responsable character varying(50) NOT NULL,
  fecha_inicio date,
  fecha_final date,
  hora_inicio time,
  hora_final time,
  descripcion character varying(255),
  max_asistentes integer,
  CONSTRAINT pkey_evento PRIMARY KEY (id_evento),
  CONSTRAINT fkey_responsable FOREIGN KEY (responsable)
      REFERENCES usuario(id_usuario) MATCH FULL
      ON UPDATE CASCADE ON DELETE CASCADE
);



CREATE TABLE usuarioAsistente
(
  id_usuario character varying(50) NOT NULL,
  id_evento integer NOT NULL,
  CONSTRAINT pkey_usuarioAsistente PRIMARY KEY (id_usuario,id_evento),
  CONSTRAINT fkey_evento FOREIGN KEY (id_evento)
      REFERENCES evento(id_evento) MATCH FULL
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fkey_asistente FOREIGN KEY (id_usuario)
      REFERENCES usuario(id_usuario) MATCH FULL
      ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE grupoAsistente(
  id_evento	integer,
  id_grupo	integer,
  CONSTRAINT 	pkey_grupoAsistente PRIMARY KEY (id_evento,id_grupo),
  CONSTRAINT 	fkey_grupoAsistente FOREIGN KEY (id_grupo) 
  REFERENCES 	grupo(id_grupo) 			
  ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT 	fkey_evento FOREIGN KEY (id_evento) 
  REFERENCES 	evento(id_evento) 			
  ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE recurso
(
  num_inventario serial,
  nombre character varying (50) NOT NULL,
  descripcion character varying (50),
  cantidad integer NOT NULL,
  CONSTRAINT pkey_recurso PRIMARY KEY (num_inventario),
);


CREATE TABLE asignacion_recursos
(
  id_evento integer ,
  num_inventario integer ,
  cantidad integer NOT NULL,
  CONSTRAINT cantidad_check CHECK (cantidad >0),
  CONSTRAINT pkey_asignacion PRIMARY KEY (id_evento,num_inventario),
  CONSTRAINT fkey_evento FOREIGN KEY (id_evento)
      REFERENCES evento(id_evento) MATCH FULL
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fkey_recursos FOREIGN KEY (num_inventario)
      REFERENCES recurso(num_inventario) MATCH FULL
      ON UPDATE CASCADE ON DELETE CASCADE
  
);

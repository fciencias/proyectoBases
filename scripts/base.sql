CREATE TABLE usuario
(
  id_usuario integer NOT NULL,
  nombre character varying(50) NOT NULL,
  ap_paterno character varying(50) NOT NULL,
  ap_materno character varying(50) NOT NULL,
  email character varying(50) NOT NULL,
  fecha_nacimiento date,
  id_password varchar(10) NOT NULL, 
  CONSTRAINT pkey_usuario PRIMARY KEY (id_usuario)
);


CREATE TABLE grupo
(
  id_grupo integer NOT NULL,
  id_responsable integer NOT NULL,
  nombre_grupo character varying(50) NOT NULL,
  fecha_creacion date,
  grupo_padre integer, 
  CONSTRAINT pkey_grupo PRIMARY KEY (id_grupo),
  CONSTRAINT fkey_grupo_padre FOREIGN KEY (grupo_padre)
      REFERENCES grupo(id_grupo) MATCH FULL
      ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE evento
(
  id_evento integer NOT NULL,
  responsable integer NOT NULL,
  fecha_inicio date,
  fecha_final date,
  hora_inicio time,
  hora_final time,
  descripcion character varying(255),
  asistente integer UNIQUE,
  recurso integer UNIQUE,
  max_asistentes integer,
  CONSTRAINT pkey_evento PRIMARY KEY (id_evento),
  CONSTRAINT fkey_responsable FOREIGN KEY (responsable)
      REFERENCES usuario(id_usuario) MATCH FULL
      ON UPDATE CASCADE ON DELETE CASCADE
);



CREATE TABLE asistente
(
  id_usuario integer NOT NULL,
  id_evento integer NOT NULL,
  CONSTRAINT fkey_asistente FOREIGN KEY (id_usuario)
      REFERENCES evento(asistente) MATCH FULL
      ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE recurso
(
  num_inventario integer NOT NULL UNIQUE,
  cantidad integer NOT NULL,
  CONSTRAINT fkey_recurso FOREIGN KEY (num_inventario)
      REFERENCES evento(recurso) MATCH FULL
      ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE asignacion_recursos
(
  id_evento integer NOT NULL,
  num_inventario integer NOT NULL,
  CONSTRAINT fkey_evento FOREIGN KEY (id_evento)
      REFERENCES evento(id_evento) MATCH FULL
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fkey_recursos FOREIGN KEY (num_inventario)
      REFERENCES recurso(num_inventario) MATCH FULL
      ON UPDATE CASCADE ON DELETE CASCADE
  
);
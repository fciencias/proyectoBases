
 /*
 Funcion que verifica si un correo es introducido con
 un formato valido
 */

CREATE OR REPLACE FUNCTION verificaCorreo()
RETURNS trigger
AS $verificaCorreo$
BEGIN
IF  TG_OP ='INSERT' AND (select new.email like '%@%.%')
  THEN
	return new;
ELSE 
raise exception 'El correo tiene mal formato';
END IF;
return null;
end;
$verificaCorreo$
LANGUAGE plpgsql;

 /*
 Disparador que se ejecuta antes de insertar un nuevo 
 usuario para verificar el formato del correo
 */

CREATE TRIGGER verifica_Correo
BEFORE INSERT ON usuario
FOR EACH ROW
EXECUTE PROCEDURE verificaCorreo();


 /*
 Funcion que actualiza la cantidad de recursos utilizados 
 por un evento.
 */
CREATE OR REPLACE FUNCTION actualizarRecursos()
RETURNS trigger
AS $actualizarRecursos$
BEGIN
IF  TG_OP ='INSERT' 
  THEN
	UPDATE recurso SET cantidad = cantidad - new.cantidad WHERE recurso.num_inventario = new.num_inventario;
	return new;
END IF;
return null;
end;
$actualizarRecursos$
LANGUAGE plpgsql;

 /*
 Disparador que se ejecuta antes de asignar recursos a un evento
 y poder actualizar lor recursos en la base.
 */

CREATE TRIGGER actualizar_Recursos
BEFORE INSERT ON asignacion_recursos
FOR EACH ROW
EXECUTE PROCEDURE actualizarRecursos();


 /*
 Funcion que verifica si hay suficientes recursos para asignar al 
 evento que los solicite, manda un mensaje si no hay suficientes recursos disponibles.
 */
CREATE OR REPLACE FUNCTION verificarRecursos()
RETURNS trigger
AS $verificarRecursos$
BEGIN

IF (select cantidad from recurso where recurso.num_inventario = new.num_inventario) < -1
  THEN
  raise exception 'No hay suficientes recursos para la asignacion';
ELSE 
 return new;
END IF;
return null;
end;
$verificarRecursos$
LANGUAGE plpgsql;


 /*
Disparador que se ejecuta antes de asignar recursos para poder 
verificar si hay recursos disponibles
 */
CREATE TRIGGER verificar_Recursos
BEFORE INSERT ON asignacion_recursos
FOR EACH ROW
EXECUTE PROCEDURE verificarRecursos();


 /*
 Funcion que checa si no se ha excedido el numero maximo
 de asistentes a un evento
 */
CREATE OR REPLACE FUNCTION maxAsistentes()
RETURNS trigger
AS $maxAsistentes$
DECLARE
numAsistentes integer;
maxA integer;
BEGIN
numAsistentes := (select count(id_usuario)
		 from usuarioAsistente);
maxA:= (select max_asistentes from evento where evento.id_evento = new.id_evento);
IF numAsistentes = maxA
  THEN
  raise exception 'No hay cupo en el evento';
ELSE 
 return new;
END IF;
return null;
end;
$maxAsistentes$
LANGUAGE plpgsql;


 /*
 Disparador que se ejecuta antes se insertar un asistente a un evento
 */
CREATE TRIGGER verificar_maxAsistentes
BEFORE INSERT ON usarioAsistente
FOR EACH ROW
EXECUTE PROCEDURE maxAsistentes();


 /*
 Funcion que libera los recursos utilizados por eventos que ya se terminaron
 */

CREATE OR REPLACE FUNCTION liberarRecursos()
RETURNS trigger
AS $liberarRecursos$
DECLARE
fecha_actual date;	
tupla record;
BEGIN
fecha_actual = current_date;
FOR tupla IN (SELECT fecha_final,hora_final,cantidad,num_inventario
		FROM evento,asignacion_recursos
		WHERE evento.id_evento = asignacion_recursos.id_evento)
LOOP
IF tupla.fecha_final <= fecha_actual AND tupla.hora_final <= current_time THEN
UPDATE recurso
SET cantidad = cantidad + tupla.cantidad
WHERE recurso.num_inventario = tupla.num_inventario;
return new;
END IF;
END LOOP;
return null;
end;
$liberarRecursos$
LANGUAGE plpgsql;


 /*
 Disparador que se ejecuta antes de asignar recursos para poder liberar los recursos
 que ya se pueden liberar.
 */
CREATE TRIGGER liberar_Recursos
BEFORE INSERT ON asignacion_recursos
FOR EACH ROW
EXECUTE PROCEDURE liberarRecursos();

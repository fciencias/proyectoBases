package calendarizador;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Galindo Martinez Jose Cruz
 * @author Salazar Sastre Martin
 * 
 */
public class Evento {
   
    private int id_evento;
    private String responsable;
    private Date fecha_inicio;
    private Date fecha_final;
    private Time hora_inicio;
    private Time hora_final;
    private String descripcion;
    private int max_asistentes;
    
}

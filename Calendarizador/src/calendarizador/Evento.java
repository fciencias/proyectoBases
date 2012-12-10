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

    /**
     *
     */
    public Evento(int id_evento, String responsable, Date fecha_inicio, Date fecha_final, Time hora_inicio, Time hora_final, String descripcion, int max_asistentes) {
        this.id_evento = id_evento;
        this.responsable = responsable;
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
        this.hora_inicio = hora_inicio;
        this.hora_final = hora_final;
        this.descripcion = descripcion;
        this.max_asistentes = max_asistentes;
    }

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_final() {
        return hora_final;
    }

    public void setHora_final(Time hora_final) {
        this.hora_final = hora_final;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getMax_asistentes() {
        return max_asistentes;
    }

    public void setMax_asistentes(int max_asistentes) {
        this.max_asistentes = max_asistentes;
    }
    
    
    
}

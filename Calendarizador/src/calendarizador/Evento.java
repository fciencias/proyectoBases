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

    /**
     * 
     * @param fecha_inicio
     * @param fecha_final
     * @param hora_inicio
     * @param hora_final
     * @param descripcion
     * @param max_asistentes 
     */
    public Evento(Date fecha_inicio, Date fecha_final, Time hora_inicio, Time hora_final, String descripcion, int max_asistentes) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
        this.hora_inicio = hora_inicio;
        this.hora_final = hora_final;
        this.descripcion = descripcion;
        this.max_asistentes = max_asistentes;
    }
   
    /**
     * 
     * @return 
     */
    public int getId_evento() {
        return id_evento;
    }

    /**
     * 
     * @param id_evento 
     */
    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    /**
     * 
     * @return 
     */
    public String getResponsable() {
        return responsable;
    }

    /**
     * 
     * @param responsable 
     */
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    /**
     * 
     * @return 
     */
    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    /**
     * 
     * @param fecha_inicio 
     */
    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    /**
     * 
     * @return 
     */
    public Date getFecha_final() {
        return fecha_final;
    }

    /**
     * 
     * @param fecha_final 
     */
    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }

    /**
     * 
     * @return 
     */
    public Time getHora_inicio() {
        return hora_inicio;
    }

    /**
     * 
     * @param hora_inicio 
     */
    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    /**
     * 
     * @return 
     */
    public Time getHora_final() {
        return hora_final;
    }

    /**
     * 
     * @param hora_final 
     */
    public void setHora_final(Time hora_final) {
        this.hora_final = hora_final;
    }

    /**
     * 
     * @return 
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * 
     * @param descripcion 
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * 
     * @return 
     */
    public int getMax_asistentes() {
        return max_asistentes;
    }

    /**
     * 
     * @param max_asistentes 
     */
    public void setMax_asistentes(int max_asistentes) {
        this.max_asistentes = max_asistentes;
    }

    @Override
    public String toString() {
        return "Evento{" + "id_evento=" + id_evento + ", responsable=" + responsable + ", fecha_inicio=" + fecha_inicio + ", fecha_final=" + fecha_final + ", hora_inicio=" + hora_inicio + ", hora_final=" + hora_final + ", descripcion=" + descripcion + ", max_asistentes=" + max_asistentes + "}\n";
    }
    
    
    
}

package calendarizador;

import java.sql.Date;

/**
 *
 * @author Galindo Martinez Jose Cruz
 * @author Salazar Sastre Martin
 */
class Grupo {
    
    private int id_grupo;
    private String responsable;
    private String nombre;
    private Date fecha_creacion;
    private int grupo_padre;

    /**
     * 
     * @param id_grupo
     * @param responsable
     * @param nombre
     * @param fecha_creacion
     * @param grupo_padre 
     */
    public Grupo(int id_grupo, String responsable, String nombre, Date fecha_creacion, int grupo_padre) {
        this.id_grupo = id_grupo;
        this.responsable = responsable;
        this.nombre = nombre;
        this.fecha_creacion = fecha_creacion;
        this.grupo_padre = grupo_padre;
    }

    public Grupo(int id_grupo, String responsable, String nombre, Date fecha_creacion) {
        this.id_grupo = id_grupo;
        this.responsable = responsable;
        this.nombre = nombre;
        this.fecha_creacion = fecha_creacion;
    }
    
    

    /**
     * 
     * @param nombre
     * @param fecha_creacion
     */
    public Grupo(String nombre, Date fecha_creacion) {
        this.nombre = nombre;
        this.fecha_creacion = fecha_creacion;
    }

    public Grupo(String nombre, Date fecha_creacion, int grupo_padre) {
        this.nombre = nombre;
        this.fecha_creacion = fecha_creacion;
        this.grupo_padre = grupo_padre;
    }
    
    

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public int getGrupo_padre() {
        return grupo_padre;
    }

    public void setGrupo_padre(int grupo_padre) {
        this.grupo_padre = grupo_padre;
    }

    @Override
    public String toString() {
        return "Grupo{" + "id_grupo=" + id_grupo + ", responsable=" + responsable + ", nombre=" + nombre + ", fecha_creacion=" + fecha_creacion + "\n}";
    }

    
    
    
}

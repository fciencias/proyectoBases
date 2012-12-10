package calendarizador;

/**
 *
 * @author pepe
 */
public class Recurso {

    private int num_inventario;
    private String nombre;
    private String descripcion;
    private int cantidad;

    public Recurso(int num_inventario, String nombre, String descripcion, int cantidad) {
        this.num_inventario = num_inventario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public Recurso(String nombre, String descripcion, int cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public int getNum_inventario() {
        return num_inventario;
    }

    public void setNum_inventario(int num_inventario) {
        this.num_inventario = num_inventario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}

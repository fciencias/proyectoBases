package calendarizador;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pepe
 */
public class RecursoBD {

    private Conexion conexion;
    private ResultSet res;
    
    
    public RecursoBD(Conexion conn) {
        conexion = conn;
        res = null;
    }
    
    public String muestraRecursos() {
        StringBuilder sb = new StringBuilder();
        sb.append("Num Inventario\t");
        sb.append("Nombre      \t");
        sb.append("Descripcion   \t");
        sb.append("Cantidad\n");
        sb.append("=========================================\n");
        try {
            res = conexion.consulta("SELECT * FROM recurso;");
            while (res.next()) {
                sb.append(res.getInt("num_inventario"));
                sb.append("\t");
                sb.append(res.getString("nombre"));
                sb.append("\t");
                sb.append(res.getString("descripcion"));
                sb.append("\t");
                sb.append(res.getInt("cantidad"));
                sb.append("\n");
            }
        } catch (SQLException sqle) {
            System.out.println("Error en recursos");
        }
        return sb.toString();
    }
    
    public void aparteRecursos(int eve, int inv, int canti) {
        try {
            conexion.actualizarBase("INSERT INTO asignacion_recursos VALUES ("
                    + eve + ", " + inv+ ", " + canti + ");");
        } catch (SQLException sqle) {
            System.out.println("Error");
        }
    }
    
}

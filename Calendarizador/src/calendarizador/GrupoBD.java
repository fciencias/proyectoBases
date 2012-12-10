package calendarizador;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Galindo Martinez Jose Cruz.
 * @author Salazar Sastre Martin.
 */
public class GrupoBD {
    
    private Conexion conexion;
    private ResultSet resultado;
    private StringBuilder st;
    
    /**
     * 
     * @param conn 
     */
    public GrupoBD(Conexion conn) {
        this.conexion = conn;
        resultado = null;
    }
    
    
    /**
     * 
     * @param identificador 
     */
    public void eliminarGrupo(String identificador) {
        st = new StringBuilder();
        st.append("DELETE FROM grupo WHERE id_grupo = ");
        st.append(identificador);
        st.append(";");
        try {
            conexion.actualizarBase(st.toString());
        } catch (SQLException e) {
            System.err.println("Error al borrar de la base de datos");
        }
    }
    
    /**
     * 
     * @param nuevoUser
     * @return 
     */
    public boolean agregarGrupo(Grupo nuevoGrupo) {
        st = new StringBuilder();
        boolean salida = true;
        st.append("INSERT INTO grupo (id_responsable, nombre_grupo, fecha_creacion)"+
                " VALUES ('");
        st.append(nuevoGrupo.getResponsable());
        st.append("','");
        st.append(nuevoGrupo.getNombre());
        st.append("','");
        st.append(nuevoGrupo.getFecha_creacion());
        st.append("');");
        
        try {
            System.out.println(nuevoGrupo.toString());
            System.out.println(st.toString());
            conexion.actualizarBase(st.toString());
        } catch (SQLException exc) {
            System.out.println("Error al insertar en la base");
            salida = false;
        } 
        return salida;
    }
    
    /**
     * 
     * @return 
     */
    public Vector<Grupo> mostrarGrupos () {
        Vector<Grupo> vector = new Vector<Grupo>();
        try {
            resultado = conexion.consulta("SELECT * FROM grupo;");
            Grupo tmp = null;
            while (resultado.next()) {
                int id = resultado.getInt("id_grupo");
                String nom = resultado.getString("id_responsable");
                String nombre_grupo = resultado.getString("nombre_grupo");
                Date fecha = resultado.getDate("fecha_creacion");
                tmp = new Grupo(id, nom, nombre_grupo, fecha);
                vector.add(tmp);
            }
        } catch (SQLException sqle) {
            System.err.println("Error al abrir los usuarios");
        }
        return vector;
    }
    
}

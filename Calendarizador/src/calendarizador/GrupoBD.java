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
        boolean salida = true;
        st.append("INSERT INTO grupo (id_responsable, nombre_grupo, fecha_creacin)"+
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
    public Vector<Usuario> mostrarGrupos () {
        Vector<Usuario> vector = new Vector<Usuario>();
        try {
            resultado = conexion.consulta("SELECT * FROM grupo;");
            Usuario tmp = null;
            while (resultado.next()) {
                /*String idU = resultado.getString("id_usuario");
                String nom = resultado.getString("nombre");
                String app = resultado.getString("ap_paterno");
                String apm = resultado.getString("ap_materno");
                String ema = resultado.getString("email");
                String pas = resultado.getString("id_password");
                Date date = resultado.getDate("fecha_nacimiento");
                tmp = new Usuario(idU, nom, app, apm, ema, pas, date);
                vector.add(tmp);*/
            }
        } catch (SQLException sqle) {
            System.err.println("Error al abrir los usuarios");
        }
        return vector;
    }
    
}

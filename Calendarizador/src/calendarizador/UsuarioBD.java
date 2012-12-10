package calendarizador;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Galindo Martinez Jose Cruz
 * @author Salazar Sastre Martin
 */
public class UsuarioBD {
    
    private Conexion conexion;
    private ResultSet resultado;
    private Usuario usuario;
    private StringBuilder st;
    
    /**
     * 
     */
    public UsuarioBD(Conexion conexionAbierta) {
        conexion = conexionAbierta;
        resultado = null;
        usuario = null;
        st = new StringBuilder();
    }
    
    /**
     * 
     */
    public boolean consultarUsuario(Usuario usuario) throws SQLException {
        st.append("SELECT id_ususario FROM usuario WHERE id_usuario = '");
        st.append(usuario.getId_usuario());
        st.append("';");
        resultado = conexion.consulta(st.toString());
        while (resultado.next() != false) {
            return true;
        }
        return false;
    }
    
    public boolean agregarUsuario(Usuario nuevoUser) {
        boolean salida = true;
        st.append("INSERT INTO usuario VALUES ('");
        st.append(nuevoUser.getId_usuario());
        st.append("','");
        st.append(nuevoUser.getNombre());
        st.append("','");
        st.append(nuevoUser.getAp_paterno());
        st.append("','");
        st.append(nuevoUser.getAp_materno());
        st.append("','");
        st.append(nuevoUser.getEmail());
        st.append("',");
        if (nuevoUser.getFecha() != null ) {
            st.append("'");
            st.append(nuevoUser.getFecha());
            st.append("','");
        } else {
            st.append("null,'");
        }
        st.append(nuevoUser.getPassword());
        st.append("');");
        try {
            System.out.println(nuevoUser.toString());
            System.out.println(st.toString());
            conexion.actualizarBase(st.toString());
        } catch (SQLException exc) {
            System.out.println("Error al insertar en la base");
            salida = false;
        } 
        return salida;
    }
    
    public void eliminarUsuario(String identificador) {
        st = new StringBuilder();
        st.append("DELETE FROM usuario WHERE id_usuario = '");
        st.append(identificador);
        st.append("';");
        try {
            conexion.actualizarBase(st.toString());
        } catch (SQLException e) {
            System.err.println("Error al borrar de la base de datos");
        }
    }
    
    public Vector<Usuario> mostrarUsuarios () {
        Vector<Usuario> vector = new Vector<Usuario>();
        try {
            resultado = conexion.consulta("SELECT * FROM usuario;");
            Usuario tmp = null;
            while (resultado.next()) {
                String idU = resultado.getString("id_usuario");
                String nom = resultado.getString("nombre");
                String app = resultado.getString("ap_paterno");
                String apm = resultado.getString("ap_materno");
                String ema = resultado.getString("email");
                String pas = resultado.getString("id_password");
                Date date = resultado.getDate("fecha_nacimiento");
                tmp = new Usuario(idU, nom, app, apm, ema, pas, date);
                vector.add(tmp);
            }
        } catch (SQLException sqle) {
            System.err.println("Error al abrir los usuarios");
        }
        return vector;
    }
    
    
}//end class

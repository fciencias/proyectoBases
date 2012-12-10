package calendarizador;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        } 
        return false;
    }
    
    public String mostrarUsuarios () throws SQLException {
        st = new StringBuilder();
        resultado = conexion.consulta("SELECT * FROM usuario;");
        while (resultado.next()) {
            st.append(resultado.getString("id_usuario"));
            st.append("\t");
            st.append(resultado.getString("nombre"));
            st.append("\n");
        }
        return st.toString();
    }
    
    
}//end class

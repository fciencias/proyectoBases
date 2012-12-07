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
    
    /**
     * 
     */
    public String consultarPasswd(Usuario usuario) throws SQLException {
        String passwd = null;
        st.append("SELECT id_password FROM usuario WHERE id_usuario = '");
        st.append(usuario.getId_usuario());
        st.append("';");
        resultado = conexion.consulta(st.toString());
        while (resultado.next() != false) {
            passwd = resultado.getString("id_passwordd");
        }
        return passwd;
    }
    
    
}//end class

package calendarizador;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Galindo Martinez Jose Cruz
 * @author Salazar Sastre Martin
 */
public class EventoBD {
    
    private Conexion conexion;
    private ResultSet resultado;
    private Evento evento;
    private StringBuilder sb;
    
    /**
     * 
     * @param arg_conexion 
     */
    public EventoBD(Conexion arg_conexion) {
        this.conexion = arg_conexion;
        resultado = null;
        evento = null;
        sb = new StringBuilder();
    }
    
    public String consultarEvento() throws SQLException {
        sb.append("");
        resultado = conexion.consulta("SELECT * FROM evento;");
        while (resultado.next()) {
            sb.append(resultado.getInt("id_evento"));
            sb.append("\t");
            sb.append(resultado.getString("responsable"));
            sb.append("\t");
            sb.append(resultado.getString("descripcion"));
            sb.append("\n");
        }
        return sb.toString();
    }
    
    
}

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
    
    public boolean agrgarEvento(Evento evento, String id_responsable) {
        sb = new StringBuilder();
        sb.append("INSERT INTO evento VALUES ('");
            sb.append(id_responsable);
            sb.append("','");
            sb.append(evento.getFecha_inicio());
            sb.append("','");
            sb.append(evento.getFecha_final());
            sb.append("','");
            sb.append(evento.getHora_inicio());
            sb.append("','");
            sb.append(evento.getHora_final());
            sb.append("','");
            sb.append(evento.getDescripcion());
            sb.append("',");
            sb.append(evento.getMax_asistentes());
            sb.append(");");
        try {
            conexion.actualizarBase(sb.toString());
        } catch (SQLException e) {
            System.err.println("No se puede agregar el evento a la base");
        }
        return true;
    }
    
    
}

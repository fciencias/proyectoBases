package calendarizador;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Vector;

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
    
    public boolean agregarEvento(Evento evento, String id_responsable) {
        boolean agregado = true;
        sb = new StringBuilder();
        sb.append("INSERT INTO evento (responsable,fecha_inicio,fecha_final"+
                ",hora_inicio,hora_final,descripcion,max_asistentes) VALUES ('");
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
            System.out.println(sb.toString());
            conexion.actualizarBase(sb.toString());
            
        } catch (SQLException e) {
            agregado = false;
            System.err.println("No se puede agregar el evento a la base");
        }
        return agregado;
    }
    
    public Vector<Evento> mostrarEventos() {
        Vector<Evento> vector = new Vector<Evento>();
        try {
            resultado = conexion.consulta("SELECT * FROM evento;");
            Evento tmp = null;
            while (resultado.next()) {
                int idE = resultado.getInt("id_evento");
                String nom = resultado.getString("responsable");
                Date fi = resultado.getDate("fecha_inicio");
                Date ff = resultado.getDate("fecha_final");
                Time hi = resultado.getTime("hora_inicio");
                Time hf = resultado.getTime("hora_final");
                String des = resultado.getString("descripcion");
                int max = resultado.getInt("max_asistentes");
                tmp = new Evento(idE, nom, fi, ff, hi, hf, des, max);
                vector.add(tmp);
            }
        } catch (SQLException sqle) {
            System.err.println("Error al abrir los eventos");
        }
        return vector;
    } 
}

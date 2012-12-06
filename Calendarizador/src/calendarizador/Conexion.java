package calendarizador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Galindo Martinez Jose Cruz
 * @author Salazar Sastre Martin
 */
public class Conexion {
    
    private String url;
    private String controlador;
    private String usuario;
    private String password;
    private Connection conexion;
    private Statement stmt;

    /**
     * Constructor de clase. Inicializa los atributos con los datos que
     * recibe como parámetro.
     * @param base El nombre de la base.
     * @param usuario El usuario para dicha base de datos.
     * @param password La contrasenia del usuario.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Conexion(String base, String usuario, String password) throws ClassNotFoundException, SQLException {
        this.controlador = "org.postgresql.Driver";
        this.url = "jdbc:postgresql:" + base;
        this.usuario = usuario;
        this.password = password;
        cargarDriver();
        abrirConexion();
    }
    
    
    /**
     *
     */
    private void cargarDriver() throws ClassNotFoundException {
        Class.forName(controlador);
        System.out.println("Driver cargado exitosamente!!!");
    }

    /**
     *
     */
    private void abrirConexion() throws SQLException {
        System.out.println("Estableciendo conexión...");
        conexion = DriverManager.getConnection(url, usuario, password);
        System.out.println("Conexion establecida exitosamente!!!");
        stmt = conexion.createStatement();
    }
    
    /**
     * Metodo que regresa la conexion activa.
     * @return Un objeto con dicha conexión.
     */
    public Connection getConexion() {
        return conexion;
    }
    
    /**
     * Método que ejecuta una sentencia de tipo DELETE o UPDATE.
     * @param query la sentencia a ejecutarse.
     * @throws SQLException
     */
    public void actualizarBase(String query) throws SQLException {
        stmt.executeUpdate(query);
        System.out.println("Actualizacion exitosa!!!");
    }

    /**
     * Método que realiza una consulta a la base de datos.
     * @param query La consulta a realizar
     * @return Un Objeto REsultSet con el resultado de la consulta.
     * @throws SQLException
     */
    public ResultSet consulta(String query) throws SQLException {
        ResultSet resultado = stmt.executeQuery(query);
        System.out.println("Consulta exitosa!!!");
        return resultado;
    }

    /**
     * Metodo que cierra el Statement
     * @throws SQLException
     */
    public void cerrarStatement() throws SQLException {
        stmt.close();
        System.out.println("Statement cerrado exitosamente!!!");
    }

    /**
     * Método que cierra la conexión a la base de datos.
     * @throws SQLException
     */
    public void cerrarConexion() throws SQLException {
        if (getConexion() != null) {
            conexion.close();
        }
        System.out.println("Conexion cerrada exitosamente!!!");
    }
    
}

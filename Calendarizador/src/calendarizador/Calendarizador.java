/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarizador;

import java.sql.SQLException;

/**
 *
 * @author pepe
 */
public class Calendarizador {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here
      Conexion coneccion = null;
      try {
        coneccion = new Conexion("supermercado", "postgres", "postgres");
      } catch (ClassNotFoundException cnfe) {
          System.err.println("NO encontre la clase");
      } catch (SQLException sqle) {
          System.err.println("Problemas con la base");
      }
      
      EventoBD edb = new EventoBD(coneccion);
      try {
        System.out.println(edb.consultarEvento());
      } catch (SQLException sqle) {
          System.out.println("Error");
      }
      //calendario cal = new calendario();
      //cal.visible();
      //GUI gui = new GUI();
      //gui.init();
      
  }
  
  
}

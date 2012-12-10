package calendarizador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author pepe
 */
public class CheckBoxes extends JDialog{

    private JTextField inNumRecurso;
    private JTextField inIdEvento;
    private JTextField cantidad;
    private JTextArea informacion;
    private JButton aceptar;
    private JButton cancelar;
    private Conexion conexion;
    
    public CheckBoxes(JFrame padre, String titulo,
            String mensaje) {
        super(padre, titulo, true);
        this.conexion = abreConexion();
        armaDialogo(padre, mensaje);
        setTitle(titulo);
        defineEscuchas();
        ponRecursos();
    }

    private void armaDialogo(JFrame padre, String mensaje) {
         JPanel panel = new JPanel();
         JPanel botones = new JPanel();
         JPanel area = new JPanel();
         panel.setPreferredSize(new Dimension(550, 550));
         panel.setLayout(new BorderLayout());
         JScrollPane rollo;
         //botones
         informacion = new JTextArea();
         informacion.setEditable(false);
         rollo = new JScrollPane(informacion);
         rollo.setPreferredSize(new Dimension(550, 500));
         panel.add(area, BorderLayout.CENTER);
         panel.add(botones, BorderLayout.SOUTH);
         area.add(rollo);
         inNumRecurso = new JTextField(7);
         JLabel rec = new JLabel("Recurso");
         JLabel ev = new JLabel("Evento");
         JLabel ca = new JLabel("Cantidad");
         inIdEvento = new JTextField(7);
         cantidad = new JTextField(7);
         botones.add(rec);
         botones.add(inNumRecurso);
         botones.add(ev);
         botones.add(inIdEvento);
         botones.add(ca);
         botones.add(cantidad);
         aceptar = new JButton("aceptar");
         botones.add(aceptar);
        
        getContentPane().add(panel);
        //getContentPane().add(botones);
        pack();
        inNumRecurso.setRequestFocusEnabled(true);
        inNumRecurso.requestFocus();
        setLocationRelativeTo(padre);
    }
    
    private void ponRecursos () {
        RecursoBD rbd = new RecursoBD(conexion);
        String datos = rbd.muestraRecursos();
        informacion.setText(datos);
    }
    
    public void corre() {
        setVisible(true);
    }

    private void defineEscuchas() {
         addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            }
        });
        
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                agregaRecursos();
                    dispose();
               
            }
        });
    }

    private Conexion abreConexion() {
        Conexion coneccion = null;
      try {
        coneccion = new Conexion("proyecto", "postgres", "");
      } catch (ClassNotFoundException cnfe) {
          System.err.println("NO encontre la clase");
      } catch (SQLException sqle) {
          System.err.println("Problemas con la base");
      }
      return coneccion;
    }
    
    private void agregaRecursos(){
        RecursoBD rbd = new RecursoBD(conexion);
        int irec = 0;
        int ieve = 0;
        int icant = 0;
        try {
            irec = Integer.parseInt(inNumRecurso.getText());
            ieve = Integer.parseInt(inIdEvento.getText());
            icant = Integer.parseInt(cantidad.getText());
        } catch (NumberFormatException nfe) {
            System.out.println("Error");
        }
            rbd.aparteRecursos(ieve, ieve, icant);
    }
    
}

package calendarizador;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Galindo Martinez Jose Cruz
 * @author Salazar Sastre Martin
 */
public class DialogoDelGroup extends JDialog {
    
    private JTextField inId;
    private JButton aceptar;
    private JButton cancelar;
    private Conexion conexion;
    
    /**
     * 
     * @param padre
     * @param titulo
     * @param mensaje
     * @param conn 
     */
    public DialogoDelGroup(JFrame padre, String titulo,
            String mensaje, Conexion conn) {
        super(padre, titulo, true);
        this.conexion = conn;
        armaDialogo(padre, mensaje);
        defineEscuchas();
    }

    private void armaDialogo(JFrame padre, String mensaje) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        JLabel etiqueta = new JLabel(mensaje, SwingConstants.LEFT);
        panel.add(etiqueta);
        panel.add(new JLabel(""));
        etiqueta = new JLabel("ID Grupo:",SwingConstants.RIGHT);
        panel.add(etiqueta);
        inId = new JTextField(15);
        panel.add(inId);
        aceptar = new JButton("aceptar");
        cancelar = new JButton("cancelar");
        panel.add(aceptar);
        panel.add(cancelar);
        getContentPane().add(panel);
        pack();
        inId.setRequestFocusEnabled(true);
        inId.requestFocus();
        setLocationRelativeTo(padre);
    }
    
    private void defineEscuchas() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            }
        });
        
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                borrarGrupo();
                dispose();
            }
        });
        
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
    }
    
    private void borrarGrupo() {
        String ident = inId.getText();
        GrupoBD edb = new GrupoBD(conexion);
        try {
            Number n = Integer.parseInt(ident);
        } catch (NumberFormatException nfe) {
            System.err.println("Se debe de poner un id de grupo.");
        }
        edb.eliminarGrupo(ident);
    }
    
    /**
     * 
     */
    public void corre() {
        setVisible(true);
    }
    
}


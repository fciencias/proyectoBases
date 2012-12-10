package calendarizador;

import com.toedter.calendar.JDateChooser;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Galindo Martinez Jose Cruz
 * @author Salazar Sastre Martin
 */
public class DialogoAddGroup extends JDialog {
    
    private JTextField inNombre;
    private JDateChooser calendario;
    private JButton aceptar;
    private JButton cancelar;
    private Grupo grupo;
    
    public DialogoAddGroup(JFrame padre, String titulo,
            String mensaje) {
        super(padre, titulo, true);
        armaDialogo(padre, mensaje);
        defineEscuchas();
    }

    private void armaDialogo(JFrame padre, String mensaje) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(94, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        JLabel etiqueta = new JLabel(mensaje, SwingConstants.LEFT);
        panel.add(etiqueta);
        panel.add(new JLabel(""));
        etiqueta = new JLabel("Nombre:",SwingConstants.RIGHT);
        panel.add(etiqueta);
        inNombre = new JTextField(25);
        panel.add(inNombre);
        etiqueta = new JLabel("fecha creacion:",SwingConstants.RIGHT);
        panel.add(etiqueta);
        calendario = new JDateChooser();
        panel.add(calendario);
        aceptar = new JButton("aceptar");
        cancelar = new JButton("cancelar");
        panel.add(aceptar);
        panel.add(cancelar);
        getContentPane().add(panel);
        pack();
        inNombre.setRequestFocusEnabled(true);
        inNombre.requestFocus();
        setLocationRelativeTo(padre);
    }
    
    private void defineEscuchas() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                grupo = null;
            }
        });
        
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (validaCampos()) {
                    dispose();
                }
            }
        });
        
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                grupo = null;
                dispose();
            }
        });
    }
    
    private boolean validaCampos() {
        boolean validos = true;
        String mensaje = "";
        String name = inNombre.getText();
        if (name.isEmpty()) {
            mensaje += "El nombre no puede ser vacio.\n";
            validos = false;
        }
        if (!validos) {
            JOptionPane.showMessageDialog(this, mensaje,"Campos Invalidos", 
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        Date fecha = calendario.getDate();
        java.sql.Date sqlfecha = null;
        if (fecha != null) {
            long sqldate = fecha.getTime();
            sqlfecha = new java.sql.Date(sqldate);
        }
        //nuevoUsuario = new Usuario(id, name, app, apm, corre, contra, sqlfecha);
        return validos;
    }
    
    
    public Grupo corre() {
       this.setVisible(true);
       return grupo;
    }
}

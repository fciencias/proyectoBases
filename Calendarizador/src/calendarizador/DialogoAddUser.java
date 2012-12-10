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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Galindo Martinez Jose Cruz
 * @author Salazar Sastre Martin
 */
public class DialogoAddUser extends JDialog {
    
    private JTextField inIdUsuario;
    private JTextField inNombre;
    private JTextField inApPaterno;
    private JTextField inApMaterno;
    private JTextField inEmail;
    private JPasswordField inPassword;
    private Date inFechaNacimiento;
    private JDateChooser calendario;
    private JButton aceptar;
    private JButton cancelar;
    private Usuario nuevoUsuario;
    
    public DialogoAddUser(JFrame padre, String titulo,
            String mensaje) {
        super(padre, titulo, true);
        armaDialogo(padre, mensaje);
        defineEscuchas();
    }

    private void armaDialogo(JFrame padre, String mensaje) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        JLabel etiqueta = new JLabel(mensaje, SwingConstants.LEFT);
        panel.add(etiqueta);
        panel.add(new JLabel(""));
        etiqueta = new JLabel("ID:",SwingConstants.RIGHT);
        panel.add(etiqueta);
        inIdUsuario = new JTextField(25);
        panel.add(inIdUsuario);
        etiqueta = new JLabel("Nombre:",SwingConstants.RIGHT);
        panel.add(etiqueta);
        inNombre = new JTextField(25);
        panel.add(inNombre);
        etiqueta = new JLabel("Apellido Paterno:",SwingConstants.RIGHT);
        panel.add(etiqueta);
        inApPaterno = new JTextField(25);
        panel.add(inApPaterno);
        etiqueta = new JLabel("Apellido Materno:",SwingConstants.RIGHT);
        panel.add(etiqueta);
        inApMaterno = new JTextField(25);
        panel.add(inApMaterno); 
        etiqueta = new JLabel("email:",SwingConstants.RIGHT);
        panel.add(etiqueta);
        inEmail = new JTextField(25);
        panel.add(inEmail);
        etiqueta = new JLabel("passwd:",SwingConstants.RIGHT);
        panel.add(etiqueta);
        inPassword = new JPasswordField(15);
        panel.add(inPassword);
        etiqueta = new JLabel("fecha nac:",SwingConstants.RIGHT);
        panel.add(etiqueta);
        calendario = new JDateChooser();
        panel.add(calendario);
        aceptar = new JButton("aceptar");
        cancelar = new JButton("cancelar");
        panel.add(aceptar);
        panel.add(cancelar);
        getContentPane().add(panel);
        pack();
        inIdUsuario.setRequestFocusEnabled(true);
        inIdUsuario.requestFocus();
        setLocationRelativeTo(padre);
    }
    
    private void defineEscuchas() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                nuevoUsuario = null;
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
                nuevoUsuario = null;
                dispose();
            }
        });
    }
    
    private boolean validaCampos() {
        boolean validos = true;
        String mensaje = "";
        String id = inIdUsuario.getText();
        String name = inNombre.getText();
        String app = inApPaterno.getText();
        String apm = inApMaterno.getText();
        String corre = inEmail.getText();
        String contra = new String(inPassword.getPassword());
        if (id.isEmpty()) {
            mensaje += "El Id no puede ser vacio.\n";
            validos = false;
        } else if (name.isEmpty()) {
            mensaje += "El nombre no puede ser vacio.\n";
            validos = false;
        } else if (app.isEmpty()) {
            mensaje += "El apellido no puede ser vacio.\n";
            validos = false;
        } else if (apm.isEmpty()) {
            mensaje += "El apellido no puede ser vacio.\n";
            validos = false;
        } else if (corre.isEmpty()) {
            mensaje += "El e-mail no puede ser vacio.\n";
            validos = false;
        } else if (contra.isEmpty()) {
            mensaje += "El password no puede ser vacio.\n";
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
        nuevoUsuario = new Usuario(id, name, app, apm, corre, contra, sqlfecha);
        return validos;
    }
    
    
    public Usuario corre() {
       this.setVisible(true);
       return nuevoUsuario;
    }
}

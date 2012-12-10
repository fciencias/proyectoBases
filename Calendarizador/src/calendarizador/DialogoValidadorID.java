package calendarizador;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class DialogoValidadorID extends JDialog {
    
    private JTextField inIdUsuario;
    private JPasswordField inPassword;
    private JButton aceptar;
    private JButton cancelar;
    private Conexion conexion;
    private ResultSet resultado;
    private String encontradoS;
    private boolean encontrado;
    
    
    public DialogoValidadorID(JFrame padre, String titulo,
            String mensaje, Conexion conn) {
        super(padre, titulo, true);
        this.conexion = conn;
        resultado = null;
        encontradoS = null;
        encontrado = false;
        armaDialogo(padre, mensaje);
        setTitle(titulo);
        defineEscuchas(padre);
    }

    //
    private void armaDialogo(JFrame padre, String mensaje) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        JLabel etiqueta = new JLabel(mensaje, SwingConstants.LEFT);
        panel.add(etiqueta);
        panel.add(new JLabel(""));
        etiqueta = new JLabel("ID Usuario:",SwingConstants.RIGHT);
        panel.add(etiqueta);
        inIdUsuario = new JTextField(15);
        panel.add(inIdUsuario);
        etiqueta = new JLabel("passwd:",SwingConstants.RIGHT);
        panel.add(etiqueta);
        inPassword = new JPasswordField(15);
        panel.add(inPassword);
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

    private void defineEscuchas(final JFrame padre) {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            }
        });
        
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                validaUsuario();    
                if (encontrado) {
                    JOptionPane.showMessageDialog(padre, "Acceso Concedido");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(padre, "Acceso Denegado");
                }
            }
        });
        
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
    }
    
    
    private void validaUsuario() {
        StringBuilder st = new StringBuilder();
        String user = inIdUsuario.getText();
        String pass = new String(inPassword.getPassword());
        st.append("SELECT id_password FROM usuario WHERE id_usuario = '");
        st.append(user);
        st.append("';");
        String passwd = null;
        encontradoS = user;
        try {
            resultado = conexion.consulta(st.toString());
            while (resultado.next()) {
                passwd = resultado.getString("id_password");
                System.out.println("Encontre esta contrasena " + passwd);
                System.out.println("y yo tengo esta contrasena " + pass);
                if (passwd.equals(pass)) {
                    encontrado = true;
                }
            }
        } catch (SQLException sqle) {
            encontrado = false;
        }
    }
    
    public String corre() {
        setVisible(true);
        return encontradoS;
    }
    
}

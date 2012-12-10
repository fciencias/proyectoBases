package calendarizador;

import com.toedter.calendar.JDateChooser;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Time;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author pepe
 */
public class DialogoAddEvento extends JDialog {
    
    private JDateChooser fecha_inicio;
    private JDateChooser fecha_fin;
    private JTextField hora_inicio;
    private JTextField hora_fin;
    private JTextField asistentes;
    private JTextArea descripcion;
    private JButton recursos;
    private JButton aceptar;
    private JButton cancelar;
    private Evento mi_evento;
    
    /**
     * 
     * @param padre
     * @param titulo
     * @param mensaje 
     */
    public DialogoAddEvento(JFrame padre, String titulo,
            String mensaje) {
        super(padre, titulo, true);
        armaDialogo(padre, mensaje);
        defineEscuchas(padre);
    }

    
    private void armaDialogo(JFrame padre, String mensaje) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        JLabel etiqueta = new JLabel(mensaje, SwingConstants.LEFT);
        panel.add(etiqueta);
        panel.add(new JLabel(""));
        etiqueta = new JLabel("Fecha Inicio:",SwingConstants.RIGHT);
        panel.add(etiqueta);
        fecha_inicio = new JDateChooser();
        panel.add(fecha_inicio);
        etiqueta = new JLabel("Fecha Fin:",SwingConstants.RIGHT);
        panel.add(etiqueta);
        fecha_fin = new JDateChooser();
        panel.add(fecha_fin);
        etiqueta = new JLabel("Hora Inicio(0-23):",SwingConstants.RIGHT);
        panel.add(etiqueta);
        hora_inicio = new JTextField(7);
        panel.add(hora_inicio);
        etiqueta = new JLabel("Hora Fin(0-23):",SwingConstants.RIGHT);
        panel.add(etiqueta);
        hora_fin = new JTextField(7);
        panel.add(hora_fin);
        etiqueta = new JLabel("Cupo:",SwingConstants.RIGHT);
        panel.add(etiqueta);
        asistentes = new JTextField(7);
        panel.add(asistentes);
        etiqueta = new JLabel("Descripcion:",SwingConstants.RIGHT);
        panel.add(etiqueta);
        descripcion = new JTextArea();
        panel.add(descripcion);
        panel.add(new JLabel(""));
        recursos = new JButton("Recursos");
        aceptar = new JButton("aceptar");
        cancelar = new JButton("cancelar");
        panel.add(recursos);
        panel.add(aceptar);
        panel.add(cancelar);
        getContentPane().add(panel);
        pack();
        fecha_inicio.setRequestFocusEnabled(true);
        fecha_inicio.requestFocus();
        setLocationRelativeTo(padre);
    }

    private boolean validaCampos() {
        boolean validos = true;
        String mensaje = "";
        try {
            Number n = Integer.parseInt(hora_inicio.getText());
            Number n2 = Integer.parseInt(hora_fin.getText());
        } catch (NumberFormatException nfe) {
            validos = false;
            mensaje += "Alguna Hora esta mal.";
            System.err.println("No se pueden convertir los numeros");
        }
        try {
            Number n = Integer.parseInt(asistentes.getText()); 
        }catch (NumberFormatException nfe) {
            validos = false;
            mensaje += "Asistentes debe ser un numero.";
            System.err.println("No se pueden convertir los numeros");
        }
        if (!validos) {
            JOptionPane.showMessageDialog(this, mensaje, "Campos Invalidos",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        int hi = Integer.parseInt(hora_inicio.getText());
        java.sql.Time thi = new Time(hi, 0, 0);
        int hf = Integer.parseInt(hora_fin.getText());
        java.sql.Time thf = new Time(hf, 0, 0);
        Date fechaI = fecha_inicio.getDate();
        Date fechaF = fecha_fin.getDate();
        long tfechai = fechaI.getTime();
        long tfechaf = fechaF.getTime();
        java.sql.Date fi = new java.sql.Date(tfechai);
        java.sql.Date ff = new java.sql.Date(tfechaf);
        int asi = Integer.parseInt(asistentes.getText());
        String desciption = descripcion.getText();
        mi_evento = new Evento(fi, ff, thi, thf, desciption, asi);
        return validos;
    }
    
    private void defineEscuchas(final JFrame padre) {
         addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mi_evento = null;
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
                mi_evento = null;
                dispose();
            }
        });
        
        recursos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                CheckBoxes cb = new CheckBoxes(padre, "", "");
                cb.corre();
            }
        });
    }
    
    /**
     * 
     * @return 
     */
    public Evento corre() {
        setVisible(true);
        return mi_evento;
    }
    
    
    
    
}

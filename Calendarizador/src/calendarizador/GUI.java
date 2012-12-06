package calendarizador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Galindo Martinez Jose Cruz
 * @author Salazar Sastre Martin
 */
public class GUI {
    
    private JFrame marco;
    private JPanel panel_principal;
    private JPanel informacion;
    private JTabbedPane pestanas;
    private JMenuBar menu;
    private static int ALTO = 750;
    private static int LARGO = 600;
    
    /**
     *
     */
    public GUI() {
        marco = new JFrame("Calendarizador");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setPreferredSize(new Dimension(ALTO, LARGO));
        //marco.setJmenuBar(creaBarraMenu());
        panel_principal = new JPanel();
        panel_principal.setPreferredSize(new Dimension(ALTO, LARGO));
        panel_principal.setLayout(new BorderLayout());
        informacion = new JPanel();
        pestanas = creaPestanas();
        informacion.add(pestanas);
        panel_principal.add(informacion, BorderLayout.CENTER);
        marco.add(panel_principal);
        marco.pack();
    }

    /**
     * 
     */
    public void init() {
        marco.setVisible(true);
    }
    
    //
    private JTabbedPane creaPestanas() {
        JTabbedPane tabedPane = new JTabbedPane();
        JPanel principal = pestanaUsuarios();
        
        
        tabedPane.addTab("Usuarios", null, principal, "Lista de Usuarios");
        tabedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        return tabedPane;
    }
    
    //
    private JPanel pestanaUsuarios() {
        JPanel principal = new JPanel();
        principal.setPreferredSize(new Dimension(650, 510));
        principal.setLayout(new BorderLayout());
        
        JPanel usuariosPanel = new JPanel();
        usuariosPanel.setPreferredSize(new Dimension(600, 500));
        
        JPanel botones = new JPanel();
        JButton adduser = new JButton("Agregar Usuario");
        JButton deluser = new JButton(" Borrar Usuario");
        botones.add(adduser);
        botones.add(deluser);
        principal.add(usuariosPanel, BorderLayout.CENTER);
        principal.add(botones, BorderLayout.SOUTH);
        return principal;
    }
    
}

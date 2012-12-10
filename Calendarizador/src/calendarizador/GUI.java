package calendarizador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

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
    private JMenuBar menu_;
    private static int ALTO = 750;
    private static int LARGO = 600;
    private Conexion conexion;
    
    /**
     *
     */
    public GUI(Conexion conexionActiva) {
        this.conexion = conexionActiva;
        marco = new JFrame("Calendarizador");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setPreferredSize(new Dimension(ALTO, LARGO));
        menu_ = creaBarraMenu();
        marco.setJMenuBar(menu_);
        panel_principal = new JPanel();
        panel_principal.setPreferredSize(new Dimension(ALTO, LARGO));
        panel_principal.setLayout(new BorderLayout());
        informacion = new JPanel();
        pestanas = creaPestanas();
        //aqui van las tablas
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
    
    public final JMenuBar creaBarraMenu() {
        JMenuBar barraMenu = new JMenuBar();
        JMenu menu = new JMenu("Calendarizar");
        barraMenu.add(menu);
        JMenuItem el;
        el = new JMenuItem("Salir");
        el.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 
            ActionEvent.CTRL_MASK));
        el.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(el);
        menu = new JMenu("Help");
        el = new JMenuItem("info");
        el.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(marco, "Copy Rigth Derechos Reservados\n"+
                        "Galindo Martinez Jose Cruz\nSalazar Sastre Martin",
                        "Acerca de...",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(el);
        barraMenu.add(menu);
        return barraMenu;
    }
    
    //
    private JTabbedPane creaPestanas() {
        JTabbedPane tabedPane = new JTabbedPane();
        JPanel panelUsuarios = pestanaUsuarios();
        JPanel panelEventos = pestanaEventos();
        JPanel panelGrupos = pestanasGrupos();
        
        tabedPane.addTab("Usuarios", null, panelUsuarios, "Lista de Usuarios");
        tabedPane.addTab("Eventos", null, panelEventos, "Lista de Eventos");
        tabedPane.addTab("Grupos", null, panelGrupos, "Lista de Grupos");
        tabedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        return tabedPane;
    }
    
    //
    private JPanel pestanaUsuarios() {
        JPanel principal = new JPanel();
        principal.setPreferredSize(new Dimension(650, 510));
        principal.setLayout(new BorderLayout());
        
        JPanel usuariosPanel = new JPanel();
        final JList<Usuario> lista = new JList<Usuario>();
        JScrollPane rollo = new JScrollPane(lista);
        rollo.setPreferredSize(new Dimension(590, 450));
        final UsuarioBD createuser = new UsuarioBD(conexion);
        Vector<Usuario> usua = createuser.mostrarUsuarios();
        lista.setListData(usua);
        
        usuariosPanel.setPreferredSize(new Dimension(600, 500));
        usuariosPanel.add(rollo);
        JPanel botones = new JPanel();
        JButton adduser = new JButton("Agregar Usuario");
        JButton deluser = new JButton(" Borrar Usuario");
        
        adduser.setToolTipText("Agrega a un nuevo usuario");
        deluser.setToolTipText("Elimina a un usuario");
        adduser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                DialogoValidador validador = new DialogoValidador(marco, "Validar", "", conexion);
                boolean ok = validador.corre();
                if (ok) {
                    DialogoAddUser agregarUsuarioDialogo = new DialogoAddUser(marco, "Agregar Usuario", "");
                    Usuario nu = agregarUsuarioDialogo.corre();
                    if (nu != null) {
                        boolean createCorrected = createuser.agregarUsuario(nu);
                        if (createCorrected) {
                            JOptionPane.showMessageDialog(marco, "Exito al insertar");
                            actualizaListaUsu(lista);
                        } else {
                            JOptionPane.showMessageDialog(marco, "Error al Insertar");
                        }
                    }
                }
            }
        });
        deluser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //Aqui va el verdadero codigo
                DialogoValidador validador = new DialogoValidador(marco, "Validar", "", conexion);
                boolean ok = validador.corre();
                if (ok) {
                    DialogoDelUser deluser = new DialogoDelUser(marco, "Eliminar", "Prueba", conexion);
                    deluser.corre();
                    actualizaListaUsu(lista);
                }
            }
        });
        botones.add(adduser);
        botones.add(deluser);
        principal.add(usuariosPanel, BorderLayout.CENTER);
        principal.add(botones, BorderLayout.SOUTH);
        return principal;
    }
    
    
    //
    private JPanel pestanaEventos() {
        JPanel principal = new JPanel();
        principal.setPreferredSize(new Dimension(650, 510));
        principal.setLayout(new BorderLayout());
        final EventoBD eventodb = new EventoBD(conexion);

        final JList<Evento> lista = new JList<Evento>();
        JScrollPane rollo = new JScrollPane(lista);
        rollo.setPreferredSize(new Dimension(590, 450));
        Vector<Evento> usua = eventodb.mostrarEventos();
        lista.setListData(usua);
        //
        JPanel eventosPanel = new JPanel();
        eventosPanel.setPreferredSize(new Dimension(600, 500));
        eventosPanel.add(rollo);
        JPanel botones = new JPanel();
        JButton addevent = new JButton("Agregar Evento");
        JButton delevent = new JButton(" Borrar Evento");
        addevent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                DialogoValidadorID dvid = new DialogoValidadorID(marco, "Validar Usuario", "", conexion);
                String idu = dvid.corre();
                if (idu != null) {
                    DialogoAddEvento dae = new DialogoAddEvento(marco, "Agregar Evento", "Agregar Evento");
                    Evento tmp = dae.corre();
                    if (tmp != null) {
                        boolean ok = eventodb.agregarEvento(tmp, idu);
                        if (ok) {
                             JOptionPane.showMessageDialog(marco, "Exito al insertar");
                            actualizaListaEve(lista);
                        } else {
                            JOptionPane.showMessageDialog(marco, "Error al Insertar");
                        }
                    }
                }
            }
        });
        delevent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                DialogoValidador dv = new DialogoValidador(marco, "Validar", "Ingresar datos", conexion);
                boolean corre = dv.corre();
                if (corre) {
                    DialogDelEvent dde = new DialogDelEvent(marco, "Eliminar", "Insertar id evento", conexion);
                    dde.corre();
                    actualizaListaEve(lista);
                }
                //Aqui va el verdadero codigo
                //JOptionPane.showMessageDialog(marco, "Aqui va algo", "null", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        botones.add(addevent);
        botones.add(delevent);
        principal.add(eventosPanel, BorderLayout.CENTER);
        principal.add(botones, BorderLayout.SOUTH);
        return principal;
    }

    private JPanel pestanasGrupos() {
        JPanel principal = new JPanel();
        principal.setPreferredSize(new Dimension(650, 510));
        principal.setLayout(new BorderLayout());
        
        JPanel usuariosPanel = new JPanel();
        usuariosPanel.setPreferredSize(new Dimension(600, 500));
        
        JPanel botones = new JPanel();
        JButton adduser = new JButton("Agregar Grupo");
        JButton deluser = new JButton(" Borrar Grupo");
        adduser.setToolTipText("Agrega a un nuevo grupo");
        deluser.setToolTipText("Elimina a un grupo");
        adduser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //Aqui va el verdadero codigo
                JOptionPane.showMessageDialog(marco, "Aqui va algo", "null", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        deluser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //Aqui va el verdadero codigo
                JOptionPane.showMessageDialog(marco, "Aqui va algo", "null", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        botones.add(adduser);
        botones.add(deluser);
        principal.add(usuariosPanel, BorderLayout.CENTER);
        principal.add(botones, BorderLayout.SOUTH);
        return principal;
    }
    
    private void actualizaListaUsu(JList<Usuario> lista) {
        UsuarioBD createuser = new UsuarioBD(conexion);
        Vector<Usuario> usua = createuser.mostrarUsuarios();
        lista.setListData(usua);
    }
    
    private void actualizaListaEve(JList<Evento> lista) {
        EventoBD eventodb = new EventoBD(conexion);
        Vector<Evento> usua = eventodb.mostrarEventos();
        lista.setListData(usua);
    }
    
    
    
}

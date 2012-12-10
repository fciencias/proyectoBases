package calendarizador;

import com.toedter.calendar.JCalendar;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author pepe
 */
public class calendario {
    
    private JFrame marco;
    private JPanel panel;
    private JCalendar calendario_;
    private JButton okboton;
    
    public calendario() {
        marco = new JFrame("prueba");
        panel = new JPanel();
        calendario_ = new JCalendar();
        okboton = new JButton("Ok");
        okboton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Date date = calendario_.getDate();
                
                JOptionPane.showMessageDialog(marco,date.toString(), 
                        "Fecha", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        calendario_.setDecorationBordersVisible(true);
        calendario_.setWeekOfYearVisible(false);
        panel.add(calendario_);
        panel.add(okboton);
        marco.setPreferredSize(new Dimension(300, 400));
        marco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        marco.add(panel);
        marco.pack();
    }
    
    public void visible() {
        marco.setVisible(true);
    }
    
    
}

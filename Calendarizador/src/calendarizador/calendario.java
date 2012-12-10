package calendarizador;

import com.toedter.calendar.JCalendar;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author pepe
 */
public class calendario {
    
    private JFrame marco;
    private JPanel panel;
    private JCalendar calendario_;
    
    public calendario() {
        marco = new JFrame("prueba");
        panel = new JPanel();
        calendario_ = new JCalendar();
        calendario_.setDecorationBordersVisible(true);
        calendario_.setWeekOfYearVisible(false);
        panel.add(calendario_);
        marco.setPreferredSize(new Dimension(400, 400));
        marco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        marco.add(panel);
        marco.pack();
    }
    
    public void visible() {
        marco.setVisible(true);
    }
    
    
}

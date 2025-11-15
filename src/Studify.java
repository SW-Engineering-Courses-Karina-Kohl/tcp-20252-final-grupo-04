import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class Studify {
    public static void main(String[] args) {
        System.out.println("Welcome to Studify!");
        JFrame frame = new JFrame("Studify");
        frame.setSize(900, 500);
        JPanel panel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        panel.setLayout(cardLayout);
        TitleScreen ts = new TitleScreen();
        ts.initializeTitleScreen(ts);
        ts.transitionToRegisterWeekScreen(panel, cardLayout);
        panel.add(ts.getTitlePanel(), "TitlePanel");
        //segunda tela
        RegisterWeekScreen rws = new RegisterWeekScreen();
        rws.initializeRegisterWeekScreen(rws, panel, cardLayout);
        panel.add(rws.getRegisterWeekPanel(), "RegisterWeekPanel");
        //temporário enquanto não faz a tela de calendário e dos time slots
        JPanel calendarPanel = new JPanel();
        calendarPanel.setSize(900, 500);
        for (int i = 0; i < 7; i++) {
            JPanel[] dayPanels = new JPanel[7];
            dayPanels[i] = new JPanel();
            dayPanels[i].setSize(900, 500);
            dayPanels[i].setLayout(null);
            panel.add(dayPanels[i], "Register" + i + "TimeSlotPanel");
        }
        panel.add(calendarPanel, "CalendarPanel");
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

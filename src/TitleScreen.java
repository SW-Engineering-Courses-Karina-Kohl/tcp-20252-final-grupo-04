import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.CardLayout;

public class TitleScreen {
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JButton startButton;

    public JButton getStartButton() {
        return startButton;
    }
    public void setStartButton() {
        this.startButton = new JButton("Gerar Agenda");
        this.startButton.setSize(300, 150);
        this.startButton.setLocation(300, 250);
        this.startButton.setFont(new Font("Arial", Font.PLAIN, 28));
    }
    public JLabel getTitleLabel() {
        return titleLabel;
    }
    public void setTitleLabel() {
        this.titleLabel = new JLabel("Studify");
        this.titleLabel.setSize(200, 100);
        this.titleLabel.setLocation(380, 70);
        this.titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 48));
    }
    public JPanel getTitlePanel() {
        return titlePanel;
    }
    public void setTitlePanel() {
        this.titlePanel = new JPanel();
        this.titlePanel.setLayout(null);
        this.titlePanel.setSize(900, 500);
    }
    public void transitionToRegisterWeekScreen(JPanel panel, CardLayout cardLayout) {
        startButton.addActionListener(e -> {
            cardLayout.show(panel, "RegisterWeekPanel");
        });
    }
    public void initializeTitleScreen(TitleScreen ts) {
        ts.setTitleLabel();
        ts.setStartButton();
        ts.setTitlePanel();
        ts.getTitlePanel().add(ts.getTitleLabel());
        ts.getTitlePanel().add(ts.getStartButton());
    }
}

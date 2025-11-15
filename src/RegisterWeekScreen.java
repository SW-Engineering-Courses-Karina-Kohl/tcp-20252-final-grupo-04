import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.CardLayout;

public class RegisterWeekScreen {
    private JPanel registerWeekPanel;
    private JLabel infoDataInicio;
    private JTextArea dataInicioInput;
    private JLabel infoDataFim;
    private JTextArea dataFimInput;
    private JLabel infoImpedimentos;
    private JTextArea impedimentosInput;
    private JButton[] botoesDiaSemana;
    private JButton nextScreen;
    private String dataInicio;
    private String dataFim;
    private String impedimentos;
   
    public JButton getNextScreenButton() {
        return nextScreen;
    }
    public void setNextScreenButton() {
        this.nextScreen = new JButton("Próxima Tela");
        this.nextScreen.setSize(200, 50);
        this.nextScreen.setLocation(670, 390);
        this.nextScreen.setFont(new Font("Arial", Font.PLAIN, 20));
    }
    public JPanel getRegisterWeekPanel() {
        return registerWeekPanel;
    }
    public void setRegisterWeekPanel() {
        this.registerWeekPanel = new JPanel();
        this.registerWeekPanel.setLayout(null);
        this.registerWeekPanel.setSize(900, 500);
    }
    public JLabel getInfoDataInicioLabel() {
        return infoDataInicio;
    }
    public void setInfoDataInicioLabel() {
        this.infoDataInicio = new JLabel("Data Início Vigência (AAAA-MM-DD):");
        this.infoDataInicio.setSize(300, 30);
        this.infoDataInicio.setLocation(50, 50);
    }
    public JTextArea getDataInicioInput() {
        return dataInicioInput;
    }
    public void setDataInicioInput() {
        this.dataInicioInput = new JTextArea();
        this.dataInicioInput.setSize(200, 30);
        this.dataInicioInput.setLocation(320, 50);
    }
    public JLabel getInfoDataFimLabel() {
        return infoDataFim;
    }
    public void setInfoDataFimLabel() {
        this.infoDataFim = new JLabel("Data Fim Vigência (AAAA-MM-DD):");
        this.infoDataFim.setSize(300, 30);
        this.infoDataFim.setLocation(50, 100);
    }
    public JTextArea getDataFimInput() {
        return dataFimInput;
    }
    public void setDataFimInput() {
        this.dataFimInput = new JTextArea();
        this.dataFimInput.setSize(200, 30);
        this.dataFimInput.setLocation(320, 100);
    }
    public JLabel getInfoImpedimentosLabel() {
        return infoImpedimentos;
    }
    public void setInfoImpedimentosLabel() {
        this.infoImpedimentos = new JLabel("Impedimentos (AAAA-MM-DD HH:MM):");
        this.infoImpedimentos.setSize(300, 30);
        this.infoImpedimentos.setLocation(50, 150);
    }
    public JTextArea getImpedimentosInput() {
        return impedimentosInput;
    }
    public void setImpedimentosInput() {
        this.impedimentosInput = new JTextArea();
        this.impedimentosInput.setSize(200, 30);
        this.impedimentosInput.setLocation(320, 150);
    }
    public void setWeekDaysButtons(JPanel panel, CardLayout cardLayout) {
        String[] diasSemana = {"Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado", "Domingo"};
        botoesDiaSemana = new JButton[diasSemana.length];
        for (int i = 0; i < diasSemana.length; i++) {
            botoesDiaSemana[i] = new JButton(diasSemana[i]);
            int botoesIndex = i;
            botoesDiaSemana[i].addActionListener(e -> {
                cardLayout.show(panel, "Register" + botoesIndex + "TimeSlotPanel");
            });
            botoesDiaSemana[i].setSize(150, 50);
            botoesDiaSemana[i].setLocation(50 + (i % 4) * 200, 250 + (i / 4) * 70);
            this.registerWeekPanel.add(botoesDiaSemana[i]);
        }
    }
    public void transitionToCalendarScreen(JPanel panel, CardLayout cardLayout) {
        nextScreen.addActionListener(e -> {
            dataInicio = dataInicioInput.getText();
            dataFim = dataFimInput.getText();
            impedimentos = impedimentosInput.getText();
            // Temporário, apenas para verificação no console
            System.out.println("Data Início: " + dataInicio);
            System.out.println("Data Fim: " + dataFim);
            System.out.println("Impedimentos: " + impedimentos);
            cardLayout.show(panel, "CalendarPanel");
        });
    }
    public String getDataInicio() {
        return dataInicio;
    }
    public String getDataFim() {
        return dataFim;
    }
    public String getImpedimentos() {
        return impedimentos;
    }
    public void initializeRegisterWeekScreen(RegisterWeekScreen rws, JPanel panel, CardLayout cardLayout) {
        rws.setRegisterWeekPanel();
        rws.setNextScreenButton();
        rws.getRegisterWeekPanel().add(rws.getNextScreenButton());
        rws.setInfoDataInicioLabel();
        rws.setDataInicioInput();
        rws.getRegisterWeekPanel().add(rws.getDataInicioInput());
        rws.getRegisterWeekPanel().add(rws.getInfoDataInicioLabel());
        rws.setInfoDataFimLabel();
        rws.setDataFimInput();
        rws.getRegisterWeekPanel().add(rws.getDataFimInput());
        rws.getRegisterWeekPanel().add(rws.getInfoDataFimLabel());
        rws.setInfoImpedimentosLabel();
        rws.getRegisterWeekPanel().add(rws.getInfoImpedimentosLabel());
        rws.setImpedimentosInput();
        rws.getRegisterWeekPanel().add(rws.getImpedimentosInput());
        rws.setWeekDaysButtons(panel, cardLayout);
        rws.transitionToCalendarScreen(panel, cardLayout);
    }
}

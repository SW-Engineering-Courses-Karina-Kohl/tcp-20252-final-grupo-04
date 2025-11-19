import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.CardLayout;
import java.util.ArrayList;
import org.tinylog.Logger;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.format.DateTimeParseException;

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
    private JButton addImpedimentos;
    private String dataInicio;
    private String dataFim;
    private List<String> impedimentos;
   
    public JButton getNextScreenButton() {
        return nextScreen;
    }
    public void setNextScreenButton() {
        this.nextScreen = new JButton("Próxima Tela");
        this.nextScreen.setSize(200, 50);
        this.nextScreen.setLocation(670, 390);
        this.nextScreen.setFont(new Font("Arial", Font.PLAIN, 20));
    }
    public JButton getAddImpedimentosButton() {
        return addImpedimentos;
    }
    public void setAddImpedimentosButton() {
        this.addImpedimentos = new JButton("Adicionar Impedimento");
        this.addImpedimentos.setSize(200, 50);
        this.addImpedimentos.setLocation(600, 140);
        this.addImpedimentos.setFont(new Font("Arial", Font.PLAIN, 16));
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
        this.infoDataInicio = new JLabel("Data Início Vigência (DD/MM/AAAA):");
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
        this.infoDataFim = new JLabel("Data Fim Vigência (DD/MM/AAAA):");
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
        this.infoImpedimentos = new JLabel("Impedimentos (DD/MM/AAAA HH:MM):");
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
            if(validateDateInput(dataInicio, "dd/MM/uuuu") && validateDateInput(dataFim, "dd/MM/uuuu")) {
                Logger.info("Datas de vigência válidas: Início - " + dataInicio + ", Fim - " + dataFim);
                cardLayout.show(panel, "CalendarPanel");
            } else {
                Logger.error("Formato de data inválido para vigência.");
            }
        });
    }
    public boolean validateDateInput(String data, String formato) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato).withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate.parse(data, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    public void coletaImpedimentos() {
        this.impedimentos = new ArrayList<>();
        addImpedimentos.addActionListener(e -> {
            if(validateDateInput(impedimentosInput.getText(), "dd/MM/uuuu HH:mm")) {
                Logger.info("Impedimento válido registrado: " + impedimentosInput.getText());
                impedimentos.add(impedimentosInput.getText());
                impedimentosInput.setText("");
            } else {
                Logger.error("Formato de impedimento inválido: " + impedimentosInput.getText());
            } 
        });
    }
    public String getDataInicio() {
        return dataInicio;
    }
    public String getDataFim() {
        return dataFim;
    }
    public List<String> getImpedimentos() {
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
        rws.setAddImpedimentosButton();
        rws.coletaImpedimentos();
        rws.getRegisterWeekPanel().add(rws.getAddImpedimentosButton());
    }
}

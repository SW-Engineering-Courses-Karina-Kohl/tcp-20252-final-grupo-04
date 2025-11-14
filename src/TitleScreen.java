
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.CardLayout;

public class TitleScreen {
    //atributos tela de título
    //possivelmente estáticos
    private int larguraPainel = 900;
    private int alturaPainel = 500;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JButton startButton;
    private int alturaTexto = 200;
    private int larguraTexto = 100;
    private int posicaoXTexto = 380;
    private int posicaoYTexto = 70;
    private int alturaBotao = 150;
    private int larguraBotao = 300;
    private int posicaoXBotao = 300;
    private int posicaoYBotao = 250;
    //métodos tela de título
    public JButton getStartButton() {
        return startButton;
    }
    public void setStartButton(int largura, int altura, int posicaoX, int posicaoY) {
        this.startButton = new JButton("Gerar Agenda");
        this.startButton.setSize(largura, altura);
        this.startButton.setLocation(posicaoX, posicaoY);
        this.startButton.setFont(new Font("Arial", Font.PLAIN, 28));
    }
    public JLabel getTitleLabel() {
        return titleLabel;
    }
    public void setTitleLabel(int largura, int altura, int posicaoX, int posicaoY) {
        this.titleLabel = new JLabel("Studify");
        this.titleLabel.setSize(largura, altura);
        this.titleLabel.setLocation(posicaoX, posicaoY);
        this.titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 48));
    }
    public JPanel getTitlePanel() {
        return titlePanel;
    }
    public void setTitlePanel(int largura, int altura) {
        this.titlePanel = new JPanel();
        this.titlePanel.setLayout(null);
        this.titlePanel.setSize(largura, altura);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Studify");
        frame.setSize(900, 500);
        JPanel panel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        panel.setLayout(cardLayout);
        TitleScreen ts = new TitleScreen();
        ts.setTitleLabel(200, 100, 380, 70);
        ts.setStartButton(300, 150, 300, 250);
        ts.setTitlePanel(900, 500);
        ts.getTitlePanel().add(ts.getTitleLabel());
        ts.getTitlePanel().add(ts.getStartButton());
        panel.add(ts.getTitlePanel(), "TitlePanel");
        //segunda tela
        RegisterWeekScreen rws = new RegisterWeekScreen();
        rws.setRegisterWeekPanel(900, 500);
        rws.setNextScreenButton(670, 390, 200, 50);
        rws.getRegisterWeekPanel().add(rws.getNextScreenButton());
        panel.add(rws.getRegisterWeekPanel(), "RegisterWeekPanel");
        rws.setInfoDataInicioLabel(50, 50, 300, 30);
        rws.setDataInicioInput(320, 50, 200, 30);
        rws.getRegisterWeekPanel().add(rws.getDataInicioInput());
        rws.getRegisterWeekPanel().add(rws.getInfoDataInicioLabel());
        rws.setInfoDataFimLabel(50, 100, 300, 30);
        rws.setDataFimInput(320, 100, 200, 30);
        rws.getRegisterWeekPanel().add(rws.getDataFimInput());
        rws.getRegisterWeekPanel().add(rws.getInfoDataFimLabel());
        rws.setInfoImpedimentosLabel(50, 150, 300, 30);
        rws.getRegisterWeekPanel().add(rws.getInfoImpedimentosLabel());
        rws.setImpedimentosInput(320, 150, 200, 30);
        rws.getRegisterWeekPanel().add(rws.getImpedimentosInput());
        rws.setSegundaFeiraButton(50, 250, 150, 50);
        rws.getRegisterWeekPanel().add(rws.getSegundaFeiraButton());
        rws.setTercaFeiraButton(250, 250, 150, 50);
        rws.getRegisterWeekPanel().add(rws.getTercaFeiraButton());
        rws.setQuartaFeiraButton(450, 250, 150, 50);
        rws.getRegisterWeekPanel().add(rws.getQuartaFeiraButton());
        rws.setQuintaFeiraButton(650, 250, 150, 50);
        rws.getRegisterWeekPanel().add(rws.getQuintaFeiraButton());
        rws.setSextaFeiraButton(350, 320, 150, 50);
        rws.getRegisterWeekPanel().add(rws.getSextaFeiraButton());
        rws.setSabadoButton(150, 320, 150, 50);
        rws.getRegisterWeekPanel().add(rws.getSabadoButton());
        rws.setDomingoButton(550, 320, 150, 50);
        rws.getRegisterWeekPanel().add(rws.getDomingoButton());
        JPanel registerMondayTimeSlotPanel = new JPanel();
        registerMondayTimeSlotPanel.setSize(900, 500);
        registerMondayTimeSlotPanel.setLayout(null);
        panel.add(registerMondayTimeSlotPanel, "RegisterMondayTimeSlotPanel");
        rws.getSegundaFeiraButton().addActionListener(e -> {
            cardLayout.show(panel, "RegisterMondayTimeSlotPanel");
        });
        rws.getNextScreenButton().addActionListener(e -> {
            String dataInicio = rws.getDataInicioInput().getText();
            String dataFim = rws.getDataFimInput().getText();
            String impedimentos = rws.getImpedimentosInput().getText();
            cardLayout.show(panel, "RegisterAssingnmentsPanel");
        });
        ts.getStartButton().addActionListener(e -> {
            cardLayout.show(panel, "RegisterWeekPanel");
        });
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
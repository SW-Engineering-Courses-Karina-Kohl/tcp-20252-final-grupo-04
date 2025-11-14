import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Font;

public class RegisterWeekScreen {
    private int larguraPainelRegistroSemana = 900;
    private int alturaPainelRegistroSemana = 500;
    private JPanel registerWeekPanel;
    private int posicaoXInfoDataInicio = 50;
    private int posicaoYInfoDataInicio = 50;
    private int larguraInfoDataInicio = 300;
    private int alturaInfoDataInicio = 30;
    private JLabel infoDataInicio;
    private JTextArea dataInicioInput;
    private int posicaoXInfoDataFim = 50;
    private int posicaoYInfoDataFim = 100;
    private int larguraInfoDataFim = 300;
    private int alturaInfoDataFim = 30;
    private JLabel infoDataFim;
    private JTextArea dataFimInput;
    private int posicaoXInfoImpedimentos = 50;
    private int posicaoYInfoImpedimentos = 150;
    private int larguraInfoImpedimentos = 300;
    private int alturaInfoImpedimentos = 30;
    private JLabel infoImpedimentos;
    private JTextArea impedimentosInput;
    private JButton segundaFeiraButton;
    private JButton tercaFeiraButton;
    private JButton quartaFeiraButton;
    private JButton quintaFeiraButton;
    private JButton sextaFeiraButton;
    private JButton sabadoButton;
    private JButton domingoButton;
    private JButton nextScreen;
    //métodos tela de registro de semana
    public JButton getSegundaFeiraButton() {
        return segundaFeiraButton;
    }
    public void setSegundaFeiraButton(int posicaoX, int posicaoY, int largura, int altura) {
        this.segundaFeiraButton = new JButton("Segunda-feira");
        this.segundaFeiraButton.setSize(largura, altura);
        this.segundaFeiraButton.setLocation(posicaoX, posicaoY);
    }
    public JButton getTercaFeiraButton() {
        return tercaFeiraButton;
    }
    public void setTercaFeiraButton(int posicaoX, int posicaoY, int largura, int altura) {
        this.tercaFeiraButton = new JButton("Terça-feira");
        this.tercaFeiraButton.setSize(largura, altura);
        this.tercaFeiraButton.setLocation(posicaoX, posicaoY);
    }
    public JButton getQuartaFeiraButton() {
        return quartaFeiraButton;
    }
    public void setQuartaFeiraButton(int posicaoX, int posicaoY, int largura, int altura) {
        this.quartaFeiraButton = new JButton("Quarta-feira");
        this.quartaFeiraButton.setSize(largura, altura);
        this.quartaFeiraButton.setLocation(posicaoX, posicaoY);
    }
    public JButton getQuintaFeiraButton() {
        return quintaFeiraButton;
    }
    public void setQuintaFeiraButton(int posicaoX, int posicaoY, int largura, int altura) {
        this.quintaFeiraButton = new JButton("Quinta-feira");
        this.quintaFeiraButton.setSize(largura, altura);
        this.quintaFeiraButton.setLocation(posicaoX, posicaoY);
    }
    public JButton getSextaFeiraButton() {
        return sextaFeiraButton;
    }
    public void setSextaFeiraButton(int posicaoX, int posicaoY, int largura, int altura) {
        this.sextaFeiraButton = new JButton("Sexta-feira");
        this.sextaFeiraButton.setSize(largura, altura);
        this.sextaFeiraButton.setLocation(posicaoX, posicaoY);
    }
    public JButton getSabadoButton() {
        return sabadoButton;
    }
    public void setSabadoButton(int posicaoX, int posicaoY, int largura, int altura) {
        this.sabadoButton = new JButton("Sábado");
        this.sabadoButton.setSize(largura, altura);
        this.sabadoButton.setLocation(posicaoX, posicaoY);
    }
    public JButton getDomingoButton() {
        return domingoButton;
    }
    public void setDomingoButton(int posicaoX, int posicaoY, int largura, int altura) {
        this.domingoButton = new JButton("Domingo");
        this.domingoButton.setSize(largura, altura);
        this.domingoButton.setLocation(posicaoX, posicaoY);
    }
    public JButton getNextScreenButton() {
        return nextScreen;
    }
    public void setNextScreenButton(int posicaoX, int posicaoY, int largura, int altura) {
        this.nextScreen = new JButton("Próxima Tela");
        this.nextScreen.setSize(largura, altura);
        this.nextScreen.setLocation(posicaoX, posicaoY);
        this.nextScreen.setFont(new Font("Arial", Font.PLAIN, 20));
    }
    public JPanel getRegisterWeekPanel() {
        return registerWeekPanel;
    }
    public void setRegisterWeekPanel(int largura, int altura) {
        this.registerWeekPanel = new JPanel();
        this.registerWeekPanel.setLayout(null);
        this.registerWeekPanel.setSize(largura, altura);
    }
    public JLabel getInfoDataInicioLabel() {
        return infoDataInicio;
    }
    public void setInfoDataInicioLabel(int posicaoX, int posicaoY, int largura, int altura) {
        this.infoDataInicio = new JLabel("Data Início Vigência (AAAA-MM-DD):");
        this.infoDataInicio.setSize(largura, altura);
        this.infoDataInicio.setLocation(posicaoX, posicaoY);
    }
    public JTextArea getDataInicioInput() {
        return dataInicioInput;
    }
    public void setDataInicioInput(int posicaoX, int posicaoY, int largura, int altura) {
        this.dataInicioInput = new JTextArea();
        this.dataInicioInput.setSize(largura, altura);
        this.dataInicioInput.setLocation(posicaoX, posicaoY);
    }
    public JLabel getInfoDataFimLabel() {
        return infoDataFim;
    }
    public void setInfoDataFimLabel(int posicaoX, int posicaoY, int largura, int altura) {
        this.infoDataFim = new JLabel("Data Fim Vigência (AAAA-MM-DD):");
        this.infoDataFim.setSize(largura, altura);
        this.infoDataFim.setLocation(posicaoX, posicaoY);
    }
    public JTextArea getDataFimInput() {
        return dataFimInput;
    }
    public void setDataFimInput(int posicaoX, int posicaoY, int largura, int altura) {
        this.dataFimInput = new JTextArea();
        this.dataFimInput.setSize(largura, altura);
        this.dataFimInput.setLocation(posicaoX, posicaoY);
    }
    public JLabel getInfoImpedimentosLabel() {
        return infoImpedimentos;
    }
    public void setInfoImpedimentosLabel(int posicaoX, int posicaoY, int largura, int altura) {
        this.infoImpedimentos = new JLabel("Impedimentos (AAAA-MM-DD HH:MM):");
        this.infoImpedimentos.setSize(largura, altura);
        this.infoImpedimentos.setLocation(posicaoX, posicaoY);
    }
    public JTextArea getImpedimentosInput() {
        return impedimentosInput;
    }
    public void setImpedimentosInput(int posicaoX, int posicaoY, int largura, int altura) {
        this.impedimentosInput = new JTextArea();
        this.impedimentosInput.setSize(largura, altura);
        this.impedimentosInput.setLocation(posicaoX, posicaoY);
    }
}

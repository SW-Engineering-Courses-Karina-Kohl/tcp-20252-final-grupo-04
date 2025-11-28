package src.view;
import src.model.entities.*;
import src.model.atividades.*;
import src.model.config.*;
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

public class TelaRegistrarSemana {
    private JPanel painelRegistrarSemana;
    private JLabel infoDataInicio;
    private JTextArea dataInicioInput;
    private JLabel infoDataFim;
    private JTextArea dataFimInput;
    private JLabel infoImpedimentos;
    private JTextArea impedimentosInput;
    private JButton[] botoesDiaSemana;
    private JButton proximaTela;
    private JButton adicionaImpedimentos;
    private String dataInicio;
    private String dataFim;
    private List<String> impedimentos;
   
    public JButton getBotaoProximaTela() {
        return proximaTela;
    }
    public void setBotaoProximaTela() {
        this.proximaTela = new JButton("Próxima Tela");
        this.proximaTela.setSize(200, 50);
        this.proximaTela.setLocation(670, 390);
        this.proximaTela.setFont(new Font("Arial", Font.PLAIN, 20));
    }
    public JButton getBotaoAdicionaImpedimentos() {
        return adicionaImpedimentos;
    }
    public void setBotaoAdicionaImpedimentos() {
        this.adicionaImpedimentos = new JButton("Adicionar Impedimento");
        this.adicionaImpedimentos.setSize(200, 50);
        this.adicionaImpedimentos.setLocation(600, 140);
        this.adicionaImpedimentos.setFont(new Font("Arial", Font.PLAIN, 16));
    }
    public JPanel getPainelRegistrarSemana() {
        return painelRegistrarSemana;
    }
    public void setPainelRegistrarSemana() {
        this.painelRegistrarSemana = new JPanel();
        this.painelRegistrarSemana.setLayout(null);
        this.painelRegistrarSemana.setSize(900, 500);
    }
    public JLabel getInfoDataInicio() {
        return infoDataInicio;
    }
    public void setInfoDataInicio() {
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
    public JLabel getInfoDataFim() {
        return infoDataFim;
    }
    public void setInfoDataFim() {
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
    public JLabel getInfoImpedimentos() {
        return infoImpedimentos;
    }
    public void setInfoImpedimentos() {
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
    public void setBotoesDiaSemana(JPanel painel, CardLayout cardLayout) {
        String[] diasSemana = {"Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado", "Domingo"};
        botoesDiaSemana = new JButton[diasSemana.length];
        for (int i = 0; i < diasSemana.length; i++) {
            botoesDiaSemana[i] = new JButton(diasSemana[i]);
            int botoesIndex = i;
            botoesDiaSemana[i].addActionListener(e -> {
                cardLayout.show(painel, "TelaRegistrarTimeSlot" + botoesIndex);
            });
            botoesDiaSemana[i].setSize(150, 50);
            botoesDiaSemana[i].setLocation(50 + (i % 4) * 200, 250 + (i / 4) * 70);
            this.painelRegistrarSemana.add(botoesDiaSemana[i]);
        }
    }
    public void transicaoTelaRegistrarAtividade(JPanel painel, CardLayout cardLayout) {
        proximaTela.addActionListener(e -> {
            dataInicio = dataInicioInput.getText();
            dataFim = dataFimInput.getText();
            if(validaVigencia(dataInicio, dataFim)) {
                cardLayout.show(painel, "PainelRegistrarAtividade");
                ControladorRegistrarSemana controlador = new ControladorRegistrarSemana(dataInicio, dataFim, impedimentos);
                controlador.processaRegistroSemana();
            }
        });
    }
    public boolean validaVigencia(String dataInicio, String dataFim) {
        boolean vigenciaValida = false;
            if(validaDataInput(dataInicio, "dd/MM/uuuu") && validaDataInput(dataFim, "dd/MM/uuuu")) {
                ControladorRegistrarSemana controlador = new ControladorRegistrarSemana(dataInicio, dataFim, impedimentos);
                controlador.processaRegistroSemana();
                if(controlador.validaConfiguracaoAgenda()) {
                    vigenciaValida = true;
                    Logger.info("Datas de vigência válidas: Início - " + dataInicio + ", Fim - " + dataFim);
                } else {
                    Logger.error("Data de início deve ser anterior à data de fim.");
                }
            } else {
                Logger.error("Formato de data inválido para vigência.");
            }
            return vigenciaValida;
    }
    public boolean validaDataInput(String data, String formato) {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern(formato).withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate.parse(data, formatador);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    public boolean validaImpedimentoInput(String impedimento) {
        boolean impedimentoValido = false;
        if(validaDataInput(impedimento, "dd/MM/uuuu HH:mm")) {
            if (impedimentos.contains(impedimento)) {
                Logger.warn("Impedimento já existe: " + impedimento);
            } else if(!impedimento.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:(00|30)")) {
                Logger.warn("Apenas 30 ou 00 como minutos.");
            } else {
            Logger.info("Impedimento válido registrado: " + impedimento);
            impedimentoValido = true;
            }
        } else {
            Logger.error("Formato de impedimento inválido: " + impedimento);
        }
        return impedimentoValido;
    }
    public void coletaImpedimentos() {
        this.impedimentos = new ArrayList<>();
        adicionaImpedimentos.addActionListener(e -> {
            if(validaImpedimentoInput(impedimentosInput.getText().trim())) {
                impedimentos.add(impedimentosInput.getText());
                impedimentosInput.setText("");
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
    public void inicializaTelaRegistrarSemana(JPanel painel, CardLayout cardLayout) {
        setPainelRegistrarSemana();
        setBotaoProximaTela();
        this.painelRegistrarSemana.add(this.proximaTela);
        setInfoDataInicio();
        setDataInicioInput();
        this.painelRegistrarSemana.add(this.dataInicioInput);
        this.painelRegistrarSemana.add(this.infoDataInicio);
        setInfoDataFim();
        setDataFimInput();
        this.painelRegistrarSemana.add(this.dataFimInput);
        this.painelRegistrarSemana.add(this.infoDataFim);
        setInfoImpedimentos();
        this.painelRegistrarSemana.add(this.infoImpedimentos);
        setImpedimentosInput();
        this.painelRegistrarSemana.add(this.impedimentosInput);
        setBotoesDiaSemana(painel, cardLayout);
        transicaoTelaRegistrarAtividade(painel, cardLayout);
        setBotaoAdicionaImpedimentos();
        coletaImpedimentos();
        this.painelRegistrarSemana.add(this.adicionaImpedimentos);
    }
    public TelaRegistrarSemana(JPanel painel, CardLayout cardLayout) {
        inicializaTelaRegistrarSemana(painel, cardLayout);
    }
    public TelaRegistrarSemana() {
    }
}
package src.view;
import src.model.entities.*;
import src.model.atividades.*;
import src.model.config.*;
import src.controller.comunicacao.ControladorRegistrarAtividade;
import src.controller.comunicacao.ConDadosEntreTelas;
import javax.swing.JButton;
import java.awt.Font;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import javax.swing.JComboBox;
import org.tinylog.Logger;

public class TelaRegistrarAtividade {
    private JButton retornarTela;
    private JPanel painelRegistrarAtividade;
    private JButton concluirRegistroAtividade;
    private JButton adicionarAtividade;
    private JLabel infoDataAtividade;
    private JTextArea dataAtividadeInput;
    private JLabel infoNomeAtividade;
    private List<String> atividadesNomes;
    private JTextArea nomeAtividadeInput;
    private List<String> atividadesDatas;
    private JLabel infoTipoAtividade;
    private JComboBox<String> tipoAtividadeInput;
    private List<String> tipoAtividade;
    private JLabel infoDisciplinaAtividade;
    private JComboBox<String> disciplinaAtividadeInput;
    private List<String> disciplinasAtividade;
    private JLabel infoDisciplina;
    private JTextArea disciplinaNomeInput;
    private JButton adicionarDisciplina;
    private List<String> prioridadesDisciplinas;
    private JTextArea disciplinaPrioridadeInput;
    private List<String> todasDisciplinas;
    private ControladorRegistrarAtividade controladorRegistrarAtividade;
    private Aluno aluno;
    private TelaAgenda painelAgenda;
    private ConDadosEntreTelas comunicacao;
    public List<String> getAtividadesNomes() {
        return atividadesNomes;
    }
    public List<String> getAtividadesDatas() {
        return atividadesDatas;
    }
    public List<String> getTipoAtividade() {
        return tipoAtividade;
    }
    public List<String> getDisciplinasAtividade() {
        return disciplinasAtividade;
    }
    public JPanel getPainelRegistrarAtividade() {
        return painelRegistrarAtividade;
    }
    public void setPainelRegistrarAtividade() {
        this.painelRegistrarAtividade = new JPanel();
        this.painelRegistrarAtividade.setLayout(null);
        this.painelRegistrarAtividade.setSize(900, 500);
    }
    public JButton getBotaoRetornarTela() {
        return retornarTela;
    }
    public void setBotaoRetornarTela() {
        this.retornarTela = new JButton("Retornar");
        this.retornarTela.setSize(200, 50);
        this.retornarTela.setLocation(50, 390);
        this.retornarTela.setFont(new Font("Arial", Font.PLAIN, 20));
    }
    public void transicaoBotaoRetornarTela(JPanel painel, CardLayout cardLayout) {
        retornarTela.addActionListener(e -> {
            cardLayout.show(painel, "PainelRegistrarSemana");
        });
    }
    public JButton getConcluirRegistroAtividade() {
        return concluirRegistroAtividade;
    }
    public void setBotaoConcluirRegistroAtividade() {
        this.concluirRegistroAtividade = new JButton("Concluir Registro");
        this.concluirRegistroAtividade.setSize(250, 50);
        this.concluirRegistroAtividade.setLocation(600, 390);
        this.concluirRegistroAtividade.setFont(new Font("Arial", Font.PLAIN, 20));
    }
    public void transicaoBotaoConcluirRegistroAtividade(JPanel painel, CardLayout cardLayout) {
        concluirRegistroAtividade.addActionListener(e -> {
            if(this.atividadesNomes != null && !this.atividadesNomes.isEmpty() && 
               this.todasDisciplinas != null && !this.todasDisciplinas.isEmpty()) {
                this.controladorRegistrarAtividade = new ControladorRegistrarAtividade(atividadesNomes, atividadesDatas, tipoAtividade, disciplinasAtividade, prioridadesDisciplinas, todasDisciplinas, comunicacao);
                this.controladorRegistrarAtividade.converteDisciplinaAtividades();
                controladorRegistrarAtividade.AdicionaAtividadesAluno();
                comunicacao.setAluno(controladorRegistrarAtividade.getAluno());
                comunicacao.transicaoAtividadeAgenda();
                Logger.info("Atividades registradas com sucesso para o aluno.");
                Logger.info(comunicacao.getConfiguracaoAgenda().getDataInicioVigencia());
                DayOfWeek diaSemana=   comunicacao.getConfiguracaoAgenda().getDataInicioVigencia().getDayOfWeek();
                Logger.info(diaSemana.getDisplayName(TextStyle.FULL, Locale.getDefault()));                
                cardLayout.show(painel, "PainelAgenda");
            } else {
                Logger.warn("Nenhuma atividade ou disciplina foi registrada antes de concluir.");
            }
        });
    }
    public JButton getAdicionarAtividade() {
        return adicionarAtividade;
    }
    public void setBotaoAdicionarAtividade() {
        this.adicionarAtividade = new JButton("Adicionar Atividade");
        this.adicionarAtividade.setSize(250, 50);
        this.adicionarAtividade.setLocation(420, 320);
        this.adicionarAtividade.setFont(new Font("Arial", Font.PLAIN, 20));
    }
    public void coletaAtividade() {
        this.atividadesDatas = new ArrayList<>();
        this.atividadesNomes = new ArrayList<>();
        this.tipoAtividade = new ArrayList<>();
        this.disciplinasAtividade = new ArrayList<>();
        TelaRegistrarSemana telaRegistrarSemana = new TelaRegistrarSemana();
        this.adicionarAtividade.addActionListener(e -> {
            String dataAtividade = dataAtividadeInput.getText();
            if(telaRegistrarSemana.validaDataInput(dataAtividade, "dd/MM/uuuu")) {
                atividadesDatas.add(dataAtividade);
                dataAtividadeInput.setText("");
                String nomeAtividade = nomeAtividadeInput.getText();
                atividadesNomes.add(nomeAtividade);
                nomeAtividadeInput.setText("");
                String tipoAtividadeAtual = (String) tipoAtividadeInput.getSelectedItem();
                this.tipoAtividade.add(tipoAtividadeAtual);
                String disciplinaAtividade = (String) disciplinaAtividadeInput.getSelectedItem();
                this.disciplinasAtividade.add(disciplinaAtividade);
                Logger.info(tipoAtividadeAtual + " da disciplina " + disciplinaAtividade + " adicionada: " + nomeAtividade + " na data " + dataAtividade);
            } else {
                Logger.error("Formato de data inválido para atividade: " + dataAtividade);
            }
        });
    }
    public JLabel getInfoDataAtividade() {
        return infoDataAtividade;
    }
    public void setInfoDataAtividade() {
        this.infoDataAtividade = new JLabel("Data da Atividade (DD/MM/AAAA):");
        this.infoDataAtividade.setSize(300, 30);
        this.infoDataAtividade.setLocation(300, 50);
    }
    public JTextArea getDataAtividadeInput() {
        return dataAtividadeInput;
    }
    public void setDataAtividadeInput() {
        this.dataAtividadeInput = new JTextArea();
        this.dataAtividadeInput.setSize(200, 30);
        this.dataAtividadeInput.setLocation(600, 50);
    }
    public JLabel getInfoNomeAtividade() {
        return infoNomeAtividade;
    }
    public void setInfoNomeAtividade() {
        this.infoNomeAtividade = new JLabel("Nome da Atividade:");
        this.infoNomeAtividade.setSize(200, 30);
        this.infoNomeAtividade.setLocation(300, 150);
    }
    public JTextArea getNomeAtividadeInput() {
        return nomeAtividadeInput;
    }
    public void setNomeAtividadeInput() {
        this.nomeAtividadeInput = new JTextArea();
        this.nomeAtividadeInput.setSize(200, 30);
        this.nomeAtividadeInput.setLocation(550, 150);
    }
    public JLabel getInfoTipoAtividade() {
        return infoTipoAtividade;
    }
    public void setInfoTipoAtividade() {
        this.infoTipoAtividade = new JLabel("Tipo de Atividade:");
        this.infoTipoAtividade.setSize(200, 30);
        this.infoTipoAtividade.setLocation(300, 200);
    }
    public JComboBox<String> getTipoAtividadeInput() {
        return tipoAtividadeInput;
    }
    public void setTipoAtividadeInput() {
        String[] tiposAtividade = {"Prova", "Exercício", "Trabalho"};
        this.tipoAtividadeInput = new JComboBox<>(tiposAtividade);
        this.tipoAtividadeInput.setSize(200, 30);
        this.tipoAtividadeInput.setLocation(550, 200);
    }
    public JLabel getInfoDisciplinaAtividade() {
        return infoDisciplinaAtividade;
    }
    public void setInfoDisciplinaAtividade() {
        this.infoDisciplinaAtividade = new JLabel("Disciplina da Atividade:");
        this.infoDisciplinaAtividade.setSize(200, 30);
        this.infoDisciplinaAtividade.setLocation(300, 250);
    }
    public JComboBox<String> getDisciplinaAtividadeInput() {
        return disciplinaAtividadeInput;
    }
    public void setDisciplinaAtividadeInput() {
        this.disciplinaAtividadeInput = new JComboBox<>();
        this.disciplinaAtividadeInput.setSize(200, 30);
        this.disciplinaAtividadeInput.setLocation(550, 250);
    }
    public JLabel getInfoDisciplina() {
        return infoDisciplina;
    }
    public void setInfoDisciplina() {
        this.infoDisciplina = new JLabel("Disciplina (nome e prioridade):");
        this.infoDisciplina.setSize(200, 30);
        this.infoDisciplina.setLocation(50, 50);
    }
    public JTextArea getDisciplinaNomeInput() {
        return disciplinaNomeInput;
    }
    public void setDisciplinaNomeInput() {
        this.disciplinaNomeInput = new JTextArea();
        this.disciplinaNomeInput.setSize(200, 30);
        this.disciplinaNomeInput.setLocation(50, 80);
    }
    public JTextArea getDisciplinaPrioridadeInput() {
        return disciplinaPrioridadeInput;
    }
    public void setDisciplinaPrioridadeInput() {
        this.disciplinaPrioridadeInput = new JTextArea();
        this.disciplinaPrioridadeInput.setSize(200, 30);
        this.disciplinaPrioridadeInput.setLocation(50, 130);
    }
    public JButton getAdicionarDisciplina() {
        return adicionarDisciplina;
    }
    public void setAdicionarDisciplina() {
        this.adicionarDisciplina = new JButton("Adicionar Disciplina");
        this.adicionarDisciplina.setSize(200, 50);
        this.adicionarDisciplina.setLocation(30, 190);
        this.adicionarDisciplina.setFont(new Font("Arial", Font.PLAIN, 16));
    }
    public boolean validaDisciplina(String disciplinaNome, String disciplinaPrioridade) {
        boolean validacao = false;
        if (disciplinaNome.isEmpty()) {
            Logger.warn("Nenhuma disciplina foi inserida.");
        } else if (disciplinasAtividade.contains(disciplinaNome)) {
            Logger.warn("Disciplina já existe: " + disciplinaNome);
        }
        try {
            Double.parseDouble(disciplinaPrioridade);
            validacao = true;
        } catch (NumberFormatException e) {
            Logger.warn("Prioridade inválida para a disciplina: " + disciplinaNome);
            return validacao;
        }
        return validacao;
    }
    public void coletaDisciplina() {
        this.todasDisciplinas = new ArrayList<>();
        this.prioridadesDisciplinas = new ArrayList<>();
        this.adicionarDisciplina.addActionListener(e -> {
            String disciplinaNome = disciplinaNomeInput.getText();
            String disciplinaPrioridade = disciplinaPrioridadeInput.getText();
            if (validaDisciplina(disciplinaNome, disciplinaPrioridade)) {
            todasDisciplinas.add(disciplinaNome);
            disciplinaNomeInput.setText("");
            this.prioridadesDisciplinas.add(disciplinaPrioridade);
            disciplinaPrioridadeInput.setText("");
            this.disciplinaAtividadeInput.addItem(disciplinaNome);
            Logger.info("Disciplina adicionada: " + disciplinaNome + " com prioridade " + disciplinaPrioridade);
            }
        });
    }
    public Aluno getAluno() {
        return this.aluno;
    }
    
    public TelaRegistrarAtividade(JPanel painel, CardLayout cardLayout,Aluno aluno, TelaAgenda painelAgenda, ConDadosEntreTelas comunicacao) {
        this.comunicacao = comunicacao;
        this.aluno = aluno;
        setPainelRegistrarAtividade();
        setBotaoRetornarTela();
        this.painelRegistrarAtividade.add(retornarTela);
        transicaoBotaoRetornarTela(painel, cardLayout);
        setBotaoConcluirRegistroAtividade();
        this.painelRegistrarAtividade.add(concluirRegistroAtividade);
        setBotaoAdicionarAtividade();
        this.painelRegistrarAtividade.add(adicionarAtividade);
        coletaAtividade();
        setInfoDataAtividade();
        this.painelRegistrarAtividade.add(infoDataAtividade);
        setDataAtividadeInput();
        this.painelRegistrarAtividade.add(dataAtividadeInput);
        setInfoNomeAtividade();
        this.painelRegistrarAtividade.add(infoNomeAtividade);
        setNomeAtividadeInput();
        this.painelRegistrarAtividade.add(nomeAtividadeInput);
        setInfoTipoAtividade();
        this.painelRegistrarAtividade.add(infoTipoAtividade);
        setTipoAtividadeInput();
        this.painelRegistrarAtividade.add(tipoAtividadeInput);
        setInfoDisciplinaAtividade();
        this.painelRegistrarAtividade.add(infoDisciplinaAtividade);
        setDisciplinaAtividadeInput();
        this.painelRegistrarAtividade.add(disciplinaAtividadeInput);
        setInfoDisciplina();
        this.painelRegistrarAtividade.add(infoDisciplina);
        setDisciplinaNomeInput();
        this.painelRegistrarAtividade.add(disciplinaNomeInput);
        setDisciplinaPrioridadeInput();
        this.painelRegistrarAtividade.add(disciplinaPrioridadeInput);
        setAdicionarDisciplina();
        this.painelRegistrarAtividade.add(adicionarDisciplina);
        coletaDisciplina();
        transicaoBotaoConcluirRegistroAtividade(painel, cardLayout);
    }
}
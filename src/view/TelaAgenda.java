package src.view;
import src.model.entities.*;
import src.model.atividades.*;
import src.model.config.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.lang.reflect.Array;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.CardLayout;

import org.tinylog.Logger;
import javax.swing.*;
import com.github.lgooddatepicker.components.DatePicker;
import java.util.List;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JTable;

public class TelaAgenda extends JPanel {
    private JPanel painelInicial;
    private JLabel tituloAplicacao;
    private JButton botaoInicial;
    private GridBagLayout layout;
    private GridBagConstraints layoutConstraints;
    private DatePicker datePicker;
    final int NOMEATIVIDADEINDEX = 1;
    final int HORARIOINDEX = 0;
    private AgendaEstudos agendaEstudos;
    private LocalDate dataSelecionada;
    private List<TimeSlotEstudo> estudosDia;

    Aluno aluno;
    public TelaAgenda() {
        painelInicial = new JPanel();
        layout = new GridBagLayout();
        layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        painelInicial.setLayout(layout);
        tituloAplicacao = new JLabel("Agenda de Atividades");
        datePicker = new DatePicker();
        painelInicial.add(datePicker, layoutConstraints);

    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void initPainel(Aluno aluno){
        System.out.println("Agenda criada para o aluno: " + this.aluno.getDisciplinas().size() + " disciplinas cadastradas.");
        this.agendaEstudos = this.aluno.getAgenda();
        setDataSelecionada(aluno.getConfiguracaoAgenda().getDataInicioVigencia());
        datePicker.setDate(dataSelecionada);
        renderTabelaHorarios();

    }

    public void setDataSelecionada(LocalDate data){
        this.dataSelecionada =  data;
    }

    public JPanel getPainelInicial() {
        return painelInicial;
    }


    private void renderTabelaHorarios() {
        String[] colunas = {"Horário", "Atividade"};
        if (agendaEstudos.getEstudosDia(dataSelecionada) != null) {
            estudosDia = agendaEstudos.getEstudosDia(datePicker.getDate());    
        }
        
        String[][] dadosLista = fetchDadosAgenda(estudosDia);
        JTable tabelaHorarios = new JTable(dadosLista, colunas);
        JScrollPane scrollPane = new JScrollPane(tabelaHorarios);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 2;
        painelInicial.add(scrollPane, layoutConstraints);
    }

    private String[][] fetchDadosAgenda(List<TimeSlotEstudo> estudos) {

        final int NUMCOLUNAS = 2;
        int numLinhas = 48;
        String[][] dados = new String[numLinhas][NUMCOLUNAS];
        for (int i = 0; i < numLinhas; i++) {
            dados[i][HORARIOINDEX] = "00:00";
            dados[i][NOMEATIVIDADEINDEX] = "Livre";
        }
        for (TimeSlotEstudo estudo: estudos){
            dados[estudos.indexOf(estudo)][HORARIOINDEX] = estudo.getInicioEstudo().toString();
            dados[estudos.indexOf(estudo)][NOMEATIVIDADEINDEX] = estudo.getAtividade().getNome();
        }
        return dados;
    }

}
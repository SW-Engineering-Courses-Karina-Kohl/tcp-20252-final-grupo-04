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
    AgendaEstudos agendaEstudos;
    LocalDate dataSelecionada;
    Aluno aluno;
    public TelaAgenda(Aluno aluno) {
        this.aluno =  aluno;
        agendaEstudos = this.aluno.getAgenda();
        dataSelecionada = LocalDate.now();
        this.agendaEstudos = agendaEstudos;
        painelInicial = new JPanel();
        layout = new GridBagLayout();
        layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        painelInicial.setLayout(layout);
        datePicker = new DatePicker();
        datePicker.setDate(dataSelecionada);
        painelInicial.add(datePicker, layoutConstraints);
        tituloAplicacao = new JLabel("Agenda de Atividades");
        renderTabelaHorarios();
        System.out.println("Agenda criada para o aluno: " + this.aluno.getDisciplinas().size() + " disciplinas cadastradas.");
        
    }


    public JPanel getPainelInicial() {
        return painelInicial;
    }
    private void setDataSelecionada() {
        this.dataSelecionada = datePicker.getDate();
    }

    private void renderTabelaHorarios() {
        String[] colunas = {"Horário", "Atividade"};
        List<TimeSlotEstudo> estudos = agendaEstudos.getEstudosDia(datePicker.getDate());
        String[][] dadosLista = fetchDadosAgenda(estudos);
        JTable tabelaHorarios = new JTable(dadosLista, colunas);
        JScrollPane scrollPane = new JScrollPane(tabelaHorarios);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 2;
        painelInicial.add(scrollPane, layoutConstraints);
    }

    private String[][] fetchDadosAgenda(List<TimeSlotEstudo> estudos) {
        final int NUMCOLUNAS = 2;
        final int NUMLINHAS = estudos.size();
        String[][] dados = new String[NUMLINHAS][NUMCOLUNAS];
        for (TimeSlotEstudo estudo: estudos){
            dados[estudos.indexOf(estudo)][HORARIOINDEX] = estudo.getInicioEstudo().toString();
            dados[estudos.indexOf(estudo)][NOMEATIVIDADEINDEX] = estudo.getAtividade().getNome();
        }
        return dados;
    }

}
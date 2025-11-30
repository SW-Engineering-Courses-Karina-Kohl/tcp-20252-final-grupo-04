package src.view;
import src.model.entities.*;
import src.model.atividades.*;
import src.model.config.*;

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

public class TelaAgenda extends JPanel {
    private JPanel painelInicial;
    private JLabel tituloAplicacao;
    private JButton botaoInicial;
    private GridBagLayout layout;
    private DatePicker datePicker;
    final int NOMEATIVIDADEINDEX = 1;
    final int HORARIOINDEX = 0;
    private AgendaEstudos agendaEstudos;
    private LocalDate dataSelecionada;
    private List<TimeSlotEstudo> estudosDia;
    private JScrollPane scrollPane;
    private JTable tabelaHorarios;
    private String[][] dadosLista;
    GridBagConstraints layoutConstraints;
    private  final String[]  COLUNAS = {"Horário", "Atividade"};


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
        datePicker.addDateChangeListener(event -> {

            if (scrollPane !=  null) {
                painelInicial.remove(scrollPane);
                painelInicial.remove(tabelaHorarios);    
            }
            dataSelecionada = event.getNewDate();
            renderTabelaHorarios();
            painelInicial.revalidate();
            painelInicial.repaint();

        });
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
        
        estudosDia = agendaEstudos.getEstudosDia(dataSelecionada);
        if (estudosDia == null) estudosDia = new ArrayList<>();
        Logger.info("estudos do dia: " + estudosDia.size());
        dadosLista = fetchDadosAgenda(estudosDia);
        tabelaHorarios = new JTable(dadosLista, COLUNAS);
        scrollPane = new JScrollPane(tabelaHorarios);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 2;
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.weightx = 1;
        layoutConstraints.weighty = 1;
        painelInicial.add(scrollPane, layoutConstraints);
    }

private String[][] fetchDadosAgenda(List<TimeSlotEstudo> estudos) {

    int numLinhas = 48;
    String[][] dados = new String[numLinhas][2];

    // Preenche padrão
    for (int i = 0; i < numLinhas; i++) {
        int hora = i / 2;             // 0,0,1,1,2,2...
        int minuto = (i % 2) * 30;    // 0,30,0,30...
        String horario = String.format("%02d:%02d", hora, minuto);
    dados[i][HORARIOINDEX] = horario;
    dados[i][NOMEATIVIDADEINDEX] = "Livre";
}

    for (TimeSlotEstudo estudo : estudos) {

        if(estudo.getAtividade() != null)
        {
            int hora = estudo.getInicioEstudo().getHour();
            int minuto = estudo.getInicioEstudo().getMinute();
            int linha = hora * 2 + (minuto == 30 ? 1 : 0);
            dados[linha][HORARIOINDEX] = estudo.getInicioEstudo().toString();
            dados[linha][NOMEATIVIDADEINDEX] = estudo.getAtividade().getNome();

        }
        
    }

    return dados;
}

}
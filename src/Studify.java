package src;
import src.model.entities.*;
import src.model.atividades.*;
import src.model.config.*;
import src.view.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class Studify {
    public static void main(String[] args) {
        JFrame janela = new JFrame("Studify");
        janela.setSize(900, 500);
        JPanel painel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        painel.setLayout(cardLayout);
        //TelaAgenda telaAgenda = new TelaAgenda();
        //painel.add(telaAgenda.getPainelInicial(), "PainelAgenda");
        //TelaInicial telaInicial = new TelaInicial(painel, cardLayout);
        //painel.add(telaInicial.getPainelInicial(), "PainelInicial");
        //segunda tela
        TelaRegistrarSemana telaRegistrarSemana = new TelaRegistrarSemana(painel, cardLayout);
        painel.add(telaRegistrarSemana.getPainelRegistrarSemana(), "PainelRegistrarSemana");
        //temporário enquanto não faz a tela de calendário e dos time slots
        TelaRegistrarAtividade telaRegistrarAtividade = new TelaRegistrarAtividade(painel, cardLayout);
        TelaRegistrarTimeSlot[] telaRegistrarTimeSlot = new TelaRegistrarTimeSlot[7];
        for (int i = 0; i < 7; i++) {
            telaRegistrarTimeSlot[i] = new TelaRegistrarTimeSlot(painel, cardLayout);
            painel.add(telaRegistrarTimeSlot[i].getPainelRegistrarTimeSlot(), "TelaRegistrarTimeSlot" + i);
        }
        painel.add(telaRegistrarAtividade.getPainelRegistrarAtividade(), "PainelRegistrarAtividade");
        //temporário enquanto não faz a classe da tela de agenda
        JPanel painelAgenda = new JPanel();
        painelAgenda.setSize(900, 500);
        painelAgenda.setLayout(null);
        painel.add(painelAgenda, "PainelAgenda");
        janela.add(painel);
        janela.setVisible(true);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
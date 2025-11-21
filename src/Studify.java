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
        TelaInicial telaInicial = new TelaInicial(painel, cardLayout);
        painel.add(telaInicial.getPainelInicial(), "PainelInicial");
        //segunda tela
        TelaRegistrarSemana telaRegistrarSemana = new TelaRegistrarSemana(painel, cardLayout);
        painel.add(telaRegistrarSemana.getPainelRegistrarSemana(), "PainelRegistrarSemana");
        //terceira tela
        TelaRegistrarAtividade telaRegistrarAtividade = new TelaRegistrarAtividade(painel, cardLayout);
        //temporário enquanto não faz a tela dos time slots
        for (int i = 0; i < 7; i++) {
            JPanel[] paineisDiasSemana = new JPanel[7];
            paineisDiasSemana[i] = new JPanel();
            paineisDiasSemana[i].setSize(900, 500);
            paineisDiasSemana[i].setLayout(null);
            painel.add(paineisDiasSemana[i], "Registrar" + i + "PainelTimeSlot");
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

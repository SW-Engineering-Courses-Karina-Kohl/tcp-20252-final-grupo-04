package src;
import src.model.entities.*;
import src.model.atividades.*;
import src.model.config.*;
import src.view.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.time.DayOfWeek;

public class Studify {
    public static void main(String[] args) {
        JFrame janela = new JFrame("Studify");
        janela.setSize(900, 500);
        JPanel painel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        painel.setLayout(cardLayout);
        
        ConfiguracaoAgenda configuracaoAgenda = new ConfiguracaoAgenda();
        
        TelaInicial telaInicial = new TelaInicial(painel, cardLayout);
        painel.add(telaInicial.getPainelInicial(), "PainelInicial");
        
        //segunda tela
        TelaRegistrarSemana telaRegistrarSemana = new TelaRegistrarSemana(painel, cardLayout, configuracaoAgenda);
        painel.add(telaRegistrarSemana.getPainelRegistrarSemana(), "PainelRegistrarSemana");
        
        TelaRegistrarAtividade telaRegistrarAtividade = new TelaRegistrarAtividade(painel, cardLayout);
        
        TelaRegistrarTimeSlot[] telaRegistrarTimeSlot = new TelaRegistrarTimeSlot[7];
        for (int i = 0; i < 7; i++) {
            telaRegistrarTimeSlot[i] = new TelaRegistrarTimeSlot(painel, cardLayout, DayOfWeek.of(i + 1), configuracaoAgenda);
            painel.add(telaRegistrarTimeSlot[i].getPainelRegistrarTimeSlot(), "TelaRegistrarTimeSlot" + i);
        }
        
        telaRegistrarSemana.setTelaRegistrarTimeSlot(telaRegistrarTimeSlot);
        
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
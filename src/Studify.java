package src;
import src.model.entities.*;
import src.controller.comunicacao.ConDadosEntreTelas;
import src.model.atividades.*;
import src.model.config.*;
import src.view.*;

import com.formdev.flatlaf.FlatLightLaf;
// Temas IntelliJ disponíveis:
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
// import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
// import com.formdev.flatlaf.intellijthemes.FlatGradiantoDarkFuchsiaIJTheme;
// import com.formdev.flatlaf.intellijthemes.FlatMaterialDesignDarkIJTheme;
// import com.formdev.flatlaf.intellijthemes.FlatMonocaiIJTheme;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.time.DayOfWeek;

public class Studify {
    public static void main(String[] args) {
        // Configurar tema - descomente um dos temas abaixo:
        try {
            // TEMA ATUAL: Arc Orange (laranja moderno)
            FlatArcOrangeIJTheme.setup();
            
            // OUTROS TEMAS DISPONÍVEIS (comente Arc Orange e descomente um destes):
            // FlatLightLaf.setup();                    // FlatLaf Light padrão
            // FlatDarkPurpleIJTheme.setup();          // Roxo escuro
            // FlatGradiantoDarkFuchsiaIJTheme.setup(); // Rosa/roxo gradiente
            // FlatMaterialDesignDarkIJTheme.setup();   // Material Design escuro
            // FlatMonocaiIJTheme.setup();             // Monokai clássico
            
        } catch (Exception e) {
            FlatLightLaf.setup(); // fallback
        }
        
        // Garantir execução na thread do Swing
        javax.swing.SwingUtilities.invokeLater(() -> {
            ConDadosEntreTelas comunicacao = new ConDadosEntreTelas();
            Aluno aluno = new Aluno();
            comunicacao.setAluno(aluno);
            comunicacao.setConfiguracaoAgenda(new ConfiguracaoAgenda());
            
            JFrame janela = new JFrame("Studify");
            janela.setSize(900, 500);
            janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            janela.setLocationRelativeTo(null); // Centralizar na tela
            
            JPanel painel = new JPanel();
            CardLayout cardLayout = new CardLayout();
            comunicacao.setPainelPrincipal(painel);
            comunicacao.setLayoutPrincipal(cardLayout);
            painel.setLayout(cardLayout);

            TelaAgenda painelAgenda = new TelaAgenda();     
            TelaInicial telaInicial = new TelaInicial(painel, cardLayout);
            TelaRegistrarSemana telaRegistrarSemana = new TelaRegistrarSemana(painel, cardLayout, comunicacao);
            TelaRegistrarAtividade telaRegistrarAtividade = new TelaRegistrarAtividade(painel, cardLayout, aluno, painelAgenda, comunicacao);
            TelaRegistrarTimeSlot[] telaRegistrarTimeSlot = new TelaRegistrarTimeSlot[7];
            
            comunicacao.setTelaRegistrarSemana(telaRegistrarSemana);
            comunicacao.setTelaRegistrarAtividade(telaRegistrarAtividade);
            comunicacao.setTelaAgenda(painelAgenda);        

            painel.add(telaInicial.getPainelInicial(), "PainelInicial");
            painel.add(telaRegistrarSemana.getPainelRegistrarSemana(), "PainelRegistrarSemana");        
            for (int i = 0; i < 7; i++) {
                telaRegistrarTimeSlot[i] = new TelaRegistrarTimeSlot(painel, cardLayout, DayOfWeek.of(i + 1), comunicacao);
                painel.add(telaRegistrarTimeSlot[i].getPainelRegistrarTimeSlot(), "TelaRegistrarTimeSlot" + i);
            }
            telaRegistrarSemana.setTelaRegistrarTimeSlot(telaRegistrarTimeSlot);
            painel.add(telaRegistrarAtividade.getPainelRegistrarAtividade(), "PainelRegistrarAtividade");
            //temporário enquanto não faz a classe da tela de agenda
            painel.add(painelAgenda.getPainelInicial(), "PainelAgenda");    
            janela.add(painel);
            janela.setVisible(true);
            janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}
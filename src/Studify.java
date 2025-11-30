package src;
import src.model.entities.*;
import src.model.atividades.*;
import src.model.config.*;
import src.view.*;

import com.formdev.flatlaf.FlatLaf;
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
            JFrame janela = new JFrame("Studify");
            janela.setSize(900, 500);
            janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            janela.setLocationRelativeTo(null); // Centralizar na tela
            
            JPanel painel = new JPanel();
            CardLayout cardLayout = new CardLayout();
            painel.setLayout(cardLayout);
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.inicializaTelaInicial();
        telaInicial.transicaoParaTelaRegistrarSemana(painel, cardLayout);
        painel.add(telaInicial.getPainelInicial(), "PainelInicial");
        //segunda tela
        TelaRegistrarSemana telaRegistrarSemana = new TelaRegistrarSemana();
        telaRegistrarSemana.inicializaTelaRegistrarSemana(painel, cardLayout);
        painel.add(telaRegistrarSemana.getPainelRegistrarSemana(), "PainelRegistrarSemana");
        //temporário enquanto não faz a tela de calendário e dos time slots
        JPanel painelAgenda = new JPanel();
        painelAgenda.setSize(900, 500);
        for (int i = 0; i < 7; i++) {
            JPanel[] paineisDiasSemana = new JPanel[7];
            paineisDiasSemana[i] = new JPanel();
            paineisDiasSemana[i].setSize(900, 500);
            paineisDiasSemana[i].setLayout(null);
            painel.add(paineisDiasSemana[i], "Registrar" + i + "PainelTimeSlot");
        }
        painel.add(painelAgenda, "PainelAgenda");
        janela.add(painel);
        janela.setVisible(true);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}

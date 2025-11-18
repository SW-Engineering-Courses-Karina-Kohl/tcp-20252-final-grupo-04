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
    }
}

package src.view;
import src.model.entities.*;
import src.model.atividades.*;
import src.model.config.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.CardLayout;
import org.tinylog.Logger;

public class TelaInicial {
    private JPanel painelInicial;
    private JLabel tituloAplicacao;
    private JButton botaoInicial;

    public JButton getBotaoInicial() {
        return botaoInicial;
    }
    public void setBotaoInicial() {
        this.botaoInicial = new JButton("Gerar Agenda");
        this.botaoInicial.setSize(300, 150);
        this.botaoInicial.setLocation(300, 250);
        this.botaoInicial.setFont(new Font("Arial", Font.PLAIN, 28));
    }
    public JLabel getTituloAplicacao() {
        return tituloAplicacao;
    }
    public void setTituloAplicacao() {
        this.tituloAplicacao = new JLabel("Studify");
        this.tituloAplicacao.setSize(200, 100);
        this.tituloAplicacao.setLocation(380, 70);
        this.tituloAplicacao.setFont(new Font("Times New Roman", Font.BOLD, 48));
    }
    public JPanel getPainelInicial() {
        return painelInicial;
    }
    public void setPainelInicial() {
        this.painelInicial = new JPanel();
        this.painelInicial.setLayout(null);
        this.painelInicial.setSize(900, 500);
    }
    public void transicaoParaTelaRegistrarSemana(JPanel painel, CardLayout cardLayout) {
        botaoInicial.addActionListener(e -> {
            Logger.info("Programa iniciado!");
            cardLayout.show(painel, "PainelRegistrarSemana");
        });
    }
    public void inicializaTelaInicial() {
        setTituloAplicacao();
        setBotaoInicial();
        setPainelInicial();
        this.painelInicial.add(this.tituloAplicacao);
        this.painelInicial.add(this.botaoInicial);
    }
}
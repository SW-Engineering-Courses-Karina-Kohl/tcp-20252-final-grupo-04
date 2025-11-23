package src.view;
import src.model.entities.*;
import src.model.atividades.*;
import src.model.config.*;
import java.awt.CardLayout;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaRegistrarTimeSlot {
    private JPanel painelRegistrarTimeSlot;
    private JCheckBox[] timeSlotsHoras;
    private JButton botaoSalvarTimeSlots;
    private JLabel infoTimeSlots;
    private List<String> timeSlotsSelecionados;

    public void setTelaRegistrarTimeSlot() {
        painelRegistrarTimeSlot = new JPanel();
        painelRegistrarTimeSlot.setSize(900, 500);
        painelRegistrarTimeSlot.setLayout(null);
    }
    public JPanel getPainelRegistrarTimeSlot() {
        return painelRegistrarTimeSlot;
    }
    public JCheckBox[] getTimeSlotsHoras() {
        return timeSlotsHoras;
    }
    public void setTimeSlotsHoras() {
        timeSlotsHoras = new JCheckBox[48];
        int posicaoY = 50;
        String minutos;
        for (int i = 0; i < 48; i++) {
            if(i % 2 == 0) {
                minutos = ":00";
            } else {
                minutos = ":30";
            }
            timeSlotsHoras[i] = new JCheckBox((i/2) + minutos);
            timeSlotsHoras[i].setBounds(50 + 200 * (i / 12), posicaoY, 100, 20);
            painelRegistrarTimeSlot.add(timeSlotsHoras[i]);
            if(i % 12 == 11) {
                posicaoY = 50;
            } else {
                posicaoY += 30;
            }
        }
    }
    public JButton getBotaoSalvarTimeSlots() {
        return botaoSalvarTimeSlots;
    }
    public void setBotaoSalvarTimeSlots() {
        botaoSalvarTimeSlots = new JButton("Salvar Time Slots");
        botaoSalvarTimeSlots.setSize(200, 50);
        botaoSalvarTimeSlots.setLocation(680, 410);
    }
    public void transicaoTelaRegistrarSemana(JPanel painelPrincipal, CardLayout cardLayout) {
        this.timeSlotsSelecionados = new ArrayList<>();
        botaoSalvarTimeSlots.addActionListener(e -> {
            for (JCheckBox timeSlot : timeSlotsHoras) {
                if (timeSlot.isSelected()) {
                    if (!timeSlotsSelecionados.contains(timeSlot.getText())) {
                        timeSlotsSelecionados.add(timeSlot.getText());
                    }
                } else {
                    timeSlotsSelecionados.remove(timeSlot.getText());
                }
            }
            System.out.println("Time Slots Selecionados: " + timeSlotsSelecionados);
            cardLayout.show(painelPrincipal, "PainelRegistrarSemana");
        });
    }
    public JLabel getInfoTimeSlots() {
        return infoTimeSlots;
    }
    public void setInfoTimeSlots() {
        this.infoTimeSlots = new JLabel("Selecione os time slots disponíveis para estudo neste dia (considere uma semana padrão):");
        this.infoTimeSlots.setSize(600, 30);
        this.infoTimeSlots.setLocation(50, 10);
    }
    public List<String> getTimeSlotsSelecionados() {
        return timeSlotsSelecionados;
    }
    public TelaRegistrarTimeSlot(JPanel painelPrincipal, CardLayout cardLayout) {
        setTelaRegistrarTimeSlot();
        setTimeSlotsHoras();
        setBotaoSalvarTimeSlots();
        transicaoTelaRegistrarSemana(painelPrincipal, cardLayout);
        this.painelRegistrarTimeSlot.add(botaoSalvarTimeSlots);
        setInfoTimeSlots();
        this.painelRegistrarTimeSlot.add(this.infoTimeSlots);
    }
    public TelaRegistrarTimeSlot() {

    }
}

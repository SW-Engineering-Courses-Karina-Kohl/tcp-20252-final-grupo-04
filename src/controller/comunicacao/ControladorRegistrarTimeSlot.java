package src.controller.comunicacao;
import src.model.entities.*;
import src.model.config.*;

import java.util.List;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;

public class ControladorRegistrarTimeSlot {
    private List<String> timeSlots;
    private DiaSemana timeSlotDiaSemana;
    private ConfiguracaoAgenda configuracaoAgenda;

    public ControladorRegistrarTimeSlot(List<String> timeSlots, ConfiguracaoAgenda configuracaoAgenda) {
        this.timeSlots = timeSlots;
        this.configuracaoAgenda = configuracaoAgenda;
    }
    public void converteTimeSlots(DayOfWeek diaSemana) {
        this.timeSlotDiaSemana = new DiaSemana(diaSemana);
        for (String timeSlotString : this.timeSlots) {
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("H:mm")
                    .withResolverStyle(ResolverStyle.STRICT);
            LocalTime dataHora = LocalTime.parse(timeSlotString, formatador);
            this.timeSlotDiaSemana.adicionarTimeSlot(dataHora);
        }
        // DiaSemana adicionados em ConfiguracaoAgenda
        if (this.configuracaoAgenda != null) {
            this.configuracaoAgenda.setDia(diaSemana, this.timeSlotDiaSemana);
        }
    }

    public ConfiguracaoAgenda getConfiguracaoAgenda() {
        return this.configuracaoAgenda;
    }
    public DiaSemana getTimeSlotDiaSemana() {
        return this.timeSlotDiaSemana;
    }
}
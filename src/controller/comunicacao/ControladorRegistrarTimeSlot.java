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

    public ControladorRegistrarTimeSlot(List<String> timeSlots) {
        this.timeSlots = timeSlots;
    }
    public void converteTimeSlots(DayOfWeek diaSemana) {
        for (String timeSlotString : this.timeSlots) {
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("HH:mm")
                    .withResolverStyle(ResolverStyle.STRICT);
            LocalTime dataHora = LocalTime.parse(timeSlotString, formatador);
            this.timeSlotDiaSemana = new DiaSemana(diaSemana);
            this.timeSlotDiaSemana.adicionarTimeSlot(dataHora);
        }

    }
    public DiaSemana getTimeSlotDiaSemana() {
        return this.timeSlotDiaSemana;
    }
}
//Precisa ser conectado com ConfiguracaoAgenda da TelaRegistrarSemana
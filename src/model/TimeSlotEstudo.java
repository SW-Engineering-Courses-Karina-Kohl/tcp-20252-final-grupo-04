package src.model;
import src.model.atividades.Atividade;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.Duration;

public class TimeSlotEstudo {
    private static final Duration _DURACAO_PADRAO = Duration.ofHours(2);
    private final LocalDateTime inicioEstudo;
    private Atividade atividade;
    private boolean disponivel;

    public TimeSlotEstudo(LocalDateTime inicioEstudo, Atividade atividade) {
        this.inicioEstudo = inicioEstudo;
        this.atividade = atividade;
        this.disponivel = true;
    }

    public TimeSlotEstudo(LocalDate data, LocalTime hora) {
        this.inicioEstudo = LocalDateTime.of(data, hora);
        this.atividade = null;
        this.disponivel = true;
    }

    public LocalDateTime getInicioEstudo() {
        return this.inicioEstudo;
    }

    public LocalDate getData() {
        return this.inicioEstudo.toLocalDate();
    }

    public LocalTime getHora() {
        return this.inicioEstudo.toLocalTime();
    }

    public Atividade getAtividade() {
        return this.atividade;
    }

    public boolean isDisponivel() {
        return this.disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public boolean atividadeValida(Atividade atividade) {
        // Implementar validação específica
        return atividade != null;
    }

    public boolean conflitaComImpedimento(Impedimento impedimento) {
        LocalDateTime fimEstudo = this.inicioEstudo.plus(_DURACAO_PADRAO);
        LocalDateTime inicioImpedimento = impedimento.getDataHora();
        
        // Verifica se o impedimento está dentro do período de estudo
        return inicioImpedimento.isAfter(this.inicioEstudo.minusMinutes(1)) && 
               inicioImpedimento.isBefore(fimEstudo.plusMinutes(1));
    }
}

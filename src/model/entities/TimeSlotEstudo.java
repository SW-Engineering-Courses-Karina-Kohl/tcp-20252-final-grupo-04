package src.model.entities;
import src.model.atividades.Atividade;
import src.model.config.Impedimento;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.Duration;

public class TimeSlotEstudo {
    private static final Duration _DURACAO_PADRAO = Duration.ofMinutes(30);
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
        if(atividade == null) {
            return false;
        }else {
            return (atividade.getDataLimite().isAfter(this.inicioEstudo.toLocalDate()));
        }
    }

    public boolean conflitaComImpedimento(Impedimento impedimento) {
        return this.inicioEstudo.equals(impedimento.getDataHora());
    }
}

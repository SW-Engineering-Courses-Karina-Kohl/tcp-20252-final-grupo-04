import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

public class TimeSlotEstudo {
    public static final Duration DURACAO_PADRAO = Duration.ofMinutes(30);
    private final LocalDateTime inicioEstudo;
    private final Atividade atividade;
    private boolean disponivel;

    // Construtores

    public TimeSlotEstudo(LocalDateTime inicioEstudo, Atividade atividade) {
        this.inicioEstudo = inicioEstudo;
        this.atividade = atividade;
        this.disponivel = false;
    }

    public TimeSlotEstudo(LocalDateTime inicioEstudo) {
        this.inicioEstudo = inicioEstudo;
        this.atividade = null;
        this.disponivel = true;
    }

    public TimeSlotEstudo(LocalDate data, LocalTime hora) {
        this.inicioEstudo = LocalDateTime.of(data, hora);
        this.atividade = null;
        this.disponivel = true;
    }

    // Getters e Setters

    public LocalDateTime getInicioEstudo() {
        return inicioEstudo;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public LocalDate getData() {
        return inicioEstudo.toLocalDate();
    }

    public LocalTime getHora() {
        return inicioEstudo.toLocalTime();
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public Duration getDuracao() {
        return DURACAO_PADRAO;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void setAtividade(Atividade atividade) {
         this.atividade = atividade; 
    }

    public void setInicioEstudo(LocalDateTime inicioEstudo) {
        this.inicioEstudo = inicioEstudo;
    }

    // Outros métodos

    public boolean atividadeValida(Atividade atividade) {
        return atividade != null;
    }

    // tem uma função parecida em impedimento, rever depois
    public boolean conflitaComImpedimento(Impedimento impedimento) {
        return inicioEstudo.isEqual(impedimento.getDataHora());
    }

}

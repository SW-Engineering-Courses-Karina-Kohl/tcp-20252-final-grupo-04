import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

public class TimeSlotEstudo {
    public static final Duration DURACAO_PADRAO = Duration.ofMinutes(30);
    private final LocalDateTime inicioEstudo;
    private final Atividade atividade;
    private final TimeSlot slotOrigem;
    private boolean disponivel;

    // Construtores

    public TimeSlotEstudo(LocalDateTime inicioEstudo, Atividade atividade, TimeSlot slotOrigem) {
        this.inicioEstudo = inicioEstudo;
        this.atividade = atividade;
        this.slotOrigem = slotOrigem;
        this.disponivel = false;
    }

    public TimeSlotEstudo(LocalDateTime inicioEstudo, TimeSlot slotOrigem) {
        this.inicioEstudo = inicioEstudo;
        this.atividade = null;
        this.slotOrigem = slotOrigem;
        this.disponivel = true;
    }

    public TimeSlotEstudo(LocalDate data, LocalTime hora) {
        this.inicioEstudo = LocalDateTime.of(data, hora);
        this.atividade = null;
        this.slotOrigem = null;
        this.disponivel = true;
    }

    public TimeSlotEstudo(LocalDate data, TimeSlot timeSlot) {
        this.inicioEstudo = LocalDateTime.of(data, timeSlot.getHoraInicio());
        this.atividade = null;
        this.slotOrigem = timeSlot;
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

    public LocalTime getHoraInicio() {
        return inicioEstudo.toLocalTime();
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public TimeSlot getSlotOrigem() {
        return slotOrigem;
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

    public void setSlotOrigem(TimeSlot slotOrigem) {
        this.slotOrigem = slotOrigem;
    }

    public void setInicioEstudo(LocalDateTime inicioEstudo) {
        this.inicioEstudo = inicioEstudo;
    }

    // Outros métodos

    public boolean isAtividadeValida(Atividade atividade) {
        return atividade != null;
    }

    // tem uma função parecida em impedimento, rever depois
    public boolean conflitaComImpedimento(Impedimento impedimento) {
        return inicioEstudo.isEqual(impedimento.getDataHora());
    }

}
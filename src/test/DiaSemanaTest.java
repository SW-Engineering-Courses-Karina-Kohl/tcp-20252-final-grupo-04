package src.test;
import src.model.config.*;
import org.junit.Test;
import java.time.LocalTime;
import java.time.DayOfWeek;

public class DiaSemanaTest {
    @Test
    public void testCriarDiaSemana() {
        DiaSemana diaSemana = new DiaSemana(DayOfWeek.MONDAY);
        assert(diaSemana.getDiaSemana() == DayOfWeek.MONDAY);
        assert(diaSemana.getHorarios().isEmpty());
    }

    @Test
    public void testAdicionarTimeSlot() {
        DiaSemana diaSemana = new DiaSemana(DayOfWeek.TUESDAY);
        diaSemana.adicionarTimeSlot(LocalTime.of(9, 0));
        diaSemana.adicionarTimeSlot(LocalTime.of(10, 0));
        assert(diaSemana.getHorarios().size() == 2);
        assert(diaSemana.getHorarios().get(0).equals(LocalTime.of(9, 0)));
        assert(diaSemana.getHorarios().get(1).equals(LocalTime.of(10, 0)));
    }

}

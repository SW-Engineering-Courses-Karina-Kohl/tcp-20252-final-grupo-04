package src.test;
import src.model.atividades.Prova;
import java.util.*;
import static org.junit.Assert.*;


import org.junit.Test;
// OBS: Como a maioria dos métodos em Prova são herdados de Atividade e não foram modificados,
// estes testes englobam os testes dos métodos herdados também, e nos seguintes apenas serão testados
// os métodos específicos de Trabalho e Exercício.
public class ProvaTest {
    @Test
    public void testCreateProva() {
        String name = "Prova de Matemática";
        Prova prova = new Prova(name, 1, java.time.LocalDate.of(2023, 10, 5), null);
        assertEquals(name, prova.getNome());
    }

    @Test
    public void testGetTotal() {
        ArrayList<Prova> provas = new ArrayList<>();
        Prova prova1 = new Prova("Prova 1", 1, java.time.LocalDate.of(2023, 10, 5), null);
        Prova prova2 = new Prova("Prova 2", 1, java.time.LocalDate.of(2023, 11, 5), null);
        provas.add(prova1);
        provas.add(prova2); 
        assertEquals(provas.size(), prova1.getTotal());
    }
    @Test
    public void testNameEmpty() {
        
        assertThrows(new Prova("", 1, java.time.LocalDate.of(2023, 10, 5), null), IllegalArgumentException.class);
    }
}

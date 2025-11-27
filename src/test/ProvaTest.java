package src.test;
import src.model.atividades.Prova;
import src.model.entities.Disciplina;

import java.util.*;
import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Test;
import src.model.entities.Disciplina;

// OBS: Como a maioria dos métodos em Prova são herdados de Atividade e não foram modificados,
// estes testes englobam os testes dos métodos herdados também, e nos seguintes apenas serão testados
// os métodos específicos de Trabalho e Exercício.
public class ProvaTest {
    @Test
    public void testCreateProva() {
        String name = "Prova de Matemática";
        Disciplina TCP = new Disciplina("TCP", 3.0);
        Prova prova = new Prova(name, java.time.LocalDate.of(2023, 10, 5), TCP);
        assertEquals(name, prova.getNome());
    }

    @Test
    public void testGetTotal() {
        ArrayList<Prova> provas = new ArrayList<>();
        Disciplina TCP = new Disciplina("TCP", 3.0);
        Prova prova1 = new Prova("Prova 1",  java.time.LocalDate.of(2023, 10, 5), TCP);
        Prova prova2 = new Prova("Prova 2", java.time.LocalDate.of(2023, 11, 5), TCP);
        provas.add(prova1);
        provas.add(prova2); 
        assertEquals(provas.size(), prova1.getTotal());
    }

    @Test
    public void testNameEmpty() {
        Disciplina ing =  new Disciplina("Test", 1);
        IllegalArgumentException thrown =  assertThrows(IllegalArgumentException.class, () -> {new Prova("", LocalDate.now(), ing);}); 
        assertEquals(thrown.getMessage(), "O nome de uma atividade não deve ser vazio!");
        IllegalArgumentException thrownNull =  assertThrows(IllegalArgumentException.class, () -> {new Prova(null, LocalDate.now(), ing);}); 
        assertEquals(thrownNull.getMessage(), "O nome de uma atividade não deve ser vazio!");
    }

    @Test
    public void testPriorityLessThanOne(){
        Disciplina ing =  new Disciplina("Test", 1);
        try {
            new Prova("Test", LocalDate.now(), ing);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "A atividade deve possuir um grau de prioridade entre 1 e 5.");
        }

    }

    @Test
    public void testPriorityBiggerThanFive(){
        Disciplina ing =  new Disciplina("Test", 7);
        try {
            new Prova("Test", LocalDate.now(), ing);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "A atividade deve possuir um grau de prioridade entre 1 e 5.");
        }
    }

   
}

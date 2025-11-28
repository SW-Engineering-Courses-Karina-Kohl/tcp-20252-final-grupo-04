package src.controller.comunicacao;
import src.model.entities.*;
import src.model.config.*;
import src.model.atividades.*;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class ControladorRegistrarAtividade {
    private List<String> atividadeNomes;
    private List<String> atividadeDatas;
    private List<String> tipoAtividades;
    private List<String> disciplinasAtividades;
    private List<String> todasDisciplinas;
    private List<String> prioridadesDisciplinas;
    private List<Disciplina> disciplinasRegistradas;
    private List<Atividade> atividadesRegistradas;

    public ControladorRegistrarAtividade(List<String> atividadeNomes, List<String> atividadeDatas,
            List<String> tipoAtividades, List<String> disciplinasAtividades, List<String> prioridadesDisciplinas) {
        this.atividadeNomes = atividadeNomes;
        this.atividadeDatas = atividadeDatas;
        this.tipoAtividades = tipoAtividades;
        this.disciplinasAtividades = disciplinasAtividades;
        this.prioridadesDisciplinas = prioridadesDisciplinas;
    }
    public void processaRegistroAtividades() {
        for (int i = 0; i < atividadeNomes.size(); i++) {
            String nome = atividadeNomes.get(i);
            String data = atividadeDatas.get(i);
            String tipo = tipoAtividades.get(i);
            String disciplina = disciplinasAtividades.get(i);
            LocalDate dataLimite = LocalDate.parse(data);
            Disciplina disciplinaObj = new Disciplina(disciplina, 1.0); // Placeholder

            switch (tipo) {
                case "Exercício":
                    Exercicio exercicio = new Exercicio(nome, dataLimite, disciplinaObj);
                    this.atividadesRegistradas.add(exercicio);
                    break;
                case "Prova":
                    Prova prova = new Prova(nome, dataLimite, disciplinaObj);
                    this.atividadesRegistradas.add(prova);
                    break;
                case "Trabalho":
                    Trabalho trabalho = new Trabalho(nome, dataLimite, disciplinaObj);
                    this.atividadesRegistradas.add(trabalho);
                    break;
                default:
                    break;
            }
        }
    }
    public void processaRegistroDisciplinas() {
        for (int i = 0; i < disciplinasAtividades.size(); i++) {
            String disciplina = todasDisciplinas.get(i);
            String prioridade = prioridadesDisciplinas.get(i);
            Disciplina novaDisciplina = new Disciplina(disciplina, Double.parseDouble(prioridade));
            disciplinasRegistradas.add(novaDisciplina);
        }
    }
}
//Necessário utilizar na classe TelaRegistrarAtividade.java
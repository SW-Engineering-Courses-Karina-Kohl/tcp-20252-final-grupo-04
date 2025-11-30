package src.controller.comunicacao;
import src.model.entities.*;
import src.model.config.*;
import src.model.atividades.*;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.HashMap;

public class ControladorRegistrarAtividade {
    private List<String> atividadeNomes;
    private List<String> atividadeDatas;
    private List<String> tipoAtividades;
    private List<String> disciplinasAtividades;
    private List<String> todasDisciplinas;
    private List<String> prioridadesDisciplinas;
    private List<Disciplina> disciplinasRegistradas;
    private List<Atividade> atividadesRegistradas;
    private Map<String, Disciplina> disciplinaMap;
    private ConDadosEntreTelas comunicacao;
    private Aluno aluno;
    public ControladorRegistrarAtividade(List<String> atividadeNomes, List<String> atividadeDatas,
            List<String> tipoAtividades, List<String> disciplinasAtividades, List<String> prioridadesDisciplinas, List<String> todasDisciplinas, ConDadosEntreTelas comunicacao) {
        this.atividadeNomes = atividadeNomes;
        this.atividadeDatas = atividadeDatas;
        this.tipoAtividades = tipoAtividades;
        this.disciplinasAtividades = disciplinasAtividades;
        this.prioridadesDisciplinas = prioridadesDisciplinas;
        this.todasDisciplinas = todasDisciplinas;
        this.atividadesRegistradas = new ArrayList<>();
        this.disciplinasRegistradas = new ArrayList<>();
        this.comunicacao = comunicacao;
        this.aluno = comunicacao.getAluno();
    }
    public void processaRegistroAtividades() {
        if (atividadeNomes.isEmpty() || atividadeNomes.size() != atividadeDatas.size() ||
            atividadeNomes.size() != tipoAtividades.size() || 
            atividadeNomes.size() != disciplinasAtividades.size()) {
            System.err.println("Erro: Listas de atividades têm tamanhos diferentes ou estão vazias.");
            return;
        }
        
        for (int i = 0; i < atividadeNomes.size(); i++) {
            String nome = atividadeNomes.get(i);
            String data = atividadeDatas.get(i);
            String tipo = tipoAtividades.get(i);
            String disciplina = disciplinasAtividades.get(i);
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/uuuu");
            LocalDate dataLimite = LocalDate.parse(data, formatador);
            if(dataLimite.isEqual(comunicacao.getConfiguracaoAgenda().getDataInicioVigencia()))
            {
                throw new IllegalArgumentException("Data limite da atividade não pode ser igual à data de início de vigência da agenda.");
            }
            Disciplina disciplinaObjeto = this.disciplinaMap.get(disciplina);

            switch (tipo) {
                case "Exercício":
                    Exercicio exercicio = new Exercicio(nome, dataLimite, disciplinaObjeto);
                    this.atividadesRegistradas.add(exercicio);
                    break;
                case "Prova":
                    Prova prova = new Prova(nome, dataLimite, disciplinaObjeto);
                    this.atividadesRegistradas.add(prova);
                    break;
                case "Trabalho":
                    Trabalho trabalho = new Trabalho(nome, dataLimite, disciplinaObjeto);
                    this.atividadesRegistradas.add(trabalho);
                    break;
                default:
                    break;
            }
            this.disciplinaMap.get(disciplina).adicionarAtividade(this.atividadesRegistradas.get(i));
        }
    }
    public void processaRegistroDisciplinas() {
        this.disciplinaMap = new HashMap<>();
        for (int i = 0; i < todasDisciplinas.size(); i++) {
            String disciplina = todasDisciplinas.get(i);
            String prioridade = prioridadesDisciplinas.get(i);
            Disciplina novaDisciplina = new Disciplina(disciplina, Double.parseDouble(prioridade));
            disciplinaMap.put(disciplina, novaDisciplina);
        }
    }
    public void converteDisciplinaAtividades() {
        processaRegistroDisciplinas();
        processaRegistroAtividades();
    }

    public Aluno getAluno() {
        return this.aluno;
    }

    //função que pega todas as atividades registradas e armazena em um Aluno{
    public void AdicionaAtividadesAluno() {
        for (Disciplina disciplina : disciplinaMap.values()) {
            aluno.adicionarDisciplina(disciplina);
        }
        comunicacao.setAluno(aluno);
    }
}
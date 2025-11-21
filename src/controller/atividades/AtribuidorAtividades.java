package src.controller.atividades;
import src.model.entities.*;
import src.model.atividades.*;
import src.model.allocation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.ArrayList;

public class AtribuidorAtividades 
{
    private List<AlocacaoAtividade> alocacoes;
    
    public void atribuir(AgendaEstudos agenda, List<Disciplina> disciplinas)
    {
        if(agenda == null)
        {
            throw new IllegalArgumentException("Agenda não pode ser nula");
        }
        if(disciplinas == null || disciplinas.isEmpty())
        {
            throw new IllegalArgumentException("Lista de disciplinas não pode ser nula ou vazia");
        }

        // Lógica para atribuir atividades às TimeSlotEstudo na agenda
        List<Atividade> atividades = this.juntarAtividadesDeDisciplinas(disciplinas);
        atividades.sort((a1, a2) -> a1.getDataLimite().compareTo(a2.getDataLimite()));

        List<TimeSlotEstudo> timeSlotsDisponiveis = new ArrayList<>(agenda.getEstudos());
        CalculadoraPesoAtividades calculadoraPeso = new CalculadoraPesoAtividades();
        calculadoraPeso.calcularPeso(atividades, timeSlotsDisponiveis);

        
        double somaPesosCalculados = 0.0;
        for(Atividade atividade : atividades)
        {
            somaPesosCalculados += atividade.getPesoCalculado();
            alocacoes.add(new AlocacaoAtividade(atividade));
        }
        // De i = 0 até atividades.size() - 1
        for(int i = 0; i < atividades.size(); i++)
        {
            // Haverá atividades.size() - i atividades a serem atribuídas: i até atividades.size() - 1
            // Calcular a porcentagem de timeSlot's que cada atividade a ser atribuído deve receber
            // Somar pesoCalculado das atividades de i até atividades.size() - 1: para evitar repetição desnecessária somaPesosCalculados pode ser decrementada a cada iteração
            somaPesosCalculados -= atividades.get(i).getPesoCalculado();    
            //Para cada atividade de i até atividades.size() - 1
            int quantidadeTimeSlotsJanela = 0;
            for(int j = i; j < atividades.size(); j++)
            {
                AlocacaoAtividade alocacao = alocacoes.get(j);
                //Calcular porcentagem = pesoCalculado / somaPesoCalculado
                alocacao.setPorcentagemTimeSlotEstudos(alocacao.getAtividade().getPesoCalculado() / somaPesosCalculados);

                //Calcular número de timeSlots a serem atribuídos para a atividade = porcentagem * quantidade de TimeSlotEstudo's da janela
                quantidadeTimeSlotsJanela = this.quantidadeTimeSlotEstudosAntesDe(alocacao.getAtividade().getDataLimite(), timeSlotsDisponiveis);
                alocacao.setQuantidadeTimeSlotEstudos(alocacao.getPorcentagemTimeSlotEstudos() * quantidadeTimeSlotsJanela);
            }
            //Arredondar quantidade de timeSlots para cada atividade
            arredondarQuantidadeTimeSlots(alocacoes, quantidadeTimeSlotsJanela);

            //Os timeSlotEstudo disponíveis na agenda são aqueles que ainda não possuem atividade atribuída e cuja data é anterior à data de entrega da atividade
            //Usar um distribuidor para distribuir as atividades conforme a quantidade arredondada
            DistribuidorAtividades distribuidor = new DistribuidorAtividades();
            //Usar quantidadeTimeSlotsJanela para saber até qual índice da lista de timeSlotsDisponiveis deve ser considerado
            distribuidor.distribuir(alocacoes, timeSlotsDisponiveis.subList(0, quantidadeTimeSlotsJanela));
            //Remover da lista de Alocacoes a primeira alocacao, que é referente a atividade i, que já foi atribuída
            alocacoes.remove(0);
            //Remover da lista de timeSlotsDisponiveis os timeSlots que já foram atribuídos
            timeSlotsDisponiveis = new ArrayList<TimeSlotEstudo>(timeSlotsDisponiveis.subList(quantidadeTimeSlotsJanela, timeSlotsDisponiveis.size()));

        }
        // 
    }

    private List<Atividade> juntarAtividadesDeDisciplinas(List<Disciplina> disciplinas)
    {
        List<Atividade> atividades = new ArrayList<>();
        for(Disciplina disciplina : disciplinas)
        {
            // Adicionar atividades da disciplina à lista
            atividades.addAll(disciplina.getAtividades());
        }
        return atividades;
    }

    public List<AlocacaoAtividade> getAlocacoes() 
    {
        return this.alocacoes;
    }

    public AlocacaoAtividade get(int index) 
    {
        return this.alocacoes.get(index);
    }

    public int quantidadeTimeSlotEstudosAntesDe(LocalDate data, List<TimeSlotEstudo> timeSlotEstudos)
{
    // Pegar todos os timeslots até o final do dia anterior
    LocalDateTime dataLimiteAjustada = data.minusDays(1).atTime(23, 59, 59);

    // Cria um TimeSlotEstudo fictício só para servir como chave
    TimeSlotEstudo chave = new TimeSlotEstudo(dataLimiteAjustada, null);

    int indice = Collections.binarySearch(
        timeSlotEstudos,
        chave,
        Comparator.comparing(TimeSlotEstudo::getInicioEstudo)
    );

    return indice >= 0 ? indice + 1 : -(indice + 1);
}


    public void arredondarQuantidadeTimeSlots(List<AlocacaoAtividade> alocacoes, int totalTimeSlotsJanela)
    {
        //Algoritmo baseado em Hamilton 
        //Arredondar para baixo todas as quantidades de timeSlots
        //Calcular a soma das quantidades arredondadas
        //Calcular o número de timeSlots restantes = totalTimeSlotsJanela - somaArredondada
        //Calcular as diferenças entre as quantidades originais e as arredondadas
        //Ordenar decrescentemente as atividades pela diferença em ordem decrescente
        //Distribuir os timeSlots restantes para as atividades com maiores diferenças, uma unidade por atividade, até que não haja mais timeSlots restantes
        int somaArredondada = 0;
        for(AlocacaoAtividade alocacao : alocacoes)
        {
            int quantidadeArredondada = (int)Math.floor(alocacao.getQuantidadeTimeSlotEstudos());
            alocacao.setQuantidadeTimeSlotEstudosArredondada(quantidadeArredondada);
            somaArredondada += quantidadeArredondada;
        }
        int timeSlotsRestantes = totalTimeSlotsJanela - somaArredondada;
        List<AlocacaoAtividade> alocacoesReversamenteOrdenadasPorDiferenca = new ArrayList<>(alocacoes);
        alocacoesReversamenteOrdenadasPorDiferenca.sort(
        Comparator.comparingDouble((AlocacaoAtividade a) -> a.getQuantidadeTimeSlotEstudos() - a.getQuantidadeTimeSlotEstudosArredondada()).reversed());
        
        for(int i = 0; i < timeSlotsRestantes; i++)
        {
            AlocacaoAtividade alocacao = alocacoesReversamenteOrdenadasPorDiferenca.get(i);
            alocacao.setQuantidadeTimeSlotEstudosArredondada(alocacao.getQuantidadeTimeSlotEstudosArredondada() + 1);
        }
    }
}

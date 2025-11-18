package src;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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

        List<TimeSlotEstudo> timeSlotsDisponiveis = agenda.getEstudos();
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
            //Somar pesoCalculado das atividades de i até atividades.size() - 1: para evitar repetição desnecessária somaPesosCalculados pode ser decrementada a cada iteração
            somaPesosCalculados -= atividades.get(i).getPesoCalculado();    
            //Para cada atividade de i até atividades.size() - 1
            for(int j = i; j < atividades.size(); j++)
            {
                AlocacaoAtividade alocacao = alocacoes.get(j);
                //Calcular porcentagem = pesoCalculado / somaPesoCalculado
                alocacao.setPorcentagemTimeSlotEstudos(alocacao.getAtividade().getPesoCalculado() / somaPesosCalculados);

                //Calcular número de timeSlots a serem atribuídos para a atividade = porcentagem * quantidade de TimeSlotEstudo's da janela
                int quantidadeTimeSlotsJanela = this.quantidadeTimeSlotEstudosAntesDe(alocacao.getAtividade().getDataEntrega(), timeSlotsDisponiveis);
                alocacao.setQuantidadeTimeSlotEstudos(alocacao.getPorcentagemTimeSlotEstudos() * quantidadeTimeSlotsJanela);
            }
            //Arredondar quantidade de timeSlots para cada atividade
            //Os timeSlotEstudo disponíveis na agenda são aqueles que ainda não possuem atividade atribuída e cuja data é anterior à data de entrega da atividade
            //Usar um distribuidor para distribuir as atividades conforme a quantidade arredondada
            //Remover da lista de Alocacoes a primeira alocacao, que é referente a atividade i, que já foi atribuída

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

    public int quantidadeTimeSlotEstudosAntesDe(LocalDate data, List<TimeSlotEstudo> timeSlotEstudo)
    {
        //usar busca binária para encontrar o índice do último timeSlotEstudo cuja data é menor ou igual à data fornecida
        //Ajustar data para incluir todos os timeSlots do dia fornecido, binarySearch encontra o primeiro maior ou igual
        LocalDateTime dataLimiteAjustada = data.plusDays(-1).atTime(23, 59, 59);
        int indice = Collections.binarySearch(timeSlotEstudo, dataLimiteAjustada, (timeSlotEstudo, dataLimiteAjustada) -> 
        timeSlotEstudo.getDataTime().compareTo(dataLimiteAjustada));

        return indice >= 0 ? indice + 1 : -(indice + 1);
    }

    public void arredondarQuantidadeTimeSlots(List<AlocacaoAtividade> alocacoes, int totalTimeSlotsJanela)
    {
        //Algoritmo baseado em Hamilton 
        //Arredondar para baixo todas as quantidades de timeSlots
        //Calcular a soma das quantidades arredondadas
        //Calcular o número de timeSlots restantes = totalTimeSlotsJanela - somaArredondada
        //Calcular as diferenças entre as quantidades originais e as arredondadas
        //Ordenar as atividades pela diferença em ordem decrescente
        int somaArredondada = 0;
        for(AlocacaoAtividade alocacao : alocacoes)
        {
            int quantidadeArredondada = (int)Math.floor(alocacao.getQuantidadeTimeSlotEstudos());
            alocacao.setQuantidadeTimeSlotEstudosArredondada(quantidadeArredondada);
            somaArredondada += quantidadeArredondada;
        }
        int timeSlotsRestantes = totalTimeSlotsJanela - somaArredondada;
        List<AlocacaoAtividade> alocacoesOrdenadasPorDiferenca = new ArrayList<>(alocacoes);
        alocacoesOrdenadasPorDiferenca.sort((a1, a2) -> 
        {
            double diferenca1 = a1.getQuantidadeTimeSlotEstudos() - a1.getQuantidadeTimeSlotEstudosArredondada();
            double diferenca2 = a2.getQuantidadeTimeSlotEstudos() - a2.getQuantidadeTimeSlotEstudosArredondada();
            return Double.compare(diferenca2, diferenca1);
        });
    }
}

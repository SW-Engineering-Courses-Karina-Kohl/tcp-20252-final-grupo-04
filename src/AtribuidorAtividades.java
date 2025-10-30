import java.util.*;

public class AtribuidorAtividades 
{
    
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
        atividades.sort((a1, a2) -> a1.getDataEntrega().compareTo(a2.getDataEntrega()));

        List<TimeSlotEstudo> timeSlotsDisponiveis = agenda.getEstudos();

        // De i = 0 até atividades.size() - 1
        for(int i = 0; i < atividades.size(); i++)
        {
            // Haverá atividades.size() - i atividades a serem atribuídas
            // Calcular a porcentagem de timeSlot's que cada atividade a ser atribuído deve receber
                //Somar pesoCalculado das atividades de i até atividades.size() - 1
                //Para cada atividade de i até atividades.size() - 1
                    //Calcular porcentagem = pesoCalculado / somaPesoCalculado
            //Os timeSlotEstudo disponíveis na agenda são aqueles que ainda não possuem atividade atribuída e cuja data é anterior à data de entrega da atividade
                //Iterar pelos timeSlotEstudo disponíveis e atribuir a atividade conforme a porcentagem calculada
        }
        // 
    }

    private List<Atividade> juntarAtividadesDeDisciplinas(List<Disciplina> disciplinas)
    {
        // Lógica para juntar todas as atividades de todas as disciplinas em uma única lista
        List<Atividade> atividades = new ArrayList<>();
        for(Disciplina disciplina : disciplinas)
        {
            // Adicionar atividades da disciplina à lista
            atividades.addAll(disciplina.getAtividades());
        }
        return atividades;
    }
}

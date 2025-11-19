package src;
public class AlocacaoAtividade {
    private Atividade atividade;
    private double porcentagemTimeSlotEstudos;
    private double quantidadeTimeSlotEstudos;
    private int quantidadeTimeSlotEstudosArredondada;

    public AlocacaoAtividade() 
    {
        this.atividade = null;
        this.porcentagemTimeSlotEstudos = 0.0;
        this.quantidadeTimeSlotEstudos = 0.0;
        this.quantidadeTimeSlotEstudosArredondada = 0;
    }
    public AlocacaoAtividade(Atividade atividade)
    {
        this.atividade = atividade;
        this.porcentagemTimeSlotEstudos = 0.0;
        this.quantidadeTimeSlotEstudos = 0.0;
        this.quantidadeTimeSlotEstudosArredondada = 0;
    
    }

    public Atividade getAtividade() {
        return atividade;
    }
    public double getPorcentagemTimeSlotEstudos() 
    {
        return porcentagemTimeSlotEstudos;
    }
    public double getQuantidadeTimeSlotEstudos() 
    {
        return quantidadeTimeSlotEstudos;
    }
    public int getQuantidadeTimeSlotEstudosArredondada() 
    {
        return quantidadeTimeSlotEstudosArredondada;
    }
     
    public void setAtividade(Atividade atividade) 
    {
        this.atividade = atividade;
    }

    public void setPorcentagemTimeSlotEstudos(double porcentagemTimeSlotEstudos) 
    {
        this.porcentagemTimeSlotEstudos = porcentagemTimeSlotEstudos;
    }

    public void setQuantidadeTimeSlotEstudos(double quantidadeTimeSlotEstudos) 
    {
        this.quantidadeTimeSlotEstudos = quantidadeTimeSlotEstudos;
    }
    public void setQuantidadeTimeSlotEstudosArredondada(int quantidadeTimeSlotEstudosArredondada) 
    {
        this.quantidadeTimeSlotEstudosArredondada = quantidadeTimeSlotEstudosArredondada;
    }

    public void decrementarQuantidadeTimeSlotEstudos() 
    {
        if(this.quantidadeTimeSlotEstudosArredondada > 0)
        {
            this.quantidadeTimeSlotEstudosArredondada--;
        }
    }
}

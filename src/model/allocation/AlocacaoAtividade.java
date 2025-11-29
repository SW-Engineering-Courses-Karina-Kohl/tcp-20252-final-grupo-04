package src.model.allocation;
import src.model.atividades.Atividade;
import org.tinylog.Logger;

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
        if (atividade == null) 
        {
            Logger.error("Erro ao tentar setar Atividade: Atividade nula");
            throw new IllegalArgumentException("Atividade nao pode ser nula");
            
        }
        this.atividade = atividade;
    }

    public void setPorcentagemTimeSlotEstudos(double porcentagemTimeSlotEstudos) 
    {
        if(porcentagemTimeSlotEstudos < 0.0)
        {
            Logger.error("Erro ao tentar setar PorcentagemTimeSlotEstudos: Porcentagem negativa");
            throw new IllegalArgumentException("PorcentagemTimeSlotEstudos nao pode ser negativa");
        }
        this.porcentagemTimeSlotEstudos = porcentagemTimeSlotEstudos;
    }

    public void setQuantidadeTimeSlotEstudos(double quantidadeTimeSlotEstudos) 
    {
        if(quantidadeTimeSlotEstudos < 0.0)
        {
            Logger.error("Erro ao tentar setar QuantidadeTimeSlotEstudos: Quantidade negativa");
            throw new IllegalArgumentException("QuantidadeTimeSlotEstudos nao pode ser negativa");
        }
        this.quantidadeTimeSlotEstudos = quantidadeTimeSlotEstudos;
    }
    public void setQuantidadeTimeSlotEstudosArredondada(int quantidadeTimeSlotEstudosArredondada) 
    {
        if(quantidadeTimeSlotEstudosArredondada < 0)
        {
            Logger.error("Erro ao tentar setar QuantidadeTimeSlotEstudosArredondada: Quantidade negativa");
            throw new IllegalArgumentException("QuantidadeTimeSlotEstudosArredondada nao pode ser negativa");
        }
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

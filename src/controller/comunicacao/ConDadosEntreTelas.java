 package src.controller.comunicacao;

import java.awt.CardLayout;

import javax.swing.JPanel;

import src.model.config.*;
import src.model.entities.Aluno;
import src.view.TelaAgenda;
import src.view.*;



public class ConDadosEntreTelas {
    private TelaRegistrarSemana telaRegistrarSemana;
    private TelaRegistrarAtividade telaRegistrarAtividade;
    private TelaAgenda telaAgenda;
    private Aluno aluno;
    private ConfiguracaoAgenda configuracaoAgenda;
    private JPanel painelPrincipal;
    private CardLayout layoutPrincipal;

    //setters
    public void setTelaRegistrarSemana(TelaRegistrarSemana telaRegistrarSemana) {
        this.telaRegistrarSemana = telaRegistrarSemana;
    }
    public void setPainelPrincipal(JPanel painelPrincipal){
        this.painelPrincipal =  painelPrincipal;
    }
    
    
    public void setLayoutPrincipal(CardLayout layoutPrincipal){
        this.layoutPrincipal =  layoutPrincipal;
    }

    public void setTelaRegistrarAtividade(TelaRegistrarAtividade telaRegistrarAtividade) {
        this.telaRegistrarAtividade = telaRegistrarAtividade;
    }
    public void setTelaAgenda(TelaAgenda telaAgenda) {
        this.telaAgenda = telaAgenda;
    }
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    public void setConfiguracaoAgenda(ConfiguracaoAgenda configuracaoAgenda) {
        this.configuracaoAgenda = configuracaoAgenda;
    }
    //getters
    public TelaRegistrarSemana getTelaRegistrarSemana() {
        return this.telaRegistrarSemana;
    }
    public TelaRegistrarAtividade getTelaRegistrarAtividade() {
        return this.telaRegistrarAtividade;
    }
    public TelaAgenda getTelaAgenda() {
        return this.telaAgenda;
    }
    public Aluno getAluno() {
        return this.aluno;
    }
    public ConfiguracaoAgenda getConfiguracaoAgenda() {
        return this.configuracaoAgenda;
    }

    public  void transicaoAtividadeAgenda() {
        this.telaAgenda.setAluno(this.aluno);
        this.telaAgenda.initPainel(this.aluno);
    }


    public void transicaoTimeSlotSemana() {
        if(this.configuracaoAgenda != null && this.telaRegistrarSemana != null) {
            this.telaRegistrarSemana.setConfiguracaoAgenda(this.configuracaoAgenda);
        }
    }



    public void configuraAgendaAluno() {
        if(this.configuracaoAgenda != null && this.aluno != null) {
            this.aluno.setConfiguracaoAgenda(this.configuracaoAgenda);
        }
    }
    
   
     
}
package src.controller.comunicacao;
import src.model.entities.*;
import src.model.config.*;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.format.DateTimeParseException;
import org.tinylog.Logger;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class ControladorRegistrarSemana {
    private String dataInicioVigencia;
    private String dataFimVigencia;
    private List<String> impedimentos;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private List<Impedimento> listaImpedimentos;
    private ConfiguracaoAgenda configuracaoAgenda;

    public ControladorRegistrarSemana(String dataInicioVigencia, String dataFimVigencia, List<String> impedimentos) {
        this.dataInicioVigencia = dataInicioVigencia;
        this.dataFimVigencia = dataFimVigencia;
        this.impedimentos = impedimentos;
    }
    public void converteDatasVigencia() {
         DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
            this.dataInicio = LocalDate.parse(this.dataInicioVigencia, formatador);
            this.dataFim = LocalDate.parse(this.dataFimVigencia, formatador);
    }
    public void converteImpedimentos() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
                .withResolverStyle(ResolverStyle.STRICT);
        this.listaImpedimentos = new ArrayList<>();
        for (String impedimentoString : this.impedimentos) {
                LocalDateTime dataHora = LocalDateTime.parse(impedimentoString, formatador);
                Impedimento impedimento = new Impedimento(dataHora);
                this.listaImpedimentos.add(impedimento);
        }
    }
    public void criaConfiguracaoAgenda() {
        this.configuracaoAgenda = new ConfiguracaoAgenda(this.dataInicio, this.dataFim);
        for (Impedimento impedimento : this.listaImpedimentos) {
            this.configuracaoAgenda.adicionarImpedimento(impedimento);
        }
    }
    public ConfiguracaoAgenda getConfiguracaoAgenda() {
        return this.configuracaoAgenda;
    }
    public void processaRegistroSemana() {
        converteDatasVigencia();
        converteImpedimentos();
        criaConfiguracaoAgenda();
    }
    //apenas para ver funcionando, por alguma razao o metodo equivalente em ConfiguracaoAgenda não está funcionando
    public boolean validaConfiguracaoAgenda() {
        if (dataInicio.isBefore(dataFim)) {
            return true;
        } else {
            return false;
        }
    }
}
package models;

import java.time.LocalDate;

public class Contrato {

    private int idContrato;
    private Aluno aluno;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private float valorMensalidade = 0;
    private String tipo;
    private int qtdAulas = 0;
    private float valorPorAula = 0;


    public Contrato(int idContrato, Aluno aluno, LocalDate dataInicio, LocalDate dataTermino, String tipo, int qtdAulas, float valorPorAula) {
        this.aluno = aluno;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.tipo = tipo;
        this.qtdAulas = qtdAulas;
        this.valorPorAula = valorPorAula;
        this.valorMensalidade = calculaMensalidade(this.qtdAulas, this.valorPorAula);
    }

    public Contrato(Aluno aluno, LocalDate dataInicio, LocalDate dataTermino, String tipo, int qtdAulas, float valorPorAula) {
        this.aluno = aluno;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.tipo = tipo;
        this.qtdAulas = qtdAulas;
        this.valorPorAula = valorPorAula;
        this.valorMensalidade = calculaMensalidade(this.qtdAulas, this.valorPorAula);
    }

    public Contrato(Aluno aluno, LocalDate dataInicio, LocalDate dataTermino, String tipo) {
        this.aluno = aluno;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.tipo = tipo;
    }

    public Contrato() {
    }

    private float calculaMensalidade(int qtdAulas, float valorPorAula) {
        return qtdAulas * valorPorAula * 4;
    }

    public float atualizaMensalidade() {
        return this.valorMensalidade = calculaMensalidade(this.qtdAulas, this.valorPorAula);
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public float getValorMensalidade() {
        return valorMensalidade;
    }

    public void setValorMensalidade(float valorMensalidade) {
        this.valorMensalidade = valorMensalidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQtdAulas() {
        return qtdAulas;
    }

    public void setQtdAulas(int qtdAulas) {
        this.qtdAulas = qtdAulas;
    }

    public float getValorPorAula() {
        return valorPorAula;
    }

    public void setValorPorAula(float valorPorAula) {
        this.valorPorAula = valorPorAula;
    }

    @Override
    public String toString() {
        return idContrato + " | Aluno : " + this.aluno.getNome() + " | IdAluno: " + this.aluno.getIdAluno() + " | Plano " + tipo + "\nValido de: " + dataInicio + 
        " até " + dataTermino + " | Aulas por semana: " + qtdAulas + " | Valor por Aula: " + valorPorAula + " | Valor da mensalidade: " + valorMensalidade;
    }
}

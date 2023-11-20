package models;

import java.time.LocalDate;
import java.time.Period;

public class Aluno {
    private int idAluno;
    private String nome;
    private LocalDate dataNascimento;
    private int idade;
    private String telefone;
    private String email;

    public Aluno(int idAluno, String nome, LocalDate dataNascimento, String telefone, String email) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.idade = calculaIdade(dataNascimento);
        this.telefone = telefone;
        this.email = email;
    }

    public Aluno(String nome, LocalDate dataNascimento, String telefone, String email) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.idade = calculaIdade(dataNascimento);
        this.telefone = telefone;
        this.email = email;
    }

    public Aluno() {
    }

    private int calculaIdade(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now();
        Period periodo = Period.between(dataNascimento, hoje);
        return periodo.getYears();
    }
    
    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return idAluno + " | " + nome + " | " + dataNascimento + " | " + idade + " | " + telefone + " | " + email;
    }
}

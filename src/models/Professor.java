package models;

public class Professor {
    private int idProfessor;
    private String nome;
    private String especialidade;

    
    public Professor(int idProfessor, String nome, String especialidade) {
        this.idProfessor = idProfessor;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public Professor(String nome, String especialidade) {
        this.nome = nome;
        this.especialidade = especialidade;
    }
    public Professor() {
    }

    public int getIdProfessor() {
        return idProfessor;
    }
    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return idProfessor + " | " + nome + " | " + especialidade;
    }

}

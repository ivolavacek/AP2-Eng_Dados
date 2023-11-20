package models;
import java.sql.Time;
import java.util.ArrayList;

public class Aula {

    

    
    private int idAula;
    private Professor professor;
    private String nome;
    private Time horarioInicio;
    private Time horarioFim;
    private String diaSemana;

    private ArrayList<Aluno> alunos;

    
 


    public Aula(int idAula, String nome, Time horarioInicio, Time horarioFim, String diaSemana) {
        this.idAula = idAula;
        this.nome = nome;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.diaSemana = diaSemana;
    }


    public Aula(String nome, Time horarioInicio, Time horarioFim, String diaSemana) {
        this.nome = nome;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.diaSemana = diaSemana;
    }


    public Aula(Professor professor, String nome, Time horarioInicio, Time horarioFim, String diaSemana) {
        this.professor = professor;
        this.nome = nome;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.diaSemana = diaSemana;
    }


    public Aula(int idAula, Professor professor, String nome, Time horarioInicio, Time horarioFim, String diaSemana) {
        this.idAula = idAula;
        this.professor = professor;
        this.nome = nome;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.diaSemana = diaSemana;
    }


    public ArrayList<Aluno> addAlunoAula(Aluno aluno){
        this.alunos.add(aluno);
        return alunos;
    }

    
    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public Professor getprofessor() {
        return this.professor;
    }

    public void setprofessor(Professor professor) {
        this.professor = professor;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Time getHorarioInicio() {
        return this.horarioInicio;
    }

    public void setHorarioInicio(Time horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public Time getHorarioFim() {
        return this.horarioFim;
    }

    public void setHorarioFim(Time horarioFim) {
        this.horarioFim = horarioFim;
    }

    public String getDiaSemana() {
        return this.diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;

    }
   public ArrayList<Aluno> getAlunos() {
        return alunos;
    }


    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDate;
import java.util.ArrayList;

import models.Aluno;
import models.Aula;


public class AulaDAO {
    private Connection connection;

    public AulaDAO(Connection connection) {
        this.connection = connection;
    }

    public void createAula(Aula aula){
        String sql = "INSERT INTO Aula (fk_idProfessor, nome, horarioInicio, horarioFim, diaSemana) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstm.setInt(1, aula.getprofessor().getIdProfessor());
            pstm.setString(2, aula.getNome());
            pstm.setTime(3, aula.getHorarioInicio());
            pstm.setTime(4, aula.getHorarioFim());
            pstm.setString(5, aula.getDiaSemana());
            try (ResultSet rst = pstm.getGeneratedKeys()) {
                while (rst.next()) {
                    aula.setIdAula(rst.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insertAlunoAula(Aula aula){
        for (Aluno aluno : aula.getAlunos()) {
            String sql = "INSERT INTO Aluno_has_Aula (fk_idAluno, fk_idAula) VALUES (?, ?)";
            try(PreparedStatement pstm = connection.prepareStatement(sql)){
                pstm.setInt(1, aluno.getIdAluno());
                pstm.setInt(2, aula.getIdAula());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }


}

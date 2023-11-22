package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.ArrayList;


import models.Aluno;
import models.Aula;
import models.Professor;


public class AulaDAO {
    private Connection connection;

    public AulaDAO(Connection connection) {
        this.connection = connection;
    }

    public void createAula(Aula aula){
        String sql = "INSERT INTO Aula (fk_idProfessor, nome, horarioInicio, horarioFim, diaSemana) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstm.setInt(1, aula.getProfessor().getIdProfessor());
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

    public void updateAula(Aula aula) {
        String sql = "UPDATE Aula SET nome = ?, horarioInicio = ?, horarioFim = ?, diaSemana = ?, fk_idProfessor = ? WHERE idAula = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, aula.getNome());
            pstm.setTime(2, aula.getHorarioInicio());
            pstm.setTime(3, aula.getHorarioFim());
            pstm.setString(4, aula.getDiaSemana());
            pstm.setInt(5, aula.getProfessor().getIdProfessor());
            pstm.setInt(6, aula.getIdAula());

            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Aula getAula(int idAula) {
        Aula aula = null;
        String sql = "SELECT a.idAula, a.nome, a.horarioInicio, a.horarioFim, a.diaSemana, a.fk_idProfessor, " +
                     "p.nome AS professorNome, p.especialidade, " +
                     "al.idAluno, al.nome AS alunoNome, al.dataNascimento, al.idade, al.telefone, al.email " +
                     "FROM Aula a " +
                     "JOIN Professor p ON a.fk_idProfessor = p.idProfessor " +
                     "LEFT JOIN Aluno_has_Aula aa ON a.idAula = aa.fk_idAula " +
                     "LEFT JOIN Aluno al ON aa.fk_idAluno = al.idAluno " +
                     "WHERE a.idAula = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, idAula);

            try (ResultSet rs = pstm.executeQuery()) {
                ArrayList<Aluno> alunos = new ArrayList<>();
                Professor professor = null;
                while (rs.next()) {
                    if (aula == null) {
                        aula = new Aula();
                        aula.setIdAula(rs.getInt("idAula"));
                        aula.setNome(rs.getString("nome"));
                        aula.setHorarioInicio(rs.getTime("horarioInicio"));
                        aula.setHorarioFim(rs.getTime("horarioFim"));
                        aula.setDiaSemana(rs.getString("diaSemana"));

                        professor = new Professor();
                        professor.setIdProfessor(rs.getInt("fk_idProfessor"));
                        professor.setNome(rs.getString("professorNome"));
                        professor.setEspecialidade(rs.getString("especialidade"));
                        aula.setProfessor(professor);
                    }
                    if (rs.getInt("idAluno") != 0) { // Verifica se há um aluno
                        Aluno aluno = new Aluno();
                        aluno.setIdAluno(rs.getInt("idAluno"));
                        aluno.setNome(rs.getString("alunoNome"));
                        aluno.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
                        aluno.setIdade(rs.getInt("idade"));
                        aluno.setTelefone(rs.getString("telefone"));
                        aluno.setEmail(rs.getString("email"));
                        alunos.add(aluno);
                    }
                }
                if (aula != null) {
                    aula.setAlunos(alunos);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return aula;
    }
    public void deleteAula(Aula aula) {
        String sqlDeleteAlunoAula = "DELETE FROM Aluno_has_Aula WHERE fk_idAula = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sqlDeleteAlunoAula)) {
            pstm.setInt(1, aula.getIdAula());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sqlDeleteAula = "DELETE FROM Aula WHERE idAula = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sqlDeleteAula)) {
            pstm.setInt(1, aula.getIdAula());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
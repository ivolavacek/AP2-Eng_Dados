package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import models.Aluno;
import models.Professor;

public class ProfessorDAO {

    private Connection connection;

    public ProfessorDAO(Connection connection) {
        this.connection = connection;
    }

    public void createProfessor(Professor professor) {

        try {
            String sql = "INSERT INTO professor (nome, especialidade) VALUES (?, ?)";
            
            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                
                pstm.setString(1, professor.getNome());
                pstm.setString(2, professor.getEspecialidade());
                
                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        professor.setIdProfessor(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Professor getProfessorById(int idProfessor) {
        try {
            String sql = "SELECT * FROM professor WHERE idProfessor = ?";
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, idProfessor);

                try (ResultSet rst = pstm.executeQuery()) {
                    if (rst.next()) {
                        Professor professor = new Professor();
                        professor.setIdProfessor(rst.getInt("idProfessor"));
                        professor.setNome(rst.getString("nome"));
                        professor.setEspecialidade(rst.getString("especialidade"));
                    
                        return professor;
                    } else {
                        System.out.println("Nenhum professor encontrado com o ID especificado.");
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Professor> getAllProfessores() {
        ArrayList<Professor> professores = new ArrayList<>();

        try {
            String sql = "SELECT * FROM professor";

            try (PreparedStatement pstm = connection.prepareStatement(sql);
                ResultSet rst = pstm.executeQuery()) {
                
                while (rst.next()) {
                    Professor professor = new Professor();
                    professor.setIdProfessor(rst.getInt("idProfessor"));
                    professor.setNome(rst.getString("nome"));
                    professor.setEspecialidade(rst.getString("especialidade"));
                    
                    professores.add(professor);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return professores;
    }

    public void updateEspecialidade(Professor professor, String especialidade) {
        try {
            String sql = "UPDATE professor SET especialidade = ? WHERE idProfessor = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, especialidade);
                pstm.setInt(2, professor.getIdProfessor());

                int rowsAffected = pstm.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Especialidade atualizada com sucesso!");

                    Professor professorAtualizado = getProfessorById(professor.getIdProfessor());
                    professor.setEspecialidade(professorAtualizado.getEspecialidade());
                } else {
                    System.out.println("Nenhum professor encontrado.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProfessor(Professor professor) {
        try {
            String sql = "DELETE FROM professor WHERE idProfessor = ?";
            
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, professor.getIdProfessor());

                int rowsAffected = pstm.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Professor excluído com sucesso!");
                } else {
                    System.out.println("Nenhum professor encontrado.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

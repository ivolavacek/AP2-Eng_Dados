package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDate;
import java.util.ArrayList;

import models.Aluno;

public class AlunoDAO {

    private Connection connection;

    public AlunoDAO(Connection connection) {
        this.connection = connection;
    }

    public void createAluno(Aluno aluno){

        try {
            String sql = "INSERT INTO aluno (nome, dataNascimento, idade, telefone, email) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, aluno.getNome());
                pstm.setObject(2, aluno.getDataNascimento());
                pstm.setInt(3, aluno.getIdade());
                pstm.setString(4, aluno.getTelefone());
                pstm.setString(5, aluno.getEmail());

                pstm.execute();
                
                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        aluno.setIdAluno(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void updateTelefone(Aluno aluno, String novoTelefone) {
        try {
            String sql = "UPDATE aluno SET telefone = ? WHERE idAluno = ?";
    
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, novoTelefone);
                pstm.setInt(2, aluno.getIdAluno());
    
                int rowsAffected = pstm.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Telefone atualizado com sucesso!");
                } else {
                    System.out.println("Nenhum aluno encontrado com o ID especificado.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateEmail(Aluno aluno, String novoEmail) {
        try {
            String sql = "UPDATE aluno SET email = ? WHERE idAluno = ?";
    
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, novoEmail);
                pstm.setInt(2, aluno.getIdAluno());
    
                int rowsAffected = pstm.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Email atualizado com sucesso!");
                } else {
                    System.out.println("Nenhum aluno encontrado.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAluno(Aluno aluno) {
        try {
            String sql = "DELETE FROM aluno WHERE idAluno = ?";
    
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, aluno.getIdAluno());
    
                int rowsAffected = pstm.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Aluno excluído com sucesso!");
                } else {
                    System.out.println("Nenhum aluno encontrado.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Aluno getAlunoById(int idAluno) {
        try {
            String sql = "SELECT * FROM aluno WHERE idAluno = ?";
    
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, idAluno);
    
                try (ResultSet rst = pstm.executeQuery()) {
                    if (rst.next()) {
                        Aluno aluno = new Aluno();
                        aluno.setIdAluno(rst.getInt("idAluno"));
                        aluno.setNome(rst.getString("nome"));
                        aluno.setDataNascimento(rst.getObject("dataNascimento", LocalDate.class));
                        aluno.setIdade(rst.getInt("idade"));
                        aluno.setTelefone(rst.getString("telefone"));
                        aluno.setEmail(rst.getString("email"));
    
                        return aluno;
                    } else {
                        System.out.println("Nenhum aluno encontrado com o ID especificado.");
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Aluno> getAllAlunos() {
        ArrayList<Aluno> alunos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM aluno";

            try (PreparedStatement pstm = connection.prepareStatement(sql);
                ResultSet rst = pstm.executeQuery()) {

                while (rst.next()) {
                    Aluno aluno = new Aluno();
                    aluno.setIdAluno(rst.getInt("idAluno"));
                    aluno.setNome(rst.getString("nome"));
                    aluno.setDataNascimento(rst.getObject("dataNascimento", LocalDate.class));
                    aluno.setIdade(rst.getInt("idade"));
                    aluno.setTelefone(rst.getString("telefone"));
                    aluno.setEmail(rst.getString("email"));

                    alunos.add(aluno);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return alunos;
    }
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDate;
import java.util.ArrayList;

import models.Contrato;
import models.Aluno;

public class ContratoDAO {

    private Connection connection;

    public ContratoDAO(Connection connection) {
        this.connection = connection;
    }

    public void createContratoComAulas(Contrato contrato){

        try {
            String sql = "INSERT INTO contrato (fk_idAluno, dataInicio, dataTermino, tipo, valorMensalidade, qtdAulas, valorPorAula) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, contrato.getAluno().getIdAluno());
                pstm.setObject(2, contrato.getDataInicio());
                pstm.setObject(3, contrato.getDataTermino());
                pstm.setString(4, contrato.getTipo());
                pstm.setFloat(5, contrato.getValorMensalidade());
                pstm.setInt(6, contrato.getQtdAulas());
                pstm.setFloat(7, contrato.getValorPorAula());

                pstm.execute();
                
                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        contrato.setIdContrato(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createContratoSemAulas(Contrato contrato){

        try {
            String sql = "INSERT INTO contrato (fk_idAluno, dataInicio, dataTermino, tipo, valorMensalidade) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, contrato.getAluno().getIdAluno());
                pstm.setObject(2, contrato.getDataInicio());
                pstm.setObject(3, contrato.getDataTermino());
                pstm.setString(4, contrato.getTipo());
                pstm.setFloat(5, contrato.getValorMensalidade());

                pstm.execute();
                
                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        contrato.setIdContrato(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void updateDataTermino(Contrato contrato, LocalDate novaDataTermino) {
        try {
            String sql = "UPDATE contrato SET dataTermino = ? WHERE idContrato = ?";
    
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setObject(1, novaDataTermino);
                pstm.setInt(2, contrato.getIdContrato());
    
                int rowsAffected = pstm.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Data de término do contrato atualizado com sucesso!");

                    Contrato contratoAtualizado = getContratoByAluno(contrato.getAluno());
                    contrato.setDataTermino(contratoAtualizado.getDataTermino());
                } else {
                    System.out.println("Nenhum contrato encontrado.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateValorMensalidade(Contrato contrato) {
        try {
            String sql = "UPDATE contrato SET valorMensalidade = ? WHERE idContrato = ?";
    
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setObject(1, contrato.getValorMensalidade());
                pstm.setInt(2, contrato.getIdContrato());
    
                int rowsAffected = pstm.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Quantidade de aulas atualizada com sucesso!");
                } else {
                    System.out.println("Nenhum contrato encontrado.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateQtdAulas(Contrato contrato, int novaQtdAulas) {
        try {
            String sql = "UPDATE contrato SET qtdAulas = ? WHERE idContrato = ?";
    
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setObject(1, novaQtdAulas);
                pstm.setInt(2, contrato.getIdContrato());
    
                int rowsAffected = pstm.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Quantidade de aulas atualizada com sucesso!");

                    Contrato contratoAtualizado = getContratoByAluno(contrato.getAluno());
                    contrato.setQtdAulas(contratoAtualizado.getQtdAulas());
                    contrato.atualizaMensalidade();
                    updateValorMensalidade(contrato);
                } else {
                    System.out.println("Nenhum contrato encontrado.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateValorPorAula(Contrato contrato, float novoValorPorAula) {
        try {
            String sql = "UPDATE contrato SET valorPorAula = ? WHERE idContrato = ?";
    
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setObject(1, novoValorPorAula);
                pstm.setInt(2, contrato.getIdContrato());
    
                int rowsAffected = pstm.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Valor por aula atualizado com sucesso!");

                    Contrato contratoAtualizado = getContratoByAluno(contrato.getAluno());
                    contrato.setValorPorAula(contratoAtualizado.getValorPorAula());
                    contrato.atualizaMensalidade();
                    updateValorMensalidade(contrato);
                } else {
                    System.out.println("Nenhum contrato encontrado com o ID especificado.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteContrato(Contrato contrato) {
        try {
            String sql = "DELETE FROM contrato WHERE idContrato = ?";
    
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, contrato.getIdContrato());
    
                int rowsAffected = pstm.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Contrato excluído com sucesso!");
                } else {
                    System.out.println("Nenhum contrato encontrado com o ID especificado.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Contrato getContratoByAluno(Aluno aluno) {
        try {
            String sql = "SELECT * FROM contrato WHERE fk_idAluno = ?";
    
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, aluno.getIdAluno());
    
                try (ResultSet rst = pstm.executeQuery()) {
                    if (rst.next()) {
                        Contrato contrato = new Contrato();
                        AlunoDAO adao = new AlunoDAO(connection);
                        contrato.setIdContrato(rst.getInt("idContrato"));
                        contrato.setAluno(adao.getAlunoById(rst.getInt("fk_idAluno")));
                        contrato.setDataInicio(rst.getObject("dataInicio", LocalDate.class));
                        contrato.setDataTermino(rst.getObject("dataTermino", LocalDate.class));
                        contrato.setTipo(rst.getString("tipo"));
                        contrato.setQtdAulas(rst.getInt("qtdAulas"));
                        contrato.setValorPorAula(rst.getFloat("valorPorAula"));
                        contrato.setValorMensalidade(rst.getFloat("valorMensalidade"));
    
                        return contrato;
                    } else {
                        System.out.println("Nenhum contrato encontrado para o aluno especificado.");
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Contrato> getAllContratos() {
        ArrayList<Contrato> contratos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM contrato";

            try (PreparedStatement pstm = connection.prepareStatement(sql);
                 ResultSet rst = pstm.executeQuery()) {

                while (rst.next()) {
                    Contrato contrato = new Contrato();
                    AlunoDAO adao = new AlunoDAO(connection);
                    contrato.setIdContrato(rst.getInt("idContrato"));
                    contrato.setAluno(adao.getAlunoById(rst.getInt("fk_idAluno")));
                    contrato.setDataInicio(rst.getObject("dataInicio", LocalDate.class));
                    contrato.setDataTermino(rst.getObject("dataTermino", LocalDate.class));
                    contrato.setTipo(rst.getString("tipo"));
                    contrato.setQtdAulas(rst.getInt("qtdAulas"));
                    contrato.setValorPorAula(rst.getFloat("valorPorAula"));
                    contrato.setValorMensalidade(rst.getFloat("valorMensalidade"));

                    contratos.add(contrato);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return contratos;
    }
}
import java.sql.Connection;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import models.Aluno;
import models.Aula;
import models.Contrato;
import models.Professor;
import dao.ConnectionFactory;
import dao.AlunoDAO;
import dao.AulaDAO;
import dao.ContratoDAO;
import dao.ProfessorDAO;

public class Main {
    public static void main(String[] args) throws Exception {

        // Cria as intancias de Conexao com banco (ConnectionFactory)
        ConnectionFactory fabricaDeConexao = new ConnectionFactory();
        Connection connection = fabricaDeConexao.connectionFactory();

        
        // > Parte do aluno

        // Criação das instancias de Alunos
        
        Aluno aluno1 = new Aluno("Tiago", LocalDate.of(1997, 1, 27), "21996558585", "tiago@gmail.com");
        Aluno aluno2 = new Aluno("Antônio", LocalDate.of(1999, 3, 27), "21998558585", "antonio@gmail.com");
        Aluno aluno3 = new Aluno("Larissa", LocalDate.of(1985, 1, 15), "21996552000", "larissa@gmail.com");
        Aluno aluno4 = new Aluno("Isabela", LocalDate.of(1993, 5, 20), "21987759085", "isa@gmail.com");
        Aluno aluno5 = new Aluno("Tatiana", LocalDate.of(2000, 4, 30), "21999999999", "tatiana@gmail.com");
        Aluno aluno6 = new Aluno("Ana", LocalDate.of(2002, 7, 21), "21981667879", "ana@gmail.com");

        // Criação da instancia do AlunoDAO
        
        AlunoDAO adao = new AlunoDAO(connection);

        // Metodo de incercao

        System.out.println("Insercao de dados na tabela 'Aluno'\n");

        adao.createAluno(aluno1);
        adao.createAluno(aluno2);
        adao.createAluno(aluno3);
        adao.createAluno(aluno4);
        adao.createAluno(aluno5);
        adao.createAluno(aluno6);

        System.out.println(aluno1);
        System.out.println(aluno2);
        System.out.println(aluno3);
        System.out.println(aluno4);
        System.out.println(aluno5);
        System.out.println(aluno6);





        // metodo de Update
        System.out.println("\n----------------");
        System.out.println("Atualizacao de dados na tabela 'Aluno'\n");

        System.out.println("Atualiza email");
        System.out.println(adao.getAlunoById(4));
        adao.updateEmail(aluno4, "bela@gmail.com");
        System.out.println(adao.getAlunoById(4));
        System.out.println("");

        System.out.println("Atualiza telefone");
        System.out.println(adao.getAlunoById(5));
        adao.updateTelefone(aluno5, "21988888888");
        System.out.println(adao.getAlunoById(5));

        // metodo de Delete

        System.out.println("\n----------------");
        System.out.println("Delecao de dados na tabela 'Aluno'\n");

        System.out.println(adao.getAlunoById(6));
        adao.deleteAluno(aluno6);
        adao.getAlunoById(6);
        

        // Consulta

        System.out.println("\n----------------");
        System.out.println("Metodo para consultar um elemento especifico da tabela 'Aluno'\n");

        Aluno alunoX = adao.getAlunoById(1);
        Aluno alunoY = adao.getAlunoById(2);

        System.out.println(alunoX);
        System.out.println(alunoY);



        System.out.println("\n----------------");
        System.out.println("Metodo para consultar todos os elementos da tabela 'Aluno'\n");

        
        ArrayList<Aluno> alunos = adao.getAllAlunos();

        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }


        System.out.println("\n----------------");
        System.out.println("Insercao de dados na tabela 'Contrato'\n");



        // Contrato
        ContratoDAO cdao = new ContratoDAO(connection);

        Contrato contrato1 = new Contrato(aluno1, LocalDate.of(2022, 12, 1), LocalDate.of(2023, 12, 1), 
        "Black", 3, 20);
        Contrato contrato2 = new Contrato(aluno2, LocalDate.of(2021, 2, 15), LocalDate.of(2024, 2, 15), 
        "Basico", 3, 18);
        Contrato contrato3 = new Contrato(aluno3, LocalDate.of(2023, 7, 30), LocalDate.of(2024, 7, 30), 
        "Black", 5, 18);
        Contrato contrato4 = new Contrato(aluno4, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 12, 1), 
        "Black", 6, 16);
        Contrato contrato5 = new Contrato(aluno5, LocalDate.of(2023, 11, 1), LocalDate.of(2024, 11, 1), 
        "Basico");
        
        cdao.createContratoComAulas(contrato1);
        cdao.createContratoComAulas(contrato2);
        cdao.createContratoComAulas(contrato3);
        cdao.createContratoComAulas(contrato4);
        cdao.createContratoSemAulas(contrato5);

        System.out.println(contrato1);
        System.out.println(contrato2);
        System.out.println(contrato3);
        System.out.println(contrato4);
        System.out.println(contrato5);
        
        System.out.println("\n----------------");
        System.out.println("Metodo para consultar um elemento especifico da tabela 'Contrato'\n");

        System.out.println(cdao.getContratoByAluno(aluno1));
        System.out.println(cdao.getContratoByAluno(aluno2));

        System.out.println("\n----------------");
        System.out.println("Atualizacao de dados na tabela 'Contrato'\n");

        System.out.println("Atualiza data de término");
        System.out.println(cdao.getContratoByAluno(aluno1));
        cdao.updateDataTermino(contrato1, LocalDate.of(2024, 12, 1));
        System.out.println(cdao.getContratoByAluno(aluno1));
        System.out.println("");

        System.out.println("Atualiza quantidade de aulas");
        System.out.println(cdao.getContratoByAluno(aluno5));
        cdao.updateQtdAulas(contrato5, 4);
        System.out.println(cdao.getContratoByAluno(aluno5));
        System.out.println("");

        System.out.println("Atualiza valor por aula");
        System.out.println(cdao.getContratoByAluno(aluno5));
        cdao.updateValorPorAula(contrato5, 18);
        System.out.println(cdao.getContratoByAluno(aluno5));
        
        System.out.println("\n----------------");
        System.out.println("Delecao de dados na tabela 'Contrato'\n");

        System.out.println(cdao.getContratoByAluno(aluno4));
        cdao.deleteContrato(contrato4);
        cdao.getContratoByAluno(aluno4);

        System.out.println("\n----------------");
        System.out.println("Metodo para consultar todos os elementos da tabela 'Contrato'\n");

        ArrayList<Contrato> contratos = cdao.getAllContratos();

        for (Contrato contrato : contratos) {
            System.out.println(contrato);
            System.out.println();
        }

        System.out.println("\n----------------");
        System.out.println("Insercao de dados na tabela 'Professor'\n");

        ProfessorDAO pdao = new ProfessorDAO(connection);

        Professor prof1 = new Professor("Paulo", "Lutas");
        Professor prof2 = new Professor("Marlucia", "Personal Trainer");
        Professor prof3 = new Professor("Rogerio", "Academia");
        Professor prof4 = new Professor("Juliana", "Pilates");
        
        pdao.createProfessor(prof1);
        pdao.createProfessor(prof2);
        pdao.createProfessor(prof3);
        pdao.createProfessor(prof4);

        System.out.println(prof1);
        System.out.println(prof2);
        System.out.println(prof3);
        System.out.println(prof4);

        System.out.println("\n----------------");
        System.out.println("Metodo para consultar um elemento especifico da tabela 'Professor'\n");

        Professor profX = pdao.getProfessorById(1);
        Professor profY = pdao.getProfessorById(2);

        System.out.println(profX);
        System.out.println(profY);

        System.out.println("\n----------------");
        System.out.println("Atualizacao de dados na tabela 'Professor'\n");

        System.out.println("Atualiza especialidade");
        System.out.println(pdao.getProfessorById(4));
        pdao.updateEspecialidade(prof4, "Academia");
        System.out.println(pdao.getProfessorById(4));

        System.out.println("\n----------------");
        System.out.println("Delecao de dados na tabela 'Professor'\n");

        System.out.println(pdao.getProfessorById(1));
        pdao.deleteProfessor(prof1);
        pdao.getProfessorById(1);

        System.out.println("\n----------------");
        System.out.println("Metodo para consultar todos os elementos da tabela 'Professor'\n");

        ArrayList<Professor> professores = pdao.getAllProfessores();

        for (Professor professor : professores) {
            System.out.println(professor);
        }

        // Criação das instâncias de Aulas com professores
        Aula aula1 = new Aula(1, prof3, "Yoga", Time.valueOf("08:00:00"), Time.valueOf("09:00:00"), "Segunda-feira");
        Aula aula2 = new Aula(2, prof4, "Pilates", Time.valueOf("10:00:00"), Time.valueOf("11:00:00"), "Terça-feira");

        // Criação da instância do AulaDAO
        AulaDAO aulaDAO = new AulaDAO(connection);

        // Método de inserção
        System.out.println("\n----------------");
        System.out.println("Inserção de dados na tabela 'Aula'\n");

        aulaDAO.createAula(aula1);
        aulaDAO.createAula(aula2);

        System.out.println(aula1);
        System.out.println(aula2);

        // Adicionar alunos às aulas
        aula1.addAlunoAula(aluno1);
        aula1.addAlunoAula(aluno2);
        aula1.addAlunoAula(aluno3);
        aula2.addAlunoAula(aluno1);
        aula2.addAlunoAula(aluno4);
        System.out.println(aula1);
        aulaDAO.insertAlunoAula(aula1);
        aulaDAO.insertAlunoAula(aula2);

        // Método de busca
        System.out.println("\n----------------");
        System.out.println("Busca de uma aula específica\n");
        Aula aulaBuscada = aulaDAO.getAula(1);
        System.out.println(aulaBuscada);

        // Método de atualização
        System.out.println("\n----------------");
        System.out.println("Atualização de dados na tabela 'Aula'\n");
        aula2.setNome("Aeróbica");
        aulaDAO.updateAula(aula2);
        System.out.println(aulaDAO.getAula(2));

        // Método de exclusão
        System.out.println("\n----------------");
        System.out.println("Exclusão de uma aula específica\n");
        aulaDAO.deleteAula(aula2);



    }
}




        


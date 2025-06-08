package br.com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TurmaDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/escola";
    private static final String USER = "postgres";
    private static final String PASSWORD = "2025";

    public void inserir(Turma turma) {
        String sql = "INSERT INTO turmas (nome, id_professor, id_aluno) VALUES (?, ?, ?)";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, turma.getNome());
            pstmt.setInt(2, turma.getIdProfessor().getId());
            pstmt.setInt(3, turma.getIdAluno().getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir turma: " + e.getMessage());
        }
    }

    public List<Turma> buscarTodas() {}

}

package br.com.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/escola";
    private static final String USER = "postgres";
    private static final String PASSWORD = "2025";

    public void inserir(Professor professor) {
        String sql = "INSERT INTO professores (nome, turma) VALUES (?, ?)";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, professor.getNome());
            pstmt.setString(2, professor.getTurma());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir professor: " + e.getMessage());
        }
    }

    public List<Professor> buscarTodos() {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM professores ORDER BY nome";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Professor professor = new Professor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("turma")
                );
                professores.add(professor);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar professores: " + e.getMessage());
        }

        return professores;
    }

    public Professor buscarPorId(int id) {
        String sql = "SELECT * FROM professores WHERE id = ?";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Professor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("turma")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar professor por ID: " + e.getMessage());
        }

        return null;
    }

    public Professor buscarPorNome(String nome) {
        String sql = "SELECT * FROM professores WHERE nome = ?";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Professor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("turma")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar professor por nome: " + e.getMessage());
        }

        return null;
    }

    public List<Professor> buscarPorTurma(String turma) {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM professores WHERE turma = ?";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, turma);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Professor professor = new Professor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("turma")
                );
                professores.add(professor);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar professores por turma: " + e.getMessage());
        }

        return professores;
    }

    public void atualizar(Professor professor) {
        String sql = "UPDATE professores SET nome = ?, turma = ? WHERE id = ?";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, professor.getNome());
            pstmt.setString(2, professor.getTurma());
            pstmt.setInt(3, professor.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar professor: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM professores WHERE id = ?";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir professor: " + e.getMessage());
        }
    }
}

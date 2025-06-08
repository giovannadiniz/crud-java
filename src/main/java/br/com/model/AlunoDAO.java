package br.com.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/escola";
    private static final String USER = "postgres";
    private static final String PASSWORD = "2025";

    public void inserir(Aluno aluno){
        String sql = "INSERT INTO alunos (nome, matricula, turma) VALUES (?, ?, ?)";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, aluno.getNome());
            pstmt.setString(2, aluno.getMatricula());
            pstmt.setString(3, aluno.getTurma());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir aluno: " + e.getMessage());
        }
    }

    public List<Aluno> buscarTodos(){
        List<Aluno> alunos = new ArrayList<Aluno>();
        String sql = "SELECT * FROM alunos ORDER BY nome";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new Aluno(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("matricula"),
                rs.getString("turma")
            );
                alunos.add(aluno);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar alunos: " + e.getMessage());
        }
        return alunos;
    }

    public Aluno buscarPorId(int id){
        String sql = "SELECT * FROM aluno WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Aluno(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("matricula"),
                    rs.getString("turma")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar aluno por ID: " + e.getMessage());
        }

        return null;
    }

    public Aluno buscarPorMatricula(String matricula){
        String sql = "SELECT * FROM alunos WHERE matricula = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, matricula);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Aluno(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("matricula"),
                    rs.getString("turma")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar aluno por matrícula: " + e.getMessage());
        }

        return null;
    }

    public void atualizar(Aluno aluno) {
        String sql = "UPDATE alunos SET nome = ?, matricula = ?, turma = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql)){

             pstmt.setString(1, aluno.getNome());
             pstmt.setString(2, aluno.getMatricula());
             pstmt.setString(3, aluno.getTurma());
             pstmt.setInt(4, aluno.getId());
             pstmt.executeUpdate();
        } catch (SQLException e) {
                throw new RuntimeException("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM alunos WHERE id = ?";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
        throw new RuntimeException("Erro ao excluir aluno: " + e.getMessage());
        }
    }
}


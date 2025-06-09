package br.com.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "2025";

    public void inserir(Turma turma) {
        String sql = "INSERT INTO turmas (nome) VALUES (?)";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, turma.getNome());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir turma: " + e.getMessage());
        }
    }

    public List<Turma> buscarTodas() {
        List <Turma> turmas = new ArrayList<>();
        String sql = "SELECT * FROM turmas ORDER BY nome";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while(rs.next()){
                Turma turma = new Turma(
                rs.getInt("id"),
                rs.getString("nome")
            );
                turmas.add(turma);
            }

        } catch (SQLException e){
            throw new RuntimeException("Erro ao buscar turmas: " + e.getMessage());
        }

        return turmas;
    }

    public Turma buscarPorId(int id) {
        String sql = "SELECT * FROM turmas WHERE id = ?";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Turma(
                        rs.getInt("id"),
                        rs.getString("nome")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar turma por ID: " + e.getMessage());
        }

        return null;
    }

    public Turma buscarPorNome(String nome) {
        String sql = "SELECT * FROM turmas WHERE nome = ?";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Turma(
                        rs.getInt("id"),
                        rs.getString("nome")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar turma por nome: " + e.getMessage());
        }

        return null;
    }

    public void atualizar(Turma turma) {
        String sql = "UPDATE turmas SET nome = ? WHERE id = ?";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, turma.getNome());
            pstmt.setInt(3, turma.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar turma: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM turmas WHERE id = ?";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir turma: " + e.getMessage());
        }
    }

    public List<TurmaDetalhada> listarTudo() {
        List <TurmaDetalhada> lista = new ArrayList<>();
        String sql = "SELECT\n" +
                "                t.id as turma_id,\n" +
                "                t.nome as turma_nome,\n" +
                "                a.id as aluno_id,\n" +
                "                a.nome as aluno_nome,\n" +
                "                p.id as professor_id,\n" +
                "                p.nome as professor_nome\n" +
                "            FROM turmas t\n" +
                "            LEFT JOIN alunos a ON a.idturma = t.id\n" +
                "            LEFT JOIN professores p ON p.idturma = t.id\n" +
                "            ORDER BY t.nome, a.nome, p.nome";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while(rs.next()){
                TurmaDetalhada detalhe = new TurmaDetalhada(
                        rs.getLong("turma_id"),
                        rs.getString("turma_nome"),
                        rs.getObject("aluno_id") != null ? rs.getLong("aluno_id") : null,
                        rs.getString("aluno_nome"),
                        rs.getObject("professor_id") != null ? rs.getLong("professor_id") : null,
                        rs.getString("professor_nome")
                );
                lista.add(detalhe);
            }

        } catch (SQLException e){
            throw new RuntimeException("Erro ao buscar turmas: " + e.getMessage());
        }

        return lista;
    }
}

package org.curso;

import br.com.control.AlunoController;
import br.com.control.ProfessorController;
import br.com.control.TurmaController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");

            Scanner scanner = new Scanner(System.in);

            System.out.println("=== Sistema Acadêmico ===");
            System.out.println("1 - Operações com Alunos");
            System.out.println("2 - Operações com Professores");
            System.out.println("3 - Operações com turmas");
            System.out.println("4 - Listar tudo");
            System.out.print("Escolha: ");

            int opcao = scanner.nextInt();

            if (opcao == 1) {
                AlunoController alunoController = new AlunoController();
                alunoController.executar();
            } else if (opcao == 2) {
                ProfessorController professorController = new ProfessorController();
                professorController.executar();
            } else if (opcao == 3) {
                TurmaController turmaController = new TurmaController();
                turmaController.executar();
            } else if (opcao == 4) {
                TurmaController turmaController = new TurmaController();
                turmaController.gerarRelatorioCompleto();
            } else {
                System.out.println("Opção inválida!");
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Driver PostgreSQL não encontrado: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
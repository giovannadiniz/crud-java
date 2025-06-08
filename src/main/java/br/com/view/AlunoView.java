package br.com.view;

import br.com.model.Aluno;

import java.util.List;
import java.util.Scanner;

public class AlunoView {
    private Scanner scanner;

    public AlunoView() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GERENCIAMENTO DE ALUNOS ===");
        System.out.println("1. Cadastrar Aluno");
        System.out.println("2. Listar Todos os Alunos");
        System.out.println("3. Buscar Aluno por Matrícula");
        System.out.println("4. Atualizar Aluno");
        System.out.println("5. Excluir Aluno");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public Aluno obterDadosAluno() {
        System.out.println("\n=== CADASTRO DE ALUNO ===");

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine().trim();

        System.out.print("Turma: ");
        String turma = scanner.nextLine().trim();

        return new Aluno(nome, matricula, turma);
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println("\n" + mensagem);
    }

    public String obterMatricula() {
        System.out.print("Digite a matrícula do aluno: ");
        return scanner.nextLine().trim();
    }

    public Aluno obterDadosAtualizacao(Aluno alunoAtual){
        System.out.println("\n=== ATUALIZAÇÃO DE ALUNO ===");
        System.out.println("Pressione ENTER para manter o valor atual");

        System.out.print("Nome [" + alunoAtual.getNome() + "]: ");
        String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) nome = alunoAtual.getNome();

        System.out.print("Matrícula [" + alunoAtual.getMatricula() + "]: ");
        String matricula = scanner.nextLine().trim();
        if (matricula.isEmpty()) matricula = alunoAtual.getMatricula();

        System.out.print("Turma [" + alunoAtual.getTurma() + "]: ");
        String turma = scanner.nextLine().trim();
        if (turma.isEmpty()) turma = alunoAtual.getTurma();

        return new Aluno(alunoAtual.getId(), nome, matricula, turma);
    }

    public boolean confirmarExclusao() {
        System.out.print("Tem certeza que deseja excluir este aluno? (s/N): ");
        String resposta = scanner.nextLine().trim().toLowerCase();
        return resposta.equals("s") || resposta.equals("sim");
    }

    public void mostrarAluno(Aluno aluno) {
        System.out.println("\n=== DADOS DO ALUNO ===");
        System.out.println("ID: " + aluno.getId());
        System.out.println("Nome: " + aluno.getNome());
        System.out.println("Matrícula: " + aluno.getMatricula());
        System.out.println("Turma: " + aluno.getTurma());
    }

    public void mostrarAlunos(List<Aluno> alunos) {
        if (alunos.isEmpty()) {
            System.out.println("\nNenhum aluno cadastrado!");
            return;
        }

        System.out.println("\n=== LISTA DE ALUNOS ===");
        System.out.printf("%-5s %-30s %-15s %-10s%n", "ID", "NOME", "MATRÍCULA", "TURMA");
        System.out.println("-".repeat(65));

        for (Aluno aluno : alunos) {
            System.out.printf("%-5d %-30s %-15s %-10s%n",
                    aluno.getId(),
                    aluno.getNome(),
                    aluno.getMatricula(),
                    aluno.getTurma()
            );
        }

        System.out.println("\nTotal de alunos: " + alunos.size());
    }
}

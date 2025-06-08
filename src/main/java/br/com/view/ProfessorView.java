package br.com.view;

import br.com.model.Professor;

import java.util.List;
import java.util.Scanner;

public class ProfessorView {
    private Scanner scanner;

    public ProfessorView(){
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GERENCIAMENTO DE PROFESSORES ===");
        System.out.println("1. Cadastrar Professor");
        System.out.println("2. Listar Todos os Professores");
        System.out.println("3. Buscar Professor por Nome");
        System.out.println("4. Buscar Professores por Turma");
        System.out.println("5. Atualizar Professor");
        System.out.println("6. Excluir Professor");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");

        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            return opcao;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public Professor obterDadosProfessor() {
        System.out.println("\n=== CADASTRO DE PROFESSOR ===");

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Turma: ");
        String turma = scanner.nextLine().trim();

        if (nome.isEmpty() || turma.isEmpty()) {
            throw new IllegalArgumentException("Todos os campos são obrigatórios!");
        }

        return new Professor(nome, turma);
    }

    public String obterNome() {
        System.out.print("Digite o nome do professor: ");
        return scanner.nextLine().trim();
    }

    public String obterTurma() {
        System.out.print("Digite a turma: ");
        return scanner.nextLine().trim();
    }

    public Professor obterDadosAtualizacao(Professor professorAtual) {
        System.out.println("\n=== ATUALIZAÇÃO DE PROFESSOR ===");
        System.out.println("Pressione ENTER para manter o valor atual");

        System.out.print("Nome [" + professorAtual.getNome() + "]: ");
        String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) nome = professorAtual.getNome();

        System.out.print("Turma [" + professorAtual.getTurma() + "]: ");
        String turma = scanner.nextLine().trim();
        if (turma.isEmpty()) turma = professorAtual.getTurma();

        return new Professor(professorAtual.getId(), nome, turma);
    }

    public boolean confirmarCadastro() {
        System.out.print("Deseja continuar mesmo assim? (s/N): ");
        String resposta = scanner.nextLine().trim().toLowerCase();
        return resposta.equals("s") || resposta.equals("sim");
    }

    public boolean confirmarExclusao() {
        System.out.print("Tem certeza que deseja excluir este professor? (s/N): ");
        String resposta = scanner.nextLine().trim().toLowerCase();
        return resposta.equals("s") || resposta.equals("sim");
    }

    public void mostrarProfessor(Professor professor) {
        System.out.println("\n=== DADOS DO PROFESSOR ===");
        System.out.println("ID: " + professor.getId());
        System.out.println("Nome: " + professor.getNome());
        System.out.println("Turma: " + professor.getTurma());
    }

    public void mostrarProfessores(List<Professor> professores) {
        if (professores.isEmpty()) {
            System.out.println("\nNenhum professor cadastrado!");
            return;
        }System.out.println("\n=== LISTA DE PROFESSORES ===");
        System.out.printf("%-5s %-30s %-15s%n", "ID", "NOME", "TURMA");
        System.out.println("-".repeat(52));

        for (Professor professor : professores) {
            System.out.printf("%-5d %-30s %-15s%n",
                    professor.getId(),
                    professor.getNome(),
                    professor.getTurma()
            );
        }

        System.out.println("\nTotal de professores: " + professores.size());
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println("\n" + mensagem);
    }
}

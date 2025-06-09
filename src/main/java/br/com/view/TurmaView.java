package br.com.view;

import br.com.model.Professor;
import br.com.model.Turma;
import br.com.model.TurmaDetalhada;

import java.util.List;
import java.util.Scanner;

public class TurmaView {
    private Scanner scanner;

    public TurmaView() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GERENCIAMENTO DE TURMAS ===");
        System.out.println("1. Cadastrar Turma");
        System.out.println("2. Listar Todas as Turmas");
        System.out.println("3. Buscar Turma por Nome");
        System.out.println("5. Atualizar Turma");
        System.out.println("6. Excluir Turma");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");

        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            return opcao;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public Turma obterDadosTurma() {
        System.out.println("\n=== CADASTRO DE TURMA ===");

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        return new Turma(nome);
    }

    public String obterNome() {
        System.out.print("Digite o nome da turma: ");
        return scanner.nextLine().trim();
    }

    public Turma obterDadosAtualizacao(Turma turmaAtual) {
        System.out.println("\n=== ATUALIZAÇÃO DE TURMA ===");
        System.out.println("Pressione ENTER para manter o valor atual");

        System.out.print("Nome [" + turmaAtual.getNome() + "]: ");
        String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) nome = turmaAtual.getNome();

        return new Turma(turmaAtual.getId(), nome);
    }

    public boolean confirmarCadastro() {
        System.out.print("Deseja continuar mesmo assim? (s/N): ");
        String resposta = scanner.nextLine().trim().toLowerCase();
        return resposta.equals("s") || resposta.equals("sim");
    }

    public boolean confirmarExclusao() {
        System.out.print("Tem certeza que deseja excluir esta turma? (s/N): ");
        String resposta = scanner.nextLine().trim().toLowerCase();
        return resposta.equals("s") || resposta.equals("sim");
    }

    public void mostrarTurma(Turma turma) {
        System.out.println("\n=== DADOS DA TURMA ===");
        System.out.println("ID: " + turma.getId());
        System.out.println("Nome: " + turma.getNome());
    }

    public void mostrarTurmas(List<Turma> turmas) {
        if (turmas.isEmpty()) {
            System.out.println("\nNenhuma turma cadastrada!");
            return;
        }System.out.println("\n=== LISTA DE TURMAS ===");
        System.out.printf("%-5s %-30s", "ID", "NOME");
        System.out.println("-".repeat(52));

        for (Turma turma : turmas) {
            System.out.printf("%-5d %-30s%n",
                    turma.getId(),
                    turma.getNome()
            );
        }

        System.out.println("\nTotal de turmas: " + turmas.size());
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println("\n" + mensagem);
    }

    public void mostrarTudo(List<TurmaDetalhada> detalhes) {
        if (detalhes.isEmpty()) {
            System.out.println("\nNenhum dado encontrado.");
            return;
        }

        System.out.println("\n=== LISTA COMPLETA DE TURMAS, ALUNOS E PROFESSORES ===");
        System.out.printf("%-5s %-20s %-5s %-20s %-5s %-20s%n",
                "TID", "TURMA", "AID", "ALUNO", "PID", "PROFESSOR");
        System.out.println("-".repeat(95));

        for (TurmaDetalhada d : detalhes) {
            System.out.printf("%-5s %-20s %-5s %-20s %-5s %-20s%n",
                    d.getTurmaId(),
                    d.getTurmaNome(),
                    d.getAlunoId() != null ? d.getAlunoId() : "-",
                    d.getAlunoNome() != null ? d.getAlunoNome() : "-",
                    d.getProfessorId() != null ? d.getProfessorId() : "-",
                    d.getProfessorNome() != null ? d.getProfessorNome() : "-"
            );
        }

        System.out.println("\nTotal de registros: " + detalhes.size());
    }
}

package br.com.control;

import br.com.model.Professor;
import br.com.model.ProfessorDAO;
import br.com.view.ProfessorView;

import java.util.List;

public class ProfessorController {
    private ProfessorDAO professorDAO = new ProfessorDAO();
    private ProfessorView professorView = new ProfessorView();

    public ProfessorController() {
        this.professorDAO = new ProfessorDAO();
        this.professorView = new ProfessorView();
    }

    public void executar(){
        boolean continuar = true;

        while(continuar) {
            int opcao = professorView.mostrarMenu();

            switch (opcao) {
                case 1 -> cadastrarProfessor();
                case 2 -> listarProfessores();
                case 3 -> buscarProfessor();
                case 4 -> buscarPorTurma();
                case 5 -> atualizarProfessor();
                case 6 -> excluirProfessor();
                case 0 -> {
                    professorView.mostrarMensagem("Saindo do sistema...");
                    continuar = false;
                }
                default -> professorView.mostrarMensagem("Opção inválida!");
            }
        }
    }

    private void cadastrarProfessor() {
        try {
            Professor professor = professorView.obterDadosProfessor();

            if (professorDAO.buscarPorNome(professor.getNome()) != null) {
                professorView.mostrarMensagem("Aviso: Já existe um professor com este nome!");
                if (!professorView.confirmarCadastro()) {
                    professorView.mostrarMensagem("Cadastro cancelado!");
                    return;
                }
            }

            professorDAO.inserir(professor);
            professorView.mostrarMensagem("Professor cadastrado com sucesso!");

        } catch (Exception e) {
            professorView.mostrarMensagem("Erro ao cadastrar professor: " + e.getMessage());
        }
    }

    private void listarProfessores() {
        try {
            List<Professor> professores = professorDAO.buscarTodos();
            professorView.mostrarProfessores(professores);
        } catch (Exception e) {
            professorView.mostrarMensagem("Erro ao listar professores: " + e.getMessage());
        }
    }

    private void buscarProfessor() {
        try {
            String nome = professorView.obterNome();
            Professor professor = professorDAO.buscarPorNome(nome);

            if (professor != null) {
                professorView.mostrarProfessor(professor);
            } else {
                professorView.mostrarMensagem("Professor não encontrado!");
            }
        } catch (Exception e) {
            professorView.mostrarMensagem("Erro ao buscar professor: " + e.getMessage());
        }
    }

    private void buscarPorTurma() {
        try {
            String turma = professorView.obterTurma();
            List<Professor> professores = professorDAO.buscarPorTurma(turma);

            if (!professores.isEmpty()) {
                professorView.mostrarProfessores(professores);
            } else {
                professorView.mostrarMensagem("Nenhum professor encontrado para a turma: " + turma);
            }
        } catch (Exception e) {
            professorView.mostrarMensagem("Erro ao buscar professores por turma: " + e.getMessage());
        }
    }

    private void atualizarProfessor() {
        try {
            String nome = professorView.obterNome();
            Professor professorExistente = professorDAO.buscarPorNome(nome);

            if (professorExistente == null) {
                professorView.mostrarMensagem("Professor não encontrado!");
                return;
            }

            professorView.mostrarProfessor(professorExistente);
            Professor dadosAtualizados = professorView.obterDadosAtualizacao(professorExistente);

            professorDAO.atualizar(dadosAtualizados);
            professorView.mostrarMensagem("Professor atualizado com sucesso!");

        } catch (Exception e) {
            professorView.mostrarMensagem("Erro ao atualizar professor: " + e.getMessage());
        }
    }

    private void excluirProfessor() {
        try {
            String nome = professorView.obterNome();
            Professor professor = professorDAO.buscarPorNome(nome);

            if (professor == null) {
                professorView.mostrarMensagem("Professor não encontrado!");
                return;
            }

            professorView.mostrarProfessor(professor);

            if (professorView.confirmarExclusao()) {
                professorDAO.excluir(professor.getId());
                professorView.mostrarMensagem("Professor excluído com sucesso!");
            } else {
                professorView.mostrarMensagem("Operação cancelada!");
            }

        } catch (Exception e) {
            professorView.mostrarMensagem("Erro ao excluir professor: " + e.getMessage());
        }
    }
}

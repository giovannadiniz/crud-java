package br.com.control;

import br.com.model.Turma;
import br.com.model.TurmaDAO;
import br.com.view.TurmaView;

import java.util.List;

public class TurmaController {
    private TurmaDAO turmaDAO = new TurmaDAO();
    private TurmaView turmaView = new TurmaView();

    public TurmaController() {
        this.turmaDAO = turmaDAO;
        this.turmaView = turmaView;
    }

    public void executar(){
        boolean continuar = true;

        while (continuar){
            int opcao = turmaView.mostrarMenu();

            switch (opcao) {
                case 1 -> cadastrarTurma();
                case 2 -> listarTurmas();
                case 3 -> buscarTurma();
                case 4 -> atualizarTurma();
                case 5 -> excluirTurma();
                case 0 -> {
                    turmaView.mostrarMensagem("Saindo do sistema...");
                    continuar = false;
                }
                default -> turmaView.mostrarMensagem("Opção inválida!");
            }
        }

    }

    private void cadastrarTurma() {
        try {
            Turma turma = turmaView.obterDadosTurma();

            if (turmaDAO.buscarPorNome(turma.getNome()) != null) {
                turmaView.mostrarMensagem("Aviso: Já existe uma turma com este nome!");
                if (!turmaView.confirmarCadastro()) {
                    turmaView.mostrarMensagem("Cadastro cancelado!");
                    return;
                }
            }

            turmaDAO.inserir(turma);
            turmaView.mostrarMensagem("Turma cadastrado com sucesso!");

        } catch (Exception e) {
            turmaView.mostrarMensagem("Erro ao cadastrar turma: " + e.getMessage());
        }
    }

    private void listarTurmas() {
        try {
            List<Turma> turmas = turmaDAO.buscarTodas();
            turmaView.mostrarTurmas(turmas);
        } catch (Exception e) {
            turmaView.mostrarMensagem("Erro ao listar turmas: " + e.getMessage());
        }
    }

    private void buscarTurma() {
        try {
            String nome = turmaView.obterNome();
            Turma turma = turmaDAO.buscarPorNome(nome);

            if (turma != null) {
                turmaView.mostrarTurma(turma);
            } else {
                turmaView.mostrarMensagem("Turma não encontrado!");
            }
        } catch (Exception e) {
            turmaView.mostrarMensagem("Erro ao buscar turma: " + e.getMessage());
        }
    }

    private void atualizarTurma() {
        try {
            String nome = turmaView.obterNome();
            Turma turmaExistente = turmaDAO.buscarPorNome(nome);

            if (turmaExistente == null) {
                turmaView.mostrarMensagem("Turma não encontrada!");
                return;
            }

            turmaView.mostrarTurma(turmaExistente);
            Turma dadosAtualizados = turmaView.obterDadosAtualizacao(turmaExistente);

            turmaDAO.atualizar(dadosAtualizados);
            turmaView.mostrarMensagem("Turma atualizada com sucesso!");

        } catch (Exception e) {
            turmaView.mostrarMensagem("Erro ao atualizar turma: " + e.getMessage());
        }
    }

    private void excluirTurma() {
        try {
            String nome = turmaView.obterNome();
            Turma turma = turmaDAO.buscarPorNome(nome);

            if (turma == null) {
                turmaView.mostrarMensagem("Turma não encontrada!");
                return;
            }

            turmaView.mostrarTurma(turma);

            if (turmaView.confirmarExclusao()) {
                turmaDAO.excluir(turma.getId());
                turmaView.mostrarMensagem("Turma excluída com sucesso!");
            } else {
                turmaView.mostrarMensagem("Operação cancelada!");
            }

        } catch (Exception e) {
            turmaView.mostrarMensagem("Erro ao excluir turma: " + e.getMessage());
        }
    }

}

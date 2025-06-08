package br.com.control;

import br.com.model.Aluno;
import br.com.model.AlunoDAO;
import br.com.view.AlunoView;

import java.util.List;

public class AlunoController {
    private AlunoDAO alunoDAO;
    private AlunoView alunoView;

    public AlunoController() {
        this.alunoDAO = new AlunoDAO();
        this.alunoView = new AlunoView();
    }

    public void executar() {
        boolean continuar = true;

        while (continuar) {
            int opcao = alunoView.mostrarMenu();

            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> listarAlunos();
                case 3 -> buscarAluno();
                case 4 -> atualizarAluno();
                case 5 -> excluirAluno();
                case 0 -> {
                    System.out.println("Saindo do sistema...");
                    continuar = false;
                }
                default -> alunoView.mostrarMensagem("Opção inválida.");
            }
        }
    }

    private void cadastrarAluno() {
        try {
            Aluno aluno = alunoView.obterDadosAluno();

            if (alunoDAO.buscarPorMatricula(aluno.getMatricula()) != null) {
                alunoView.mostrarMensagem("Erro: Matrícula já existe!");
                return;
            }

            alunoDAO.inserir(aluno);
            alunoView.mostrarMensagem("Aluno cadastrado com sucesso!");

        } catch (Exception e) {
            alunoView.mostrarMensagem("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }

    private void listarAlunos(){
        try {
            List<Aluno> alunos = alunoDAO.buscarTodos();
            alunoView.mostrarAlunos(alunos);
        } catch (Exception e){
            alunoView.mostrarMensagem("Erro ao listar alunos: " + e.getMessage());
        }
    }

    private void buscarAluno(){
        try{
            String matricula = alunoView.obterMatricula();
            Aluno aluno = alunoDAO.buscarPorMatricula(matricula);

            if (aluno != null) {
                alunoView.mostrarAluno(aluno);
            } else {
                alunoView.mostrarMensagem("Aluno não encontrado.");
            }
        } catch (Exception e) {
            alunoView.mostrarMensagem("Erro ao buscar aluno: " + e.getMessage());
        }
    }

    public void atualizarAluno(){
        try{
            String matricula = alunoView.obterMatricula();
            Aluno alunoExistente = alunoDAO.buscarPorMatricula(matricula);

            if (alunoExistente == null){
                alunoView.mostrarMensagem("Aluno não encontrado.");
                return;
            }

            alunoView.mostrarAluno(alunoExistente);
            Aluno dadosAtualizados = alunoView.obterDadosAtualizacao(alunoExistente);

            alunoDAO.atualizar(dadosAtualizados);
            alunoView.mostrarMensagem("Aluno atualizado com sucesso!");

        } catch (Exception e) {
            alunoView.mostrarMensagem("Erro ao atualizar aluno:" + e.getMessage());
        }
    }

    private void excluirAluno(){
        try{
            String matricula = alunoView.obterMatricula();
            Aluno aluno = alunoDAO.buscarPorMatricula(matricula);

            if(aluno == null){
                alunoView.mostrarMensagem("Aluno não encontrado.");
                return;
            }

            alunoView.mostrarAluno(aluno);

            if (alunoView.confirmarExclusao()){
                alunoDAO.excluir(aluno.getId());
                alunoView.mostrarMensagem("Aluno excluído com sucesso!");
            } else {
                alunoView.mostrarMensagem("Exclusão cancelada.");
            }
        } catch (Exception e){
            alunoView.mostrarMensagem("Erro ao excluir aluno: " + e.getMessage());
        }
    }
}
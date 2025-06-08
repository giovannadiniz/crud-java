package br.com.model;

public class Turma {
    private int id;
    private String nome;
    private Professor idProfessor;
    private Aluno idAluno;

    public Turma() {}

    public Turma(String nome, Professor idProfessor, Aluno idAluno) {
        this.nome = nome;
        this.idProfessor = idProfessor;
        this.idAluno = idAluno;
    }

    public Turma(int id, String nome, Professor idProfessor, Aluno idAluno) {
        this.id = id;
        this.nome = nome;
        this.idProfessor = idProfessor;
        this.idAluno = idAluno;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Professor getIdProfessor() {
        return idProfessor;
    }
    public void setIdProfessor(Professor idProfessor) {
        this.idProfessor = idProfessor;
    }
    public Aluno getIdAluno() {
        return idAluno;
    }
    public void setIdAluno(Aluno idAluno) {
        this.idAluno = idAluno;
    }

    @Override
    public String toString() {
        return "Turma{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idProfessor=" + idProfessor +
                ", idAluno=" + idAluno +
                '}' +
                "\n";
    }
}

package br.com.model;

public class Professor {
    private int id;
    private String nome;
    private String turma;

    public Professor() {}

    public Professor(String nome, String turma) {
        this.nome = nome;
        this.turma = turma;
    }

    public Professor(int id, String nome, String turma) {
        this.id = id;
        this.nome = nome;
        this.turma = turma;
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
    public String getTurma() {
        return turma;
    }
    public void setTurma(String turma) {
        this.turma = turma;
    }

    @Override
    public String toString(){
        return "Professor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", turma='" + turma + '\'' +
                '}';
    }
}

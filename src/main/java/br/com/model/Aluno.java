package br.com.model;

public class Aluno {

    private int id;
    private String nome;
    private String matricula;
    private int turma;

    public Aluno() {}

    public Aluno(int id, String nome, String matricula, int turma){
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.turma = turma;
    }

    public Aluno(String nome, String matricula, int turma) {
        this.nome = nome;
        this.matricula = matricula;
        this.turma = turma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula(){
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getTurma(){
        return turma;
    }

    public void setTurma(int  idTurma) {
        this.turma = turma;
    }

    @Override
    public String toString(){
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", idTurma='" + turma + '\'' +
                '}';
    }

}

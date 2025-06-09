package br.com.model;

public class TurmaDetalhada {
    private Long turmaId;
    private String turmaNome;
    private Long alunoId;
    private String alunoNome;
    private Long professorId;
    private String professorNome;

    public TurmaDetalhada(Long turmaId, String turmaNome, Long alunoId, String alunoNome, Long professorId, String professorNome) {
        this.turmaId = turmaId;
        this.turmaNome = turmaNome;
        this.alunoId = alunoId;
        this.alunoNome = alunoNome;
        this.professorId = professorId;
        this.professorNome = professorNome;
    }

    public Long getTurmaId() { return turmaId; }
    public String getTurmaNome() { return turmaNome; }
    public Long getAlunoId() { return alunoId; }
    public String getAlunoNome() { return alunoNome; }
    public Long getProfessorId() { return professorId; }
    public String getProfessorNome() { return professorNome; }
}

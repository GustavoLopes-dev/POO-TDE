import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario {
    private static final int MAX_TURMAS = 10;
    private static final int MAX_DISCIPLINAS = 10;
    private static final int MAX_SALAS = 10;

    private String emailAcad;
    private String curso;
    private List<Turma> turmas = new ArrayList<>();
    private List<Disciplina> disciplinas = new ArrayList<>();
    private List<Sala> salas = new ArrayList<>();

    public Aluno(String nome, String cpf, Integer matricula, String email, String emailAcad, Curso curso,
                 List<Disciplina> disciplinas, List<Turma> turmas, List<Sala> salas) {
        super(nome, cpf, matricula, email);

        this.emailAcad = (emailAcad == null || emailAcad.isEmpty())
                ? super.getNome().toLowerCase() + "@educorp.com"
                : emailAcad;

        if (turmas != null) {
            this.turmas.addAll(turmas);
        }

        if (disciplinas != null) {
            this.disciplinas.addAll(disciplinas);
        }

        if (salas != null) {
            this.salas.addAll(salas);
        }
    }

    public String getEmailAcad() {
        return emailAcad;
    }

    public void setEmailAcad(String emailAcad) {
        this.emailAcad = emailAcad;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void addTurma(Turma novaTurma) {
        if (turmas.size() < MAX_TURMAS) {
            turmas.add(novaTurma);
            System.out.println("Turma adicionada com sucesso!");
        } else {
            System.out.println("Limite de turmas atingido. Não foi possível adicionar a turma.");
        }
    }

    public Disciplina getDisciplina(int posicao) {
        if (posicao >= 0 && posicao < disciplinas.size()) {
            return disciplinas.get(posicao);
        } else {
            System.out.println("Posição inválida.");
            return null;
        }
    }

    public void addDisciplina(Disciplina novaDisciplina) {
        if (disciplinas.size() < MAX_DISCIPLINAS) {
            disciplinas.add(novaDisciplina);
            System.out.println("Disciplina adicionada com sucesso!");
        } else {
            System.out.println("Limite de disciplinas atingido. Não foi possível adicionar a disciplina.");
        }
    }

    public Sala getSala(int posicao) {
        if (posicao >= 0 && posicao < salas.size()) {
            return salas.get(posicao);
        } else {
            System.out.println("Posição inválida.");
            return null;
        }
    }

    public void addSala(Sala novaSala) {
        if (salas.size() < MAX_SALAS) {
            salas.add(novaSala);
            System.out.println("Sala adicionada com sucesso!");
        } else {
            System.out.println("Limite de salas atingido. Não foi possível adicionar a sala.");
        }
    }

    public void realocarTurma(Turma turmaAtual, Turma novaTurma) {
        if (turmaAtual != null && novaTurma != null) {
            if (turmas.contains(turmaAtual)) {
                turmas.remove(turmaAtual);
                turmas.add(novaTurma);
                System.out.println("Turma realocada com sucesso!");
            } else {
                System.out.println("O aluno não está matriculado na turma atual. Não é possível realocar.");
            }
        } else {
            System.out.println("Turma atual ou nova turma é nula. Não é possível realizar a realocação.");
        }
    }

    public void removerTurma(Turma turmaRemover) {
        if (turmaRemover != null) {
            if (turmas.contains(turmaRemover)) {
                turmas.remove(turmaRemover);
                System.out.println("Turma removida com sucesso!");
            } else {
                System.out.println("O aluno não está matriculado na turma especificada. Não é possível remover.");
            }
        } else {
            System.out.println("Turma a ser removida é nula. Não é possível remover.");
        }
    }

    @Override
    public String toString() {
        return "\nAluno: " + getNome()+ " - Matricula : " + super.getMatricula() + "\n[EmailAcad : " + emailAcad + "\nCurso: " + curso + "\nTurmas :" + turmas.toString() +
            "\nDisciplinas : " + disciplinas.toString() + "\nSalas : " + salas.toString() +"]";
    }
    public String toStringDetailed() {
        return "\nAluno: " + getNome()+ " - Matricula : " + super.getMatricula() + "\n[EmailAcad : " + emailAcad + "\nCurso: " + curso + "\nTurmas :" + turmas.toString() +
            "\nDisciplinas : " + disciplinas.toString() + "\nSalas : " + salas.toString() + super.toString() + "]";
    }
}

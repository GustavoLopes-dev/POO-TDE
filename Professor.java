import java.util.ArrayList;
import java.util.List;

public class Professor extends Usuario {
    private String emailAcad;
    private String escola;
    private boolean alocado;
    private List<Disciplina> disciplinas = new ArrayList<>();
    private ArrayList<Turma> turmas = new ArrayList<>();

    public Professor(String nome, String cpf, Integer matricula, String email, List<Disciplina> disciplinas,
                     String escola) {
        super(nome, cpf, matricula, email);

        // Corrigindo a lógica de atribuição do e-mail acadêmico
        this.emailAcad = (email != null) ? email : (super.getNome() + "@educacorp.com");

        this.escola = escola;

        // Corrigindo a lógica de atribuição de 'alocado'
        this.alocado = (disciplinas != null) && !disciplinas.isEmpty();

        this.disciplinas = (disciplinas != null) ? List.copyOf(disciplinas) : List.of();
    }

    public String getEscola() {
        return escola;
    }
    public void setEscola(String escola) {
        this.escola = escola;
    }
    

    public boolean isAlocado() {
        return alocado;
    }

    public void setEmailAcad(String emailAcad) {
        this.emailAcad = emailAcad;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
    public void addDisciplina(Disciplina disciplina) {
       this.disciplinas.add(disciplina);
       this.alocado = true;
    }


    public boolean setTurmas(Turma turma) {
        this.turmas.add(turma);

        if (this.turmas.contains(turma)) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Turma> getTurmas() {
        return turmas;
    }

    public void realocarTurma(Turma turmaAtual, Turma novaTurma) {
        if (turmas.contains(turmaAtual)) {
            int indexTurmaAtual = turmas.indexOf(turmaAtual);
            turmas.add(indexTurmaAtual, novaTurma);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nTipo: Professor" +
                "\nEmailAcad: " + emailAcad + "\nEscola: " + escola +
                "\nAlocado: " + alocado + "\nDisciplinas: " + disciplinas;
    }

    public String getEmailAcad() {
        return emailAcad;
    }
}

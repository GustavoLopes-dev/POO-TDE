import java.util.ArrayList;
import java.util.Arrays;

public class Disciplina {
    private String titulo;
    private Integer codigo;
    private Integer cargaHoraria;
    private String descricao;
    private Integer aulasSemana;

    private ArrayList<Professor> professoresMinistrantes = new ArrayList<>();
    private ArrayList<Sala> salas = new ArrayList<>();
    private ArrayList<Turma> turmas = new ArrayList<>();

    // Construtor da classe Disciplina
    public Disciplina(String titulo, Integer codigo, Integer cargaHoraria, String descricao, Integer aulasSemana,
                      Professor[] professoresMinistrantes, Sala[] salas2, Sala[] labs) {
        this.titulo = (titulo != null) ? titulo.toUpperCase() : null;
        this.codigo = (codigo != null && codigo > 0) ? codigo : null;
        this.cargaHoraria = (cargaHoraria != null && cargaHoraria > 0) ? cargaHoraria : null;
        this.descricao = (descricao != null) ? descricao.toUpperCase() : "Não possui descrição.";
        this.aulasSemana = (aulasSemana != null && aulasSemana > 0) ? aulasSemana : null;

        // Inicialização das listas
        if (professoresMinistrantes != null) {
            this.professoresMinistrantes.addAll(Arrays.asList(professoresMinistrantes));
        }

        if (salas2 != null) {
            this.salas.addAll(Arrays.asList(salas2));
        }

        if (labs != null) {
            this.turmas.addAll(Arrays.asList(labs));
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getAulasSemana() {
        return aulasSemana;
    }

    public ArrayList<Professor> getProfessoresMinistrantes() {
        return professoresMinistrantes;
    }

    public void setNewProfMinistrante(Professor professor) {
        professoresMinistrantes.add(professor);
    } 

    public ArrayList<Sala> getSalas() {
        return salas;
    }

    public ArrayList<Turma> getTurmas() {
        return turmas;
    }

    @Override
    public String toString() {
        return "\nDisciplina: \n[Titulo : " + titulo + "\nCodigo : " + codigo + "\nCarga Horaria=" + cargaHoraria +
                "\nDescrica : " + descricao + "\n Aulas por semana : " + aulasSemana +
                "\nProfessores Ministrantes " + professoresMinistrantes.toString() + "\nSalas : " + salas +
                "\nTurmas : " + turmas + "]";
    }
}

import java.util.ArrayList;

public class Curso {
    private int id;
    private String titulo;
    private final int LIMITE_ALUNOS = 200;
    private ArrayList<Aluno> alunos = new ArrayList<>();

    public Curso(String titulo, int id) {
        this.titulo = titulo;
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String listarAlunos() {
        // Retornar uma representação mais controlada da lista de alunos (por exemplo, formatada)
        return alunos.toString();
    }

    public void adicionarAluno(Aluno aluno) {
        if (aluno != null && alunos.size() < LIMITE_ALUNOS) {
            alunos.add(aluno);
            System.out.println("Aluno " + aluno.getNome() + " adicionado ao curso " + titulo + ".");
        } else {
            System.out.println("Limite de alunos atingido para o curso " + titulo + ". Não foi possível adicionar o aluno.");
        }
    }

    public int getNumeroAlunos() {
        return alunos.size();
    }

    public int getVagasRestantes() {
        return LIMITE_ALUNOS - alunos.size();
    }

    @Override
    public String toString() {
        return "Curso [ID : " + id + ", Titulo : " + titulo + ", Aluno : " + alunos.toString() + "]";
    }

    
}

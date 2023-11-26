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
        StringBuilder result = new StringBuilder("Alunos no curso " + titulo + ":\n");

        for (Aluno aluno : alunos) {
            result.append(aluno.toString()).append("\n");
        }

        return result.toString();
    }

    public void adicionarAluno(Aluno aluno) {
        if (aluno != null && !alunos.contains(aluno) && alunos.size() < LIMITE_ALUNOS) {
            alunos.add(aluno);
            System.out.println("Aluno " + aluno.getNome() + " adicionado ao curso " + titulo + ".");
        } else {
            System.out.println("Não foi possível adicionar o aluno ao curso " + titulo + ".");
        }
    }

    public int getNumeroAlunos() {
        return alunos.size();
    }

    public int getVagasRestantes() {
        return LIMITE_ALUNOS - alunos.size();
    }

    public int getId() {
        return id;
    }


    
    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    @Override
    public String toString() {
        return "Curso [ID : " + id + ", Titulo : " + titulo + ", Alunos : " + alunos.toString() + "]";
    }

    
}

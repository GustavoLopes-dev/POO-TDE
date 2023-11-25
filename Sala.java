import java.util.ArrayList;

public class Sala extends Turma {
    private Integer numSala;
    private Professor profMinistrante;

    public Sala(String titulo, Integer codigo, Integer cargaHoraria, String descricao, Integer aulasSemana,
            Professor[] professoresMinistrantes, Sala[] salas, Sala[] labs, Integer vagas, Integer numSala,
            ArrayList<String> horarios) {
        super(titulo, codigo, cargaHoraria, descricao, aulasSemana, professoresMinistrantes, salas, labs);

        if (numSala != null) {
            this.numSala = numSala;
        } else {
            throw new NullPointerException("A sala necessita obrigatóriamente de um número para ser criada");
        }
        this.profMinistrante = null;
    }

    public void alocaProfMinistrante(Professor profMinistrante) {
        this.profMinistrante = profMinistrante;
    }

    public void setNumSala(Integer num) {
        this.numSala = num;
    }

    @Override
    public String toString() {
        return "Sala : \n[Número da Sala : " + numSala + "\nProfessor Ministrante : " + profMinistrante + "] \n\n";
    }
}

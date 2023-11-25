import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Usuario {
    static final Set<Integer> matriculasGeradas = new HashSet<>();
    private String nome;
    private String cpf;
    private Integer matricula;
    private String email;

    public Usuario(String nome, String cpf, Integer matricula, String email) {
        this.nome = nome;
        this.cpf = cpf;

        if (matricula == null) {
            this.matricula = gerarMatricula();
        } else {
            this.matricula = matricula;
        }
        this.email = email;
    }

    public int gerarMatricula() {
        Random random = new Random();
        int matricula;

        do {
            matricula = 100 + random.nextInt(900); // Gera um número aleatório de 3 dígitos
        } while (matriculasGeradas.contains(matricula));

        matriculasGeradas.add(matricula);
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "\n Usuario - Visão Geral \n" +
                "Nome: " + nome + "\nCPF: " + cpf +
                "\nMatricula: " + matricula + "\nEmail: " + email;
    }

}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Main {
    private static int idMapProf = 0;
    private static int idMapAluno = 0;
    private static int idMapTurma = 0;
    private static int idMapDisciplina = 0;

    private static final int GESTAO_ALUNOS = 1;
    private static final int GESTAO_PROFESSORES = 2;
    private static final int GESTAO_TURMAS = 3;
    private static final int GESTAO_DISCIPLINAS = 4;
    private static final int IMPRESSOES = 5;

    public static int getIdMapProf() {
        return idMapProf;
    }

    public static void setIdMapProf() {
        idMapProf += 1;
    }

    public static int getIdMapAluno() {
        return idMapAluno;
    }

    public static void setIdMapAluno() {
        idMapAluno += 1;
    }

    public static int getIdMapTurma() {
        return idMapTurma;
    }

    public static void setIdMapTurma() {
        idMapTurma += 1;
    }

    static Map<Integer, Professor> professores = new HashMap<>();
    static Map<Integer, Aluno> alunos = new HashMap<>();
    static Map<Integer, Turma> turmas = new HashMap<>();
    static Map<Integer, Disciplina> disciplinas = new HashMap<>();
    static Map<Integer, Sala> salas = new HashMap<>();

    static ArrayList<Curso> cursos = new ArrayList<>();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int etapa = 0;
        int selectOut = 0;
        int selectIn = 0;

        try {
            do {
                System.out.println("SELECIONE A OPÇÃO DESEJADA: ");
                System.out.println(GESTAO_ALUNOS + " - GESTÃO DE ALUNOS");
                System.out.println(GESTAO_PROFESSORES + " - GESTÃO DE PROFESSORES");
                System.out.println(GESTAO_TURMAS + " - GESTÃO DE TURMAS");
                System.out.println(GESTAO_DISCIPLINAS + " - GESTÃO DE DISCIPLINAS");

                System.out.printf("OPÇÃO: ");

                selectOut = input.nextInt();
                etapa += 1;

                switch (selectOut) {
                    case GESTAO_ALUNOS:
                        etapa += 1;
                        menuGestaoAlunos(input);
                        break;

                    case GESTAO_PROFESSORES:
                        etapa += 1;
                        menuGestaoProfessores(input);
                        break;

                    case GESTAO_TURMAS:
                        etapa += 1;
                        // menuGestaoTurmas(input);
                        break;

                    case GESTAO_DISCIPLINAS:
                        etapa += 1;
                        // menuGestaoProfessores(input);
                        break;
                    case IMPRESSOES:

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } while (selectOut != 0);
        } catch (InputMismatchException e) {
            System.err.println("O tipo de entrada apresentado é inválido : " + e);
            System.err.println("O erro " + e + " foi detectado na etapa " + etapa + " do processo.");
        }
    }

    public static void menuGestaoAlunos(Scanner input) {
        int selectIn = 0;

        System.out.println("\n1 - CADASTRO DE ALUNOS");
        System.out.println("2 - MATRICULA DE ALUNOS");
        System.out.println("3 - ALOCAÇÃO DE ALUNOS");
        System.out.println("4 - REALOCAÇÃO DE ALUNOS");
        System.out.println("5 - VISUALIZAR ALUNOS");
        System.out.println("6 - ACESSAR DADOS DE ALUNOS");
        System.out.println("7 - EDITAR ALUNO");

        int idSelecionado;
        System.out.printf("OPÇÃO: ");
        selectIn = input.nextInt();

        switch (selectIn) {
            case 1:
                cadastroAluno(input);
                break;

            case 2:
                realizaMatricula(input);
                break;

            case 3:
                alocacaoAluno(input);
                break;
            case 4:
                realocarAluno(input);
                break;
            case 5:
                visualizarAlunos();
                break;

            case 6:
                dadosAluno(input);
                break;

            case 7:
                editarAluno(input);
                break;

            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
        }
    }

    public static void menuGestaoProfessores(Scanner input) {
        int selectIn = 0;

        System.out.println("\n1 - CADASTRO DE PROFESSORES");
        System.out.println("2 - ALOCAÇÃO DE PROFESSORES");
        System.out.println("3 - REALOCAÇÃO DE PROFESSORES");
        System.out.println("4 - VISUALIZAR PROFESSORES");
        System.out.println("5 - ACESSAR DADOS DE PROFESSORES");
        System.out.println("6 - EDITAR PROFESSORES");

        int idSelecionado;
        System.out.printf("OPÇÃO: ");
        selectIn = input.nextInt();

        switch (selectIn) {
            case 1:
                cadastroProfessor(input);
                break;

            case 2:
                alocacaoProfessor(input);

            case 3:
                realocacaoProfessor(input);

            case 4:
                visualizarProfessores();

            case 5:
                dadosAluno(input);

            case 6:
                editarProfessor(input);

            default:
                break;
        }

    }

    public static void cadastroAluno(Scanner input) {
        try {

        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.printf("INICIANDO CADASTRO DE ALUNO...\n\n");
        System.out.printf("\nNOME: ");
        String nomeCompleto = input.nextLine();

        System.out.printf("\nCPF: ");
        Long cpf = input.nextLong();
        input.nextLine(); // Consumir a quebra de linha após a leitura de long

        System.out.printf("\nMATRICULA (3 DÍGITOS):");
        Integer matricula = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha após a leitura de int

        System.out.printf("\nEMAIL: ");
        String email = input.nextLine();

        System.out.printf("\nEMAIL ACADÊMICO: ");
        String emailAcad = input.nextLine();

        Aluno aluno = new Aluno(nomeCompleto, cpf, matricula, email, emailAcad, null, null, null, null);
        alunos.put(matricula, aluno);
        if (turmas != null) {

            System.out.println("\nTURMA DISPONÍVEIS: ");
            for (Entry<Integer, Turma> entry : turmas.entrySet()) {
                Integer chave = entry.getKey();
                Turma valor = entry.getValue();
                System.out.println("ID" + chave + ", Valor: " + valor.toString());
            }

            System.out.println("SELECIONE O ID PARA A TURMA QUE O USUÁRIO SERÁ VINCULADO: ");
            Integer idSelect = input.nextInt();

            // Verifica se a turma existe antes de vincular o aluno
            if (turmas.containsKey(idSelect)) {
                aluno.addTurma(turmas.get(idSelect));
                System.out.println("Aluno vinculado à turma com sucesso!");
            } else {
                System.out.println("ID de turma inválido. Não foi possível vincular o aluno.");
            }
        }

        if (disciplinas != null) {
            System.out.println("\nDISCIPLINAS DISPONÍVEIS: ");
            for (Entry<Integer, Disciplina> entry : disciplinas.entrySet()) {
                Integer chave = entry.getKey();
                Disciplina valor = entry.getValue();
                System.out.println("ID" + chave + ", Valor: " + valor.toString());
            }

            System.out.println("SELECIONE O ID PARA A DISCIPLINA QUE O USUÁRIO SERÁ VINCULADO: ");
            Integer idSelect = input.nextInt();

            // Verifica se a turma existe antes de vincular o aluno
            if (disciplinas.containsKey(idSelect)) {
                aluno.addDisciplina(disciplinas.get(idSelect));
                System.out.println("Aluno vinculado à disciplina com sucesso!");
            } else {
                System.out.println("ID de disciplina inválido. Não foi possível vincular o aluno.");
            }

        }

        if (salas != null) {
            System.out.println("\nSALAS DISPONÍVEIS: ");
            for (Entry<Integer, Sala> entry : salas.entrySet()) {
                Integer chave = entry.getKey();
                Sala valor = entry.getValue();
                System.out.println("ID" + chave + ", Valor: " + valor.toString());
            }

            System.out.println("SELECIONE O ID PARA A SALA QUE O USUÁRIO SERÁ VINCULADO: ");
            Integer idSelect = input.nextInt();

            // Verifica se a turma existe antes de vincular o aluno
            if (salas.containsKey(idSelect)) {
                aluno.addDisciplina(salas.get(idSelect));
                System.out.println("Aluno vinculado à sala com sucesso!");
            } else {
                System.out.println("ID de sala inválido. Não foi possível vincular o aluno.");
            }

        }

    }

    public static void realizaMatricula(Scanner input) {
        if (alunos != null && alunos.size() >= 0 && !(alunos.isEmpty())) {
            visualizarAlunos();

            // Capturar a entrada do usuário para selecionar um aluno
            System.out.print("Digite o ID do aluno desejado: ");
            int idSelecionado = input.nextInt();
            input.nextLine(); // Consumir a quebra de linha após a leitura de int

            // Verificar se o ID selecionado corresponde a um aluno existente
            if (alunos.containsKey(idSelecionado)) {
                Aluno alunoSelecionado = alunos.get(idSelecionado);
                // Agora você pode usar 'alunoSelecionado' para realizar a matrícula no curso
                System.out.println("Aluno selecionado: " + alunoSelecionado.getNome());
                matriculaAluno(alunoSelecionado, cursos, input);
            } else {
                System.out.println("ID de aluno inválido. Tente novamente.");
            }
        } else {
            throw new NullPointerException("Não há alunos cadastrados.");
        }
    }

    public static void matriculaAluno(Aluno aluno, ArrayList<Curso> cursos, Scanner input) {
        if (aluno != null && aluno.getCurso() == null) {
            System.out.printf("CURSO QUE O ALUNO SERÁ VINCULADO: ");
            String cursoLocacao = input.nextLine();

            Curso cursoEncontrado = null;

            for (Curso curso : cursos) {
                if (curso.getTitulo().equalsIgnoreCase(cursoLocacao)) {
                    cursoEncontrado = curso;
                    break; // Se encontrado, saia do loop
                }
            }

            if (cursoEncontrado != null) {
                aluno.setCurso(cursoEncontrado.getTitulo());
                System.out.println("Aluno matriculado no curso: " + cursoEncontrado.getTitulo());

            } else {
                System.out.println("Curso não encontrado.");
            }
        }
    }

    public static void alocacaoAluno(Scanner input) {
        int idSelecionado;

        if (turmas != null && alunos != null) {
            // Exibir alunos cadastrados
            System.out.println("\nALUNOS CADASTRADOS: ");
            for (Entry<Integer, Aluno> entry : alunos.entrySet()) {
                Integer chave = entry.getKey();
                Aluno valor = entry.getValue();
                System.out.println("ID" + chave + ", Valor: " + valor.toString());
            }

            // Exibir turmas cadastradas
            System.out.println("\nTURMAS CADASTRADAS: ");
            for (Entry<Integer, Turma> entry : turmas.entrySet()) {
                Integer chave = entry.getKey();
                Turma valor = entry.getValue();
                System.out.println("ID" + chave + ", Valor: " + valor.toString());
            }

            // Capturar a entrada do usuário para selecionar um aluno
            System.out.print("Digite o ID do aluno desejado: ");
            idSelecionado = input.nextInt();
            input.nextLine(); // Consumir a quebra de linha após a leitura de int

            // Verificar se o ID selecionado corresponde a um aluno existente
            if (alunos.containsKey(idSelecionado)) {
                Aluno alunoSelecionado = alunos.get(idSelecionado);
                // Exibir aluno selecionado
                System.out.println("Aluno selecionado: " + alunoSelecionado.getNome());

                // Capturar a entrada do usuário para selecionar uma turma
                System.out.print("Digite o ID da turma desejada: ");
                idSelecionado = input.nextInt();
                input.nextLine(); // Consumir a quebra de linha após a leitura de int

                // Verificar se o ID selecionado corresponde a uma turma existente
                if (turmas.containsKey(idSelecionado)) {
                    Turma turmaSelecionada = turmas.get(idSelecionado);
                    // Alocar aluno na turma selecionada
                    turmaSelecionada.adicionaAluno(alunoSelecionado);
                    System.out.println("Aluno alocado na turma com sucesso.");
                } else {
                    System.out.println("ID de turma inválido. Tente novamente.");
                }
            } else {
                System.out.println("ID de aluno inválido. Tente novamente.");
            }
        }

    }

    public static void realocarAluno(Scanner input) {
        visualizarAlunos();

        // Capturar a entrada do usuário para selecionar um aluno
        System.out.print("Digite o ID do aluno desejado: ");
        int idSelecionado = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha após a leitura de int

        // Verificar se o ID selecionado corresponde a um aluno existente
        if (alunos.containsKey(idSelecionado)) {
            Aluno alunoSelecionado = alunos.get(idSelecionado);
            // Agora você pode usar 'alunoSelecionado' para realizar a matrícula no curso
            System.out.println("Aluno selecionado: " + alunoSelecionado.getNome());
            matriculaAluno(alunoSelecionado, cursos, input);
            realocacaoAluno(input);
        } else {
            System.out.println("ID de aluno inválido. Tente novamente.");
        }
    }

    public static void realocacaoAluno(Scanner input) {
        // Exibir a lista de alunos cadastrados
        visualizarAlunos();

        // Capturar a entrada do usuário para selecionar um aluno para realocação
        System.out.print("Digite o ID do aluno que deseja realocar: ");
        int idAluno = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha após a leitura de int

        // Verificar se o ID selecionado corresponde a um aluno existente
        if (alunos.containsKey(idAluno)) {
            Aluno alunoParaRealocar = alunos.get(idAluno);
            // Exibir o aluno selecionado
            System.out.println("Aluno selecionado para realocação: " + alunoParaRealocar.getNome());

            // Exibir a lista de turmas do aluno para que o usuário escolha qual realocar
            System.out.println("\nTURMAS DO ALUNO:");
            for (Turma turma : alunoParaRealocar.getTurmas()) {
                System.out.println("ID" + turma.getId() + ", Valor: " + turma.toString());
            }

            // Capturar a entrada do usuário para selecionar a turma atual
            System.out.print("Digite o ID da turma atual do aluno: ");
            int idTurmaAtual = input.nextInt();
            input.nextLine(); // Consumir a quebra de linha após a leitura de int

            // Capturar a entrada do usuário para selecionar a nova turma
            System.out.print("Digite o ID da nova turma: ");
            int idNovaTurma = input.nextInt();
            input.nextLine(); // Consumir a quebra de linha após a leitura de int

            // Verificar se o ID selecionado corresponde a turmas existentes
            Turma turmaAtual = null;
            Turma novaTurma = null;
            for (Turma turma : alunoParaRealocar.getTurmas()) {
                if (turma.getId() == idTurmaAtual) {
                    turmaAtual = turma;
                }
                if (turma.getId() == idNovaTurma) {
                    novaTurma = turma;
                }
            }

            if (turmaAtual != null && novaTurma != null) {
                // Realizar a realocação do aluno para a nova turma
                alunoParaRealocar.realocarTurma(turmaAtual, novaTurma);
                System.out.println("Aluno realocado com sucesso para a turma: " + novaTurma.getId());
            } else {
                System.out.println("ID de turma inválido. A realocação não foi realizada.");
            }
        } else {
            System.out.println("ID de aluno inválido. A realocação não foi realizada.");
        }

    }

    public static void editarAluno(Scanner input) {
        visualizarAlunos();

        System.out.print("Digite o ID do aluno que deseja editar: ");
        int idAluno = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha após a leitura de int

        // Verificar se o ID selecionado corresponde a um aluno existente
        if (alunos.containsKey(idAluno)) {
            Aluno alunoParaEditar = alunos.get(idAluno);
            // Exibir o aluno selecionado
            System.out.println("Aluno selecionado para edição: " + alunoParaEditar.getNome());

            // Agora você pode permitir a edição de diferentes campos do aluno, por exemplo:
            System.out.print("Digite o novo nome do aluno (ou pressione Enter para manter o mesmo): ");
            String novoNome = input.nextLine();
            if (!novoNome.isEmpty()) {
                alunoParaEditar.setNome(novoNome);
            }

            System.out.print("Digite o novo email do aluno (ou pressione Enter para manter o mesmo): ");
            String novoEmail = input.nextLine();
            if (!novoEmail.isEmpty()) {
                alunoParaEditar.setEmail(novoEmail);
            }

            System.out.print("Digite o novo email acadêmico do aluno (ou pressione Enter para manter o mesmo): ");
            String novoEmailAcad = input.nextLine();
            if (!novoEmailAcad.isEmpty()) {
                alunoParaEditar.setEmailAcad(novoEmailAcad);
            }

            // Adicione outros campos conforme necessário

            System.out.println("Dados do aluno atualizados com sucesso: " + alunoParaEditar.toString());
        } else {
            System.out.println("ID de aluno inválido. Não foi possível realizar a edição.");
        }
    }

    public static void visualizarAlunos() {
        System.out.println("\nALUNOS CADASTRADOS: ");
        for (Entry<Integer, Aluno> entry : alunos.entrySet()) {
            Integer chave = entry.getKey();
            Aluno valor = entry.getValue();
            System.out.println("ID" + chave + ", Valor: " + valor.toString());
        }

    }

    public static void dadosAluno(Scanner input) {
        visualizarAlunos();

        // Capturar a entrada do usuário para selecionar um aluno
        System.out.printf("Digite o ID do aluno desejado: ");
        int idSelecionado = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha após a leitura de int

        // Verificar se o ID selecionado corresponde a um aluno existente
        if (alunos.containsKey(idSelecionado)) {
            Aluno alunoSelecionado = alunos.get(idSelecionado);
            // Agora você pode usar 'alunoSelecionado' para realizar a matrícula no curso
            System.out.println("Dados: [\n" + alunoSelecionado.toString() + "]");
        }

    }

    public static void cadastroProfessor(Scanner input) {
        System.out.printf("INICIANDO CADASTRO DE PROFESSOR...\n\n");
        System.out.printf("\nNOME: ");
        String nomeCompleto = input.nextLine();
        input.nextLine();

        System.out.printf("\nCPF: ");
        Long cpf = input.nextLong();
        input.nextLine(); // Consumir a quebra de linha após a leitura de long

        System.out.printf("\nMATRICULA (3 DÍGITOS):");
        Integer matricula = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha após a leitura de int

        System.out.printf("\nEMAIL: ");
        String email = input.nextLine();

        System.out.printf("\nEMAIL ACADÊMICO: ");
        String emailAcad = input.nextLine();

        Professor professor = new Professor(nomeCompleto, cpf, matricula, email, null, emailAcad);
        professores.put(matricula, professor);

        if (disciplinas != null && disciplinas.size() >= 0 && !(disciplinas.isEmpty())) {
            System.out.println("\nDISCIPLINAS DISPONÍVEIS: ");
            for (Entry<Integer, Disciplina> entry : disciplinas.entrySet()) {
                Integer chave = entry.getKey();
                Disciplina valor = entry.getValue();
                System.out.println("ID" + chave + ", Valor: " + valor.toString());
            }

            System.out.println("SELECIONE O ID PARA A DISCIPLINA QUE O PROFESSOR SERÁ VINCULADO: ");
            Integer idSelect = input.nextInt();

            // Verifica se a turma existe antes de vincular o aluno
            if (disciplinas.containsKey(idSelect)) {
                professor.addDisciplina(disciplinas.get(idSelect));
                disciplinas.get(idSelect).setNewProfMinistrante(professor);
                System.out.println("Professor vinculado à disciplina com sucesso!");
            } else {
                System.out.println("ID de disciplina inválido. Não foi possível vincular o aluno.");
            }
        }

    }

    public static void alocacaoProfessor(Scanner input) {
        int idSelecionado;

        if (turmas != null && professores != null) {
            // Exibir alunos cadastrados
            System.out.println("\nPROFESSORES CADASTRADOS: ");
            for (Entry<Integer, Professor> entry : professores.entrySet()) {
                Integer chave = entry.getKey();
                Professor valor = entry.getValue();
                System.out.println("ID" + chave + ", Valor: " + valor.toString());
            }

            // Exibir turmas cadastradas
            System.out.println("\nTURMAS CADASTRADAS: ");
            for (Entry<Integer, Turma> entry : turmas.entrySet()) {
                Integer chave = entry.getKey();
                Turma valor = entry.getValue();
                System.out.println("ID" + chave + ", Valor: " + valor.toString());
            }

            // Capturar a entrada do usuário para selecionar um professor
            System.out.print("Digite o ID do professor desejado: ");
            idSelecionado = input.nextInt();
            input.nextLine(); // Consumir a quebra de linha após a leitura de int

            // Verificar se o ID selecionado corresponde a um prof existente
            if (professores.containsKey(idSelecionado)) {
                Professor professorSelecionado = professores.get(idSelecionado);
                // Exibir prof selecionado
                System.out.println("Aluno selecionado: " + professorSelecionado.getNome());

                // Capturar a entrada do usuário para selecionar uma turma
                System.out.print("Digite o ID da turma desejada: ");
                idSelecionado = input.nextInt();
                input.nextLine(); // Consumir a quebra de linha após a leitura de int

                // Verificar se o ID selecionado corresponde a uma turma existente
                if (turmas.containsKey(idSelecionado)) {
                    Turma turmaSelecionada = turmas.get(idSelecionado);
                    // Alocar aluno na turma selecionada
                    turmaSelecionada.setProfessor(professorSelecionado);
                    professorSelecionado.setTurmas(turmaSelecionada);
                    ;
                    System.out.println("Professor alocado na turma com sucesso.");
                } else {
                    System.out.println("ID de turma inválido. Tente novamente.");
                }
            } else {
                System.out.println("ID de professor inválido. Tente novamente.");
            }
        }

    }

    public static void realocacaoProfessor(Scanner input) {
        // Exibir a lista de alunos cadastrados
        visualizarProfessores();

        // Capturar a entrada do usuário para selecionar um aluno para realocação
        System.out.print("Digite o ID do professor que deseja realocar: ");
        int idProf = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha após a leitura de int

        // Verificar se o ID selecionado corresponde a um aluno existente
        if (professores.containsKey(idProf)) {
            Professor professorParaRealocar = professores.get(idProf);
            // Exibir o aluno selecionado
            System.out.println("Professor selecionado para realocação: " + professorParaRealocar.getNome());

            // Exibir a lista de turmas do aluno para que o usuário escolha qual realocar
            System.out.println("\nTURMAS DO PROFESSOR:");
            for (Turma turma : professorParaRealocar.getTurmas()) {
                System.out.println("ID da Turma: " + turma.getId());
            }

            // Capturar a entrada do usuário para selecionar a turma atual
            System.out.print("Digite o ID da turma do professor que deseja realocação: ");
            int idTurmaAtual = input.nextInt();
            input.nextLine(); // Consumir a quebra de linha após a leitura de int

            // Verificar se a turma atual existe
            Turma turmaAtual = turmas.get(idTurmaAtual);
            if (turmaAtual != null) {
                // Exibir a lista de todas as turmas disponíveis
                System.out.println("\nTURMAS CADASTRADAS: ");
                for (Entry<Integer, Turma> entry : turmas.entrySet()) {
                    Integer chave = entry.getKey();
                    Turma valor = entry.getValue();
                    System.out.println("ID" + chave + ", Valor: " + valor.toString());
                }

                // Capturar a entrada do usuário para selecionar a nova turma
                System.out.print("Digite o ID da nova turma: ");
                int idNovaTurma = input.nextInt();
                input.nextLine(); // Consumir a quebra de linha após a leitura de int

                // Verificar se a nova turma existe
                Turma novaTurma = turmas.get(idNovaTurma);
                if (novaTurma != null) {
                    // Realizar a realocação do professor para a nova turma
                    professorParaRealocar.realocarTurma(turmaAtual, novaTurma); // Atualiza as turmas dentro de
                                                                                // professor
                    turmas.get(turmaAtual).setProfessor(professorParaRealocar); // atualiza o professor dentro de
                    System.out.println("Professor realocado com sucesso para a turma: " + novaTurma.getId());
                } else {
                    System.out.println("ID de nova turma inválido. A realocação não foi realizada.");
                }
            } else {
                System.out.println("ID de aluno inválido. A realocação não foi realizada.");
            }
        }
    }

    public static void visualizarProfessores() {
        if (professores != null) {
            System.out.println("\nPROFESSORES CADASTRADOS: ");
            for (Entry<Integer, Professor> entry : professores.entrySet()) {
                Integer chave = entry.getKey();
                Professor valor = entry.getValue();
                System.out.println("ID" + chave + ", Valor: " + valor.toString());
            }

        } else {
            throw new NullPointerException("Professores não pode estar vazia.");
        }

    }

    public static void dadosProfessor(Scanner input) {
        visualizarAlunos();

        // Capturar a entrada do usuário para selecionar um aluno
        System.out.printf("Digite o ID do aluno desejado: ");
        int idSelecionado = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha após a leitura de int

        // Verificar se o ID selecionado corresponde a um aluno existente
        if (alunos.containsKey(idSelecionado)) {
            Aluno alunoSelecionado = alunos.get(idSelecionado);
            // Agora você pode usar 'alunoSelecionado' para realizar a matrícula no curso
            System.out.println("Dados: [\n" + alunoSelecionado.toString() + "]");
        }

    }

    public static void editarProfessor(Scanner input) {
        visualizarProfessores();

        System.out.print("Digite o ID do professor que deseja editar: ");
        int idProf = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha após a leitura de int

        // Verificar se o ID selecionado corresponde a um aluno existente
        if (professores.containsKey(idProf)) {
            Professor professorParaEditar = professores.get(idProf);
            // Exibir o aluno selecionado
            System.out.println("Professor selecionado para edição: " + professorParaEditar.getNome());

            // Agora você pode permitir a edição de diferentes campos do aluno, por exemplo:
            System.out.print("Digite o novo nome do professor (ou pressione Enter para manter o mesmo): ");
            String novoNome = input.nextLine();
            if (!novoNome.isEmpty()) {
                professorParaEditar.setNome(novoNome);
            }

            System.out.print("Digite o novo email do aluno (ou pressione Enter para manter o mesmo): ");
            String novoEmail = input.nextLine();
            if (!novoEmail.isEmpty()) {
                professorParaEditar.setEmail(novoEmail);
            }

            System.out.print("Digite o novo email acadêmico do aluno (ou pressione Enter para manter o mesmo): ");
            String novoEmailAcad = input.nextLine();
            if (!novoEmailAcad.isEmpty()) {
                professorParaEditar.setEmailAcad(novoEmailAcad);
            }

            // Adicione outros campos conforme necessário

            System.out.println("Dados do aluno atualizados com sucesso: " + professorParaEditar.toString());
        } else {
            System.out.println("ID de aluno inválido. Não foi possível realizar a edição.");
        }
    }

    public static void visualizarDisciplinas() {
        if (disciplinas != null) {
            System.out.println("\nDISCIPLINAS DISPONÍVEIS: ");
            for (Entry<Integer, Disciplina> entry : disciplinas.entrySet()) {
                Integer chave = entry.getKey();
                Disciplina valor = entry.getValue();
                System.out.println("ID" + chave + ", Valor: " + valor.toString());
            }

        } else {
            throw new NullPointerException("Displinas não pode estar vazia.");
        }
    }

    public static void visualizarTurmas() {
        if (turmas != null) {
            System.out.println("\nTURMAS EXISTENTES: ");
            for (Entry<Integer, Turma> entry : turmas.entrySet()) {
                Integer chave = entry.getKey();
                Turma valor = entry.getValue();
                System.out.println("ID" + chave + ", Valor: " + valor.toString());
            }

        } else {
            throw new NullPointerException("Turmas não pode estar vazia.");
        }
    }

    public static void menuGestaoTurmas(Scanner input) {
        /**
         * private Integer id;
         * private Integer vagas;
         * private ArrayList<Aluno> alunos = new ArrayList<>();
         * private Professor professor;
         * private Boolean lotada;
         * 
         * OPÇÕES:
         * MUDAR ID
         * MUDAR NÚMERO DE VAGAS
         * EXCLUIR ALUNO
         * MUDAR PROFESSOR
         * VER SE ESTÁ LOTADA
         */

        System.out.println("\n\n1 - MUDAR ID DE TURMA");
        System.out.println("2 - MUDAR NÚMERO DE VAGAS");
        System.out.println("3 - EXCLUIR ALUNO DE TURMA");
        System.out.println("4 - MUDAR PROFESSOR DA TURMA");
        System.out.println("5 - DISPONIBILIDADE DE VAGAS");
        int selectIn = input.nextInt();
        input.nextLine();

        switch (selectIn) {
            case 1:
                visualizarTurmas();
                System.out.print("Digite o Id da turma que deseja alterar: ");
                Integer idTurma = input.nextInt();
                input.nextLine();

                if (idTurma != null && turmas.containsKey(idTurma)) {
                    Turma turmaAlterar = turmas.get(idTurma);

                    System.out.print("Digite o novo Id da turma que deseja alterar: ");
                    Integer newId = input.nextInt();
                    input.nextLine();

                    if (newId != null) {
                        turmaAlterar.setId(newId);

                        if (turmaAlterar.getId() == newId) {
                            System.out.println("Id alterado com sucesso!");
                        }
                    }
                }

                break;

            case 2:
                visualizarTurmas();
                System.out.print("Digite o Id da turma que deseja alterar: ");
                idTurma = input.nextInt();
                input.nextLine();

                if (idTurma != null && turmas.containsKey(idTurma)) {
                    Turma turmaAlterar = turmas.get(idTurma);
                    System.out.println("Número atual de vagas: " + turmaAlterar.getVagas());

                    System.out.print("Digite o novo número de vagas da turma que deseja alterar: ");
                    Integer vagas = input.nextInt();
                    input.nextLine();

                    if (vagas != null) {
                        turmaAlterar.setVagas(vagas);

                        if (turmaAlterar.getVagas() == vagas) {
                            System.out.println("Quantidade de vagas alterada com sucesso!");
                            turmaAlterar.incVagas();

                            System.out.println("Vagas restantes: " + turmaAlterar.getVagasRestantes());
                        }
                    }
                }

                break;
            case 3:
                visualizarTurmas();
                System.out.printf("Digite o id da turma:");
                Integer idTurmaExc = input.nextInt();
                input.nextLine();

                if (idTurmaExc != null && turmas.containsKey(idTurmaExc)) {
                    Turma turmaExclusaoAluno = turmas.get(idTurmaExc);

                    if (turmaExclusaoAluno.getAlunos() != null) {
                        visualizarAlunos();

                        System.out.printf("Digite o id do aluno que será excluído: ");
                        Integer idAluno = input.nextInt();
                        input.nextLine();

                        Aluno alunoExc = alunos.get(idAluno);

                        if (idAluno != null && alunos.containsKey(idAluno) && alunoExc != null
                                && turmaExclusaoAluno.getAlunos().contains(alunoExc)) {
                            turmaExclusaoAluno.getAlunos().remove(alunoExc);

                            if (!turmaExclusaoAluno.getAlunos().contains(alunoExc)) {
                                System.out.println("Aluno excluido com sucesso!");
                            } else {
                                System.out.println("Exclusão falhou.");
                            }

                        }
                    }

                }
                break;
            case 4:
                break;
            case 5:

            default:
                break;
        }

    }

    public static void menuGestaoDisciplinas() {

    }
}
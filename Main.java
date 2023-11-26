import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Main {
    private static int idMapProf = 0;
    private static int idMapAluno = 0;
    private static int idMapTurma = 0;

    private static final int GESTAO_ALUNOS = 1;
    private static final int GESTAO_PROFESSORES = 2;
    private static final int GESTAO_TURMAS = 3;
    private static final int GESTAO_DISCIPLINAS = 4;
    private static final int GESTAO_CURSOS = 5;
    private static final int IMPRESSOES = 6;

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

        try (BufferedReader reader = new BufferedReader(new FileReader("base_cursos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String titulo = parts[0].replaceAll("\"", "").trim();
                int id = Integer.parseInt(parts[1].trim());
                Curso curso = new Curso(titulo, id);
                cursos.add(curso);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Imprimir os cursos
        /**
         * for (Curso curso : cursos) {
         * System.out.println(curso.toString());
         * }
         */

        try (BufferedReader reader = new BufferedReader(new FileReader("base_disciplinas.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Disciplina disciplina = criarDisciplina(line);
                disciplinas.put(disciplina.getCodigo(), disciplina);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        carregarAlunos(alunos);
        carregarProfessores(professores);

        // Agora tem um mapa de disciplinas

        /**
         * for (Map.Entry<Integer, Disciplina> entry : disciplinas.entrySet()) {
         * System.out.println(entry.getKey() + ": " + entry.getValue() + "\n\n");
         * }
         */

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
                System.out.println(GESTAO_CURSOS + " - GESTÃO DE CURSOS");
                System.out.println(IMPRESSOES + " - EXPORTAR BASE DE DADOS");

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
                        menuGestaoTurmas(input);
                        break;

                    case GESTAO_DISCIPLINAS:
                        etapa += 1;
                        menuGestaoDisciplinas(input);
                        break;

                    case GESTAO_CURSOS:
                        etapa += 1;
                        menuGestaoCursos(input);

                    case IMPRESSOES:
                        etapa += 1;
                        menuExportar(input);
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

                            // verifica se o aluno foi excluído.
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
                visualizarTurmas();
                System.out.println("Selecione o Id da turma que deseja alterar: ");
                Integer idTurmaEdit = input.nextInt();
                input.nextLine();

                if (turmas.containsKey(idTurmaEdit)) {
                    Turma turmaSel = turmas.get(idTurmaEdit);
                    System.out.println("Dados gerais da turma: ");
                    turmaSel.toString();

                    System.out.println("\nINICIANDO EDIÇÃo...");
                    /*
                     * private Integer id;
                     * private Integer vagas;
                     * private Integer vagasLocadas;
                     * private ArrayList<Aluno> alunos = new ArrayList<>();
                     * private Professor professor;
                     * private Boolean lotada;
                     */

                    System.out.printf("\n\nNOVO ID: ");
                    Integer newID = input.nextInt();
                    input.nextLine();
                    turmaSel.setId(idTurmaEdit);

                    System.out.printf("\nNOVO NÚMERO DE VAGAS: ");
                    Integer newNumVagas = input.nextInt();
                    input.nextLine();
                    turmaSel.setVagas(newNumVagas);
                    if (turmaSel.setVagas(newNumVagas)) {
                        System.out.println("Número de vagas alterado com sucesso.");
                    } else {
                        System.out.println("Operação falhou.");
                    }

                    System.out.printf("NOVO PROFESSOR: \n");
                    System.out.println("SELECIONE UM DOS PROFESSORES EXISTENTES PARA ALTERA-LO PARA ESTA TURMA:");
                    visualizarProfessores();

                    System.out.printf("Digite o Id do Professor desejado para o novo professor da turma: ");
                    Integer idProf = input.nextInt();
                    input.nextLine();

                    if (professores.containsKey(idProf)) {
                        Professor profAlocacao = professores.get(idProf);

                        turmaSel.setProfessor(profAlocacao);
                        if (turmaSel.getProfessor().equals(profAlocacao)) {
                            profAlocacao.setTurmas(turmaSel);

                            if (profAlocacao.setTurmas(turmaSel)) {
                                System.out.println("Professor alterado com sucesso!");
                                System.out.println("Visualizando dados pós alteração...\n");

                                turmas.get(profAlocacao).toString();
                            } else {
                                System.out.println("Operação falhou.");
                            }
                        }
                    }
                }
                break;

            case 5:
                visualizarTurmas();
                System.out.print("DIGITE O ID DA TURMA QUE DESEJA CHECAR A  DISPONIBILIDADE DE VAGAS: ");
                System.out.printf("OPÇÃO: ");
                Integer idSelec = input.nextInt();
                input.nextLine();

                if (turmas.containsKey(idSelec)) {
                    Turma turmaSelect = turmas.get(idSelec);

                    System.out.println(turmaSelect.getVagasRestantes());
                    System.out.println(turmaSelect.getLotada());
                } else {
                    System.out.println("Opção inválida.");
                }
            default:
                break;
        }

    }

    public static void cadastroAluno(Scanner input) {
        System.out.printf("INICIANDO CADASTRO DE ALUNO...");

        String nomeCompleto = null;
        System.out.print("\nNOME (Obrigatório): ");
        input.nextLine();
        nomeCompleto = input.nextLine();
        if (nomeCompleto.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo.");
        }

        String cpf = null;
        System.out.print("CPF (Obrigatório) : ");
        cpf = input.nextLine();

        cpf.replace(" ", "");
        cpf.replace(".", "");
        cpf.replace("-", "");
        if (cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo.");
        }

        Integer matricula = null;
        System.out.print("MATRICULA (Opcional - 3 DÍGITOS): ");
        String matriculaInput = input.nextLine().trim();
        if (!matriculaInput.isEmpty()) {
            matricula = Integer.parseInt(matriculaInput);
        }

        String email = null;
        System.out.print("EMAIL (Obrigatório): ");
        email = input.nextLine();
        if (email.isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser nulo.");
        }

        System.out.print("EMAIL ACADÊMICO (Opcional): ");
        String emailAcad = input.nextLine();

        Aluno aluno = new Aluno(nomeCompleto, cpf, matricula, email, emailAcad, null, null, null, null);
        if (aluno.getMatricula() == null) {
            aluno.setMatricula(aluno.gerarMatricula());
        }

        alunos.put(aluno.getMatricula(), aluno);
        salvarAlunos(alunos);

        if (turmas != null && turmas.size() > 0) {
            System.out.println("\nTURMA DISPONÍVEIS: ");
            visualizarTurmas();
            System.out.println("SELECIONE O ID PARA A TURMA QUE O USUÁRIO SERÁ VINCULADO: ");
            Integer idSelect = input.nextInt();

            // Verifica se a turma existe antes de vincular o aluno
            if (turmas.containsKey(idSelect)) {
                aluno.addTurma(turmas.get(idSelect));
                System.out.println("Aluno vinculado à turma com sucesso!");
            } else {
                System.out.println("ID de turma inválido. Não foi possível vincular o aluno.");
            }
        } else {
            System.out.println("NÃO HÁ TURMAS CADASTRADAS NO MOMENTO ");
        }

        if (disciplinas != null && disciplinas.size() > 0) {
            System.out.println("\nDISCIPLINAS DISPONÍVEIS: ");
            visualizarDisciplinas();

            System.out.println("SELECIONE O ID PARA A DISCIPLINA QUE O USUÁRIO SERÁ VINCULADO: ");
            Integer idSelect = input.nextInt();

            // Verifica se a turma existe antes de vincular o aluno
            if (disciplinas.containsKey(idSelect)) {
                aluno.addDisciplina(disciplinas.get(idSelect));
                System.out.println("Aluno vinculado à disciplina com sucesso!");
            } else {
                System.out.println("ID de disciplina inválido. Não foi possível vincular o aluno.");
            }

        } else {
            System.out.println("NÃO HÁ DISCIPLINAS CADASTRADAS NO MOMENTO ");
        }

        if (salas != null && salas.size() > 0) {
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

        } else {
            System.out.println("NÃO HÁ SALAS CADASTRADAS NO MOMENTO ");
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

            String novoNome = null;
            // Agora você pode permitir a edição de diferentes campos do aluno, por exemplo:
            System.out.print("Digite o novo nome do aluno (ou pressione Enter para manter o mesmo): ");
            novoNome = input.nextLine();
            if (!novoNome.isEmpty()) {
                alunoParaEditar.setNome(novoNome);
            } else {
                throw new IllegalArgumentException("Nome não pode ser nulo.");
            }

            String novoEmail = null;
            System.out.print("Digite o novo email do aluno (ou pressione Enter para manter o mesmo): ");
            novoEmail = input.nextLine();
            if (!novoEmail.isEmpty()) {
                alunoParaEditar.setEmail(novoEmail);
            } else {
                throw new IllegalArgumentException("Email não pode ser nulo.");
            }

            System.out.print("Digite o novo email acadêmico do aluno (ou pressione Enter para manter o mesmo): ");
            String novoEmailAcad = input.nextLine();
            if (!novoEmailAcad.isEmpty()) {
                alunoParaEditar.setEmailAcad(novoEmailAcad);
            } else {
                System.out.println("Email antigo mantido");
            }

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
            System.out.println("\nDados: [\n" + alunoSelecionado.toStringDetailed() + "]");
        }

    }

    public static void cadastroProfessor(Scanner input) {
        System.out.printf("INICIANDO CADASTRO DE PROFESSOR...");
        String nomeCompleto = null;
        System.out.printf("\nNOME: (Obrigatório) ");
        input.nextLine();
        nomeCompleto = input.nextLine();
        if (nomeCompleto.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo.");
        }

        String cpf = null;
        System.out.printf("CPF (Obrigatório): ");
        cpf = input.nextLine(); // Consumir a quebra de linha após a leitura de long

        cpf.replace(" ", "");
        cpf.replace(".", "");
        cpf.replace("-", "");
        if (cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo.");
        }

        Integer matricula = null;
        System.out.print("MATRICULA (Opcional - 3 DÍGITOS): ");
        String matriculaInput = input.nextLine().trim();
        if (!matriculaInput.isEmpty()) {
            matricula = Integer.parseInt(matriculaInput);
        }

        String email = null;
        System.out.print("EMAIL (Obrigatório): ");
        email = input.nextLine();
        if (email.isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser nulo.");
        }

        System.out.print("EMAIL ACADÊMICO (Opcional): ");
        String emailAcad = input.nextLine();

        Professor professor = new Professor(nomeCompleto, cpf, matricula, email, null, emailAcad);
        professores.put(matricula, professor);
        salvarProfessores();

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
        if (turmas != null && turmas.size() > 0) {
            System.out.println("\nTURMAS EXISTENTES: ");
            for (Entry<Integer, Turma> entry : turmas.entrySet()) {
                Integer chave = entry.getKey();
                Turma valor = entry.getValue();
                System.out.println("ID" + chave + ", Valor: " + valor.toString());
            }

        } else {
            throw new NullPointerException("Turmas está vazia. Crie turmas.\n");
        }
    }

    public static void menuGestaoDisciplinas(Scanner input) {
        /*
         * private String titulo;
         * private Integer codigo;
         * private Integer cargaHoraria;
         * private String descricao;
         * private Integer aulasSemana;
         * 
         * private ArrayList<Professor> professoresMinistrantes = new ArrayList<>();
         * private ArrayList<Sala> salas = new ArrayList<>();
         * private ArrayList<Turma> turmas = new ArrayList<>();
         */
        int selectIn = 0;

        System.out.println("\n1 - CADASTRO NOVA DISCIPLINA");
        System.out.println("2 - EXCLUIR DISCIPLINA");
        System.out.println("3 - MUDAR DESCRIÇÃO DE DISCIPLINA");
        System.out.println("4 - EXCLUIR UM PROFESSOR");
        System.out.println("5 - EXCLUIR UMA SALA");
        System.out.println("6 - EXCLUIR UMA TURMA");
        System.out.println("7 - VISUALIZAR DISCIPLINAS");

        int idSelecionado;
        System.out.printf("OPÇÃO: ");
        selectIn = input.nextInt();

        switch (selectIn) {
            case 1:
                cadastroDisciplina(input);
                break;

            case 2:
                excluirDisciplina(input);
                break;

            case 3:
                mudaDescricao(input);
                break;
            case 4:
                excluirProfessor(input);
                break;
            case 5:
                excluirSalas(input);
                break;

            case 6:
                excluirTurma(input);
                break;

            case 7:
                editarAluno(input);
                break;

            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
        }
    }

    public static void cadastroDisciplina(Scanner input) {
        // Capturar o título da disciplina (obrigatório)
        System.out.print("Título da Disciplina (obrigatório): ");
        String titulo = input.nextLine();

        // Capturar o código da disciplina
        Integer codigo = null;
        System.out.print("Código da Disciplina (deixe em branco para gerar aleatoriamente): ");
        String codigoInput = input.nextLine();
        if (!codigoInput.isEmpty()) {
            codigo = Integer.parseInt(codigoInput);
        } else {
            // Gerar código aleatório se não fornecido pelo usuário
            Random random = new Random();
            codigo = random.nextInt(999); // Altere o valor máximo conforme necessário
        }

        // Capturar a carga horária (obrigatória, entre 40 e 160 horas)
        int cargaHoraria;
        do {
            System.out.print("Carga Horária (entre 40 e 160 horas, obrigatório): ");
            cargaHoraria = input.nextInt();
            input.nextLine(); // Consumir a quebra de linha após a leitura de int

            if (cargaHoraria < 40 || cargaHoraria > 160) {
                System.out.println("Carga horária inválida.");
            }
        } while (cargaHoraria < 40 || cargaHoraria > 160);

        // Capturar a descrição (opcional)
        System.out.print("Descrição (opcional): ");
        String descricao = input.nextLine();

        // Capturar as aulas por semana (obrigatório)
        System.out.print("Aulas por Semana (obrigatório): ");
        int aulasSemana = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha após a leitura de int

        // Validar se o título e as aulas por semana foram fornecidos
        if (!titulo.isEmpty() && aulasSemana > 0) {
            Disciplina disciplina = new Disciplina(titulo, codigo, cargaHoraria, descricao, aulasSemana, null, null,
                    null);
            disciplinas.put(codigo, disciplina);
            salvarDisciplinas(disciplinas);
            System.out.println("Disciplina cadastrada com sucesso!");
        } else {
            System.out.println("Título e aulas por semana são campos obrigatórios. Disciplina não cadastrada.");
        }

    }

    public static void excluirDisciplina(Scanner input) {
        // Visualizar disciplinas antes da exclusão
        visualizarDisciplinas();

        // Capturar a entrada do usuário para selecionar uma disciplina para excluir
        System.out.print("Digite o ID da disciplina que deseja excluir: ");
        int idDisciplina = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha após a leitura de int

        // Verificar se o ID selecionado corresponde a uma disciplina existente
        if (disciplinas.containsKey(idDisciplina)) {
            Disciplina disciplinaExcluir = disciplinas.get(idDisciplina);

            // Exibir informações sobre a disciplina antes de excluí-la
            System.out.println("Disciplina selecionada para exclusão: " + disciplinaExcluir.getTitulo());
            System.out.println("Código: " + disciplinaExcluir.getCodigo());
            System.out.println("Carga Horária: " + disciplinaExcluir.getCargaHoraria());
            System.out.println("Descrição: " + disciplinaExcluir.getDescricao());
            System.out.println("Aulas por Semana: " + disciplinaExcluir.getAulasSemana());

            // Confirmar a exclusão com o usuário
            System.out.print("Deseja realmente excluir esta disciplina? (S/N): ");
            String confirmacao = input.nextLine().trim().toUpperCase();

            if (confirmacao.equals("S")) {
                // Realizar a exclusão da disciplina
                disciplinas.remove(idDisciplina);
                System.out.println("Disciplina excluída com sucesso.");
            } else {
                System.out.println("Exclusão cancelada.");
            }
        } else {
            System.out.println("ID de disciplina inválido. A exclusão não foi realizada.");
        }
    }

    public static void mudaDescricao(Scanner input) {
        // Visualizar disciplinas antes da mudança
        visualizarDisciplinas();

        // Capturar a entrada do usuário para selecionar uma disciplina para mudar a
        // descrição
        System.out.print("Digite o ID da disciplina que deseja mudar a descrição: ");
        int idDisciplina = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha após a leitura de int

        // Verificar se o ID selecionado corresponde a uma disciplina existente
        if (disciplinas.containsKey(idDisciplina)) {
            Disciplina disciplinaParaMudarDescricao = disciplinas.get(idDisciplina);

            // Exibir informações sobre a disciplina antes de mudar a descrição
            System.out.println(
                    "Disciplina selecionada para mudar a descrição: " + disciplinaParaMudarDescricao.getTitulo());
            System.out.println("Descrição Atual: " + disciplinaParaMudarDescricao.getDescricao());

            // Capturar a nova descrição do usuário
            System.out.print("Digite a nova descrição da disciplina: ");
            String novaDescricao = input.nextLine();

            // Atualizar a descrição da disciplina
            disciplinaParaMudarDescricao.setDescricao(novaDescricao);
            System.out.println("Descrição da disciplina atualizada com sucesso.");
        } else {
            System.out.println("ID de disciplina inválido. A mudança de descrição não foi realizada.");
        }
    }

    public static void excluirProfessor(Scanner input) {
        // Visualizar disciplinas antes da exclusão
        visualizarDisciplinas();

        // Capturar a entrada do usuário para selecionar uma disciplina para excluir um
        // professor
        System.out.print("Digite o ID da disciplina da qual deseja excluir um professor: ");
        int idDisciplina = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha após a leitura de int

        // Verificar se o ID selecionado corresponde a uma disciplina existente
        if (disciplinas.containsKey(idDisciplina)) {
            Disciplina disciplinaParaExcluirProfessor = disciplinas.get(idDisciplina);

            // Exibir informações sobre a disciplina antes da exclusão
            System.out.println(
                    "Disciplina selecionada para exclusão de professor: " + disciplinaParaExcluirProfessor.getTitulo());
            System.out.println(
                    "Professores Ministrantes Atuais: " + disciplinaParaExcluirProfessor.getProfessoresMinistrantes());

            // Capturar a entrada do usuário para selecionar um professor a ser excluído
            System.out.print("Digite o ID do professor que deseja excluir da disciplina: ");
            int idProfessor = input.nextInt();
            input.nextLine(); // Consumir a quebra de linha após a leitura de int

            // Verificar se o ID do professor corresponde a um professor existente na
            // disciplina
            if (disciplinaParaExcluirProfessor.existeProfessor(idProfessor)) {
                Professor professorParaExcluir = disciplinaParaExcluirProfessor.getProfessorById(idProfessor);

                // Excluir o professor da disciplina
                disciplinaParaExcluirProfessor.removerProfessorMinistrante(professorParaExcluir);
                System.out.println("Professor excluído da disciplina com sucesso.");
            } else {
                System.out.println("ID de professor inválido. A exclusão não foi realizada.");
            }
        } else {
            System.out.println("ID de disciplina inválido. A exclusão não foi realizada.");
        }
    }

    public static void excluirSalas(Scanner input) {
        visualizarSalas();

        // Capturar a entrada do usuário para selecionar uma sala
        System.out.print("Digite o ID da sala que deseja excluir: ");
        int idSala = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha após a leitura de int

        // Verificar se o ID selecionado corresponde a uma sala existente
        if (salas.containsKey(idSala)) {
            // Remover a sala do mapa de salas
            salas.remove(idSala);
            System.out.println("Sala removida com sucesso.");
        } else {
            System.out.println("ID de sala inválido. A exclusão não foi realizada.");
        }
    }

    public static void excluirTurma(Scanner input) {
        // Exibir disciplinas cadastradas
        visualizarDisciplinas();

        // Capturar a entrada do usuário para selecionar uma disciplina
        System.out.print("Digite o código da disciplina da qual deseja excluir a turma: ");
        int codigoDisciplina = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha após a leitura de int

        // Verificar se o código da disciplina selecionado existe
        if (disciplinas.containsKey(codigoDisciplina)) {
            Disciplina disciplinaSelecionada = disciplinas.get(codigoDisciplina);

            // Exibir turmas da disciplina selecionada
            System.out.println("\nTURMAS DA DISCIPLINA " + disciplinaSelecionada.getTitulo() + ": ");
            for (Turma turma : disciplinaSelecionada.getTurmas()) {
                System.out.println("ID da Turma: " + turma.getId());
            }

            // Capturar a entrada do usuário para selecionar a turma a ser removida
            System.out.print("Digite o ID da turma que deseja excluir: ");
            int idTurma = input.nextInt();
            input.nextLine(); // Consumir a quebra de linha após a leitura de int

            // Verificar se o ID da turma selecionado existe na disciplina
            if (disciplinaSelecionada.existeTurma(idTurma)) {
                disciplinaSelecionada.removerTurma(idTurma);
                System.out.println("Turma removida com sucesso da disciplina: " + disciplinaSelecionada.getTitulo());
            } else {
                System.out.println("ID de turma inválido. A remoção não foi realizada.");
            }
        } else {
            System.out.println("Código de disciplina inválido. A remoção não foi realizada.");
        }
    }

    public static void visualizarSalas() {
        if (salas != null && !salas.isEmpty()) {
            System.out.println("\nSALAS DISPONÍVEIS: ");
            for (Entry<Integer, Sala> entry : salas.entrySet()) {
                Integer chave = entry.getKey();
                Sala valor = entry.getValue();
                System.out.println("ID" + chave + ", Valor: " + valor.toString());
            }
        } else {
            System.out.println("Não há salas disponíveis para excluir.");
        }
    }

    public static void menuGestaoCursos(Scanner input) {
        int opcao;

        do {
            System.out.println("1 - CADASTRAR NOVO CURSO");
            System.out.println("2 - EXCLUIR CURSO");
            System.out.println("3 - VISUALIZAR CURSOS");
            System.out.println("0 - VOLTAR AO MENU PRINCIPAL");

            System.out.print("Escolha a opção desejada: ");
            opcao = input.nextInt();
            input.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    cadastrarNovoCurso(input);
                    break;

                case 2:
                    excluirCurso(input);
                    break;

                case 3:
                    visualizarCursos();
                    break;

                case 0:
                    System.out.println("Retornando ao Menu Principal...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
    }

    public static void cadastrarNovoCurso(Scanner input) {
        System.out.print("Digite o título do novo curso: ");
        String titulo = input.nextLine();

        // Você pode gerar um ID automaticamente ou solicitar ao usuário
        System.out.print("Digite o ID do novo curso: ");
        int idCurso = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha

        Curso novoCurso = new Curso(titulo, idCurso);

        // Adicionar o novo curso à lista de cursos
        cursos.add(novoCurso);

        System.out.println("Novo curso cadastrado com sucesso!");
    }

    public static void excluirCurso(Scanner input) {
        // Mostrar os cursos disponíveis
        System.out.println("Cursos disponíveis para exclusão:");
        for (Curso curso : cursos) {
            System.out.println(curso.getTitulo() + " (ID: " + curso.getId() + ")");
        }

        // Solicitar o ID do curso a ser excluído
        System.out.print("Digite o ID do curso que deseja excluir: ");
        int idCursoExcluir = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha

        // Encontrar o curso na lista
        Curso cursoExcluir = null;
        for (Curso curso : cursos) {
            if (curso.getId() == idCursoExcluir) {
                cursoExcluir = curso;
                break;
            }
        }

        // Se o curso for encontrado, remover dos alunos e da lista de cursos
        if (cursoExcluir != null) {
            for (Aluno aluno : cursoExcluir.getAlunos()) {
                aluno.removerCurso(cursoExcluir);
            }

            cursos.remove(cursoExcluir);
            System.out.println("Curso removido com sucesso!");
        } else {
            System.out.println("Curso não encontrado. Nenhum curso removido.");
        }
    }

    public static void visualizarCursos() {
        System.out.println("Cursos disponíveis:");

        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");
        } else {
            for (Curso curso : cursos) {
                System.out.println(curso.toString());
            }
        }
    }

    private static Disciplina criarDisciplina(String linha) {
        String[] parts = linha.split(",");

        // Verificar se há pelo menos 6 partes na linha
        if (parts.length < 4) {
            throw new IllegalArgumentException("Formato inválido da linha: " + linha);
        }

        String titulo = parts[0].replaceAll("\"", "").trim();
        int codigo = Integer.parseInt(parts[1].trim());
        int cargaHoraria = Integer.parseInt(parts[2].trim());
        String descricao = parts[3].replaceAll("\"", "").trim();
        int aulasSemana = Integer.parseInt(parts[4].trim());
        // Adicione os outros atributos conforme necessário

        return new Disciplina(titulo, codigo, cargaHoraria, descricao, aulasSemana, null, null, null);
    }

    public static void salvarAlunos(Map<Integer, Aluno> alunos) {
        verificarArquivo();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("base_alunos.txt", true))) {
            for (Entry<Integer, Aluno> entry : alunos.entrySet()) {
                Aluno aluno = entry.getValue();
                String linha = alunoParaLinha(aluno);
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void verificarArquivo() {
        File arquivo = new File("base_alunos.txt");
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String alunoParaLinha(Aluno aluno) {
        StringBuilder linha = new StringBuilder();
        linha.append(aluno.getMatricula()).append(",");
        linha.append(aluno.getNome()).append(",");
        linha.append(aluno.getCpf()).append(",");
        linha.append(aluno.getEmail()).append(",");
        linha.append(aluno.getEmailAcad()).append(",");
        // Adicione outros atributos conforme necessário
        return linha.toString();
    }

    public static void salvarProfessores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("base_professores.txt"))) {
            for (Entry<Integer, Professor> entry : professores.entrySet()) {
                Professor professor = entry.getValue();
                writer.write(professor.getMatricula() + "," +
                        professor.getNome() + "," +
                        professor.getCpf() + "," +
                        professor.getEmail() + "," + "\n");
            }
            System.out.println("Professores salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar professores: " + e.getMessage());
        }
    }

    private static void verificarArquivoTurmas() {
        File file = new File("base_turmas.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void salvarTurmas(Map<Integer, Turma> turmas) {
        verificarArquivoTurmas();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("base_turmas.txt", true))) {
            for (Map.Entry<Integer, Turma> entry : turmas.entrySet()) {
                String linha = turmaParaLinha(entry.getValue());
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String turmaParaLinha(Turma turma) {
        StringBuilder linha = new StringBuilder();
        linha.append(turma.getId()).append(",");
        linha.append(turma.getVagas()).append(",");
        linha.append(turma.getVagasRestantes()).append(",");
        linha.append(turma.getLotada()).append(",");
        // Adicione outros atributos conforme necessário
        return linha.toString();
    }

    private static void verificarArquivoCursos() {
        File file = new File("base_cursos.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void salvarCursos(Map<Integer, Curso> cursos) {
        verificarArquivoCursos();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("base_cursos.txt", true))) {
            for (Map.Entry<Integer, Curso> entry : cursos.entrySet()) {
                String linha = cursoParaLinha(entry.getValue());
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String cursoParaLinha(Curso curso) {
        StringBuilder linha = new StringBuilder();
        linha.append(curso.getId()).append(",");
        linha.append(curso.getTitulo()).append(",");
        linha.append(curso.getNumeroAlunos()).append(",");
        linha.append(curso.getVagasRestantes()).append(",");
        // Adicione outros atributos conforme necessário
        return linha.toString();
    }

    private static void verificarArquivoDisciplinas() {
        File file = new File("base_disciplinas.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void salvarDisciplinas(Map<Integer, Disciplina> disciplinas) {
        verificarArquivoDisciplinas();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("base_disciplinas.txt", true))) {
            for (Map.Entry<Integer, Disciplina> entry : disciplinas.entrySet()) {
                String linha = disciplinaParaLinha(entry.getValue());
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String disciplinaParaLinha(Disciplina disciplina) {
        StringBuilder linha = new StringBuilder();
        linha.append(disciplina.getCodigo()).append(",");
        linha.append(disciplina.getTitulo()).append(",");
        linha.append(disciplina.getCargaHoraria()).append(",");
        linha.append(disciplina.getDescricao()).append(",");
        linha.append(disciplina.getAulasSemana()).append(",");
        // Adicione outros atributos conforme necessário
        return linha.toString();
    }

    private static void verificarArquivoSalas() {
        File file = new File("base_salas.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void salvarSalas(Map<Integer, Sala> salas) {
        verificarArquivoSalas();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("base_salas.txt", true))) {
            for (Map.Entry<Integer, Sala> entry : salas.entrySet()) {
                String linha = salaParaLinha(entry.getValue());
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String salaParaLinha(Sala sala) {
        StringBuilder linha = new StringBuilder();
        linha.append(sala.getId()).append(",");
        linha.append(sala.getDescricao()).append(",");
        // Adicione outros atributos conforme necessário
        return linha.toString();
    }

    private static void carregarProfessores(Map<Integer, Professor> professores) {
        try (BufferedReader reader = new BufferedReader(new FileReader("base_professores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Professor professor = linhaParaProfessor(line);
                professores.put(professor.getMatricula(), professor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Professor linhaParaProfessor(String linha) {
        String[] parts = linha.split(",");

        // Verificar se há pelo menos 4 partes na linha
        if (parts.length != 4) {
            throw new IllegalArgumentException("Formato inválido da linha: " + linha);
        }

        try {
            String nome = parts[1].replaceAll("\"", "").trim();
            String cpf = parts[2].replaceAll("\"", "").trim();
            int matricula = Integer.parseInt(parts[0].trim());
            String email = parts[3].replaceAll("\"", "").trim();

            // Não há emailAcad neste exemplo, você pode adicioná-lo conforme necessário

            return new Professor(nome, cpf, matricula, email, null, null);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Erro ao converter valores na linha: " + linha, e);
        }
    }

    private static void carregarAlunos(Map<Integer, Aluno> alunos) {
        try (BufferedReader reader = new BufferedReader(new FileReader("base_alunos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Aluno aluno = linhaParaAluno(line);
                alunos.put(aluno.getMatricula(), aluno);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Aluno linhaParaAluno(String linha) {
        String[] parts = linha.split(",");
        int matricula = Integer.parseInt(parts[0].trim());
        String nome = parts[1].replaceAll("\"", "").trim();
        String cpf = parts[2].replaceAll("\"", "").trim();
        String email = parts[3].replaceAll("\"", "").trim();
        String emailAcad = parts[4].replaceAll("\"", "").trim();

        // Adicione outros atributos conforme necessário

        return new Aluno(nome, cpf, matricula, email, emailAcad, null, null, null, null);
    }

    public static void menuExportar(Scanner input) {
        try {
            Integer selec = null;
            do {
                System.out.println("1 - EXPORTAR BASE DE ALUNOS");
                System.out.println("2 - EXPORTAR BASE DE PROFESSORES");
                System.out.println("3 - EXPORTAR BASE DE CURSOS");
                System.out.println("4 - EXPORTAR BASE DE DISCIPLINAS");

                System.out.printf("OPÇÃO: ");
                selec = input.nextInt();
                input.nextLine();
            } while (selec == null);

            switch (selec) {
                case 1:
                    try {
                        converterTxtParaCsv("base_alunos.txt", "base_alunos.csv");
                        System.out.println("Conversão concluída com sucesso!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;

                case 2:
                    try {
                        converterTxtParaCsv("base_professores.txt", "base_professores.csv");
                        System.out.println("Conversão concluída com sucesso!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                case 3: 
                try {
                        converterTxtParaCsv("base_cursos.txt", "base_cursos.csv");
                        System.out.println("Conversão concluída com sucesso!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                case 4: 
                    try {
                        converterTxtParaCsv("base_disciplina.txt", "base_disciplina.csv");
                        System.out.println("Conversão concluída com sucesso!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                default:
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getCause());
            System.out.println(e.getLocalizedMessage());
            System.out.println("O valor digitado não é um inteiro.");
        }
    }

    private static void converterTxtParaCsv(String inputFile, String outputFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                FileWriter writer = new FileWriter(outputFile)) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Substitui as vírgulas por ponto e vírgula
                line = line.replace(",", ";");
                writer.write(line);
                writer.write("\n"); // Adiciona uma quebra de linha entre as entradas
            }
        }
    }
}
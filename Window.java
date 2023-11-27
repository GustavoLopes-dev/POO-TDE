import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class Window extends JFrame {
    Main main = new Main();
    public Window() {
        setTitle("Sistema de Gestão Escolar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton btnGestaoAlunos = new JButton("Gestão de Alunos");
        JButton btnGestaoProfessores = new JButton("Gestão de Professores");
        JButton btnGestaoTurmas = new JButton("Gestão de Turmas");
        JButton btnGestaoDisciplinas = new JButton("Gestão de Disciplina");

        btnGestaoAlunos.addActionListener(new BotaoGestaoListener());
        btnGestaoProfessores.addActionListener(new BotaoGestaoListener());
        btnGestaoTurmas.addActionListener(new BotaoGestaoListener());
        btnGestaoDisciplinas.addActionListener(new BotaoGestaoListener());

        panel.add(btnGestaoAlunos);
        panel.add(btnGestaoProfessores);
        panel.add(btnGestaoTurmas);
        panel.add(btnGestaoDisciplinas);

        add(panel);
        setVisible(true);
    }

    private class BotaoGestaoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton botaoClicado = (JButton) e.getSource();

            switch (botaoClicado.getText()) {
                case "Gestão de Alunos":
                    abrirJanela(new JanelaGestaoAlunos());
                    break;
                case "Gestão de Professores":
                    abrirJanela(new JanelaGestaoProfessores());
                    break;
                case "Gestão de Turmas":
                    abrirJanela(new JanelaGestaoTurmas());
                    break;
                case "Gestão de Disciplina":
                    abrirJanela(new JanelaGestaoDisciplinas());
                    break;
            }
        }
    }

    private void abrirJanela(JFrame janela) {
        janela.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Window());
    }

    // janelas gestoes ...
    public class JanelaGestaoAlunos extends JFrame {
        public JanelaGestaoAlunos() {
            setTitle("Gestão de Alunos");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 300);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(8, 1));

            JButton btnCadastroAlunos = new JButton("CADASTRO DE ALUNOS");
            JButton btnMatriculaAluno = new JButton("MATRICULAR ALUNOS");
            JButton btnAlocacaoAlunos = new JButton("ALOCAÇÃO DE ALUNOS");
            JButton btnRealocacaoAlunos = new JButton("REALOCAÇÃO DE ALUNOS");
            JButton btnDadosAlunos = new JButton("ACESSAR DADOS ALUNO");
            JButton btnVisualizarAlunos = new JButton("VISUALIZAR ALUNOS");
            JButton btnEditarAluno = new JButton("EDITAR ALUNO");

            btnCadastroAlunos.addActionListener(new BotaoGestaoAlunosListener());
            btnMatriculaAluno.addActionListener(new BotaoGestaoAlunosListener());
            btnAlocacaoAlunos.addActionListener(new BotaoGestaoAlunosListener());
            btnRealocacaoAlunos.addActionListener(new BotaoGestaoAlunosListener());
            btnDadosAlunos.addActionListener(new BotaoGestaoAlunosListener());
            btnVisualizarAlunos.addActionListener(new BotaoGestaoAlunosListener());
            btnEditarAluno.addActionListener(new BotaoGestaoAlunosListener());

            panel.add(btnCadastroAlunos);
            panel.add(btnMatriculaAluno);
            panel.add(btnAlocacaoAlunos);
            panel.add(btnRealocacaoAlunos);
            panel.add(btnDadosAlunos);
            panel.add(btnVisualizarAlunos);
            panel.add(btnEditarAluno);

            add(panel);
            setVisible(true);
        }

        private class BotaoGestaoAlunosListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton botaoClicado = (JButton) e.getSource();
                if (botaoClicado.getText().equals("CADASTRO DE ALUNOS")) {
                    new JanelaCadastrarAluno();
                } else if (botaoClicado.getText().equals("MATRICULAR ALUNOS")) {
                    // new JAnelaMAtriculaAluno();
                } else if (botaoClicado.getText().equals("ALOCAÇÃO DE ALUNOS")) {
                    new JanelaAlocarAluno();
                } else if (botaoClicado.getText().equals("REALOCAÇÃO DE ALUNOS")) {
                    // new JanelaRealocacaoAlunos();
                } else if (botaoClicado.getText().equals("ACESSAR DADOS ALUNO")) {
                    // new JAnelaDadosAlunos();
                } else if (botaoClicado.getText().equals("VISUALIZAR ALUNOS")) {
                    new JanelaVisualizarAlunos(main);
                } else if (botaoClicado.getText().equals("EDITAR ALUNO")) {
                    new JanelaEditarAluno();
                }
            }
        }
    }

    class JanelaGestaoProfessores extends JFrame {
        public JanelaGestaoProfessores() {
            setTitle("Gestão de Professores");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 300);
            setLocationRelativeTo(null);

           JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(8, 1));

            JButton btnCadastroProfessores = new JButton("CADASTRO DE PROFESSORES");
            JButton btnAlocacaoProfessor = new JButton("ALOCAÇÃO DE PROFESSORES");
            JButton btnReAlocacaoProfessor = new JButton("REALOCAÇÃO DE PROFESSORES");
            JButton btnVisualizarProfessores = new JButton("VISUALIZAR PROFESSORES");
            JButton btnDadosProfessores = new JButton("ACESSAR DADOS DE PROFESSORES");
            JButton btnEditarProfessores = new JButton("EDITAR PROFESSORES");

            btnCadastroProfessores.addActionListener(new BotaoGestaoProfessoresListener());
            btnAlocacaoProfessor.addActionListener(new BotaoGestaoProfessoresListener());
            btnReAlocacaoProfessor.addActionListener(new BotaoGestaoProfessoresListener());
            btnVisualizarProfessores.addActionListener(new BotaoGestaoProfessoresListener());
            btnDadosProfessores.addActionListener(new BotaoGestaoProfessoresListener());
            btnEditarProfessores.addActionListener(new BotaoGestaoProfessoresListener());

            panel.add(btnCadastroProfessores);
            panel.add(btnAlocacaoProfessor);
            panel.add(btnReAlocacaoProfessor);
            panel.add( btnVisualizarProfessores);
            panel.add( btnDadosProfessores);
            panel.add(btnEditarProfessores);

            add(panel);
            setVisible(true);
        }
         private class BotaoGestaoProfessoresListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton botaoClicado = (JButton) e.getSource();
                if (botaoClicado.getText().equals("CADASTRO DE PROFESSORES")) {
                    new JanelaCadastroProfessor();
                } else if (botaoClicado.getText().equals("ALOCAÇÃO DE PROFESSORES")) {
    
                } else if (botaoClicado.getText().equals("REALOCAÇÃO DE PROFESSORES")) {
                  
                } else if (botaoClicado.getText().equals("VISUALIZAR PROFESSORES")) {
                   new JanelaVisualizarProfessores();
                } else if (botaoClicado.getText().equals("ACESSAR DADOS DE PROFESSORES")) {
                    
                } else if (botaoClicado.getText().equals("EDITAR PROFESSORES")) {
                  
                } 
            }
        }
    }

    class JanelaGestaoTurmas extends JFrame {
        public JanelaGestaoTurmas() {
            setTitle("Gestão de Turmas");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 300);
            setLocationRelativeTo(null);

            // Adicione componentes e botões de cadastro para turmas aqui

            setVisible(true);
        }
    }

    class JanelaGestaoDisciplinas extends JFrame {
        public JanelaGestaoDisciplinas() {
            setTitle("Gestão de Disciplinas");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 300);
            setLocationRelativeTo(null);

            // Adicione componentes e botões de cadastro para disciplinas aqui

            setVisible(true);
        }
    }

    // janelas alunos
    public class JanelaCadastrarAluno extends JFrame {
        private PlaceholderTextField nomeField, cpfField, matriculaField, emailField, cursoField;
        private JButton cadastrarButton;

        public JanelaCadastrarAluno() {
            setTitle("Cadastrar Aluno");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 300);
            setLocationRelativeTo(null);

            nomeField = new PlaceholderTextField("Digite o nome");
            cpfField = new PlaceholderTextField("Digite o CPF");
            matriculaField = new PlaceholderTextField("Digite a matrícula");
            emailField = new PlaceholderTextField("Digite o email");
            cursoField = new PlaceholderTextField("Digite o curso");
            cadastrarButton = new JButton("Cadastrar");

            setLayout(new GridLayout(6, 2));
            add(new JLabel("Nome:"));
            add(nomeField);
            add(new JLabel("CPF:"));
            add(cpfField);
            add(new JLabel("Matrícula:"));
            add(matriculaField);
            add(new JLabel("Email:"));
            add(emailField);
            add(new JLabel("Curso:"));
            add(cursoField);
            add(new JLabel(" "));
            add(cadastrarButton);

            cadastrarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cadastrarAluno();
                }
            });

            setVisible(true);
        }

        private void cadastrarAluno() {
            String nome = nomeField.getText();
            String cpf = cpfField.getText();
            String matricula = matriculaField.getText();
            String email = emailField.getText();
            String curso = cursoField.getText();

            // Lógica para cadastrar o aluno
            System.out.println("Aluno cadastrado: Nome = " + nome + ", CPF = " + cpf + ", Matrícula = " + matricula +
                    ", Email = " + email + ", Curso = " + curso);

            // Limpar os campos após o cadastro
            nomeField.setText("");
            cpfField.setText("");
            matriculaField.setText("");
            emailField.setText("");
            cursoField.setText("");

            dispose();
        }
    }

    class PlaceholderTextField extends JTextField {
        private String placeholder;

        public PlaceholderTextField(String placeholder) {
            this.placeholder = placeholder;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (getText().isEmpty()) {
                g.setColor(Color.GRAY);
                g.drawString(placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
            }
        }
    }

    class JanelaAlocarAluno extends JFrame {

    }

    class JanelaEditarAluno extends JFrame {

    }

    public class JanelaVisualizarAlunos extends JFrame {
        private Map<Integer, Aluno> alunos;

        public JanelaVisualizarAlunos(Main main) {
            setTitle("Visualizar Alunos");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 300);
            setLocationRelativeTo(null);

            this.alunos = main.alunos; // Acessa o mapa 'alunos' da instância de Main

            // Chame o método para criar e exibir a lista de alunos
            exibirListaAlunos();

            setVisible(true);
        }

        private void exibirListaAlunos() {
            // Extrai os nomes dos alunos como um array de Strings
            String[] nomesAlunos = alunos.values().stream().map(Aluno::getNome).toArray(String[]::new);

            // Cria a JList com os nomes dos alunos
            JList<String> listaAlunos = new JList<>(nomesAlunos);

            // Adiciona a JList a um JScrollPane para permitir rolar
            JScrollPane scrollPane = new JScrollPane(listaAlunos);
            add(scrollPane);
        }
    }
    public class JanelaCadastroProfessor extends JFrame {
    private JTextField campoNome;
    private JTextField campoCPF;
    private JTextField campoMatricula;
    private JTextField campoEmail;
    private JTextField campoEmailAcademico;

    public JanelaCadastroProfessor() {
        setTitle("Cadastro de Professor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        panel.add(new JLabel("NOME: (Obrigatório)"));
        campoNome = new JTextField();
        panel.add(campoNome);

        panel.add(new JLabel("CPF (Obrigatório):"));
        campoCPF = new JTextField();
        panel.add(campoCPF);

        panel.add(new JLabel("MATRICULA (Opcional - 3 DÍGITOS):"));
        campoMatricula = new JTextField();
        panel.add(campoMatricula);

        panel.add(new JLabel("EMAIL (Obrigatório):"));
        campoEmail = new JTextField();
        panel.add(campoEmail);

        panel.add(new JLabel("EMAIL ACADÊMICO (Opcional):"));
        campoEmailAcademico = new JTextField();
        panel.add(campoEmailAcademico);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new BotaoCadastrarListener());
        panel.add(btnCadastrar);

        add(panel);
        setVisible(true);
    }

    private class BotaoCadastrarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cadastrarProfessor();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cadastrarProfessor() {
        System.out.println("INICIANDO CADASTRO DE PROFESSOR...");

        String nomeCompleto = campoNome.getText().trim();
        if (nomeCompleto.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo.");
        }

        String cpf = campoCPF.getText().replaceAll("[.\\-\\s]", "");
        if (cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo.");
        }

        Integer matricula = null;
        String matriculaInput = campoMatricula.getText().trim();
        if (!matriculaInput.isEmpty()) {
            matricula = Integer.parseInt(matriculaInput);
        }

        String email = campoEmail.getText().trim();
        if (email.isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser nulo.");
        }

        String emailAcad = campoEmailAcademico.getText().trim();

        
        System.out.println("\nDISCIPLINAS DISPONÍVEIS: ");

        dispose();
    }
  }

  public class JanelaAlocacaoProfessor extends JFrame {

    private JComboBox<String> comboProfessores;
    private JComboBox<String> comboTurmas;

    public JanelaAlocacaoProfessor(Map<Integer, Professor> professores, Map<Integer, Turma> turmas) {
        setTitle("Alocação de Professor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        // Criar listas de nomes de professores e turmas
        String[] nomesProfessores = new String[professores.size()];
        String[] nomesTurmas = new String[turmas.size()];

        int i = 0;
        for (Entry<Integer, Professor> entry : professores.entrySet()) {
            nomesProfessores[i] = entry.getKey() + ": " + entry.getValue().getNome();
            i++;
        }

        i = 0;
        for (Entry<Integer, Turma> entry : turmas.entrySet()) {
            nomesTurmas[i] = entry.getKey() + ": " + entry.getValue().getId();
            i++;
        }

        comboProfessores = new JComboBox<>(nomesProfessores);
        comboTurmas = new JComboBox<>(nomesTurmas);

        panel.add(new JLabel("Professores:"));
        panel.add(comboProfessores);

        panel.add(new JLabel("Turmas:"));
        panel.add(comboTurmas);

        JButton btnAlocar = new JButton("Alocar Professor");
        btnAlocar.addActionListener(new BotaoAlocarListener(professores, turmas));
        panel.add(btnAlocar);

        add(panel);
        setVisible(true);
    }

    private class BotaoAlocarListener implements ActionListener {
        private final Map<Integer, Professor> professores;
        private final Map<Integer, Turma> turmas;

        public BotaoAlocarListener(Map<Integer, Professor> professores, Map<Integer, Turma> turmas) {
            this.professores = professores;
            this.turmas = turmas;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int idProfessorSelecionado = Integer.parseInt(
                    comboProfessores.getSelectedItem().toString().split(":")[0].trim()
            );
            int idTurmaSelecionada = Integer.parseInt(
                    comboTurmas.getSelectedItem().toString().split(":")[0].trim()
            );

            // Verificar se o ID selecionado corresponde a um professor existente
            if (professores.containsKey(idProfessorSelecionado)) {
                Professor professorSelecionado = professores.get(idProfessorSelecionado);

                // Verificar se o ID selecionado corresponde a uma turma existente
                if (turmas.containsKey(idTurmaSelecionada)) {
                    Turma turmaSelecionada = turmas.get(idTurmaSelecionada);

                    // Alocar professor na turma selecionada
                    turmaSelecionada.setProfessor(professorSelecionado);
                    professorSelecionado.setTurmas(turmaSelecionada);

                    System.out.println("Professor alocado na turma com sucesso.");
                } else {
                    System.out.println("ID de turma inválido. Tente novamente.");
                }
            } else {
                System.out.println("ID de professor inválido. Tente novamente.");
            }
            

            // Fechar a janela após a alocação
            dispose();
            }
        }
    }

        public class JanelaVisualizarProfessores extends JFrame {

            public JanelaVisualizarProfessores() {
                setTitle("Visualizar Professores Cadastrados");
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setSize(400, 300);
                setLocationRelativeTo(null);
        
                JPanel panel = new JPanel();
        
        }
    }
}

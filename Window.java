import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
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
            if (botaoClicado.getText().equals("Gestão de Alunos")) {
                new JanelaGestaoAlunos();
            } else if (botaoClicado.getText().equals("Gestão de Professores")) {
                new JanelaGestaoProfessores();
            } else if (botaoClicado.getText().equals("Gestão de Turmas")) {
                new JanelaGestaoTurmas();
            } else if (botaoClicado.getText().equals("Gestão de Disciplina")) {
                new JanelaGestaoDisciplinas();
            }
        }
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
                    new JanelaVisualizarAlunos();
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
                    
                } else if (botaoClicado.getText().equals("ALOCAÇÃO DE PROFESSORES")) {
                   
                } else if (botaoClicado.getText().equals("REALOCAÇÃO DE PROFESSORES")) {
                  
                } else if (botaoClicado.getText().equals("VISUALIZAR PROFESSORES")) {
                   
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

    class JanelaVisualizarAlunos extends JFrame {

    }
}

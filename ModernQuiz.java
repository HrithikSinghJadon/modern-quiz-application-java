import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class ModernQuiz extends JFrame implements ActionListener {

    String questions[][] = new String[10][5];
    String answers[][] = new String[10][2];
    String useranswers[][] = new String[10][1];

    JLabel qnoLabel, questionLabel, timerLabel, progressLabel;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup groupoptions;
    JButton nextButton, submitButton, lifelineButton;
    JProgressBar progressBar;

    public static int timer = 15;
    public static int ans_given = 0;
    public static int count = 0;
    public static int score = 0;

    String name;
    String category;
    boolean lifelineUsed = false;

    // Modern colors
    Color primaryColor = new Color(41, 128, 185);
    Color accentColor = new Color(46, 204, 113);
    Color dangerColor = new Color(231, 76, 60);
    Color warningColor = new Color(243, 156, 18);
    Color backgroundColor = new Color(236, 240, 241);
    Color cardBackground = Color.WHITE;

    ModernQuiz(String name, String category) {
        this.name = name;
        this.category = category;

        setTitle("Simple Minds Quiz - " + category);
        getContentPane().setBackground(backgroundColor);
        setLayout(null);

        // Header panel with gradient
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, primaryColor, getWidth(), 0, new Color(52, 73, 94));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setBounds(0, 0, 1400, 100);
        headerPanel.setLayout(null);
        add(headerPanel);

        // Player name display
        JLabel playerLabel = new JLabel("Player: " + name);
        playerLabel.setBounds(50, 30, 400, 40);
        playerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        playerLabel.setForeground(Color.WHITE);
        headerPanel.add(playerLabel);

        // Category display
        JLabel categoryLabel = new JLabel("Category: " + category);
        categoryLabel.setBounds(50, 60, 400, 25);
        categoryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        categoryLabel.setForeground(new Color(236, 240, 241));
        headerPanel.add(categoryLabel);

        // Timer display with icon
        timerLabel = new JLabel("⏱️ Time: 15s");
        timerLabel.setBounds(1100, 30, 250, 40);
        timerLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        headerPanel.add(timerLabel);

        // Progress bar
        progressBar = new JProgressBar(0, 10);
        progressBar.setBounds(500, 35, 500, 30);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setString("Question 1 of 10");
        progressBar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressBar.setForeground(accentColor);
        progressBar.setBackground(new Color(236, 240, 241));
        headerPanel.add(progressBar);

        // Question card panel
        JPanel questionPanel = new JPanel();
        questionPanel.setBounds(50, 130, 1300, 150);
        questionPanel.setBackground(cardBackground);
        questionPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(189, 195, 199), 2, true),
                new EmptyBorder(20, 30, 20, 30)
        ));
        questionPanel.setLayout(null);
        add(questionPanel);

        // Question number
        qnoLabel = new JLabel();
        qnoLabel.setBounds(10, 10, 80, 40);
        qnoLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        qnoLabel.setForeground(primaryColor);
        questionPanel.add(qnoLabel);

        // Question text
        questionLabel = new JLabel();
        questionLabel.setBounds(100, 10, 1150, 120);
        questionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        questionLabel.setForeground(new Color(44, 62, 80));
        questionLabel.setVerticalAlignment(SwingConstants.TOP);
        questionPanel.add(questionLabel);

        // Options panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setBounds(50, 310, 1300, 350);
        optionsPanel.setBackground(backgroundColor);
        optionsPanel.setLayout(new GridLayout(4, 1, 0, 15));
        add(optionsPanel);

        // Create styled radio buttons
        opt1 = createStyledRadioButton();
        opt2 = createStyledRadioButton();
        opt3 = createStyledRadioButton();
        opt4 = createStyledRadioButton();

        optionsPanel.add(opt1);
        optionsPanel.add(opt2);
        optionsPanel.add(opt3);
        optionsPanel.add(opt4);

        // Group radio buttons
        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);
        groupoptions.add(opt4);

        // Control buttons panel
        JPanel controlPanel = new JPanel();
        controlPanel.setBounds(50, 690, 1300, 80);
        controlPanel.setBackground(backgroundColor);
        controlPanel.setLayout(null);
        add(controlPanel);

        // Lifeline button
        lifelineButton = new JButton("🎯 50-50 LIFELINE");
        lifelineButton.setBounds(0, 0, 250, 70);
        lifelineButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lifelineButton.setBackground(warningColor);
        lifelineButton.setForeground(Color.WHITE);
        lifelineButton.setFocusPainted(false);
        lifelineButton.setBorderPainted(false);
        lifelineButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lifelineButton.addActionListener(this);
        addHoverEffect(lifelineButton, warningColor, new Color(211, 136, 16));
        controlPanel.add(lifelineButton);

        // Next button
        nextButton = new JButton("NEXT ➔");
        nextButton.setBounds(850, 0, 200, 70);
        nextButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        nextButton.setBackground(primaryColor);
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(false);
        nextButton.setBorderPainted(false);
        nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nextButton.addActionListener(this);
        addHoverEffect(nextButton, primaryColor, new Color(31, 97, 141));
        controlPanel.add(nextButton);

        // Submit button
        submitButton = new JButton("SUBMIT QUIZ ✓");
        submitButton.setBounds(1070, 0, 230, 70);
        submitButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        submitButton.setBackground(accentColor);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setBorderPainted(false);
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.setEnabled(false);
        submitButton.addActionListener(this);
        addHoverEffect(submitButton, accentColor, new Color(39, 174, 96));
        controlPanel.add(submitButton);

        // Initialize questions
        initializeQuestions();

        // Start first question
        start(count);

        setSize(1400, 850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private JRadioButton createStyledRadioButton() {
        JRadioButton rb = new JRadioButton();
        rb.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        rb.setBackground(cardBackground);
        rb.setForeground(new Color(44, 62, 80));
        rb.setBorder(new CompoundBorder(
                new LineBorder(new Color(189, 195, 199), 2, true),
                new EmptyBorder(15, 20, 15, 20)
        ));
        rb.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        rb.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (rb.isEnabled()) {
                    rb.setBackground(new Color(240, 248, 255));
                }
            }
            public void mouseExited(MouseEvent e) {
                rb.setBackground(cardBackground);
            }
        });

        return rb;
    }

    private void addHoverEffect(JButton button, Color normalColor, Color hoverColor) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (button.isEnabled()) {
                    button.setBackground(hoverColor);
                }
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(normalColor);
            }
        });
    }

    private void initializeQuestions() {
        questions[0][0] = "Which is used to find and fix bugs in the Java programs?";
        questions[0][1] = "JVM";
        questions[0][2] = "JDB";
        questions[0][3] = "JDK";
        questions[0][4] = "JRE";

        questions[1][0] = "What is the return type of the hashCode() method in the Object class?";
        questions[1][1] = "int";
        questions[1][2] = "Object";
        questions[1][3] = "long";
        questions[1][4] = "void";

        questions[2][0] = "Which package contains the Random class?";
        questions[2][1] = "java.util";
        questions[2][2] = "java.lang";
        questions[2][3] = "java.awt";
        questions[2][4] = "java.io";

        questions[3][0] = "An interface with no fields or methods is known as?";
        questions[3][1] = "Runnable Interface";
        questions[3][2] = "Abstract Interface";
        questions[3][3] = "Marker Interface";
        questions[3][4] = "CharSequence Interface";

        questions[4][0] = "In which memory a String is stored when we create using new operator?";
        questions[4][1] = "Stack";
        questions[4][2] = "String memory";
        questions[4][3] = "Heap memory";
        questions[4][4] = "Random storage";

        questions[5][0] = "Which of the following is a marker interface?";
        questions[5][1] = "Runnable interface";
        questions[5][2] = "Remote interface";
        questions[5][3] = "Readable interface";
        questions[5][4] = "Result interface";

        questions[6][0] = "Which keyword is used for accessing features of a package?";
        questions[6][1] = "import";
        questions[6][2] = "package";
        questions[6][3] = "extends";
        questions[6][4] = "export";

        questions[7][0] = "In Java, JAR stands for?";
        questions[7][1] = "Java Archive Runner";
        questions[7][2] = "Java Archive";
        questions[7][3] = "Java Application Resource";
        questions[7][4] = "Java Application Runner";

        questions[8][0] = "Which of the following is a mutable class in Java?";
        questions[8][1] = "java.lang.StringBuilder";
        questions[8][2] = "java.lang.Short";
        questions[8][3] = "java.lang.Byte";
        questions[8][4] = "java.lang.String";

        questions[9][0] = "Which option leads to the portability and security of Java?";
        questions[9][1] = "Bytecode is executed by JVM";
        questions[9][2] = "The applet makes Java code secure";
        questions[9][3] = "Use of exception handling";
        questions[9][4] = "Dynamic binding between objects";

        // Set correct answers
        answers[0][1] = "JDB";
        answers[1][1] = "int";
        answers[2][1] = "java.util";
        answers[3][1] = "Marker Interface";
        answers[4][1] = "Heap memory";
        answers[5][1] = "Remote interface";
        answers[6][1] = "import";
        answers[7][1] = "Java Archive";
        answers[8][1] = "java.lang.StringBuilder";
        answers[9][1] = "Bytecode is executed by JVM";
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == nextButton) {
            ans_given = 1;

            if (groupoptions.getSelection() == null) {
                useranswers[count][0] = "";
            } else {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand();
            }

            if (count == 8) {
                nextButton.setEnabled(false);
                submitButton.setEnabled(true);
            }

            count++;
            start(count);

        } else if (ae.getSource() == lifelineButton) {
            if (!lifelineUsed) {
                // Disable two wrong options
                if (count == 2 || count == 4 || count == 6 || count == 8 || count == 9) {
                    opt2.setEnabled(false);
                    opt3.setEnabled(false);
                } else {
                    opt1.setEnabled(false);
                    opt4.setEnabled(false);
                }
                lifelineButton.setEnabled(false);
                lifelineButton.setText("✓ LIFELINE USED");
                lifelineButton.setBackground(new Color(149, 165, 166));
                lifelineUsed = true;
            }

        } else if (ae.getSource() == submitButton) {
            ans_given = 1;
            if (groupoptions.getSelection() == null) {
                useranswers[count][0] = "";
            } else {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand();
            }

            for (int i = 0; i < useranswers.length; i++) {
                if (useranswers[i][0].equals(answers[i][1])) {
                    score += 10;
                }
            }
            setVisible(false);
            new ModernScore(name, score);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        if (timer > 0) {
            timer--;
        }

        // Update timer label with color coding
        if (timer > 10) {
            timerLabel.setText("⏱️ Time: " + timer + "s");
            timerLabel.setForeground(Color.WHITE);
        } else if (timer > 5) {
            timerLabel.setText("⏱️ Time: " + timer + "s");
            timerLabel.setForeground(warningColor);
        } else {
            timerLabel.setText("⚠️ Time: " + timer + "s");
            timerLabel.setForeground(dangerColor);
        }

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        repaint();

        if (ans_given == 1) {
            ans_given = 0;
            timer = 15;
        } else if (timer < 0) {
            timer = 15;

            if (count == 8) {
                nextButton.setEnabled(false);
                submitButton.setEnabled(true);
            }
            if (count == 9) {
                if (groupoptions.getSelection() == null) {
                    useranswers[count][0] = "";
                } else {
                    useranswers[count][0] = groupoptions.getSelection().getActionCommand();
                }

                for (int i = 0; i < useranswers.length; i++) {
                    if (useranswers[i][0].equals(answers[i][1])) {
                        score += 10;
                    }
                }
                setVisible(false);
                new ModernScore(name, score);
            } else {
                if (groupoptions.getSelection() == null) {
                    useranswers[count][0] = "";
                } else {
                    useranswers[count][0] = groupoptions.getSelection().getActionCommand();
                }
                count++;
                start(count);
            }
        }
    }

    public void start(int count) {
        qnoLabel.setText("Q" + (count + 1));
        questionLabel.setText("<html>" + questions[count][0] + "</html>");

        opt1.setText(questions[count][1]);
        opt1.setActionCommand(questions[count][1]);
        opt1.setEnabled(true);

        opt2.setText(questions[count][2]);
        opt2.setActionCommand(questions[count][2]);
        opt2.setEnabled(true);

        opt3.setText(questions[count][3]);
        opt3.setActionCommand(questions[count][3]);
        opt3.setEnabled(true);

        opt4.setText(questions[count][4]);
        opt4.setActionCommand(questions[count][4]);
        opt4.setEnabled(true);

        groupoptions.clearSelection();

        // Update progress bar
        progressBar.setValue(count + 1);
        progressBar.setString("Question " + (count + 1) + " of 10");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ModernQuiz("Test User", "Java Fundamentals"));
    }
}
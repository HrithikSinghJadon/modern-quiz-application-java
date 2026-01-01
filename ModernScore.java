import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class ModernScore extends JFrame implements ActionListener {

    JButton playAgainButton, exitButton;

    // Modern colors
    Color primaryColor = new Color(41, 128, 185);
    Color accentColor = new Color(46, 204, 113);
    Color dangerColor = new Color(231, 76, 60);
    Color warningColor = new Color(243, 156, 18);
    Color backgroundColor = new Color(236, 240, 241);

    ModernScore(String name, int score) {
        setTitle("Quiz Results");
        getContentPane().setBackground(backgroundColor);
        setLayout(null);

        // Determine performance level
        String performance;
        String emoji;
        Color performanceColor;
        String message;

        if (score >= 80) {
            performance = "EXCELLENT!";
            emoji = "🏆";
            performanceColor = accentColor;
            message = "Outstanding performance! You're a Java expert!";
        } else if (score >= 60) {
            performance = "GOOD JOB!";
            emoji = "🌟";
            performanceColor = primaryColor;
            message = "Well done! Keep practicing to achieve excellence!";
        } else if (score >= 40) {
            performance = "NOT BAD!";
            emoji = "👍";
            performanceColor = warningColor;
            message = "You're on the right track. Practice more to improve!";
        } else {
            performance = "KEEP TRYING!";
            emoji = "💪";
            performanceColor = dangerColor;
            message = "Don't give up! Every expert was once a beginner!";
        }

        // Main result panel with gradient background
        JPanel resultPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, performanceColor, 0, getHeight(), backgroundColor);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        resultPanel.setBounds(0, 0, 1000, 300);
        resultPanel.setLayout(null);
        add(resultPanel);

        // Trophy/Emoji
        JLabel emojiLabel = new JLabel(emoji);
        emojiLabel.setBounds(430, 40, 140, 140);
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 120));
        emojiLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultPanel.add(emojiLabel);

        // Performance label
        JLabel performanceLabel = new JLabel(performance);
        performanceLabel.setBounds(0, 180, 1000, 50);
        performanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 42));
        performanceLabel.setForeground(Color.WHITE);
        performanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultPanel.add(performanceLabel);

        // Thank you message
        JLabel thankYouLabel = new JLabel("Thank you, " + name + "!");
        thankYouLabel.setBounds(0, 235, 1000, 40);
        thankYouLabel.setFont(new Font("Segoe UI", Font.PLAIN, 28));
        thankYouLabel.setForeground(Color.WHITE);
        thankYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultPanel.add(thankYouLabel);

        // Score card panel
        JPanel scorePanel = new JPanel();
        scorePanel.setBounds(250, 330, 500, 200);
        scorePanel.setBackground(Color.WHITE);
        scorePanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(189, 195, 199), 2, true),
                new EmptyBorder(30, 40, 30, 40)
        ));
        scorePanel.setLayout(null);
        add(scorePanel);

        // Score label
        JLabel scoreTextLabel = new JLabel("YOUR SCORE");
        scoreTextLabel.setBounds(0, 20, 420, 30);
        scoreTextLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        scoreTextLabel.setForeground(new Color(127, 140, 141));
        scoreTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scorePanel.add(scoreTextLabel);

        // Actual score display
        JLabel scoreValueLabel = new JLabel(score + " / 100");
        scoreValueLabel.setBounds(0, 60, 420, 70);
        scoreValueLabel.setFont(new Font("Segoe UI", Font.BOLD, 64));
        scoreValueLabel.setForeground(performanceColor);
        scoreValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scorePanel.add(scoreValueLabel);

        // Motivational message
        JLabel messageLabel = new JLabel("<html><center>" + message + "</center></html>");
        messageLabel.setBounds(100, 560, 800, 60);
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        messageLabel.setForeground(new Color(52, 73, 94));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(messageLabel);

        // Statistics panel
        JPanel statsPanel = new JPanel();
        statsPanel.setBounds(250, 640, 500, 80);
        statsPanel.setBackground(new Color(240, 248, 255));
        statsPanel.setBorder(new LineBorder(new Color(189, 195, 199), 1, true));
        statsPanel.setLayout(new GridLayout(1, 3));
        add(statsPanel);

        // Correct answers
        JLabel correctLabel = new JLabel("<html><center>✓ Correct<br/><b>" + (score/10) + "</b></center></html>");
        correctLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        correctLabel.setForeground(accentColor);
        statsPanel.add(correctLabel);

        // Wrong answers
        JLabel wrongLabel = new JLabel("<html><center>✗ Wrong<br/><b>" + (10 - score/10) + "</b></center></html>");
        wrongLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        wrongLabel.setForeground(dangerColor);
        statsPanel.add(wrongLabel);

        // Percentage
        JLabel percentLabel = new JLabel("<html><center>% Score<br/><b>" + score + "%</b></center></html>");
        percentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        percentLabel.setForeground(primaryColor);
        statsPanel.add(percentLabel);

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBounds(250, 750, 500, 80);
        buttonsPanel.setBackground(backgroundColor);
        buttonsPanel.setLayout(null);
        add(buttonsPanel);

        // Play Again button
        playAgainButton = new JButton("🔄 PLAY AGAIN");
        playAgainButton.setBounds(0, 0, 240, 70);
        playAgainButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        playAgainButton.setBackground(accentColor);
        playAgainButton.setForeground(Color.WHITE);
        playAgainButton.setFocusPainted(false);
        playAgainButton.setBorderPainted(false);
        playAgainButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        playAgainButton.addActionListener(this);
        addHoverEffect(playAgainButton, accentColor, new Color(39, 174, 96));
        buttonsPanel.add(playAgainButton);

        // Exit button
        exitButton = new JButton("✕ EXIT");
        exitButton.setBounds(260, 0, 240, 70);
        exitButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        exitButton.setBackground(dangerColor);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setBorderPainted(false);
        exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitButton.addActionListener(this);
        addHoverEffect(exitButton, dangerColor, new Color(192, 57, 43));
        buttonsPanel.add(exitButton);

        // Frame settings
        setSize(1000, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void addHoverEffect(JButton button, Color normalColor, Color hoverColor) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(normalColor);
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == playAgainButton) {
            // Reset quiz variables
            ModernQuiz.timer = 15;
            ModernQuiz.ans_given = 0;
            ModernQuiz.count = 0;
            ModernQuiz.score = 0;

            setVisible(false);
            new ModernLogin();
        } else if (ae.getSource() == exitButton) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ModernScore("Test User", 80));
    }
}
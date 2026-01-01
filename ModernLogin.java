import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class ModernLogin extends JFrame implements ActionListener {

    JButton startButton;
    JTextField nameField;
    JComboBox<String> categoryCombo;

    // Modern color scheme
    Color primaryColor = new Color(41, 128, 185);      // Blue
    Color secondaryColor = new Color(52, 73, 94);      // Dark Blue
    Color accentColor = new Color(46, 204, 113);       // Green
    Color backgroundColor = new Color(236, 240, 241);  // Light Gray
    Color textColor = new Color(44, 62, 80);           // Dark Gray

    ModernLogin() {
        // Frame setup with modern design
        setTitle("Simple Minds Quiz Application");
        getContentPane().setBackground(backgroundColor);
        setLayout(null);

        // Create gradient panel for header
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth(), h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, primaryColor, w, 0, secondaryColor);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        headerPanel.setBounds(0, 0, 1200, 150);
        headerPanel.setLayout(null);
        add(headerPanel);

        // App icon/logo
        JLabel iconLabel = new JLabel("🎓");
        iconLabel.setBounds(50, 35, 80, 80);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 70));
        headerPanel.add(iconLabel);

        // Main heading with shadow effect
        JLabel heading = new JLabel("SIMPLE MINDS QUIZ");
        heading.setBounds(150, 40, 900, 50);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 48));
        heading.setForeground(Color.WHITE);
        headerPanel.add(heading);

        // Subtitle
        JLabel subtitle = new JLabel("Test Your Java Knowledge");
        subtitle.setBounds(150, 95, 500, 30);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        subtitle.setForeground(new Color(236, 240, 241));
        headerPanel.add(subtitle);

        // Main content panel with rounded border
        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(200, 220, 800, 400);
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(189, 195, 199), 1, true),
                new EmptyBorder(40, 60, 40, 60)
        ));
        contentPanel.setLayout(null);
        add(contentPanel);

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome! Ready to challenge yourself?");
        welcomeLabel.setBounds(40, 20, 700, 35);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcomeLabel.setForeground(textColor);
        contentPanel.add(welcomeLabel);

        // Name label with icon
        JLabel nameLabel = new JLabel("👤  Your Name");
        nameLabel.setBounds(40, 90, 300, 30);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        nameLabel.setForeground(textColor);
        contentPanel.add(nameLabel);

        // Modern text field with custom styling
        nameField = new JTextField();
        nameField.setBounds(40, 130, 680, 50);
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        nameField.setForeground(textColor);
        nameField.setBackground(backgroundColor);
        nameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(189, 195, 199), 2, true),
                new EmptyBorder(5, 15, 5, 15)
        ));
        contentPanel.add(nameField);

        // Category selection label
        JLabel categoryLabel = new JLabel("📚  Select Quiz Category");
        categoryLabel.setBounds(40, 200, 300, 30);
        categoryLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        categoryLabel.setForeground(textColor);
        contentPanel.add(categoryLabel);

        // Modern combo box
        String[] categories = {"Java Fundamentals", "OOP Concepts", "Advanced Java", "Mixed Questions"};
        categoryCombo = new JComboBox<>(categories);
        categoryCombo.setBounds(40, 240, 680, 50);
        categoryCombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        categoryCombo.setBackground(backgroundColor);
        categoryCombo.setForeground(textColor);
        categoryCombo.setBorder(new LineBorder(new Color(189, 195, 199), 2, true));
        contentPanel.add(categoryCombo);

        // Modern start button with hover effect
        startButton = new JButton("START QUIZ");
        startButton.setBounds(250, 320, 280, 60);
        startButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
        startButton.setBackground(accentColor);
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startButton.addActionListener(this);

        // Add hover effect
        startButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                startButton.setBackground(new Color(39, 174, 96)); // Darker green
            }
            public void mouseExited(MouseEvent e) {
                startButton.setBackground(accentColor);
            }
        });
        contentPanel.add(startButton);

        // Footer information
        JLabel footerLabel = new JLabel("⏱️ 10 Questions • 15 Seconds Each • 50-50 Lifeline Available");
        footerLabel.setBounds(0, 650, 1200, 30);
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        footerLabel.setForeground(new Color(127, 140, 141));
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(footerLabel);

        // Frame properties
        setSize(1200, 750);
        setLocationRelativeTo(null);  // Center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == startButton) {
            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter your name to continue!",
                        "Name Required",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            String category = (String) categoryCombo.getSelectedItem();
            setVisible(false);
            new ModernQuiz(name, category);
        }
    }

    public static void main(String[] args) {
        // Set system look and feel for better appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new ModernLogin());
    }
}
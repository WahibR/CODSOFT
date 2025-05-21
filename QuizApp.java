import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizApp extends JFrame implements ActionListener {
    String[][] questions = {
            {"What is the capital of France?", "Paris", "London", "Berlin", "Madrid", "Paris"},
            {"What is 2 + 2?", "3", "4", "5", "6", "4"},
            {"What is the largest planet?", "Earth", "Venus", "Jupiter", "Mars", "Jupiter"}
    };

    int index = 0, score = 0, timeLeft = 10;
    JLabel questionLabel, timerLabel;
    JRadioButton[] options = new JRadioButton[4];
    ButtonGroup group;
    JButton submitButton;
    Timer timer;

    public QuizApp() {
        setTitle("Quiz App");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new GridLayout(2, 1));
        questionLabel = new JLabel();
        timerLabel = new JLabel("Time Left: 10", SwingConstants.RIGHT);
        top.add(questionLabel);
        top.add(timerLabel);
        add(top, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(4, 1));
        group = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            group.add(options[i]);
            center.add(options[i]);
        }
        add(center, BorderLayout.CENTER);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton, BorderLayout.SOUTH);

        loadQuestion();

        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft);
            if (timeLeft == 0) {
                submitAnswer(true);
            }
        });
        timer.start();

        setVisible(true);
    }

    public void loadQuestion() {
        if (index == questions.length) {
            showResult();
            return;
        }

        timeLeft = 10;
        timerLabel.setText("Time Left: " + timeLeft);
        questionLabel.setText("Q" + (index + 1) + ": " + questions[index][0]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(questions[index][i + 1]);
            options[i].setSelected(false);
        }
    }

    public void submitAnswer(boolean autoSubmit) {
        timer.stop();

        String selected = null;
        for (JRadioButton option : options) {
            if (option.isSelected()) {
                selected = option.getText();
                break;
            }
        }

        if (!autoSubmit && selected == null) {
            JOptionPane.showMessageDialog(this, "Please choose an option.");
            timer.start();
            return;
        }

        if (questions[index][5].equals(selected)) {
            score++;
        }

        index++;
        loadQuestion();
        timer.start();
    }

    public void showResult() {
        JOptionPane.showMessageDialog(this, "Your score: " + score + " out of " + questions.length);
        System.exit(0);
    }

    public void actionPerformed(ActionEvent e) {
        submitAnswer(false);
    }

    public static void main(String[] args) {
        new QuizApp();
    }
}

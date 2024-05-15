import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tic implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JButton[] buttons;
    private JLabel turnLabel;
    private boolean xTurn;
    private boolean gameOver;

    public Tic() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
         frame.setLocationRelativeTo(null);

        panel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[9];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("");
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        turnLabel = new JLabel("X's turn");
        turnLabel.setHorizontalAlignment(JLabel.CENTER);

       frame.add(panel, BorderLayout.CENTER);
        frame.add(turnLabel, BorderLayout.SOUTH);

        xTurn = true;
        gameOver = false;

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            return;
        }

        JButton button = (JButton)e.getSource();
        if (button.getText().equals("")) {
            if (xTurn) {
                button.setText("X");
                turnLabel.setText("O's turn");
            } else {
                button.setText("O");
                turnLabel.setText("X's turn");
            }
            xTurn = !xTurn;

            if (checkForWin()) {
                String winner = xTurn ? "O" : "X";
                JOptionPane.showMessageDialog(frame, winner + " wins!");
                gameOver = true;
            } else if (checkForDraw()) {
                JOptionPane.showMessageDialog(frame, "It's a draw!");
                gameOver = true;
            }
        }
    }

    private boolean checkForWin() {
        String[] positions = new String[9];
        for (int i = 0; i < buttons.length; i++) {
            positions[i] = buttons[i].getText();
        }

        for (int i = 0; i < 9; i += 3) {
            if (!positions[i].equals("") && positions[i].equals(positions[i+1]) && positions[i].equals(positions[i+2])) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (!positions[i].equals("") && positions[i].equals(positions[i+3]) && positions[i].equals(positions[i+6])) {
                return true;
            }
        }

        if (!positions[0].equals("") && positions[0].equals(positions[4]) && positions[0].equals(positions[8])) {
            return true;
        }

        if (!positions[2].equals("") && positions[2].equals(positions[4]) && positions[2].equals(positions[6])) {
            return true;
        }

        return false;
    }

    private boolean checkForDraw() {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new Tic();
    }
}
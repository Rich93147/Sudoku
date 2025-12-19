import java.awt.*;
import javax.swing.*;

public class Sudoku {
    int boardWidth = 800;
    int boardHeight = 850;
    final int MISTAKES_LEFT = 3;

    //OBJS:
    /*
    1. Create a "panel" that displays:
        a. The board itself
        b. Sudoku, name of the game
        c. Mistakes left
        d. Numbers to place
    2. Create a label that holds the text (The panel holds the label)
    3. Code a random Sudoku board generator, call in to create board
     */

    JFrame frame = new JFrame("Sudoku");

    //Labels
    JLabel titleLabel = new JLabel();
    JLabel mcLabel = new JLabel();

    //Panels
    JPanel titlePanel = new JPanel();
    JPanel mcPanel = new JPanel();
    // Create board (Must have 9 entries, 9 digits long for a 9x9 board
    // Note for future me: use _ or 0 for empty tiles or something
    int[][] boardSol = RandomSudokuBoardCreator.generateBoard();

    int[][] ranBoard = RandomSudokuBoardCreator.sudokufyBoard(boardSol);

    Sudoku() {
        // Window configs
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Title text configs
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setText("Sudoku");

        // Mistake text configs
        mcLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        mcLabel.setHorizontalAlignment(JLabel.CENTER);
        mcLabel.setText("Mistakes: " + MISTAKES_LEFT);

        // Text Panel configs
        titlePanel.add(titleLabel);

        frame.add(titlePanel, BorderLayout.NORTH);
    }
}

package sudoku;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * The main Sudoku program
 */
public class SudokuMain extends JFrame {
   // private variables
   GameBoard board = new GameBoard();
   JButton btnNewGame = new JButton("New Game");

   // Constructor
   public SudokuMain() {
      Container cp = getContentPane();
      cp.setLayout(new BorderLayout());

      cp.add(board, BorderLayout.CENTER);

      // Add a button to the south to re-start the game 
      cp.add(btnNewGame, BorderLayout.SOUTH);
      RestartBtnListener restartListener = new RestartBtnListener();
      btnNewGame.addActionListener(restartListener);
      
      board.init();
      pack();     // Pack the UI components, instead of setSize()
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Handle window closing
      setTitle("Sudoku");
      setVisible(true);
   }

   /** The entry main() entry method */
   public static void main(String[] args) {
      // [TODO 1] Check Swing program template on how to run the constructor
      SudokuMain sudoku = new SudokuMain();
   }
   
   private class RestartBtnListener implements ActionListener{
	   @Override
	   public void actionPerformed(ActionEvent e) {
		   SudokuMain sudoku = new SudokuMain(); //need to find a way to reset board
	   }
   }
}
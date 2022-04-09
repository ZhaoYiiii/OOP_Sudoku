package sudoku;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * The main Sudoku program
 */
public class SudokuMain extends JFrame {
	// private variables
	final static boolean shouldFill = true;
	GameBoard board = new GameBoard();
	JButton btnNewGame = new JButton("New Game");
	JButton exitGame = new JButton("EXIT");

	// Constructor
	public SudokuMain() {
		Container cp = getContentPane();
		cp.setLayout(new GridBagLayout());

		GridBagConstraints boardCons = new GridBagConstraints();
		if(shouldFill) {
			boardCons.fill = GridBagConstraints.BOTH;
		}
		boardCons.gridx = 1;
		boardCons.gridy = 0;
		cp.add(board, boardCons);

		// Add a button to the south to re-start the game 
		GridBagConstraints btnRSCons = new GridBagConstraints();
		btnRSCons.fill = GridBagConstraints.HORIZONTAL;
		btnRSCons.gridx = 1;
		btnRSCons.gridy = GridBagConstraints.RELATIVE;
		btnRSCons.gridwidth = 1;
		btnRSCons.weighty = 1.0;
		cp.add(btnNewGame, btnRSCons);
		ExitBtnListener restartListener = new ExitBtnListener();
		btnNewGame.addActionListener(restartListener);

		GridBagConstraints btnExitCons = new GridBagConstraints();
		btnExitCons.fill = GridBagConstraints.HORIZONTAL;
		btnExitCons.gridx = 1;
		btnExitCons.gridy = GridBagConstraints.RELATIVE;
		btnExitCons.gridwidth = 1;
		btnExitCons.weighty = 1.0;
		cp.add(btnNewGame, btnExitCons);
		cp.add(exitGame, btnExitCons);
		RestartBtnListener exitListener = new RestartBtnListener();
		btnNewGame.addActionListener(exitListener);

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
		public void actionPerformed(ActionEvent e) { //need to find a way to reset board
			CloseFrame();
			SudokuMain sudoku = new SudokuMain();
		}
	}

	private class ExitBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) { //Exit
			CloseFrame(); 
		}
	}

	public void CloseFrame() {
		super.dispose();
	}
}
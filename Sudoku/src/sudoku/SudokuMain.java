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
	JButton exitGame = new JButton("EXIT");
	GridBagConstraints gbc = new GridBagConstraints();
	
	

	// Constructor
	public SudokuMain() {
		Container cp = this.getContentPane();
		cp.setLayout(new GridBagLayout());

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		cp.add(board, gbc);

		// Add a button to the south to re-start the game 
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.gridwidth = 1;
		gbc.weighty = 1.0;
		cp.add(btnNewGame, gbc);
		
				
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.gridwidth = 1;
		gbc.weighty = 1.0;
		cp.add(exitGame, gbc);
		
		ExitBtnListener restartListener = new ExitBtnListener();
		btnNewGame.addActionListener(restartListener);

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
		SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	            new SudokuMain();
	         }
		});
	}

	private class RestartBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) { //need to find a way to reset board
			CloseFrame();
		    new SudokuMain();
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
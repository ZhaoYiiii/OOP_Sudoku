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
	JButton btnExit = new JButton("EXIT");
	JButton btnReset = new JButton("Reset");
	GridBagConstraints gbc = new GridBagConstraints();
	
	

	// Constructor
	public SudokuMain() {
		Container cp = this.getContentPane();
		cp.setLayout(new GridBagLayout());
		 
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 9;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.insets = new Insets(20, 20, 0, 20);
		cp.add(board, gbc);

		// Add a button to the south to re-start the game 
		NewGameBtnListener newGameListener = new NewGameBtnListener();
		btnNewGame.addActionListener(newGameListener);
				
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		gbc.weighty = 0.5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipadx = 134;
		gbc.ipady = 10;
		gbc.insets = new Insets(10, 50, 0, 50);
		cp.add(btnNewGame, gbc);
		
		ResetBtnListener resetListener = new ResetBtnListener();
		btnReset.addActionListener(resetListener);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		gbc.weighty = 0.5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipadx = 134;
		gbc.ipady = 10;
		gbc.insets = new Insets(10, 50, 0, 50);
		cp.add(btnReset, gbc);
		
		ExitBtnListener exitListener = new ExitBtnListener();
		btnExit.addActionListener(exitListener);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 4;
		gbc.weighty = 0.5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 10;
		gbc.insets = new Insets(10, 50, 10, 50);
		cp.add(btnExit, gbc);
				
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

	private class NewGameBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) { //New Game
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

	private class ResetBtnListener implements ActionListener{ //need to implement
		@Override
		public void actionPerformed(ActionEvent e) { //Reset
			for(int row = 0; row < 9; row++) {
				for(int col = 0; col < 9; col++) {
					Cell sourceCell = (Cell)e.getSource();
					
					}
				}

			}
		}

	
	public void CloseFrame() {
		super.dispose();
	}
}
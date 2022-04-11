package sudoku;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.*;

/**
 * The main Sudoku program
 */
public class Scoreboard extends JFrame {
	// private variables
	GameBoard board = new GameBoard();
	JButton btnNewGame = new JButton("New Game");
	JButton btnExit = new JButton("EXIT");
	GridBagConstraints gbc = new GridBagConstraints();
	
	// Constructor
	public Scoreboard() {
		
		Container cp = this.getContentPane();
		cp.setLayout(new GridBagLayout());
			
		// Add a button to the south to re-start the game 
		NewGameBtnListener newGameListener = new NewGameBtnListener();
		btnNewGame.addActionListener(newGameListener);
		btnNewGame.setBackground(Color.GREEN);
		Font font = new Font("Dialog", Font.BOLD, 20);
		btnNewGame.setFont(font);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.ipadx = 100;
		gbc.ipady = 10;
		gbc.insets = new Insets(10, 50, 0, 30);
		cp.add(btnNewGame, gbc);
		
		ExitBtnListener exitListener = new ExitBtnListener();
		btnExit.addActionListener(exitListener);
		btnExit.setBackground(Color.MAGENTA);
		btnExit.setFont(font);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 9;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.ipady = 10;
		gbc.insets = new Insets(10, 50, 10, 50);
		cp.add(btnExit, gbc);
				
		pack();     // Pack the UI components, instead of setSize()
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Handle window closing
		setTitle("Sudoku - Scoreboard");
		setVisible(true);
	}
	
	
	
	

	private class NewGameBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) { //New Game
			CloseFrame();
			SudokuMain sudoku = new SudokuMain();
			sudoku.setJMenuBar(sudoku.Menu());
			sudoku.start();
		}
	}

	private class ExitBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) { //Exit
			CloseFrame(); 
			System.exit(0);
		}
	}

	public void CloseFrame() {
		super.dispose();
	}
}
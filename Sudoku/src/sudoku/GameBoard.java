package sudoku;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class GameBoard extends JPanel {
	// Name-constants for the game board properties
	public static final int GRID_SIZE = 9;    // Size of the board
	public static final int SUBGRID_SIZE = 3; // Size of the sub-grid

	// Name-constants for UI sizes
	public static final int CELL_SIZE = 60;   // Cell width/height in pixels
	public static final int BOARD_WIDTH  = CELL_SIZE * GRID_SIZE;
	public static final int BOARD_HEIGHT = CELL_SIZE * GRID_SIZE;
	// Board width/height in pixels

	// The game board composes of 9x9 "Customized" JTextFields,
	private Cell[][] cells = new Cell[GRID_SIZE][GRID_SIZE];
	// It also contains a Puzzle
	private Puzzle puzzle = new Puzzle();
	private static final SudokuMain sudoku = new SudokuMain();
	//Border oldBorder = jTextField.getBorder();
	//Border redBorder = BorderFactory.createMatteBorder(0, 0, 0, 5, Color.RED);
	//Border newBorder = BorderFactory.createCompoundBorder(redBorder, oldBorder);
	//jTextField.setBorder(newBorder);

	// Constructor
	public GameBoard() {
		super.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));  // JPanel
		// Allocate the 2D array of Cell, and added into JPanel.
		for (int row = 0; row < GRID_SIZE; ++row) {
			for (int col = 0; col < GRID_SIZE; ++col) {
				cells[row][col] = new Cell(row, col);
				super.add(cells[row][col]);   // JPanel //Sorry for hardcoding this
				if((row == 0 && col == 0) || (row == 0 && col == 3) ||(row == 0 && col == 6) || (row == 3 && col == 0) || (row == 3 && col == 3) ||(row == 3 && col == 6) ||(row == 6 && col == 0) ||(row == 6 && col == 3) ||(row == 6 && col == 6)) {
					Border blackBorder = BorderFactory.createMatteBorder(3, 3, 0, 0, Color.BLACK); //(top,left,btm,right)
					Border blackInnerBorder = BorderFactory.createMatteBorder(0, 0, 2, 2, Color.BLACK);
					Border newBorder = BorderFactory.createCompoundBorder(blackBorder, blackInnerBorder);
					cells[row][col].setBorder(newBorder);
				}
				if((row == 0 && col == 1) || (row == 0 && col == 4) ||(row == 0 && col == 7) || (row == 3 && col == 1) || (row == 3 && col == 4) ||(row == 3 && col == 7) ||(row == 6 && col == 1) ||(row == 6 && col == 4) ||(row == 6 && col == 7)) {
					Border blackBorder = BorderFactory.createMatteBorder(3, 0, 0, 0, Color.BLACK);
					Border blackInnerBorder = BorderFactory.createMatteBorder(0, 0, 2, 2, Color.BLACK);
					Border newBorder = BorderFactory.createCompoundBorder(blackBorder, blackInnerBorder);
					cells[row][col].setBorder(newBorder);
				}
				if((row == 0 && col == 2) || (row == 0 && col == 5) ||(row == 0 && col == 8) || (row == 3 && col == 2) || (row == 3 && col == 5) ||(row == 3 && col == 8) ||(row == 6 && col == 2) ||(row == 6 && col == 5) ||(row == 6 && col == 8)) {
					Border blackBorder = BorderFactory.createMatteBorder(3, 0, 0, 3, Color.BLACK);
					Border blackInnerBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK);
					Border newBorder = BorderFactory.createCompoundBorder(blackBorder, blackInnerBorder);
					cells[row][col].setBorder(newBorder);
				}
				if((row == 1 && col == 0) || (row == 1 && col == 3) ||(row == 1 && col == 6) || (row == 4 && col == 0) || (row == 4 && col == 3) ||(row == 4 && col == 6) ||(row == 7 && col == 0) ||(row == 7 && col == 3) ||(row == 7 && col == 6)) {
					Border blackBorder = BorderFactory.createMatteBorder(0, 3, 0, 0, Color.BLACK);
					Border blackInnerBorder = BorderFactory.createMatteBorder(0, 0, 2, 2, Color.BLACK);
					Border newBorder = BorderFactory.createCompoundBorder(blackBorder, blackInnerBorder);
					cells[row][col].setBorder(newBorder);
				}
				if((row == 1 && col == 1) || (row == 1 && col == 4) ||(row == 1 && col == 7) || (row == 4 && col == 1) || (row == 4 && col == 4) ||(row == 4 && col == 7) ||(row == 7 && col == 1) ||(row == 7 && col == 4) ||(row == 7 && col == 7)) {
					Border blackBorder = BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK);
					Border blackInnerBorder = BorderFactory.createMatteBorder(0, 0, 2, 2, Color.BLACK);
					Border newBorder = BorderFactory.createCompoundBorder(blackBorder, blackInnerBorder);
					cells[row][col].setBorder(newBorder);
				}
				if((row == 1 && col == 2) || (row == 1 && col == 5) ||(row == 1 && col == 8) || (row == 4 && col == 2) || (row == 4 && col == 5) ||(row == 4 && col == 8) ||(row == 7 && col == 2) ||(row == 7 && col == 5) ||(row == 7 && col == 8)) {
					Border blackBorder = BorderFactory.createMatteBorder(0, 0, 0, 3, Color.BLACK);
					Border blackInnerBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK);
					Border newBorder = BorderFactory.createCompoundBorder(blackBorder, blackInnerBorder);
					cells[row][col].setBorder(newBorder);
				}
				if((row == 2 && col == 0) || (row == 2 && col == 3) ||(row == 2 && col == 6) || (row == 5 && col == 0) || (row == 5 && col == 3) ||(row == 5 && col == 6) ||(row == 8 && col == 0) ||(row == 8 && col == 3) ||(row == 8 && col == 6)) {
					Border blackBorder = BorderFactory.createMatteBorder(0, 3, 3, 0, Color.BLACK);
					Border blackInnerBorder = BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK);
					Border newBorder = BorderFactory.createCompoundBorder(blackBorder, blackInnerBorder);
					cells[row][col].setBorder(newBorder);
				}
				if((row == 2 && col == 1) || (row == 2 && col == 4) ||(row == 2 && col == 7) || (row == 5 && col == 1) || (row == 5 && col == 4) ||(row == 5 && col == 7) ||(row == 8 && col == 1) ||(row == 8 && col == 4) ||(row == 8 && col == 7)) {
					Border blackBorder = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK);
					Border blackInnerBorder = BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK);
					Border newBorder = BorderFactory.createCompoundBorder(blackBorder, blackInnerBorder);
					cells[row][col].setBorder(newBorder);
				}
				if((row == 2 && col == 2) || (row == 2 && col == 5) ||(row == 2 && col == 8) || (row == 5 && col == 2) || (row == 5 && col == 5) ||(row == 5 && col == 8) ||(row == 8 && col == 2) ||(row == 8 && col == 5) ||(row == 8 && col == 8)) {
					Border blackBorder = BorderFactory.createMatteBorder(0, 0, 3, 3, Color.BLACK);
					Border blackInnerBorder = BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK);
					Border newBorder = BorderFactory.createCompoundBorder(blackBorder, blackInnerBorder);
					cells[row][col].setBorder(newBorder);
				}
			}
		}
		
		

		// [TODO 3] Allocate a common listener as the ActionEvent listener for all the
		//  Cells (JTextFields)
		CellInputListener listener = new CellInputListener();


		// [TODO 4] Every editable cell adds this common listener
		for (int row = 0; row < GRID_SIZE; ++row) {
			for (int col = 0; col < GRID_SIZE; ++col) {
				if (cells[row][col].isEditable()) {
					cells[row][col].addKeyListener(listener);   // For all editable rows and cols
				}
			}
		}

		super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
	}

	/**
	 * Initialize the puzzle number, status, background/foreground color,
	 *   of all the cells from puzzle[][] and isRevealed[][].
	 * Call to start a new game.
	 */
	public void init() {
		// Get a new puzzle
		puzzle.newPuzzle(2);

		// Based on the puzzle, initialize all the cells.
		for (int row = 0; row < GRID_SIZE; ++row) {
			for (int col = 0; col < GRID_SIZE; ++col) {
				cells[row][col].init(puzzle.numbers[row][col], puzzle.isShown[row][col]);
			}
		}
	}

	/**
	 * Return true if the puzzle is solved
	 * i.e., none of the cell have status of NO_GUESS or WRONG_GUESS
	 */
	public boolean isSolved() {
		for (int row = 0; row < GRID_SIZE; ++row) {
			for (int col = 0; col < GRID_SIZE; ++col) {
				if (cells[row][col].status == CellStatus.NO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
					return false;
				}
			}
		}
		return true;
	}

	// [TODO 2] Define a Listener Inner Class
	private class CellInputListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
			// Get a reference of the JTextField that triggers this action event
			int inputNumber = Character.getNumericValue(e.getKeyChar());
			Cell sourceCell = (Cell)e.getSource();

			// Retrieve the int entered
			//int numberIn = Integer.parseInt(sourceCell.inputNumber);
			// For debugging
			System.out.println("You entered " + inputNumber);

			/*
			 * [TODO 5]
			 * Check the numberIn against sourceCell.number.
			 * Update the cell status sourceCell.status,
			 * and re-paint the cell via sourceCell.paint().
			 */
			if (inputNumber == sourceCell.number) {
				sourceCell.status = CellStatus.CORRECT_GUESS;
			} else {
				if(inputNumber != sourceCell.number)
				{
					sourceCell.status = CellStatus.WRONG_GUESS;
				}
				if(inputNumber == -1)
				{
					sourceCell.status = CellStatus.NO_GUESS;
				}
			}
			sourceCell.paint();

			/*
			 * [TODO 6][Later] Check if the player has solved the puzzle after this move,
			 *   by call isSolved(). Put up a congratulation JOptionPane, if so.
			 */
			if(isSolved())
				JOptionPane.showMessageDialog(null, "        Congratulation!\n" + "        You completed the sudoku in " + sudoku.getTF());
		}
		public void keyPressed(KeyEvent e) {

		}
		public void keyReleased(KeyEvent e) {

		}
	}

}
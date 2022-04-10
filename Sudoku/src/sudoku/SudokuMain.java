package sudoku;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

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
	private static final int N = 60;
	//private static final String stop = "Stop";
	//private static final String start = "Start";
	private final ClockListener cl = new ClockListener();
	private final Timer t = new Timer(1000, cl);
	private final JTextField tf = new JTextField(8);
	GridBagConstraints gbc = new GridBagConstraints();
	
	
	// Constructor
	public SudokuMain() {
		Container cp = this.getContentPane();
		cp.setLayout(new GridBagLayout());
				
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 9;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 0.5;
		gbc.insets = new Insets(20, 20, 0, 20);
		cp.add(board, gbc);

		// Add a button to the south to re-start the game 
		NewGameBtnListener newGameListener = new NewGameBtnListener();
		btnNewGame.addActionListener(newGameListener);
				
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.ipadx = 100;
		gbc.ipady = 10;
		gbc.insets = new Insets(10, 50, 0, 30);
		cp.add(btnNewGame, gbc);
		
		ResetBtnListener resetListener = new ResetBtnListener();
		btnReset.addActionListener(resetListener);
		
		gbc.gridx = 4;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.ipadx = 100;
		gbc.ipady = 10;
		gbc.insets = new Insets(10, 0, 0, 50);
		cp.add(btnReset, gbc);
		
		ExitBtnListener exitListener = new ExitBtnListener();
		btnExit.addActionListener(exitListener);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 9;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.ipady = 10;
		gbc.insets = new Insets(10, 50, 10, 50);
		cp.add(btnExit, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 9;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.ipady = 10;
		gbc.insets = new Insets(10, 50, 10, 50);
		cp.add(tf,gbc);
		
		board.init();
		
		pack();     // Pack the UI components, instead of setSize()
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Handle window closing
		setTitle("Sudoku");
		setVisible(true);
	}
	
	public JMenuBar Menu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_A);
		file.getAccessibleContext().setAccessibleDescription(
		        "New Game & Exit");
		menuBar.add(file);
		
		JMenuItem newGame = new JMenuItem("New Game",
				KeyEvent.VK_T);
		newGame.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_1, ActionEvent.ALT_MASK));
		newGame.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything");
		newGame.addActionListener(new ActionListener() {
			@Override
			 public void actionPerformed(ActionEvent e){
				CloseFrame();
				SudokuMain sudoku = new SudokuMain();
				sudoku.setJMenuBar(sudoku.Menu());
				sudoku.start();
	            }
		});
		file.add(newGame);
		
		JMenuItem exit = new JMenuItem("Exit",
				KeyEvent.VK_T);
		exit.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_1, ActionEvent.ALT_MASK));
		exit.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything");
		exit.addActionListener(new ActionListener() {
			@Override
			 public void actionPerformed(ActionEvent e){
				CloseFrame();
	            }
		});
		file.add(exit);
		
		return menuBar;
	}
	
	public JPanel UpTimer() {
		t.setInitialDelay(0);
		JPanel upTimer = new JPanel();
		
	    tf.setEditable(false);
	    upTimer.add(tf);
	   // final JToggleButton b = new JToggleButton(stop);
	    //b.addItemListener(new ItemListener() {

	        //@Override
	       // public void itemStateChanged(ItemEvent e) {
	       //     if (b.isSelected()) {
	      //          t.stop();
	      //          b.setText(start);
	       //     } else {
	      //          t.start();
	       //        b.setText(stop);
	      //      }
	     //   }
	   // });
	   // upTimer.add(b);
		return upTimer;
	}
	
	public void start() {
	    t.start();
	}

	/** The entry main() entry method */
	public static void main(String[] args) {
		// [TODO 1] Check Swing program template on how to run the constructor
		SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	        	SudokuMain sudoku = new SudokuMain();
	     		sudoku.setJMenuBar(sudoku.Menu());
	     		sudoku.start();
	            
	         }
		});
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
	
	private class ClockListener implements ActionListener {

	    private int hours;
	    private int minutes;
	    private int seconds;
	    private String hour;
	    private String minute;
	    private String second;

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        NumberFormat formatter = new DecimalFormat("00");
	        if (seconds == N) {
	            seconds = 00;
	            minutes++;
	        }

	        if (minutes == N) {
	            minutes = 00;
	            hours++;
	        }
	        hour = formatter.format(hours);
	        minute = formatter.format(minutes);
	        second = formatter.format(seconds);
	        tf.setText(String.valueOf(hour + ":" + minute + ":" + second));
	        seconds++;
	    }
	}

	
	public void CloseFrame() {
		super.dispose();
	}
}
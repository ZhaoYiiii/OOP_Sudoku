package sudoku;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;


import javax.swing.*;
/**
 * The main Sudoku program
 */
public class SudokuMain extends JFrame{
	// private variables
	GameBoard board = new GameBoard();
	JButton btnNewGame = new JButton("New Game");
	JButton btnExit = new JButton("EXIT");
	JButton btnReset = new JButton("Reset");
	int hintsLeft = 3;
	JButton btnHint = new JButton("Hint (" + hintsLeft + " / 3 left)");
	private static final int N = 60;
	private final ClockListener cl = new ClockListener();
	private final Timer t = new Timer(1000, cl);
	private final JTextField tf = new JTextField(8);
	private static String time;
	private int totalSeconds;
	GridBagConstraints gbc = new GridBagConstraints();
	private boolean playCompleted;
	
	// Constructor
	public SudokuMain(){
		
		board.setSudokuMain(this);
		
		Container cp = this.getContentPane();
		cp.setLayout(new GridBagLayout());
		
		//cp.setBackground(new Color(80, 225, 197));
		
		SoundEffect.init();
		SoundEffect.BG_MUSIC.playContinue();
				
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 9;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.insets = new Insets(20, 20, 20, 20);
		cp.add(board, gbc);

		Font font = new Font("Dialog", Font.BOLD, 20);
		
		// hint button
		HintBtnListener hintListener = new HintBtnListener();
		btnHint.addActionListener(hintListener);
		//btnHint.setBackground(new Color(246, 224, 163));
		btnHint.setBackground(Color.GREEN);
		btnHint.setFont(font);
		btnHint.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
				
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 9;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.ipadx = 100;
		gbc.ipady = 3;
		gbc.insets = new Insets(0, 50, 0, 50);
		cp.add(btnHint, gbc);
		
		// Add a button to the south to re-start the game 
		NewGameBtnListener newGameListener = new NewGameBtnListener();
		btnNewGame.addActionListener(newGameListener);
		//btnNewGame.setBackground(new Color(152, 224, 163));
		btnNewGame.setBackground(Color.GREEN);
		btnNewGame.setFont(font);
		btnNewGame.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
				
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.ipadx = 100;
		gbc.ipady = 3;
		gbc.insets = new Insets(10, 50, 0, 30);
		cp.add(btnNewGame, gbc);
		
		ResetBtnListener resetListener = new ResetBtnListener();
		btnReset.addActionListener(resetListener);
		//btnReset.setBackground(new Color(203, 95, 77));
		btnReset.setBackground(Color.CYAN);
		btnReset.setFont(font);
		btnReset.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
		
		gbc.gridx = 4;
		gbc.gridy = 3;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.ipadx = 100;
		gbc.ipady = 3;
		gbc.insets = new Insets(10, 0, 0, 50);
		cp.add(btnReset, gbc);
		
		ExitBtnListener exitListener = new ExitBtnListener();
		btnExit.addActionListener(exitListener);
		//btnExit.setBackground(new Color(244, 181, 222));
		btnExit.setBackground(Color.MAGENTA);
		btnExit.setFont(font);
		btnExit.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 9;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.ipady = 3;
		gbc.insets = new Insets(10, 50, 10, 50);
		cp.add(btnExit, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 9;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.ipady = 3;
		gbc.insets = new Insets(20, 50, 0, 50);
		tf.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
		cp.add(tf,gbc);
		
		board.init();
		
	    
		pack();     // Pack the UI components, instead of setSize()
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Handle window closing
		setLocationRelativeTo(null);
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
				System.exit(0);
	            }
		});
		
		file.add(exit);
		
		JMenu jScore = new JMenu("High Score");
		file.setMnemonic(KeyEvent.VK_A);
		file.getAccessibleContext().setAccessibleDescription(
		        "Scoreboard");
		menuBar.add(jScore);
		
		JMenuItem scoreboard = new JMenuItem("Scoreboard",
				KeyEvent.VK_T);
		scoreboard.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_1, ActionEvent.ALT_MASK));
		scoreboard.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything");
		scoreboard.addActionListener(new ActionListener() {
			@Override
			 public void actionPerformed(ActionEvent e){
				Scoreboard sb = new Scoreboard();
				sb.setSudokuMain(SudokuMain.this);
	            }
		});
		jScore.add(scoreboard);
		
		JMenu audio = new JMenu("Audio");
		file.setMnemonic(KeyEvent.VK_A);
		file.getAccessibleContext().setAccessibleDescription(
		        "Audio");
		menuBar.add(audio);
		
		JMenuItem onAudio = new JMenuItem("Audio ON",
				KeyEvent.VK_T);
		onAudio.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_1, ActionEvent.ALT_MASK));
		onAudio.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything");
		onAudio.addActionListener(new ActionListener() {
			@Override
			 public void actionPerformed(ActionEvent e){
				SoundEffect.volume = SoundEffect.Volume.LOW;
				SoundEffect.BG_MUSIC.playResume();
	            }
		});
		audio.add(onAudio);
		
		JMenuItem offAudio = new JMenuItem("Audio OFF",
				KeyEvent.VK_T);
		offAudio.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_1, ActionEvent.ALT_MASK));
		offAudio.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything");
		offAudio.addActionListener(new ActionListener() {
			@Override
			 public void actionPerformed(ActionEvent e){
				SoundEffect.volume = SoundEffect.Volume.MUTE;
				SoundEffect.BG_MUSIC.playMute();
	            }
		});
		audio.add(offAudio);
		
		return menuBar;
	}
	
	public JPanel UpTimer() {
		t.setInitialDelay(0);
		JPanel upTimer = new JPanel();
		
	    tf.setEditable(false);
	    upTimer.add(tf);
		return upTimer;
	}
	
	public String getTF() { 
		t.stop();
		return time;
	}
	
	public int getTotalSeconds() {
		
		return totalSeconds-1;
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
	
	private class HintBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) { //New Game
			if(hintsLeft!=0) {
				// change button text
				hintsLeft--;
				btnHint.setText("Hint (" + hintsLeft + " / 3 left)");
				// use 1 hint: enter a correct input for player
				board.useHint();
			}
		}
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

	private class ResetBtnListener implements ActionListener{ //need to implement
		@Override
		public void actionPerformed(ActionEvent e) { //Reset
			board.clearInputs();
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
			if(board.isSolved()){
				t.stop();
			}
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
			time = String.valueOf(hour + ":" + minute + ":" + second);
			Font font1 = new Font("SansSerif", Font.BOLD, 30);
			tf.setFont(font1);
			tf.setHorizontalAlignment(JTextField.CENTER);
			tf.setText(time);
			tf.setEditable(false);
			tf.setCaretColor(Color.WHITE);
			seconds++;
			totalSeconds++;
		}

	}

	public void CloseFrame() {
		super.dispose();
	}
	
}
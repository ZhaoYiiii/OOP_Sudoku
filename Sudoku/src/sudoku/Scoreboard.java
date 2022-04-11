package sudoku;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.awt.List;

import javax.swing.*;

/**
 * The main Sudoku program
 */
public class Scoreboard extends JFrame {
	// private variables
	GameBoard board = new GameBoard();
	JButton btnNewGame = new JButton("New Game");
	JButton btnClose = new JButton("Close");
	GridBagConstraints gbc = new GridBagConstraints();
	
	// Constructor
	public Scoreboard() {
		
		Container cp = this.getContentPane();
		cp.setLayout(new GridBagLayout());
		
		Font font = new Font("Dialog", Font.BOLD, 15);
		
		
		// scoreboard table
		String column[]={"#","NAME","TIMING"};         
				
		JTable scoreboardTable_easy =new JTable(getScoreData("easy"), column); 
		JTable scoreboardTable_medium =new JTable(getScoreData("medium"), column); 
		JTable scoreboardTable_hard =new JTable(getScoreData("hard"), column); 
				
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 9;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 0.5;
		gbc.insets = new Insets(20, 20, 0, 20);
		//cp.add(scoreboardTable, gbc);
				
		// tab pane
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel tab1 = new JPanel(new BorderLayout());
		tab1.add(scoreboardTable_easy);
		tabbedPane.addTab("Easy", tab1);
		
		JPanel tab2 = new JPanel(new BorderLayout());
		tab2.add(scoreboardTable_medium);
		tabbedPane.addTab("Medium", tab2);
		
		JPanel tab3 = new JPanel(new BorderLayout());
		tab3.add(scoreboardTable_hard);
		tabbedPane.addTab("Hard", tab3);
		
		cp.add(tabbedPane, gbc);
		
		//ImageIcon icon = createImageIcon("images/middle.gif");

//		JComponent panel1 = makeTextPanel("Panel #1");
//		tabbedPane.addTab("Tab 1", null, panel1,
//		                  "Does nothing");
//		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
//
//		JComponent panel2 = makeTextPanel("Panel #2");
//		tabbedPane.addTab("Tab 2", null, panel2,
//		                  "Does twice as much nothing");
//		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
//
//		JComponent panel3 = makeTextPanel("Panel #3");
//		tabbedPane.addTab("Tab 3", null, panel3,
//		                  "Still does nothing");
//		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		
		
		
		// new game button
		NewGameBtnListener newGameListener = new NewGameBtnListener();
		btnNewGame.addActionListener(newGameListener);
		btnNewGame.setBackground(Color.GREEN);
		btnNewGame.setFont(font);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.ipadx = 100;
		gbc.ipady = 10;
		gbc.insets = new Insets(6, 50, 0, 30);
		cp.add(btnNewGame, gbc);
		
		// close button
		CLoseBtnListener closeListener = new CLoseBtnListener();
		btnClose.addActionListener(closeListener);
		btnClose.setBackground(Color.MAGENTA);
		btnClose.setFont(font);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 9;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.ipady = 10;
		gbc.insets = new Insets(6, 50, 10, 30);
		cp.add(btnClose, gbc);
				
		pack();     // Pack the UI components, instead of setSize()
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Handle window closing
		setTitle("Sudoku - Scoreboard");
		setVisible(true);
	}
	
	
	public String[][] getScoreData(String difficultyString){
		
		ArrayList<String[]> scores = new ArrayList<String[]>();
		File fileObj = new File("scoreboard.txt");
		Scanner scanner;
		try {
			scanner = new Scanner(fileObj);
			int lineIndex = 0;
			while(scanner.hasNextLine()){   //Can also use a BufferedReader
			    String data = scanner.nextLine();
			    if(data.contains("|")) {    //Ensure line contains "|"
			    	
			    	int indexOfFirst = data.indexOf("|");
			    	String difficulty = data.substring(0, indexOfFirst);
			    	System.out.println(difficulty);
			    	//System.out.println(name + " " + timing + " " + seconds + " " + difficulty + " ");
			    	
			    	if(difficulty.equals(difficultyString)) {
			    		String[] line = new String[3];
			    		
			    		int indexOfSecond = data.indexOf("|", indexOfFirst+1);
				    	int indexOfThird = data.indexOf("|", indexOfSecond+1);
				    	int indexOfFourth = data.indexOf("|", indexOfThird+1);
			    		String name = data.substring(indexOfFirst+1, indexOfSecond);
				        String timing = data.substring(indexOfSecond+1, indexOfThird);
				    	String seconds = data.substring(indexOfThird+1, indexOfFourth);
			    		
				        line[0] = name;
				        line[1] = timing;
				        line[2] = seconds;
				        scores.add(line);
			    	}
			    }
			    lineIndex++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[][] sortedScores = sortDataBySeconds(scores);
		
		return sortedScores;
	}
	
	public String[][] sortDataBySeconds(ArrayList<String[]> scores){
		
		// convert arraylist into array for sorting
		String[][] scoresArray = new String[scores.size()][3];
		
		for(int i=0;i<scores.size();i++) {
			String[] line = new String[3];
			line[0] = scores.get(i)[0];	// name
			line[1] = scores.get(i)[1];	// timing
			line[2] = scores.get(i)[2];	// seconds
			scoresArray[i] = line;
		}
		
		// sort by seconds
		Arrays.sort(scoresArray, Comparator.comparingInt(a -> Integer.parseInt(a[2])));

		System.out.println(Arrays.deepToString(scoresArray));
		
		// change array format for display
		String[][] sortedScores = new String[scoresArray.length][3];
		
		for(int i=0;i<scoresArray.length;i++) {
			String[] line = new String[3];
			line[0] = String.valueOf(i+1);	// #
			line[1] = scoresArray[i][0];	// name
			line[2] = scoresArray[i][1];	// timing
			sortedScores[i] = line;
		}
		
		return sortedScores;
		
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

	private class CLoseBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) { //Close scoreboard window
			CloseFrame(); 
		}
	}

	public void CloseFrame() {
		super.dispose();
	}
}
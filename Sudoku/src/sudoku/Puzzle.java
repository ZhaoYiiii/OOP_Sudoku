package sudoku;

/**
 * The Sudoku number puzzle to be solved
 */
public class Puzzle {
	// All variables have package access
	// The numbers on the puzzle

	int[][] numbers = new int[GameBoard.GRID_SIZE][GameBoard.GRID_SIZE];
	// The masks - to show or not
	boolean[][] isShown = new boolean[GameBoard.GRID_SIZE][GameBoard.GRID_SIZE];
	int[][] sections = new int[GameBoard.GRID_SIZE][GameBoard.GRID_SIZE];


	// generate random number
	int randomGenerator(int num) 
	{
		return (int)Math.floor((Math.random()*num+1));
	}

	// fill 1 section (for diagonal)
	int[] fillSection(int sessionIdx) {	
		int[] sectionfill = new int[9];
		boolean isDupe = true;

//		for(int idx = 0; idx < 9; idx++) { // should shift into do clause?
			for(int idxIn = 0; idxIn < 9; idxIn++) {
				do {
					int random = randomGenerator(9);
					sectionfill[idxIn] = random; // should be 'idxIn' instead?
					// fill random number in numbers[]
					fillNumber(sessionIdx, idxIn, random);
					
					// check if duplicate (loop through sectionfill)
					for(int i = 0; i < 9; i++) {
						if(sectionfill[i] != 0) { // if element is filled
							isDupe = ifDuplicate(sectionfill[i], sectionfill[idxIn]);
						}
						
					}
				}
//				while(ifDuplicate(sectionfill[idx], sectionfill[idxIn]) == true);
				while(isDupe == true);
			}

//		}
		return sectionfill;
	}
	
	// fill number into specific numbers[r][c]
	void fillNumber(int sectionIdx, int numberIdx, int number) {
		
		int sectionCount = sectionIdx+1;
		int numberCount = numberIdx+1;
		int r = -1; // row index
		int r1 = 0; // add on 1
		int r2 = 0; // add on 2
		int c = -1; // column index
		int c1 = 0; // add on 1
		int c2 = 0; // add on 2
		
		// Calculate row
		if(sectionCount>6) {
			r1 = 6;
		} else if(sectionCount>3) {
			r1 = 3;
		} else {
			r1 = 0;
		}
		// add on rows within section
		if(numberCount>6) {
			r2 = 3;
		} else if(sectionCount>3) {
			r2 = 2;
		} else {
			r2 = 1;
		}
		
		r = r + r1 + r2;
		
		// Calculate column
		if(sectionCount%3 == 0) {
			c1 = 2*3;
		} else {
			c1 = (sectionCount%3)*3;
		}
		// add on columns within section
		if(numberCount%3 == 0) {
			c2 = 3;
		} else {
			c2 = (numberCount%3);
		}
		
		c = c+ c1 + c2;
		
		// fill in number
		numbers[r][c] = number;
		
	}
	
	void fillRandomNumberAndCheck(int sectionIdx, int numberIdx) {
		
		
		
	}

	// check for dupes
	public boolean ifDuplicate(int anchor, int running) 
	{
		boolean duplicate;
		if(running == anchor) {
			duplicate = true; 
		}
		else {
			duplicate = false;
		}

		return duplicate;
	}


	// Constructor
	public Puzzle() {
		super();
	}

	// Generate a new puzzle given the number of cells to be guessed
	// Need to set the arrays numbers and isShown
	public void newPuzzle(int numToGuess) {
		// Hardcoded here for simplicity.
		int[][] hardcodedNumbers =
		 {{5, 3, 4, 6, 7, 8, 9, 1, 2},
		  {6, 7, 2, 1, 9, 5, 3, 4, 8},
		  {1, 9, 8, 3, 4, 2, 5, 6, 7},
		  {8, 5, 9, 7, 6, 1, 4, 2, 3},
		  {4, 2, 6, 8, 5, 3, 7, 9, 1},
		  {7, 1, 3, 9, 2, 4, 8, 5, 6},
		  {9, 6, 1, 5, 3, 7, 2, 8, 4},
		  {2, 8, 7, 4, 1, 9, 6, 3, 5},
		  {3, 4, 5, 2, 8, 6, 1, 7, 9}};
		
		// fill diagonal sections
		//for(int i = 0; i < 9; i+=4) {
		//	int array1 [] = fillSection(i);
		//	for(int j = 0; j < 9; j++) {
		//		sections[i][j] = array1[j];
		//	}  
		//}
		//for(int i = 0; i < 9; i+=4) {
		//	for(int j = 0; j < 9; j++) {
		//		System.out.println(sections[i][j]);
				
		//	}  
		//	System.out.println();
		//}
		
		//System.out.println(sections);
		
		// fill remaining of sections (1,2,3,5,6,7)
//		for(int i = 0; i < 9; i++) {
//			
//		}
		
		// Copy from hardcoded number
		for (int row = 0; row < GameBoard.GRID_SIZE; ++row) {
		   for (int col = 0; col < GameBoard.GRID_SIZE; ++col) {
		      numbers[row][col] = hardcodedNumbers[row][col];
		   }
		}

		// Need to use numToGuess!
		// For testing, only 2 cells of "8" is NOT shown
		boolean[][] hardcodedIsShown =
			{{true, true, true, true, true, false, true, true, true},
					{true, true, true, true, true, true, true, true, false},
					{true, true, true, true, true, true, true, true, true},
					{true, true, true, true, true, true, true, true, true},
					{true, true, true, true, true, true, true, true, true},
					{true, true, true, true, true, true, true, true, true},
					{true, true, true, true, true, true, true, true, true},
					{true, true, true, true, true, true, true, true, true},
					{true, true, true, true, true, true, true, true, true}};

		// Copy from hardcoded masks
		for (int row = 0; row < GameBoard.GRID_SIZE; ++row) {
			for (int col = 0; col < GameBoard.GRID_SIZE; ++col) {
				isShown[row][col] = hardcodedIsShown[row][col];
			}
		}
	}

	//(For advanced students) use singleton design pattern for this class
}
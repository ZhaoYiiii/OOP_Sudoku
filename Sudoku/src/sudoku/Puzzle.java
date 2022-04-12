package sudoku;

import java.util.Random;

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
	int width = GameBoard.GRID_SIZE;
	int smallWidth = (int) Math.sqrt(width);


	// generate random number
	int randomGenerator(int num) 
	{
		return (int)Math.floor((Math.random()*num+1));
	}

	// fill 1 section (for diagonal)
	int[] fillSection(int sectionIdx) {	
		int[] sectionfill = new int[9];
		boolean isDupe = true;
		int random = 0;
			for(int idxIn = 0; idxIn < 9; idxIn++) {
				while(isDupe) {
					random = randomGenerator(9);
					// check if duplicate (loop through sectionfill)
					for(int i = 0; i < idxIn; i++) {
							isDupe = ifDuplicate(sectionfill[i], random);
							//System.out.println(isDupe);
							if(isDupe) {
								break;
							}
					}
					if(idxIn == 0) {
						isDupe = false;
					}
				}
				// fill random number in sectionfill[]
				sectionfill[idxIn] = random;
				// fill random number in numbers[][]
				fillNumber(sectionIdx, idxIn, random);
				isDupe = true;
			}
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
		} else if(numberCount>3) {
			r2 = 2;
		} else {
			r2 = 1;
		}
		
		r = r + r1 + r2;
		
		// Calculate column
		if(sectionCount%3 == 0) {
			c1 = 2*3;
			//System.out.print(c1);
		} else {
			c1 = (sectionCount%3-1)*3;
			//System.out.print(c1);
		}
		// add on columns within section
		if(numberCount%3 == 0) {
			c2 = 3;
			//System.out.print(c2);
		} else {
			c2 = (numberCount%3);
			//System.out.print(c2);
		}
		//System.out.println();
		
		c = c + c1 + c2;
		
		// fill in number
		numbers[r][c] = number;
		
	}
	
	// A recursive function to fill remaining
    boolean fillRemaining(int i, int j)
    {
    	
        //  System.out.println(i+" "+j);
        if (j>=width && i<width-1)
        {
            i = i + 1;
            j = 0;
        }
        if (i>=width && j>=width)
            return true;
 
        if (i < smallWidth)
        {
            if (j < smallWidth)
                j = smallWidth;
        }
        else if (i < width-smallWidth)
        {
            if (j==(int)(i/smallWidth)*smallWidth)
                j =  j + smallWidth;
        }
        else
        {
            if (j == width-smallWidth)
            {
                i = i + 1;
                j = 0;
                if (i>=width)
                    return true;
            }
        }
 
        for (int num = 1; num<=width; num++)
        {
            if (!isDuplicate_numbers(i, j, num))
            {
                numbers[i][j] = num;
                if (fillRemaining(i, j+1))
                    return true;
 
                numbers[i][j] = 0;
            }
        }
        return false;
    }
    
 // check for duplicates in section, row, column of numbers matrix
    boolean isDuplicate_numbers(int i, int j, int num)
    {
    	
    	// check section
    	int rowStart = i-i%smallWidth;
    	int colStart = j-j%smallWidth;
    	
        for (int r = 0; r<smallWidth; r++) {
        	for (int c = 0; c<smallWidth; c++) {
        		if (numbers[rowStart+r][colStart+c]==num) {
        			return true;
        		}
        	}
        }
        // check row
        for (int c = 0; c<width; c++) {
        	if (numbers[i][c] == num) {
        		return true;
        	}
        }
        // check column
        for (int r = 0; r<width; r++)
            if (numbers[r][j] == num)
                return true;
 
        return false;
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
	public void newPuzzle(int difficulty) {		
		// fill diagonal sections
		for(int i = 0; i < 9; i+=4) {
			int array1 [] = fillSection(i);
			for(int j = 0; j < 9; j++) {
				sections[i][j] = array1[j];
			}  
		}
		
		// print numbers test
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(numbers[i][j]);
				
			}  
			System.out.println();
		}
		

		
		fillRemaining(0, smallWidth);
		
				// print numbers test
				for(int i = 0; i < 9; i++) {
					for(int j = 0; j < 9; j++) {
						System.out.print(numbers[i][j]);
						
					}  
					System.out.println();
				}
		
		
				Random rand = new Random();
				boolean[][] shown = new boolean[9][9];
				for(int idx = 0; idx < 9; idx++){ //initialize 
					for(int idxj = 0; idxj < 9; idxj++){
						shown[idx][idxj] = true;
					}
				}
				
				int blanks = 10;
				
				if (difficulty == 0) {
					blanks = 10;
				}else if(difficulty == 1) {
					blanks = 25;
				}else if(difficulty == 2) {
					blanks = 35;
				}
				
				for(int idx = 0; idx < blanks; idx++){ //the 10 is dependant on difficulty
					int a = -1;
					int b = -1;
					do{
					a = rand.nextInt(0,9);
					b = rand.nextInt(0,9);
					}
					while(shown[a][b] == false);
					shown[a][b]=false;
				}		
		// Need to use numToGuess!
		// For testing, only 2 cells of "8" is NOT shown
		

		for (int row = 0; row < GameBoard.GRID_SIZE; ++row) {
			for (int col = 0; col < GameBoard.GRID_SIZE; ++col) {
				isShown[row][col] = shown[row][col];
			}
		}
	}

	//(For advanced students) use singleton design pattern for this class
}
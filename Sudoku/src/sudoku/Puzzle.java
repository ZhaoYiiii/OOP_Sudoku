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
							System.out.println(isDupe);
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
			System.out.print(c1);
		} else {
			c1 = (sectionCount%3-1)*3;
			System.out.print(c1);
		}
		// add on columns within section
		if(numberCount%3 == 0) {
			c2 = 3;
			System.out.print(c2);
		} else {
			c2 = (numberCount%3);
			System.out.print(c2);
		}
		System.out.println();
		
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
	
	// fill 1 section (for remaining)
//		int[] fillRemainingSection(int sectionIdx) {	
//			int[] sectionfill = new int[9];
//			boolean isDupeInNumbers = true;
//			boolean isDupeInsection = true;
//			int number = 0;
//			
//				for(int idxIn = 0; idxIn < 9; idxIn++) { // loop through each number index
//					number = 1;
//					while(number<10) {
//						
//						isDupeInsection = true;
//						
////						while(isDupeInsection) {
//							//random = randomGenerator(9);
//							// check if duplicate (loop through sectionfill)
//							for(int i = 0; i < idxIn; i++) {
//								isDupeInsection = ifDuplicate(sectionfill[i], number);
//								System.out.println(idxIn);
//								System.out.println(number);
//								if(isDupeInsection) {
//									break;
//								}
//							}
//							if(idxIn == 0) {
//								isDupeInsection = false;
//							}
////						}
//							if(!isDupeInsection) {
//								// check if duplicate and fill random number in numbers[][] if false
//								isDupeInNumbers = isDuplicate_fillInNumber(sectionIdx, idxIn, number);
//								System.out.println(isDupeInNumbers);
//								if(!isDupeInNumbers) {
//									break;
//								}
//							}
//						
//						number++;
//					}
//					//System.out.println(isDupeInsection);
//					// fill random number in sectionfill[]
//					sectionfill[idxIn] = number;
//					isDupeInNumbers = true;
//				}
//			return sectionfill;
//		}
	
//	public boolean isDuplicate_fillInNumber (int sectionIdx, int numberIdx, int number) {
//		
//		int sectionCount = sectionIdx+1;
//		int numberCount = numberIdx+1;
//		int r = -1; // row index
//		int r1 = 0; // add on 1
//		int r2 = 0; // add on 2
//		int c = -1; // column index
//		int c1 = 0; // add on 1
//		int c2 = 0; // add on 2
//		
//		// Calculate row
//		if(sectionCount>6) {
//			r1 = 6;
//		} else if(sectionCount>3) {
//			r1 = 3;
//		} else {
//			r1 = 0;
//		}
//		// add on rows within section
//		if(numberCount>6) {
//			r2 = 3;
//		} else if(numberCount>3) {
//			r2 = 2;
//		} else {
//			r2 = 1;
//		}
//		
//		r = r + r1 + r2;
//		
//		// Calculate column
//		if(sectionCount%3 == 0) {
//			c1 = 2*3;
//			//System.out.print(c1);
//		} else {
//			c1 = (sectionCount%3-1)*3;
//			//System.out.print(c1);
//		}
//		// add on columns within section
//		if(numberCount%3 == 0) {
//			c2 = 3;
//			//System.out.print(c2);
//		} else {
//			c2 = (numberCount%3);
//			//System.out.print(c2);
//		}
//		System.out.println();
//		
//		c = c + c1 + c2;
//		
//		boolean duplicate = false;
//		
//		for(int i=0 ; i<9 ; i++) {
//			if(numbers[r][i] == number) {
//				duplicate = true;
//				break;
//			}
//		}
//		
//		if(!duplicate) {
//			for(int i=0 ; i<9 ; i++) {
//				if(numbers[i][c] == number) {
//					duplicate = true;
//					break;
//				}
//			}
//		}
//		
//		if(!duplicate) {
//			// fill in number
//			numbers[r][c] = number;
//		}
//		
//		return duplicate;
//		
//	}

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
//		int[][] hardcodedNumbers =
//		 {{5, 3, 4, 6, 7, 8, 9, 1, 2},
//		  {6, 7, 2, 1, 9, 5, 3, 4, 8},
//		  {1, 9, 8, 3, 4, 2, 5, 6, 7},
//		  {8, 5, 9, 7, 6, 1, 4, 2, 3},
//		  {4, 2, 6, 8, 5, 3, 7, 9, 1},
//		  {7, 1, 3, 9, 2, 4, 8, 5, 6},
//		  {9, 6, 1, 5, 3, 7, 2, 8, 4},
//		  {2, 8, 7, 4, 1, 9, 6, 3, 5},
//		  {3, 4, 5, 2, 8, 6, 1, 7, 9}};
		
		
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
		
//		// fill remaining sections (1,2,3,5,6,7)
//		int[] remainder = {1,2,3,5,6,7};
//		for(int i = 0; i < remainder.length; i++) {
//			int array1 [] = fillRemainingSection(remainder[i]);
//			for(int j = 0; j < 9; j++) {
//				sections[remainder[i]][j] = array1[j];
//			}  
//		}
		
		fillRemaining(0, smallWidth);
		
				// print numbers test
				for(int i = 0; i < 9; i++) {
					for(int j = 0; j < 9; j++) {
						System.out.print(numbers[i][j]);
						
					}  
					System.out.println();
				}
		
		// Copy from hardcoded number
//		for (int row = 0; row < GameBoard.GRID_SIZE; ++row) {
//		   for (int col = 0; col < GameBoard.GRID_SIZE; ++col) {
//		      numbers[row][col] = hardcodedNumbers[row][col];
//		   }
//		}
				Random rand = new Random();
				boolean[][] shown = new boolean[9][9];
				for(int idx = 0; idx < 9; idx++){ //initialize 
					for(int idxj = 0; idxj < 9; idxj++){
						shown[idx][idxj] = true;
					}
				}
				for(int idx = 0; idx < 10; idx++){ //the 10 is dependant on difficulty
					//do{
					int a = rand.nextInt(0,8);
					int b = rand.nextInt(0,8);
					shown[a][b]=false;
					//}
					//while(shown[a][b] != false);
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
				isShown[row][col] = shown[row][col];
			}
		}
	}

	//(For advanced students) use singleton design pattern for this class
}
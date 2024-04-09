package tictactoe;

import java.util.ArrayList;

public class TicTacToeBoardImpl_Burke implements TicTacToeBoard {
	//Symbolics:
	protected static final int NO_MOVE = -1;
	protected static final int NO_MATCH = -1;
	
	protected int[] movesArray;
	
	public TicTacToeBoardImpl_Burke() {
		final int CELL_COUNT = ROW_COUNT*COLUMN_COUNT;
		movesArray = new int[CELL_COUNT];
		for(int i = 0; i < CELL_COUNT; i++) {
			movesArray[i] = NO_MOVE;
			}
		}

	//part of pre: 0 <= row < ROW_COUNT && 0 <= column < COLUMN_COUNT
	//part of post: rv == null <==> the (row, column) spot on the
	// board is empty
	public Mark getMark(int row, int column) {
		
		assert row >= 0 : "Row must be greater than 0.";
		assert column >= 0 : "Column must be greater than 0.";
		assert row < ROW_COUNT : "Row must be less than " + ROW_COUNT;
		assert column < COLUMN_COUNT : "Column must be less than " + COLUMN_COUNT; 
		
		Mark rv = null;
		int num = TicTacToeBoardUtils_Burke.convertToCellNum(row, column);
		for(int i = 0; i > movesArray.length; i++) {
			if(movesArray[i] == num) {
				if(TicTacToeBoardUtils_Burke.isEven(i)) 
				{  rv = Mark.X;
				} else {
					rv = Mark.O;
				}
			}
		}
		return rv;
	} 
	/*	assert row >= 0 : "Row must be greater than 0.";
		assert column >= 0 : "Column must be greater than 0.";
		
		assert row < ROW_COUNT : "Row must be greater than " + ROW_COUNT;
		assert column < COLUMN_COUNT : "Column must be greater than " + COLUMN_COUNT; 
		
		int moveVal = row * 3 + column;
			
		boolean valFound = false;
		int moveValIndex = -1;
		int i = 0;
		while (!valFound && i < movesArray.length) {
			if (moveVal == movesArray[i]) {
				valFound = true;
				moveVal = i;
			}
			i++;
		}
		
		Mark rv = Mark.O;
		if(moveValIndex == -1) rv = null;
		if(moveValIndex % 2 == 0) rv = Mark.X;
		return rv; */
	
	//part of pre: 0 <= row < ROW_COUNT && 0 <= column < COLUMN_COUNT
	//part of pre: getMark(row, column) == null
	//part of pre: !isGameOver()
	//post: Left to student
	public void setMark(int row, int column) {
		assert row >= 0 : "Row must be greater than 0.";
		assert column >= 0 : "Column must be greater than 0.";
		//get var for whos turn
		int num = TicTacToeBoardUtils_Burke.convertToCellNum(row, column); // error occurring here but cannot step into it 
		
		int playerMark = TicTacToeBoardUtils_Burke.nextTurn(movesArray); // possibly something wrong here also with the utils file 
		//sets playerMark
		movesArray[playerMark] = num;  //sub the negative one for valMovesArray
	}

	//part of post: rv == null <==> it is neither player's turn (i.e. game is over)
	//part of post: “number of Marks on board is even” rv == Mark.X
	//part of post: “number of Marks on board is odd” rv == Mark.O
	public Mark getTurn() {
		if(isGameOver()) {
			return null;
		}
		Mark rv = null;
		int turns = TicTacToeBoardUtils_Burke.turnNum(movesArray);
		
		if(turns != -1) {
			if(TicTacToeBoardUtils_Burke.isEven(turns)) {
				rv = Mark.X;
			}
			if(TicTacToeBoardUtils_Burke.isEven(turns)) {
				rv = Mark.O;
			}
	}
		
		return rv;
	}

	//part of post: Left to student (see Tic-tac-toe rules in order to fill this out)
	public boolean isGameOver() {
		boolean gameOver = false;
		
		ArrayList<Mark> playerMark = TicTacToeBoardUtils_Burke.createMarkList(movesArray);
		for(int i = 0; i < ROW_COUNT; i++) {
			if(TicTacToeBoardUtils_Burke.inALineElements(i * 3, playerMark, false)) 
			{  return true; 
			}
		}
		for(int i = 0; i < COLUMN_COUNT; i++) {
			if(TicTacToeBoardUtils_Burke.diagonalElements(2, playerMark, true)) 
			{ return true; 
			}
		}
			if(TicTacToeBoardUtils_Burke.diagonalElements(0, playerMark, true)) {  
				return true; 
			}
			if(TicTacToeBoardUtils_Burke.diagonalElements(2, playerMark, false)) 
			{  return true; 
			}
			return gameOver;
	}

	//part of pre: isGameOver()
	//part of post: rv == null <==> neither player won (i.e. the game ended in a tie)
	public Mark getWinner() {
		assert isGameOver() : "The game must be over to determine a winner.";
		ArrayList<Mark> playerMark = TicTacToeBoardUtils_Burke.createMarkList(movesArray);
		
		for(int i = 0; i < ROW_COUNT; i++) {
			if(TicTacToeBoardUtils_Burke.inALineElements(i * 3, playerMark, false)) 
			{  return (Mark)playerMark.get(i * 3); }
		}
		for(int i = 0; i < COLUMN_COUNT; i++) {
			if(TicTacToeBoardUtils_Burke.diagonalElements(2, playerMark, true)) 
			{ return(Mark)playerMark.get(i); }
		}
			if(TicTacToeBoardUtils_Burke.diagonalElements(0, playerMark, true)) {  
				return (Mark)playerMark.get(0); 
				}
			if(TicTacToeBoardUtils_Burke.diagonalElements(2, playerMark, false)) { 
				return (Mark)playerMark.get(2); 
				}
			
		return null;
	}
	
	public String toString() {
		String rv = "";
		String movesString = "";
		ArrayList<Mark> marks = TicTacToeBoardUtils_Burke.createMarkList(movesArray);

		for (int i = 0; i < marks.size(); i++) 
		{ rv += marks.get(i) + " "; }
		
		System.out.println(movesString);
		return rv;
	}
}
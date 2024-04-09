package tictactoe;

import java.util.ArrayList;

public class TicTacToeBoardUtils_Burke {
	
	public static int convertToCellNum( int row, int column)
	
	{ return row * 3 + column; }

	public static int nextTurn( int [] movesArray) { 
		int rv = -1; 
		for(int i = 0; i < movesArray.length; i++){ 
			if( movesArray[i] == TicTacToeBoardImpl_Burke.NO_MATCH) {
				rv = i; 
				break;
			}
		}
		return rv; 
	}
 
	public static boolean isEven(int num) {
		if (num % 2 == 0) {
			return true;
		}  else { 
			return false; 
		}
	}


	public static int turnNum(int[] movesArray) {
		return nextTurn(movesArray);
	}

	public static boolean contains(int[] movesArray, int num) {
		boolean rv = false; 

		for ( int i = 0; i < movesArray.length; i++) {
			if( movesArray[i] == num) {
				rv = true;
				break;
			}
		}
		return rv; 
	}

	public static int indexOf(int[] movesArray, int num) {
		int rv = -1; 
		for( int i = 0; i < movesArray.length; i++) {
			if( movesArray[i] == num) {
				rv = i; 
				break;
			}
		}
		return rv; 
	}
	
	public static ArrayList<Mark> createMarkList(int[] movesArray) {
		ArrayList<Mark> playerMark = new ArrayList<Mark>();

		for( int i = 0; i < movesArray.length; i++) {
			playerMark.add(null);
		}

		for (int i = 0; i < movesArray.length; i++) {
			if (TicTacToeBoardUtils_Burke.contains(movesArray, i)) {
				int indexMark = TicTacToeBoardUtils_Burke.indexOf(movesArray, i);
				if (TicTacToeBoardUtils_Burke.isEven(indexMark)) {
					playerMark.add(i, Mark.X);
				} 
				else {
					playerMark.add(i, Mark.O);
				}
			}
		}
		return playerMark;
	}


	public static boolean inALineElements(int cell, ArrayList<Mark> cells, boolean vertical) {
		if(vertical) {
			int diff = 3;
			return eq(cells.get(cell), cells.get(cell + diff), cells.get(cell+(diff * 2)));
		}
		else {
			int diff = 1;
			return eq(cells.get(cell), cells.get(cell + diff), cells.get(cell + (diff * 2)));
		}
	}
	
	public static boolean diagonalElements(int cell, ArrayList<Mark> cells, boolean right) {
		if(right) {
			int diff = 4;
			return eq(cells.get(cell), cells.get(cell+diff), cells.get(cell+(diff * 2)));
		}
		else {
			int diff = 2;
			return eq(cells.get(cell), cells.get(cell+diff), cells.get(cell+(diff*2)));
		}
	}

	public static boolean eq(Mark v1, Mark v2, Mark v3) {
		if(v1 == null || v2 == null || v3 == null) {
			return false;
		}
		return v1 == v2 && v2 == v3;
	}
}
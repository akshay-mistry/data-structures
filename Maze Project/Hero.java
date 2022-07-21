public class Hero  {

	private int row;
	private int col;
	private int dim;
	private String[][] maze;
	private char dir;
	private int moves;

	public Hero(int row, int col, int dim, String[][] maze, char dir, int moves) {

		this.row = row;
		this.col = col;
		this.dim = dim;
		this.maze = maze;
		this.dir = dir;
		this.moves = moves;

	}


	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int getDim() {
		return dim;
	}


	public String[][] getMaze() {
		return maze;
	}

	public char getDir() {
		return dir;
	}

	public int getMoves() {
		return moves;
	}

	public void changeDirection(int key) {

		if (dir == 'N') {
			if (key == 39) {
				dir = 'E';
			}
			else if (key == 37) {
				dir = 'W';
			}
		}
		else if (dir == 'S') {
			if (key == 39) {
				dir = 'W';
			}
			else if (key == 37) {
				dir = 'E';
			}
		}
		else if (dir == 'E') {
			if (key == 39) {
				dir = 'S';
			}
			else if (key == 37) {
				dir = 'N';
			}
		}
		else if (dir == 'W') {
			if (key == 39) {
				dir = 'N';
			}
			else if (key == 37) {
				dir = 'S';
			}
		}

	}


	public void move() {

		int newCol = 0;
		int newRow = 0;

		switch (dir) {
			case 'E': //right
			 	newCol = col + 1;
				if (!maze[row][newCol].equals("-")) {
					col++;
					moves++;
				}
				break;
			case 'W': //left
				newCol = col - 1;
				if (!maze[row][newCol].equals("-")) {
					col--;
					moves++;
				}
				break;
			case 'N': //up
				newRow = row - 1;
				if (!maze[newRow][col].equals("-")) {
					row--;
					moves++;
				}
				break;
			case 'S': //down
				newRow = row + 1;
				if (!maze[newRow][col].equals("-")) {
					row++;
					moves++;
				}
				break;
		}

	}
}
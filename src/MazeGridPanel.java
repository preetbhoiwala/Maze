import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JPanel;

public class MazeGridPanel extends JPanel{
	private int rows;
	private int cols;
	private Cell[][] maze;



	// extra credit
	public void genDFSMaze() {
		System.out.println("in genDFSMaze");
		boolean[][] visited;
		Stack<Cell> stack  = new Stack<Cell>();
		Cell start = maze[0][0];
		stack.push(start);
	}

	//homework
	public void solveMaze() {
		System.out.println("in solveMaze");
		Stack<Cell> stack  = new Stack<Cell>(); 		//initiazing a stack
		Cell start = maze[0][0];						// 2-day array maze starts with the background green
		start.setBackground(Color.GREEN);
		Cell finish = maze[rows-1][cols-1];
		finish.setBackground(Color.RED);
		//my code
		stack.push(start);
		Cell current = start;

		while(current.getBackground() != Color.RED && !stack.empty()){
															//while stack isn't empty

			current = stack.peek();// start the maze													// Returns what is on top of the stack

			if (current.getBackground() == Color.RED){
				break;
											// If direction gets to red exit then program breaks
			}
			if(!current.northWall && !visited(current.row - 1, current.col)){
				// as long as not at north wall or already place already
				stack.push(maze[current.row - 1][current.col]);
				//then Move north

				current.setBackground(Color.CYAN);
				// Sets route to color Cyan
			}
			else if(!current.southWall && !visited(current.row + 1, current.col)){
				// Not at south wall or a visited square
				stack.push(maze[current.row + 1][current.col]);
				// Move south
				current.setBackground(Color.CYAN);
				// Sets path to Cyan
			}

			else if(!current.eastWall && !visited(current.row, current.col + 1)){
				// Not at east wall or visited square

				stack.push(maze[current.row][current.col + 1]);
				// Move east

				current.setBackground(Color.CYAN);
				// Sets path to Cyan
			}

			else if(!current.westWall && !visited(current.row, current.col - 1)){
				// Not at west wall or visited square

				stack.push(maze[current.row][current.col - 1]);
				// Move south

				current.setBackground(Color.CYAN);
				// Sets path to Cyan
			}


			else {
				current.setBackground(Color.GRAY);
				// Sets backup color to GRAY

				stack.pop();
				// back off stack when hitting a dead end and restart
			}
		}


	}


	public boolean visited(int row, int col) {
		Cell c = maze[row][col];
		Color status = c.getBackground();
		if(status.equals(Color.WHITE)  || status.equals(Color.RED)  ) {
			return false;
		}


		return true;


	}


	public void genNWMaze() {
		
		for(int row = 0; row  < rows; row++) {
			for(int col = 0; col < cols; col++) {
//if I'm in the corner
				if(row == 0 && col ==0) {
					continue;
				}
				//if in top, make sure there's no east or west

				else if(row ==0) {
					maze[row][col].westWall = false;
					maze[row][col-1].eastWall = false;
				} else if(col == 0) {
					maze[row][col].northWall = false;
					maze[row-1][col].southWall = false;
				}else {
					boolean north = Math.random()  < 0.5;
					if(north ) {
						maze[row][col].northWall = false;
						maze[row-1][col].southWall = false;
					} else {  // remove west
						maze[row][col].westWall = false;
						maze[row][col-1].eastWall = false;
					}
					maze[row][col].repaint();
				}
			}
		}
		this.repaint();
		
	}

	public MazeGridPanel(int rows, int cols) {
		this.setPreferredSize(new Dimension(800,800));
		this.rows = rows;
		this.cols = cols;
		this.setLayout(new GridLayout(rows,cols));
		this.maze =  new Cell[rows][cols];
		for(int row = 0 ; row  < rows ; row++) {
			for(int col = 0; col < cols; col++) {

				maze[row][col] = new Cell(row,col);

				this.add(maze[row][col]);
			}

		}


		this.genNWMaze();
		this.solveMaze();
		
	}




}

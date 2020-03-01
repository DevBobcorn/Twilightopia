package bobcorn.twilightopia.maze;

import java.awt.Point;
import java.util.Stack;

public class Maze {
	private Point entrance = null;
	private Point exit = null;
	private int rowNumber;
	private int colNumber;
	private Lattice[][] mazeLattice;
	public static final char DepthFirstSearchSolveMaze = 0;
	public static final char BreadthFirstSearchSolveMaze = 1;
	private char solveMaze = DepthFirstSearchSolveMaze;
	public static final char DepthFirstSearchCreateMaze = 0;
	public static final char RandomizedPrimCreateMaze = 1;
	public static final char RecursiveDivisionCreateMaze = 2;
	private char createMaze = DepthFirstSearchCreateMaze;

	public Maze(int row, int col) {
		this.setRowNumber(row);
		this.setColNumber(col);
		mazeLattice = new Lattice[getRowNumber() + 2][getColNumber() + 2];
	}

	public void init() {
		mazeLattice = new Lattice[getRowNumber() + 2][getColNumber() + 2];
		for (int i = 1; i < getRowNumber() + 1; ++i)
			for (int j = 1; j < getColNumber() + 1; ++j) {
				mazeLattice[i][j] = new Lattice();
			}
		for (int i = 0; i < getRowNumber() + 2; ++i) {
			mazeLattice[i][0] = new Lattice();
			mazeLattice[i][getColNumber() + 1] = new Lattice();
		}
		for (int j = 0; j < getColNumber() + 2; ++j) {
			mazeLattice[0][j] = new Lattice();
			mazeLattice[getRowNumber() + 1][j] = new Lattice();
		}
		setEntrance(new Point(0, 1));
		setExit(new Point(getColNumber() + 1, getRowNumber()));
		mazeLattice[getEntrance().y][getEntrance().x].setPassable(true);
		mazeLattice[getExit().y][getExit().x].setPassable(true);
	}

	public boolean isOutofBorder(int x, int y) {
		if ((x == 0 && y == 1) || (x == getColNumber() + 1 && y == getRowNumber()))
			return false;
		else
			return (x > getColNumber() || y > getRowNumber() || x < 1 || y < 1) ? true : false;
	}

	public void createMaze() {
		init();
		AbstractCreateMaze c = null;
		if (getCreateMaze() == DepthFirstSearchCreateMaze)
			c = new DepthFirstSearchCreateMaze();
		else if (getCreateMaze() == RandomizedPrimCreateMaze)
			c = new RandomizedPrimCreateMaze();
		else if (getCreateMaze() == RecursiveDivisionCreateMaze)
			c = new RecursiveDivisionCreateMaze();
		c.createMaze(mazeLattice, getColNumber(), getRowNumber());
	}

	public Stack<Point> solveMaze(Point p) {
		AbstractSolveMaze a = null;
		if (getSolveMaze() == BreadthFirstSearchSolveMaze)
			a = new BreadthFirstSearchSolveMaze();
		else if (getSolveMaze() == DepthFirstSearchSolveMaze)
			a = new DepthFirstSearchSolveMaze();
		return a.solveMaze(mazeLattice, p, getExit(), getColNumber(), getRowNumber());
	}

	public Point getEntrance() {
		return entrance;
	}

	public void setEntrance(Point entrance) {
		this.entrance = entrance;
	}

	public Point getExit() {
		return exit;
	}

	public void setExit(Point exit) {
		this.exit = exit;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public int getColNumber() {
		return colNumber;
	}

	public void setColNumber(int colNumber) {
		this.colNumber = colNumber;
	}

	public char getSolveMaze() {
		return solveMaze;
	}

	public void setSolveMaze(char solveMaze) {
		this.solveMaze = solveMaze;
	}

	public char getCreateMaze() {
		return createMaze;
	}

	public void setCreateMaze(char createMaze) {
		this.createMaze = createMaze;
	}
	
	public Lattice[][] getLattice() {
		return this.mazeLattice;
	}
}
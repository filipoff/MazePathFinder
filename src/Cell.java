import java.util.ArrayList;

public class Cell {
	private char symbol;
	private Point position;
	private Cell parent;
	private Maze owner;
	private int gCost;
	private int hCost;
	private boolean isInOpenedList;
	private boolean isInClosedList;
	private boolean isPassable;
	private boolean isWater;

	public Cell() {
		this.symbol = 0;
		this.position = new Point(0, 0);
		this.gCost = 0;
		this.hCost = 0;
		this.parent = null;
		this.owner = null;
		this.isInOpenedList = false;
		this.isInClosedList = false;
		this.isPassable = false;
		this.isWater = false;
	}

	public Cell(Maze owner, char symbol, Point position) {
		this.symbol = symbol;
		this.position = position;
		this.owner = owner;
		this.gCost = 0;
		this.hCost = 0;
		this.parent = null;
		this.isInOpenedList = false;
		this.isInClosedList = false;
		this.isPassable = false;
		this.isWater = false;
	}

	public Maze getOwner() {
		return owner;
	}

	public void setOwner(Maze owner) {
		this.owner = owner;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public int getgCost() {
		return gCost;
	}

	public void setgCost(byte gCost) {
		this.gCost = gCost;
	}

	public int gethCost() {
		return hCost;
	}

	public void sethCost(byte hCost) {
		this.hCost = hCost;
	}

	public Cell getParent() {
		return parent;
	}

	public void setParent(Cell parent) {
		this.parent = parent;
	}

	public boolean isInOpenedList() {
		return isInOpenedList;
	}

	public void setInOpenedList(boolean isInOpenedList) {
		this.isInOpenedList = isInOpenedList;
	}

	public boolean isInClosedList() {
		return isInClosedList;
	}

	public void setInClosedList(boolean isInClosedList) {
		this.isInClosedList = isInClosedList;
	}

	public boolean isPassable() {
		return isPassable;
	}

	public void setPassable(boolean isPassable) {
		this.isPassable = isPassable;
	}

	public boolean isWater() {
		return isWater;
	}

	public void setWater(boolean isWater) {
		this.isWater = isWater;
	}

	// TODO : hashCode and equals ?

	public ArrayList<Cell> getValidNeighbours() {
		ArrayList<Cell> neighbours = new ArrayList<Cell>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0)
					// skip this.position
					continue;
				Point neighourPosition = new Point(this.position.getxCoord()
						+ i, this.position.getyCoord() + j);
				if (owner.getCellAt(neighourPosition) != null) {
					neighbours.add(owner.getCellAt(neighourPosition));
				}
			}
		}
		return neighbours;
	}
}

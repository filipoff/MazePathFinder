public class Cell {
	private char symbol;
	private Point position;
	private Maze owner;
	private CellType type;

	public Cell() {
		this.symbol = 0;
		this.position = new Point(0, 0);
		this.owner = null;
		this.type = CellType.UNKNOWN;
	}

	public Cell(Maze owner, char symbol, Point position) {
		this.symbol = symbol;
		this.position = position;
		this.owner = owner;
		this.type = CellType.UNKNOWN;
	}

	public Maze getOwner() {
		return owner;
	}

	public CellType getType() {
		return type;
	}

	public void setType(CellType type) {
		this.type = type;
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
}

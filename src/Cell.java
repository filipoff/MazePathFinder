public class Cell {
	public abstract static class Properties {

	}

	private char symbol;
	private Point position;
	private Maze owner;
	private CellType type;
	protected Properties properties;

	public Cell() {
		this.symbol = 0;
		this.position = new Point(0, 0);
		this.owner = null;
		this.type = CellType.UNKNOWN;
		this.properties = null;
	}

	public Cell(Maze owner, char symbol, Point position, CellType type) {
		this.symbol = symbol;
		this.position = position;
		this.owner = owner;
		this.type = type;
		this.properties = null;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
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

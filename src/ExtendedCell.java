public class ExtendedCell {
	private Cell cell;
	private Cell cameFrom;
	private int costToHere;
	private int priority;

	public ExtendedCell(Cell cell, Cell cameFrom, int costToHere, int priority) {
		this.cell = cell;
		this.cameFrom = cameFrom;
		this.costToHere = costToHere;
		this.priority = priority;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public Cell getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(Cell cameFrom) {
		this.cameFrom = cameFrom;
	}

	public int getCostToHere() {
		return costToHere;
	}

	public void setCostToHere(int costToHere) {
		this.costToHere = costToHere;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}

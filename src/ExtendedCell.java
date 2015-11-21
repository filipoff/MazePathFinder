public class ExtendedCell {
	// reference to maze cell
	private Cell cell;

	// parent of the current extended cell
	private ExtendedCell cameFrom;

	// cost from start to this extended cell
	private int costToHere;

	// priority, which is used by the priority queue for the search
	private int priority;

	public ExtendedCell(Cell cell, ExtendedCell cameFrom, int costToHere,
			int priority) {
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

	public ExtendedCell getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(ExtendedCell cameFrom) {
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
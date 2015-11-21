public class CellPriorityPair {

	private Cell cell;
	private int priority;

	public CellPriorityPair(Cell cell, int priority) {
		this.cell = cell;
		this.priority = priority;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}

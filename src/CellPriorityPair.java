public class CellPriorityPair {

	private Cell cell;
	private float priority;

	public CellPriorityPair(Cell cell, float priority) {
		this.cell = cell;
		this.priority = priority;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public float getPriority() {
		return priority;
	}

	public void setPriority(float priority) {
		this.priority = priority;
	}

}

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class AStarPathFinder {
	private static final float STRAIGHT_COST = 1f;
	private static final float DIAGONAL_COST = 1.5f;
	private PriorityQueue<Cell> front;
	private HashMap<Cell, Float> costFromStartOf;
	private HashMap<Cell, Float> costToEndOf;
	private HashMap<Cell, Cell> parentOf;

	private float heuristic(Cell start, Cell goal) {
		int dx = Math.abs(start.getPosition().getxCoord()
				- goal.getPosition().getxCoord());
		int dy = Math.abs(start.getPosition().getyCoord()
				- goal.getPosition().getyCoord());

		return STRAIGHT_COST * (dx + dy) + (DIAGONAL_COST - 2 * STRAIGHT_COST)
				* Math.min(dx, dy);
	}

	public List<Point> findPath(Maze maze, Point start, Point goal) {
		List<Point> result = new ArrayList<Point>();

		// initialize the data structures
		costFromStartOf = new HashMap<Cell, Float>();
		costToEndOf = new HashMap<Cell, Float>();
		parentOf = new HashMap<Cell, Cell>();
		front = new PriorityQueue<Cell>();

		// get cell at start point
		Cell startCell = maze.getCellAt(start);
		// get cell at goal point
		Cell goalCell = maze.getCellAt(goal);

		// add the start cell to the front
		front.add(startCell);

		// set the cost from the start cell to the start (0)
		costFromStartOf.put(startCell, 0f);

		// set the cost to the end cell from the start cell
		costToEndOf.put(startCell, 0f + heuristic(startCell, goalCell));

		// while there are still cells in the front
		while (!front.isEmpty()) {

			// get the cell with both the lowest cost from start and cost to end
			Cell current = front.poll();

			// the current cell is the goal cell
			if (current == goalCell) {
				while (parentOf.get(current) != null) {
					result.add(current.getPosition());
					current.setSymbol('*');
				}
				break;
			}

			// for each valid neighbour of the current cell
			for (Cell neighbour : current.getValidNeighbours()) {

				// calculate the cost from the start to this neighbour
				float newCostFromStartToNeighbour = costFromStartOf
						.get(current) + heuristic(current, neighbour);

				// if this neighbour is not visited, add it
				// or if it is visited but this new path that is found
				// is shorter than the current path to the neighbour
				// set the current path of the neighbour with this new shorter
				// path
				if (!costFromStartOf.containsKey(neighbour)
						|| newCostFromStartToNeighbour < costFromStartOf
								.get(neighbour)) {
					costFromStartOf.put(neighbour, newCostFromStartToNeighbour);
				}

				// calculate the cost from this neighbour to the end
				costToEndOf.put(neighbour, newCostFromStartToNeighbour
						+ heuristic(neighbour, goalCell));

				// set the parent of this neighbour to it's father
				parentOf.put(neighbour, current);

				// add this neighbour to the front
				front.add(neighbour);
			}
		}
		return result;

	}
}

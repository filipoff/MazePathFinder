import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class AStarPathFinder {
	private static final float STRAIGHT_COST = 1f;
	private static final float DIAGONAL_COST = 1.5f;

	// front where cells are inspected
	private PriorityQueue<CellPriorityPair> front;

	// cost from start
	private HashMap<Cell, Float> costToHere;

	// who's parent of the current cell
	private HashMap<Cell, Cell> cameFrom;

	private float heuristic(Cell current, Cell goal) {
		int xDistance = Math.abs(current.getPosition().getxCoord()
				- goal.getPosition().getxCoord());
		int yDistance = Math.abs(current.getPosition().getyCoord()
				- goal.getPosition().getyCoord());

		return STRAIGHT_COST * (xDistance + yDistance)
				+ (DIAGONAL_COST - 2 * STRAIGHT_COST)
				* Math.min(xDistance, yDistance);
	}

	private float costBetween(Cell current, Cell neighbour) {

		if (current.getType() == CellType.WATER)
			// current is water cell, cost is 2
			return 2f;

		if (current.getPosition().getxCoord() == neighbour.getPosition()
				.getxCoord()
				|| current.getPosition().getyCoord() == neighbour.getPosition()
						.getyCoord())
			// the neighbour is a vertical/horizontal cell
			return 1f;

		else
			// the neighbour is a diagonal cell
			return 1.5f;

	}

	public List<Point> findPath(Maze maze, Point start, Point goal) {
		List<Point> result = new ArrayList<Point>();

		// initialize the data structures
		costToHere = new HashMap<Cell, Float>();
		cameFrom = new HashMap<Cell, Cell>();
		front = new PriorityQueue<CellPriorityPair>(10,
				new CellPriorityPairComparator());

		// get the start cell
		Cell startCell = maze.getCellAt(start);

		// get the goal cell
		Cell goalCell = maze.getCellAt(goal);

		// if start or goal is invalid
		// return empty list
		if (startCell == null || goalCell == null)
			return result;

		// cost from start cell to start cell is 0
		costToHere.put(startCell, 0f);

		// parent of start cell is null
		cameFrom.put(startCell, null);

		// add the start cell to the front, with priority - estimated total cost
		front.add(new CellPriorityPair(startCell,
				heuristic(startCell, goalCell)));

		// while the front has cells to inspect

		while (!front.isEmpty()) {

			// get the cell with the lowest estimated total cost
			Cell current = front.poll().getCell();

			// if it's the goal, stop and fill the list with points that
			// make the path
			if (current == goalCell) {
				result.add(current.getPosition());
				while ((current = cameFrom.get(current)) != startCell) {
					current.setSymbol('*');
					result.add(current.getPosition());
				}
				break;
			}

			// for every valid neighbour of the current cell
			for (Cell neighbour : maze.getValidNeighboursOf(current)) {

				// calculated the cost from start to this neighbour
				float neighbourNextCost = costToHere.get(current)
						+ costBetween(current, neighbour);

				// if this neighbour is newly discovered cell
				// or if it's an already discovered cell
				// but the newly calculated cost is less than
				// the current cost to this neighbour
				// meaning a shorter path is found to this neighbour

				if (!costToHere.containsKey(neighbour)
						|| neighbourNextCost < costToHere.get(neighbour)) {

					// update this neighbour cost
					costToHere.put(neighbour, neighbourNextCost);

					// set or update this neighbour's parent
					cameFrom.put(neighbour, current);

					// add this neighbour to the front
					front.add(new CellPriorityPair(neighbour, neighbourNextCost
							+ heuristic(neighbour, goalCell)));

				}
			}

		}
		Collections.reverse(result);
		return result;

	}
}

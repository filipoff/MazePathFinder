import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStarPathFinder {

	// front where cells are inspected
	private PriorityQueue<Cell> front;

	protected static class AStarCellProperties extends Cell.Properties {
		// cost from start to this cell
		public int costToHere;

		// who's parent of the this cell
		public Cell cameFrom;

		// priority of this cell, used by the front
		public int priority;
	}

	protected void setPriority(Cell current, int priority) {
		AStarCellProperties prop = (AStarCellProperties) current
				.getProperties();
		if (prop == null) {
			prop = new AStarCellProperties();
			current.setProperties(prop);
		}
		prop.priority = priority;
	}

	protected Integer getPriority(Cell current) {
		AStarCellProperties prop = (AStarCellProperties) current
				.getProperties();
		if (prop == null) {
			return null;
		}
		return prop.priority;
	}

	protected void setCameFrom(Cell current, Cell parent) {
		AStarCellProperties prop = (AStarCellProperties) current
				.getProperties();
		if (prop == null) {
			prop = new AStarCellProperties();
			current.setProperties(prop);
		}
		prop.cameFrom = parent;
	}

	protected Cell getCameFrom(Cell current) {
		AStarCellProperties prop = (AStarCellProperties) current
				.getProperties();
		if (prop == null) {
			return null;
		}
		return prop.cameFrom;
	}

	protected void setCostToHere(Cell current, int cost) {
		AStarCellProperties prop = (AStarCellProperties) current
				.getProperties();
		if (prop == null) {
			prop = new AStarCellProperties();
			current.setProperties(prop);
		}
		prop.costToHere = cost;
	}

	protected Integer getCostToHere(Cell current) {
		AStarCellProperties prop = (AStarCellProperties) current
				.getProperties();
		if (prop == null) {
			return null;
		}
		return prop.costToHere;
	}

	private int heuristic(Cell current, Cell goal) {
		int xDistance = Math.abs(current.getPosition().getxCoord()
				- goal.getPosition().getxCoord());
		int yDistance = Math.abs(current.getPosition().getyCoord()
				- goal.getPosition().getyCoord());

		return Constants.STRAIGHT_COST * (xDistance + yDistance)
				+ (Constants.DIAGONAL_COST - 2 * Constants.STRAIGHT_COST)
				* Math.min(xDistance, yDistance);
	}

	private int costBetween(Cell current, Cell neighbour) {

		if (current.getType() == CellType.WATER)
			// current is water cell
			return Constants.WATER_COST;

		if (current.getPosition().getxCoord() == neighbour.getPosition()
				.getxCoord()
				|| current.getPosition().getyCoord() == neighbour.getPosition()
						.getyCoord())
			// the neighbour is a vertical/horizontal cell
			return Constants.STRAIGHT_COST;

		else
			// the neighbour is a diagonal cell
			return Constants.DIAGONAL_COST;

	}

	public List<Point> findPath(Maze maze, Point start, Point goal) {
		List<Point> result = new ArrayList<Point>();

		// initialize the front
		front = new PriorityQueue<Cell>((p1, p2) -> getPriority(p1)
				- getPriority(p2));

		// get the start cell
		Cell startCell = maze.getCellAt(start);

		// get the goal cell
		Cell goalCell = maze.getCellAt(goal);

		// if start or goal is invalid
		// return empty list
		if (startCell == null || goalCell == null)
			return result;

		// cost from start cell to start cell is 0
		setCostToHere(startCell, 0);

		// parent of start cell is null
		setCameFrom(startCell, null);

		// priority of the start cell is the estimated cost to the goal cell
		setPriority(startCell, heuristic(startCell, goalCell));

		// add the start cell to the front
		front.add(startCell);
		// while the front has cells to inspect

		while (!front.isEmpty()) {

			// get the cell with the lowest estimated total cost
			Cell current = front.poll();

			// if it's the goal, stop and fill the list with points that
			// make the path
			if (current == goalCell) {
				result.add(current.getPosition());
				while ((current = getCameFrom(current)) != startCell) {
					current.setSymbol('*');
					result.add(current.getPosition());
				}
				break;
			}

			// for every valid neighbour of the current cell
			for (Cell neighbour : maze.getValidNeighboursOf(current)) {

				// calculated the cost from start to this neighbour
				int neighbourNextCost = getCostToHere(current)
						+ costBetween(current, neighbour);

				// if this neighbour is newly discovered cell
				// or if it's an already discovered cell
				// but the newly calculated cost is less than
				// the current cost to this neighbour
				// meaning a shorter path is found to this neighbour

				if (getCostToHere(neighbour) == null
						|| neighbourNextCost < getCostToHere(neighbour)) {

					// set or update this neighbour's cost
					setCostToHere(neighbour, neighbourNextCost);

					// set or update this neighbour's parent
					setCameFrom(neighbour, current);

					// set priority of this neighbour
					setPriority(neighbour,
							neighbourNextCost + heuristic(neighbour, goalCell));

					// add this neighbour to the front
					front.add(neighbour);

				}
			}

		}
		Collections.reverse(result);
		return result;

	}
}

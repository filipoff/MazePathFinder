import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze {

	private int height;
	private int width;
	private Cell[][] maze;
	private AStarPathFinder pathFinder;

	public Maze() {
		this.height = 0;
		this.width = 0;
		this.maze = null;
	}

	public Maze(String filePath) {

		String line = null;
		List<String> lines = new ArrayList<String>();
		try (FileReader fr = new FileReader(new File(filePath));
				BufferedReader br = new BufferedReader(fr)) {
			while ((line = br.readLine()) != null) {
				lines.add(line.replace(",", ""));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.height = lines.size();
		this.width = lines.get(0).length();
		this.maze = new Cell[height][width];

		for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				Point currentPosition = new Point(row, column);
				char currentSymbol = lines.get(row).charAt(column);
				Cell currentCell = new Cell(this, currentSymbol,
						currentPosition,
						CellType.getCellTypeForSymbol(currentSymbol));
				this.maze[row][column] = currentCell;
			}
		}

	}

	public Cell getCellAt(Point position) {
		int x = position.getxCoord();
		int y = position.getyCoord();
		if (x >= 0 && x < this.height && y >= 0 && y < this.width) {
			return this.maze[x][y];
		}
		return null;
	}

	public void print() {
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				System.out.print("[" + maze[i][j].getSymbol() + "]");
			}
			System.out.println();
		}
	}

	private boolean isValidNeighbour(Cell neighbour) {
		if ((neighbour != null)
				&& (neighbour.getType() == CellType.PASSABLE
						|| neighbour.getType() == CellType.WATER || neighbour
						.getType() == CellType.GOAL)) {
			return true;
		}
		return false;
	}

	public ArrayList<Cell> getValidNeighboursOf(Cell current) {
		ArrayList<Cell> neighbours = new ArrayList<Cell>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0)
					// skip current position
					continue;
				int x = current.getPosition().getxCoord();
				int y = current.getPosition().getyCoord();
				Point neighourPosition = new Point(x + i, y + j);
				Cell neighbour = getCellAt(neighourPosition);
				if (isValidNeighbour(neighbour)) {
					neighbours.add(neighbour);
				}
			}
		}
		return neighbours;
	}

	public List<Point> findPath(Point start, Point goal) {
		pathFinder = new AStarPathFinder();
		return pathFinder.findPath(this, start, goal);
	}

	public void markPath(List<Point> path, char symbol) {
		for (Point point : path) {
			Cell current = getCellAt(point);
			if (current != null) {
				current.setSymbol(symbol);
			}
		}
	}
}

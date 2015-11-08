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

	public Maze() {
		this.height = 0;
		this.width = 0;
		this.maze = null;
	}

	public Maze(String filePath) {

		String line = null;
		int line_length = 0;
		List<String> lines = new ArrayList<String>();
		try (FileReader fr = new FileReader(new File(filePath));
				BufferedReader br = new BufferedReader(fr)) {
			if ((line = br.readLine()) != null) {
				line_length = line.length();
			}
			while ((line = br.readLine()) != null) {
				if (line.length() != line_length) {
					lines.clear();
					throw new FileNotFoundException();
				}
				lines.add(line);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.height = lines.size();
		this.width = line_length;
		this.maze = new Cell[height][width];

		for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				Point currentPosition = new Point(row, column);
				char currentSymbol = lines.get(row).charAt(column);
				Cell currentCell = new Cell(this, currentSymbol,
						currentPosition);
				this.maze[row][column] = currentCell;
			}
		}

	}

	public Cell getCellAt(Point position) {
		if (position.getxCoord() < this.height && position.getxCoord() >= 0
				&& position.getyCoord() < this.width
				&& position.getyCoord() >= 0) {
			return this.maze[position.getxCoord()][position.getyCoord()];
		}
		return null;
	}
	
}

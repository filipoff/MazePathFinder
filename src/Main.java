import java.util.List;

public class Main {
	public static void main(String[] args) {
		Maze m = new Maze(args[0]);
		Point start = new Point(Integer.parseInt(args[1]),
				Integer.parseInt(args[2]));
		Point end = new Point(Integer.parseInt(args[3]),
				Integer.parseInt(args[4]));
		List<Point> path = m.findPath(start, end);
		m.markPath(path, '*');
		m.print();

		for (Point step : path) {
			System.out.print(step.toString());

			if (!step.equals(end)) {
				System.out.print(" ,");
			}
		}
	}
}

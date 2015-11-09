public class Main {
	public static void main(String[] args) {
		Maze m = new Maze("map1.csv");
		Point start = new Point(5, 0);
		Point end = new Point(4, 7);
		m.findPath(start, end);
		m.print();
	}
}

final class Point {
	private final byte x;
	private final byte y;

	public Point(byte x, byte y) {
		this.x = x;
		this.y = y;
	}

	public byte getxCoord() {
		return x;
	}

	public byte getyCoord() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point))
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}

public enum CellType {
	PASSABLE(' '), WATER('~'), WALL('N'), UNKNOWN('?'), GOAL('X'), CHARACTER(
			'M');
	private char symbol;

	private CellType(char symbol) {
		this.symbol = symbol;
	}

	public static CellType getCellTypeForSymbol(final char symbol) {
		for (CellType type : CellType.values()) {
			if (type.symbol == symbol)
				return type;
		}
		return CellType.UNKNOWN;
	}

}

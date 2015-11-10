import java.util.Comparator;

public class CellPriorityPairComparator implements Comparator<CellPriorityPair> {

	@Override
	public int compare(CellPriorityPair pair1, CellPriorityPair pair2) {

		if (pair1.getPriority() == pair2.getPriority())
			return 0;
		else if (pair1.getPriority() > pair2.getPriority())
			return 1;
		else
			return -1;
	}
}

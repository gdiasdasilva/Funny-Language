package semantics;

public class IntValue implements IValue {

	private final int val;

	public IntValue(int intVal)
	{
		val = intVal;
	}

	public VType typeOf() {
		return VType.INTEGER;
	}

	public int getVal() {
		return val;
	}

	public String toString() {
		return Integer.toString(val);
	}
}
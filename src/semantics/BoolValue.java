package semantics;

public class BoolValue implements IValue {

	boolean val;

	public BoolValue(boolean v)
	{
		val = v;
	}

	public VType typeOf() {
		return VType.BOOLEAN;
	}

	public boolean getVal() {
		return val;
	}

	public String toString() {
		return Boolean.toString(val);
	}
}



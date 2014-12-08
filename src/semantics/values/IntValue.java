package semantics.values;

import semantics.typeSystem.Type.VType;

public class IntValue implements IValue {

	public final int val;

	public IntValue(int intVal)
	{
		val = intVal;
	}

	public String toString() {
		return Integer.toString(val);
	}

	@Override
	public VType getType() {
		return VType.INTEGER;
	}
}
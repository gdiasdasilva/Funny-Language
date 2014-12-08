package semantics.interpreter;

import semantics.typeSystem.Type.VType;

public class IntValue implements IValue {

	final int val;

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
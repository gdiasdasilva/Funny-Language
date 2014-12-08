package semantics.interpreter;

import semantics.typeSystem.Type.VType;

public class BoolValue implements IValue {

	final boolean b;

	public BoolValue(boolean b) {
		this.b = b;
	}

	public String toString() {
		return "boolean value " + b;
	}

	@Override
	public VType getType() {
		return VType.BOOLEAN;
	}
}



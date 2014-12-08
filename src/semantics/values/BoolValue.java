package semantics.values;

import semantics.typeSystem.Type.VType;

public class BoolValue implements IValue {

	public final boolean b;

	public BoolValue(boolean b) {
		this.b = b;
	}

	public String toString() {
		return Boolean.toString(b);
	}

	@Override
	public VType getType() {
		return VType.BOOLEAN;
	}
}



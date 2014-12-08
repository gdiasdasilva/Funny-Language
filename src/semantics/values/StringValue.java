package semantics.values;

import semantics.typeSystem.Type.VType;

public class StringValue implements IValue {

	public final String s;
	
	public StringValue(String s)
	{
		this.s = s;
	}

	public String toString() {
		return "string with value " + s;
	}

	@Override
	public VType getType() {
		return VType.STRING;
	}

}

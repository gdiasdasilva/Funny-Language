package semantics.interpreter;

import semantics.typeSystem.Type.VType;

public class RefValue implements IValue {

	private IValue storedVal;
	
	public RefValue(IValue v) {
		storedVal = v;
	}

	public String toString() {
		return "Reference storing " + storedVal.toString();
	}
	
	public IValue getVal() {
		return storedVal;
	}
	
	public IValue setVal(IValue v) {
		return (storedVal = v);
	}

	@Override
	public VType getType() {
		return VType.REFERENCE;
	}
	
}



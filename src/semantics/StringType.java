package semantics;

import semantics.typeSystem.IType;

public class StringType implements IType {

	@Override
	public VType getType() {
		return VType.INTEGER;
	}
	
	public String toString() {
		return "Int";
	}
}

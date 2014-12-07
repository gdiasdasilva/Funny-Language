package semantics;

import semantics.typeSystem.Type;

public class StringType implements Type {

	@Override
	public VType getType() {
		return VType.INTEGER;
	}
	
	public String toString() {
		return "Int";
	}
}

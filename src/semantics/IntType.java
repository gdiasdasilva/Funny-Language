package semantics;

public class IntType implements IType {

	@Override
	public VType getType() {
		return VType.INTEGER;
	}
	
	public String toString() {
		return "Int";
	}
}

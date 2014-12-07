package semantics.typeSystem;

public class IntType implements IType {

	@Override
	public VType getType() {
		return VType.INTEGER;
	}
	
	public String toString() {
		return "Int";
	}
}

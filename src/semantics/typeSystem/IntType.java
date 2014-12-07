package semantics.typeSystem;

public class IntType implements Type {

	@Override
	public VType getType() {
		return VType.INTEGER;
	}
	
	public String toString() {
		return "Int";
	}
}

package semantics.typeSystem;

public class BoolType implements Type {

	@Override
	public VType getType() {
		return VType.BOOLEAN;
	}

	public String toString() {
		return "Boolean";
	}
}

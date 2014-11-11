package semantics;

public class BoolType implements IType {

	@Override
	public VType getType() {
		return VType.BOOLEAN;
	}

	public String toString() {
		return "Boolean";
	}
}

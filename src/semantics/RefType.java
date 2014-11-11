package semantics;

public class RefType implements IType {

	@Override
	public VType getType() {
		return VType.REFERENCE;
	}
	
	public String toString() {
		return "Ref";
	}

}

package semantics.typeSystem;


public class StringType implements Type {

	@Override
	public VType getType() {
		return VType.STRING;
	}
	
	public String toString() {
		return "String Type";
	}
}

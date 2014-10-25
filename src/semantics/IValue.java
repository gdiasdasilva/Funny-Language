package semantics;

public abstract class IValue {

	public enum VType {
		INTEGER, BOOLEAN, REFERENCE, STRING
	}
	
	public static String nameOfType(VType t) {
		switch (t) {
		case INTEGER:
			return "Integer";
		case BOOLEAN:
			return "Boolean";
		case REFERENCE:
			return "Reference";
		case STRING:
			return "String";
		default:
			return "Unknown";
		}
	}

	public abstract VType typeOf();

	public abstract String toString();

}
package semantics.typeSystem;

public interface Type
{
	public enum VType {
		INTEGER, BOOLEAN, REFERENCE, STRING, FUNCTION, CMD, RECORD
	}
	
	VType getType();
}

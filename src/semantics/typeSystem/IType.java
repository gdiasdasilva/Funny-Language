package semantics.typeSystem;

public interface IType
{
	public enum VType {
		INTEGER, BOOLEAN, REFERENCE, STRING, FUNCTION, CMD
	}
	
	VType getType();
}

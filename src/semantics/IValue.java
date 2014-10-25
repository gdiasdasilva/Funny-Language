package semantics;

public interface IValue {

	enum VType {
		INTEGER, BOOLEAN, REFERENCE, STRING
	}

	VType typeOf();

	String toString();

}
package semantics.interpreter;

import semantics.typeSystem.Type.VType;

public interface IValue {
	String toString();
	VType getType();
}
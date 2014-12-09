package semantics.typeSystem;

import java.util.List;

public final class FunType implements Type {

	final List<Type> paramTypes;
	final Type returnType;
	
	public FunType(Type returnType, List<Type> paramTypes) {
		this.paramTypes = paramTypes;
		this.returnType = returnType;
	}
	
	@Override
	public VType getType() {
		return VType.FUNCTION;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FunType)
			return (paramTypes.equals(((FunType) obj).paramTypes) && returnType.equals(((FunType) obj).returnType));
		return false;
	}

	public String toString() {
		return "FUNCTION w/ params of type " + paramTypes + ".";
	}
}

package semantics.typeSystem;

import java.util.List;

import semantics.Environment;

public final class FunType implements Type {

	final List<Type> paramTypes;
	final Type returnType;
	
	public FunType(List<Type> paramTypes, Type returnType)
	{
		this.paramTypes = paramTypes;
		this.returnType = returnType;
	}
	
	@Override
	public VType getType()
	{
		return VType.FUNCTION;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FunType)
			if ((paramTypes.equals(((FunType) obj).paramTypes)) && (returnType.equals(((FunType) obj).returnType)))
				return true;
		return false;
	}

	public String toString() {
		return "FUNCTION w/ params of type " + paramTypes + " and return type " + returnType + ".";
	}
	
	public Environment<Type> beginScope()
	{
		return e.beginScope();
	}
	
	public void endScope()
	{
		e.endScope();
	}
}

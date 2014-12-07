package semantics.typeSystem;

import java.util.List;

public final class FunType implements IType {

	final List<IType> paramTypes;
	final IType returnType;
	
	public FunType(List<IType> paramTypes, IType returnType)
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
}

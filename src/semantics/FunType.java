package semantics;

import java.util.List;

public class FunType implements IType {

	final List<IType> params;
	final IType returnType;
	
	public FunType(List<IType> params, IType returnType)
	{
		this.params = params;
		this.returnType = returnType;
	}
	
	@Override
	public VType getType()
	{
		return VType.FUNCTION;
	}
	
	public String toString() {
		return "Function";
	}
}

package semantics;

import java.util.List;

public class FunType implements IType {

	List<IType> args;
	IType returnType;
	
	public FunType(List<IType> args, IType returnType)
	{
		this.args = args;
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

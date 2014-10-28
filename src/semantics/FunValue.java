package semantics;

import ast.ASTNode;

public class FunValue extends IValue {

	public final ASTNode expBody;
	public final String parameter;
	
	public FunValue(ASTNode expBody, String parameter)
	{
		this.expBody = expBody;
		this.parameter = parameter;
	}
	
	@Override
	public VType typeOf()
	{
		return VType.FUNCTION;
	}

	@Override
	public String toString()
	{
		return expBody.toString();
	}
	
	public String getParameter()
	{
		return parameter;	
	}
	
	public ASTNode getBody()
	{
		return expBody;
	}
}

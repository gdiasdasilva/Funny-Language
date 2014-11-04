package semantics;

import java.util.List;

import ast.ASTNode;

public class FunValue extends IValue {

	public final ASTNode expBody;
	public final List<String> parameters;
	
	public FunValue(ASTNode expBody, List<String> parameters)
	{
		this.expBody = expBody;
		this.parameters = parameters;
	}
	
	@Override
	public VType typeOf()
	{
		return VType.FUNCTION;
	}

	@Override
	public String toString()
	{
		return "Function w/ params " + parameters.toString();
	}
	
	public List<String> getParameters()
	{
		return parameters;	
	}
	
	public ASTNode getBody()
	{
		return expBody;
	}
}

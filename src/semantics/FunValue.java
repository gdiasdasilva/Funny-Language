package semantics;

import java.util.List;

import ast.ASTNode;

public class FunValue extends IValue {

	public final ASTNode expBody;
	public final List<String> parameters;
	public final IEnv e;
	
	public FunValue(ASTNode expBody, List<String> parameters, IEnv e)
	{
		this.expBody = expBody;
		this.parameters = parameters;
		this.e = e;
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
	
	public IEnv beginScope()
	{
		return e.beginScope();
	}
	
	public void endScope()
	{
		e.endScope();
	}
	
}

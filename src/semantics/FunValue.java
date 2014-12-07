package semantics;

import java.util.List;

import ast.ASTNode;
import ast.Param;

public class FunValue extends IValue {

	public final ASTNode expBody;
	public final List<Param> parameters;
	public final IEnv e;
	
	public FunValue(ASTNode expBody, List<Param> parameters, IEnv e)
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
	
	public List<Param> getParameters()
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

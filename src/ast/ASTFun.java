package ast;

import java.util.List;

import semantics.IEnv;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTFun implements ASTNode {
	
	public final List<Param> params;
	public final ASTNode body;
	
	public ASTFun(List<Param> params, ASTNode body) 
	{
		this.params = params;
		this.body = body;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor, IEnv e) throws SemanticException {
		return visitor.visit(this, e);
	}

}

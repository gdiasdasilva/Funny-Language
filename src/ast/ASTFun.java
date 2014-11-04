package ast;

import java.util.List;

import semantics.SemanticException;
import semantics.Visitor;

public class ASTFun implements ASTNode {

	public final List<String> params;
	public final ASTNode body;
	
	public ASTFun(List<String> params, ASTNode body) 
	{
		this.params = params;
		this.body = body;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor) throws SemanticException {
		return visitor.visit(this);
	}

}

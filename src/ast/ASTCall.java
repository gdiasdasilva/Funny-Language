package ast;

import semantics.SemanticException;
import semantics.Visitor;

public class ASTCall implements ASTNode {

	public final ASTNode fun;
	public final ASTNode arg;
	
	public ASTCall(ASTNode fun, ASTNode arg)
	{
		this.fun = fun;
		this.arg = arg;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor) throws SemanticException {
		return visitor.visit(this);
	}

}

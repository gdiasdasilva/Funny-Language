package ast;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTWhile implements ASTNode {

	public final ASTNode c; // condition (boolean expression)
	public final ASTNode b; // body
	
	public ASTWhile(ASTNode c, ASTNode b)
	{
		this.c = c;
		this.b = b;
	}
	
	@Override
	public <T, S> T accept(Visitor<T, S> visitor, Environment<S>
 e) throws SemanticException {
		return visitor.visit(this, e);
	}

}

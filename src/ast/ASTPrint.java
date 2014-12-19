package ast;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTPrint implements ASTNode {

	public final ASTNode node;
	
	public ASTPrint(ASTNode node)
	{
		this.node = node;
	}
	
	@Override
	public <T, S> T accept(Visitor<T, S> visitor, Environment<S>
 e) throws SemanticException {
		return visitor.visit(this, e);
	}

}

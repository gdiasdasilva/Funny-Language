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
	public <T> T accept(Visitor<T> visitor, Environment<T> e) throws SemanticException {
		return visitor.visit(this, e);
	}

}

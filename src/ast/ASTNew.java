package ast;

import semantics.SemanticException;
import semantics.Visitor;

public class ASTNew implements ASTNode {

	public final ASTNode node;
	
	public ASTNew(ASTNode node)
	{
		this.node = node;
	}	
	
	@Override
	public <T> T accept(Visitor<T> visitor) throws SemanticException {
		return visitor.visit(this);
	}

}

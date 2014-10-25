package ast;

import semantics.SemanticException;
import semantics.Visitor;

public class ASTId implements ASTNode {

	public final String id;
	
	public ASTId(String id)
	{
		this.id = id;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) throws SemanticException {
		return visitor.visit(this);
	}

}

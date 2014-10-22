package ast;

import semantics.IValue;
import semantics.Visitor;

public class ASTId implements ASTNode {

	public final String id;
	
	public ASTId(String id)
	{
		this.id = id;
	}

	@Override
	public IValue accept(Visitor<IValue> visitor) throws Exception {
		return visitor.visit(this);
	}

}

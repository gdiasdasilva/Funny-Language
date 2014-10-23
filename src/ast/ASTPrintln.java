package ast;

import semantics.IValue;
import semantics.Visitor;

public class ASTPrintln implements ASTNode {

	public final ASTNode node;
	
	public ASTPrintln(ASTNode node)
	{
		this.node = node;
	}
	
	@Override
	public IValue accept(Visitor<IValue> visitor) throws Exception {
		return visitor.visit(this);
	}

}

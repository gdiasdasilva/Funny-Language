package ast;

import semantics.IValue;
import semantics.Visitor;

public class ASTEq implements ASTNode {

	public final ASTNode l, r;
	
	public ASTEq(ASTNode l, ASTNode r) {
		this.l = l;
		this.r = r;
	}

	@Override
	public IValue accept(Visitor<IValue> visitor) throws Exception {
		return visitor.visit(this);
	}
}

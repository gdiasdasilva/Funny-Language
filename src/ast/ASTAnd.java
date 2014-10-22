package ast;

import semantics.IValue;
import semantics.Visitor;

public class ASTAnd implements ASTNode {
	
	public final ASTNode l, r;
	
	public ASTAnd(ASTNode l, ASTNode r) {
		this.l = l;
		this.r = r;
	}

	@Override
	public IValue accept(Visitor<IValue> visitor) throws Exception {
		return visitor.visit(this);
	}

}

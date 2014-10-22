package ast;

import semantics.IValue;
import semantics.Visitor;

public class ASTLseq implements ASTNode {
	
	public final ASTNode l, r;
	
	public ASTLseq(ASTNode l, ASTNode r) {
		this.l = l;
		this.r = r;
	}

	@Override
	public IValue accept(Visitor<IValue> visitor) throws Exception {
		return visitor.visit(this);
	}

}

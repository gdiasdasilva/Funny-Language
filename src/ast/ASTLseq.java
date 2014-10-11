package ast;

import semantics.Visitor;

public class ASTLseq implements ASTNode {
	
	public final ASTNode l, r;
	
	public ASTLseq(ASTNode l, ASTNode r) {
		this.l = l;
		this.r = r;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}

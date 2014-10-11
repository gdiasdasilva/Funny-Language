package ast;

import semantics.Visitor;

public class ASTOr implements ASTNode {
	
	public final ASTNode l, r;
	
	public ASTOr(ASTNode l, ASTNode r) {
		this.l = l;
		this.r = r;
	}


	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}

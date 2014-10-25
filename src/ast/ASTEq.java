package ast;

import semantics.SemanticException;
import semantics.Visitor;

public class ASTEq implements ASTNode {

	public final ASTNode l, r;
	
	public ASTEq(ASTNode l, ASTNode r) {
		this.l = l;
		this.r = r;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) throws SemanticException {
		return visitor.visit(this);
	}
}

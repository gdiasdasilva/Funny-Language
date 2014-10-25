package ast;

import semantics.SemanticException;
import semantics.Visitor;

public class ASTAnd implements ASTNode {
	
	public final ASTNode l, r;
	
	public ASTAnd(ASTNode l, ASTNode r) {
		this.l = l;
		this.r = r;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) throws SemanticException {
		return visitor.visit(this);
	}

}

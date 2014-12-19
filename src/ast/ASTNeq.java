package ast;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTNeq implements ASTNode {
	
	public final ASTNode l, r;
	
	public ASTNeq(ASTNode l, ASTNode r) {
		this.l = l;
		this.r = r;
	}

	@Override
	public <T, S> T accept(Visitor<T, S> visitor, Environment<S>
 e) throws SemanticException {
		return visitor.visit(this, e);
	}

}

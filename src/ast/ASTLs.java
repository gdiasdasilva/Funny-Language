package ast;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTLs implements ASTNode {
	
	public final ASTNode l, r;

	public ASTLs(ASTNode l, ASTNode r) {
		this.l = l;
		this.r = r;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor, Environment<T> e) throws SemanticException {
		return visitor.visit(this, e);
	}

}

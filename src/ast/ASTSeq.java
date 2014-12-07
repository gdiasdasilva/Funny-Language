package ast;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTSeq implements ASTNode {

	public final ASTNode f, s;
	
	public ASTSeq(ASTNode f, ASTNode s) {
		this.f = f;
		this.s = s;
	}

	@Override
	public <T> T accept(Visitor<T> visitor, Environment<T> e) throws SemanticException {
		return visitor.visit(this, e);
	}

}

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
	public <T, S> T accept(Visitor<T, S> visitor, Environment<S>
 e) throws SemanticException {
		return visitor.visit(this, e);
	}

}

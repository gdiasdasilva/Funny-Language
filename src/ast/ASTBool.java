package ast;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTBool implements ASTNode {
	
	public final boolean bool;

	public ASTBool(boolean bool) {
		this.bool = bool;
	}

	@Override
	public <T> T accept(Visitor<T> visitor, Environment<T> e) throws SemanticException {
		return visitor.visit(this, e);
	}

}

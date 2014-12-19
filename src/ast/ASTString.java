package ast;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTString implements ASTNode {

	public final String string;
	
	public ASTString(String string) {
		this.string = string;
	}
	
	@Override
	public <T, S> T accept(Visitor<T, S> visitor, Environment<S>
 e) throws SemanticException {
		return visitor.visit(this, e);
	}

}

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
	public <T> T accept(Visitor<T> visitor, Environment<T> e) throws SemanticException {
		return visitor.visit(this, e);
	}

}

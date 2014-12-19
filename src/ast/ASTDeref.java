package ast;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;
import semantics.typeSystem.Type;

public class ASTDeref implements ASTNode {

	public final ASTNode node;
	private Type t;
	
	public void setType(Type t) {
		this.t = t;
	}
	
	public Type getType() {
		return t;
	}
	
	public ASTDeref(ASTNode node) {
		this.node = node;
	}
	
	@Override
	public <T, S> T accept(Visitor<T, S> visitor, Environment<S> e) throws SemanticException {
		return visitor.visit(this, e);
	}

}

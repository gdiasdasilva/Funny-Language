package ast;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;
import semantics.typeSystem.Type;

public class ASTAssign implements ASTNode {

	public final ASTNode l;
	public final ASTNode r;
	
	private Type rType;
	
	public void setRType(Type t) {
		rType = t;
	}
	
	public Type getRType() {
		return rType;
	}
	
	public ASTAssign(ASTNode l, ASTNode r)
	{
		this.l = l;
		this.r = r;
	}

	@Override
	public <T, S> T accept(Visitor<T, S> visitor, Environment<S> e) throws SemanticException {
		return visitor.visit(this, e);
	}
}

package ast;

import semantics.IEnv;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTAssign implements ASTNode {

	public final ASTNode l;
	public final ASTNode r;
	
	public ASTAssign(ASTNode l, ASTNode r)
	{
		this.l = l;
		this.r = r;
	}

	@Override
	public <T> T accept(Visitor<T> visitor, IEnv e) throws SemanticException {
		return visitor.visit(this, e);
	}
}

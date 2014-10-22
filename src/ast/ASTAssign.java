package ast;

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
	public <T> T accept(Visitor<T> visitor) throws Exception {
		return visitor.visit(this);
	}

}

package ast;

import semantics.Visitor;

public class ASTDecl implements ASTNode {

	public final String id;
	public final ASTNode def, body;
	
	public ASTDecl(String id, ASTNode def, ASTNode body)
	{
		this.id = id;
		this.def = def;
		this.body = body;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}

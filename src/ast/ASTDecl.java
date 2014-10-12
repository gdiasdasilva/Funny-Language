package ast;

import java.util.List;

import semantics.Visitor;

public class ASTDecl implements ASTNode {

	public final List<String> ids;
	public final ASTNode body;
	public final List<ASTNode> defs;
	
	public ASTDecl(List<String> ids, List<ASTNode> defs, ASTNode body)
	{
		this.ids = ids;
		this.defs = defs;
		this.body = body;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) throws Exception {
		return visitor.visit(this);
	}

}

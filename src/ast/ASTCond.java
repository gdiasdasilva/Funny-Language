package ast;

import semantics.IValue;
import semantics.Visitor;

public class ASTCond implements ASTNode {
	
	public final ASTNode condNode, thenNode, elseNode;

	public ASTCond (ASTNode condNode, ASTNode thenNode, ASTNode elseNode) {
		this.condNode = condNode;
		this.thenNode = thenNode;
		this.elseNode = elseNode;
	}

	@Override
	public IValue accept(Visitor<IValue> visitor) throws Exception {
		return visitor.visit(this);
	}
}

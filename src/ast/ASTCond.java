package ast;

import semantics.IEnv;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTCond implements ASTNode {
	
	public final ASTNode condNode, thenNode, elseNode;

	public ASTCond (ASTNode condNode, ASTNode thenNode, ASTNode elseNode) {
		this.condNode = condNode;
		this.thenNode = thenNode;
		this.elseNode = elseNode;
	}

	@Override
	public <T> T accept(Visitor<T> visitor, IEnv e) throws SemanticException {
		return visitor.visit(this, e);
	}
}

package ast;

import semantics.Environment;
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
	public <T, S> T accept(Visitor<T, S> visitor, Environment<S>
 e) throws SemanticException {
		return visitor.visit(this, e);
	}
}

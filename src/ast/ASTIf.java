package ast;

import semantics.Environment;
import semantics.SemanticException;
import semantics.Visitor;

public class ASTIf implements ASTNode {
	
	public final ASTNode condNode, thenNode, elseNode;

	public ASTIf(ASTNode condNode, ASTNode thenNode, ASTNode elseNode) {
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

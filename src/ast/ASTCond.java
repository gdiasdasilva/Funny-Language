package ast;

import semantics.CodeBlock;
import semantics.Cond;
import semantics.Visitor;

public class ASTCond implements ASTNode {
	
	public final ASTNode condNode, thenNode, elseNode;

	public ASTCond (ASTNode condNode, ASTNode thenNode, ASTNode elseNode) {
		this.condNode = condNode;
		this.thenNode = thenNode;
		this.elseNode = elseNode;
	}
	
	@Override
	public int eval() {
		return (condNode.eval() != 0 ? thenNode.eval() : elseNode.eval());
	}

	@Override
	public void compile(CodeBlock c) {
		condNode.compile(c);
		c.insertCondBranch(Cond.EQ);
		elseNode.compile(c);
		c.insertGoto();
		c.insertThenLabel();
		thenNode.compile(c);
		c.insertFinalLabel();
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}

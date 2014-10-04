package ast;

import semantics.Visitor;
import semantics.compiler.CodeBlock;

public class ASTUnMinus implements ASTNode {

	public final ASTNode v;
	
	public ASTUnMinus(ASTNode v) {
		this.v = v;
	}

	@Override
	public int eval() {
		return -v.eval();
	}

	@Override
	public void compile(CodeBlock c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}

package ast;

import semantics.Value;
import semantics.Visitor;

import compiler.CodeBlock;

public class ASTNum implements ASTNode {
	
	public final Value<Integer> iVal;

	public ASTNum(Value<Integer> iVal) {
		this.iVal = iVal;
	}

	@Override
	public int eval() {
		// TODO Auto-generated method stub
		return 0;
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

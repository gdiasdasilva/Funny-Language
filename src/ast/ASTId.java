package ast;

import semantics.Visitor;
import semantics.compiler.CodeBlock;

public class ASTId implements ASTNode {

	public final String id;
	
	public ASTId(String id)
	{
		this.id = id;
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
		// TODO Auto-generated method stub
		return null;
	}

}

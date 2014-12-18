package semantics.compiler;

import semantics.Environment;
import semantics.IdentiferDeclaredTwiceException;
import semantics.UndefinedIdException;

public class CompilerEnvironement implements Environment<CodeBlock> {
	
	private int level;

	@Override
	public Environment<CodeBlock> beginScope() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Environment<CodeBlock> endScope() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assoc(String id, CodeBlock val)
			throws IdentiferDeclaredTwiceException {
		// TODO Auto-generated method stub

	}
	
	public int getUpperId() {
		return 0;
	}

	@Override
	public CodeBlock find(String id) throws UndefinedIdException {
		// TODO Auto-generated method stub
		return null;
	}

}

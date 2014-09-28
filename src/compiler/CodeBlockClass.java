package compiler;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class CodeBlockClass implements CodeBlock {
	private final String ADD_OP = "iadd";
	private final String DIV_OP = "idiv";
	private final String MUL_OP = "imul";
	private final String SUB_OP = "isub";
	private final String PUSH_I = "sipush ";
	
	private List<String> codeBlock;

	public CodeBlockClass() {
		codeBlock = new LinkedList<String>();
	}
	
	@Override
	public void writeToFile(File f) {
		// TODO Auto-generated method stub
	}

	@Override
	public void insertOp(Op op) {
		switch (op) {
			case ADD: codeBlock.add(ADD_OP);
					  break;
			case DIV: codeBlock.add(DIV_OP);
					  break;
			case MUL: codeBlock.add(MUL_OP);
			  		  break;
			case SUB: codeBlock.add(SUB_OP);
	  		  		  break;
			default:
				break;
			}
	}

	@Override
	public void insertIntArgument(int i) {
		codeBlock.add(PUSH_I + i);
	}
	
	public List<String> getStringList() {
		return codeBlock;
	}
}

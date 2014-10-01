package semantics;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class CodeBlock {
	private final String ADD_OP = "iadd";
	private final String DIV_OP = "idiv";
	private final String MUL_OP = "imul";
	private final String SUB_OP = "isub";
	private final String PUSH_I = "sipush ";
	
	private final String EQ_OP = "if_icmpeq ";
	
	private List<String> codeBlock;

	public CodeBlock() {
		codeBlock = new LinkedList<String>();
	}
	
	public void writeToFile(File f) {
		// TODO Auto-generated method stub
	}

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

	public void insertIntArgument(int i) {
		codeBlock.add(PUSH_I + i);
	}

	public void insertCondBranch(Cond cond) {
		switch (cond) {
		case EQ: codeBlock.add(EQ_OP + "ThenLabel");
				  break;
		default:
			break;
		}
	}

	public void insertGoto() {
		codeBlock.add("goto FinalLabel");
	}

	public void insertThenLabel() {
		codeBlock.add("ThenLabel:");
	}

	public void insertFinalLabel() {
		codeBlock.add("FinalLabel:");
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String s : codeBlock) {
			sb.append(s);
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public void merge(CodeBlock cb) {
		codeBlock.addAll(cb.codeBlock);
	}
}

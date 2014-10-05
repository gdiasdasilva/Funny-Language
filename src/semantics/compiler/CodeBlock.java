package semantics.compiler;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class CodeBlock {
	
//    if_icmpeq  <label>
//    if_icmpge  <label>
//    if_icmpgt  <label>
//    if_icmple  <label>
//    if_icmplt  <label>
//    if_icmpne  <label>
	
	// arithmetic/integer operations
	private static final String ADD_OP = "iadd";
	private static final String DIV_OP = "idiv";
	private static final String MUL_OP = "imul";
	private static final String SUB_OP = "isub";
	private static final String PUSH_I = "sipush ";
	
	// logic/boolean operations
	// using integers to represent truth values
	// zero is 'false', all other integers are 'true'
	private static final String AND_OP = MUL_OP;
	private static final String OR_OP = ADD_OP;
	
	// comparisons
	private static final String EQ_OP = "if_icmpeq ";
	private static final String NEQ_OP = "if_icmpne ";
	private static final String LS_OP = "if_icmplt ";
	private static final String LSEQ_OP = "if_icmple ";
	private static final String GR_OP = "if_icmpgt ";
	private static final String GREQ_OP = "if_icmpge ";
	
	private List<String> codeBlock;

	public CodeBlock() {
		codeBlock = new LinkedList<String>();
	}
	
	public void writeToFile(File f) {
		// TODO Auto-generated method stub
	}

	public void insertOp(Op op) {
		switch (op) {
		case ADD:
			codeBlock.add(ADD_OP);
			break;
		case DIV:
			codeBlock.add(DIV_OP);
			break;
		case MUL:
			codeBlock.add(MUL_OP);
			break;
		case SUB:
			codeBlock.add(SUB_OP);
			break;
		case AND:
			codeBlock.add(AND_OP);
			break;
		case OR:
			codeBlock.add(OR_OP);
			break;
		default:
			break;
		}
	}
	
	public void insertIntArgument(int i) {
		codeBlock.add(PUSH_I + i);
	}

	public void insertCondBranching(Cond cond, int thenLabel) {
		switch (cond) {
		case EQ:
			codeBlock.add(EQ_OP + "Then" + thenLabel);
			break;
		case NEQ:
			codeBlock.add(NEQ_OP + "Then" + thenLabel);
			break;
		case LS:
			codeBlock.add(LS_OP + "Then" + thenLabel);
			break;
		case LSEQ:
			codeBlock.add(LSEQ_OP + "Then" + thenLabel);
			break;
		case GR:
			codeBlock.add(GR_OP + "Then" + thenLabel);
			break;
		case GREQ:
			codeBlock.add(GREQ_OP + "Then" + thenLabel);
			break;
		default:
			break;
		}
	}

	public void insertGotoContinue(int labelId) {
		codeBlock.add("goto Continue" + labelId);
	}

	public void insertThenLabel(int labelId) {
		codeBlock.add("Then" + labelId + ":");
	}

	public void insertContinueLabel(int labelId) {
		codeBlock.add("Continue" + labelId + ":");
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
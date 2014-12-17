package semantics.compiler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
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
	// zero is 'false', one is 'true'
	private static final String AND_OP = "iand";
	private static final String OR_OP = "ior";
	
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
	
	public void writeToFile(File f) throws IOException {
		Files.delete(f.toPath());
		BufferedReader[] files = {
				new BufferedReader(new FileReader(new File("Header.j"))),
				new BufferedReader(new FileReader(new File("Footer.j")))
		};
		List<String> sl = new LinkedList<String>();
		for (int i = 0; i < 2; i++) {
			while (files[i].ready()) {
				sl.add(files[i].readLine());
			}
			files[i].close();
			if (i == 0)
				sl.addAll(codeBlock);
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));

		for (String s : sl) {
			bw.write(s);
			bw.newLine();
		}
		
		bw.close();
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
//		case NOT:
//			codeBlock.add(NOT_OP);
//			break;
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

	public void append(CodeBlock cb) {
		codeBlock.addAll(cb.codeBlock);
	}
}

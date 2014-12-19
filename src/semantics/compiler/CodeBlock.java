package semantics.compiler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.LinkedList;
import java.util.List;

public class CodeBlock {
	
	static final String CLASS_NAME_LINE =             ".class                   ";
	static final String SUPER_LINE =                  ".super                   java/lang/Object";
	static final String INIT =                        ".method public <init>()V";
	static final String ALOAD_0 =                     "   aload_0";
	static final String INVOKE_OBJECT_INIT =          "   invokenonvirtual java/lang/Object/<init>()V";
	static final String RETURN =                      "return";
	static final String END_METHOD =                  ".end method\n";
	static final String MAIN =                        ".method public static main([Ljava/lang/String;)V";
	static final String INVOKE_PRINTLN =              "invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V";
	static final String INVOKE_PRINT =                "invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V";
	static final String LIMITS =                      ".limit locals 32\n.limit stack 256";
	static final String GET_PRINT_STREAM =            "getstatic java/lang/System/out Ljava/io/PrintStream;";
	static final String INVOKE_STRING_FROM_INT =      "invokestatic java/lang/String/valueOf(I)Ljava/lang/String;";
	static final String NEW_INST =                    "new ";
	static final String DUP =                         "dup";
	static final String FRAME =                       "frame_";
	static final String ALOAD_SL =                    "aload 2";
	static final String ASTORE_SL =                   "astore 2";
	public static final String MAIN_CLASS_NAME =      "Code";
	public static final String CODE_DIR =             "code/";
	
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
	private List<FrameClass> frames;
	
	private RefToIntClass getRefToInt() {
		return new RefToIntClass();
	}
	
	private RefToRefClass getRefToRef() {
		return new RefToRefClass();
	}
	
	static List<String> getPreamble() {
		List<String> p = new LinkedList<String>();
		p.add(CLASS_NAME_LINE + MAIN_CLASS_NAME);
		p.add(SUPER_LINE + "\n");
		p.add(INIT);
		p.add(ALOAD_0);
		p.add(INVOKE_OBJECT_INIT);
		p.add("   " + RETURN);
		p.add(END_METHOD);
		p.add(MAIN);
		p.add(LIMITS + "\n");
		return p;
	}
	
	static List<String> getEnding() {
		List<String> e = new LinkedList<String>();
		e.add(RETURN);
		e.add(END_METHOD);
		return e;
	}
	
	public CodeBlock() {
		codeBlock = new LinkedList<String>();
		frames = new LinkedList<FrameClass>();
	}
		
	public void writeToFile() throws IOException {
		File mainCode = new File(CODE_DIR + MAIN_CLASS_NAME + ".j");
		mainCode.mkdirs();
		try {
			Files.delete(mainCode.toPath());
		} catch (NoSuchFileException e) { /* nothing to delete, proceed */ }
		
		List<String> sl = new LinkedList<String>();
		
		sl.addAll(getPreamble());
		sl.addAll(codeBlock);
		sl.addAll(getEnding());

		// write main code
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mainCode)));
		for (String s : sl) {
			bw.write(s);
			bw.newLine();
		}
		bw.close();
		
		// write each frame class
		for (FrameClass f : frames) {
			String frameFileName = CODE_DIR + f.frameId + ".j";
			try {
				Files.delete((new File(frameFileName)).toPath());
			} catch (NoSuchFileException e) { /* nothing to delete, proceed */ }
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(frameFileName)));
			bw.write(f.toString());
			bw.close();
		}
		
		String refToInt = CODE_DIR + "refToInt.j";
		try {
			Files.delete((new File(refToInt)).toPath());
		} catch (NoSuchFileException e) { /* nothing to delete, proceed */ }
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(refToInt)));
		bw.write(getRefToInt().toString());
		bw.close();
		
		String refToRef = CODE_DIR + "refToRef.j";
		try {
			Files.delete((new File(refToRef)).toPath());
		} catch (NoSuchFileException e) { /* nothing to delete, proceed */ }
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(refToRef)));
		bw.write(getRefToRef().toString());
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
	
	public void insertOtherInstruction(String instruction) {
		codeBlock.add(instruction);
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
	
	public void insertFrame(FrameClass f) {
		frames.add(f);
		codeBlock.add(NEW_INST + f.frameId);
		codeBlock.add(DUP);
		codeBlock.add("invokespecial " + f.frameId + "/<init>()V");
		if (f.ancestorFrameId.length() > 0) {
			codeBlock.add(DUP);
			codeBlock.add(ALOAD_SL);
			putFieldId(f.frameId, "sl", "L" + f.ancestorFrameId + ";");
		}
		codeBlock.add(ASTORE_SL);
	}
	
	public void insertIntRef() {
		codeBlock.add(NEW_INST + "refToInt");
		codeBlock.add(DUP);
		codeBlock.add("invokespecial refToInt/<init>()V");
		codeBlock.add(DUP);
	}
	
	public void insertRefRef() {
		codeBlock.add(NEW_INST + "refToRef");
		codeBlock.add(DUP);
		codeBlock.add("invokespecial refToRef/<init>()V");
		codeBlock.add(DUP);
	}
	
	public void insertGetAncestorFrame(String currentFrameId, String ancestorFrameId) {
		codeBlock.add("getfield " + currentFrameId + "/sl " + "L" + ancestorFrameId + ";");
	}
	
	public void insertGetFieldValue(String frameId, String fieldName, String fieldType) {
		codeBlock.add("getfield " + frameId + "/" + fieldName + " " + fieldType);
	}
	
	public void insertCheckcastRefInt() {
		codeBlock.add("checkcast refToInt");
	}
	
	public void insertCheckcastRefRef() {
		codeBlock.add("checkcast refToRef");
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String s : getPreamble()) {
			sb.append(s);
			sb.append('\n');
		}
		for (String s : codeBlock) {
			sb.append(s);
			sb.append('\n');
		}
		for (String s : getEnding()) {
			sb.append(s);
			sb.append('\n');
		}
		
		// For development purposes, print the frame classes too
		sb.append('\n');
		sb.append("FRAME CLASS\n\n");
		for (FrameClass frame : frames) {
			sb.append(frame.toString());
			sb.append('\n');
		}
		return sb.toString();
	}

	public void append(CodeBlock cb) {
		codeBlock.addAll(cb.codeBlock);
		frames.addAll(cb.frames);
	}

	public void insertLoadSL() {
		codeBlock.add(ALOAD_SL);
	}
	
	public void insertStoreSL() {
		codeBlock.add(ASTORE_SL);
	}

	public void putFieldId(String frameId, String field, String type) {
		codeBlock.add("putfield " + frameId + "/" + field + " " + type);
	}
}

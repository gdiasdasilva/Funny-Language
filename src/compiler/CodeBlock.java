package compiler;

import java.io.File;
import java.util.List;

public interface CodeBlock {
	void writeToFile(File f);
	void insertOp(Op op);
	void insertIntArgument(int i);
	void insertThenLabel();
	void insertFinalLabel();
	void insertCondBranch(Cond cond);
	void insertGoto();
	List<String> getStringList();
}

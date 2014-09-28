package compiler;

import java.io.File;
import java.util.List;

public interface CodeBlock {
	void writeToFile(File f);
	void insertOp(Op op);
	void insertIntArgument(int i);
	List<String> getStringList();
}

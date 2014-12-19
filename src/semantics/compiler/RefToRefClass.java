package semantics.compiler;

class RefToRefClass {
	
	static final String REFVALUE_FIELD =      ".field                   public v Ljava/lang/Object;";
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(CodeBlock.CLASS_NAME_LINE + "refToRef" + "\n");
		sb.append(CodeBlock.SUPER_LINE + "\n");
		sb.append(REFVALUE_FIELD + "\n");
		sb.append(CodeBlock.INIT + "\n");
		sb.append(CodeBlock.ALOAD_0 + "\n");
		sb.append(CodeBlock.INVOKE_OBJECT_INIT + "\n");
		sb.append(CodeBlock.RETURN + "\n");
		sb.append(CodeBlock.END_METHOD + "\n");
		return sb.toString();
	}
	
}

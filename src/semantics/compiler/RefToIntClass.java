package semantics.compiler;

class RefToIntClass {
	
	static final String VALUE_FIELD =         ".field                   public v I";
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(CodeBlock.CLASS_NAME_LINE + "refToInt" + "\n");
		sb.append(CodeBlock.SUPER_LINE + "\n");
		sb.append(VALUE_FIELD + "\n");
		sb.append(CodeBlock.INIT + "\n");
		sb.append(CodeBlock.ALOAD_0 + "\n");
		sb.append(CodeBlock.INVOKE_OBJECT_INIT + "\n");
		sb.append(CodeBlock.RETURN + "\n");
		sb.append(CodeBlock.END_METHOD + "\n");
		return sb.toString();
	}
}

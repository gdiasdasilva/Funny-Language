package semantics.compiler;

class RefToRefClass {
	
	static final String REFVALUE_FIELD =      ".field                   public v Ljava/lang/Object;";
	
	final String refId;
	
	public RefToRefClass(int id) {
		refId = "ref_" + id;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(CodeBlock.CLASS_NAME_LINE + refId + "\n");
		sb.append(CodeBlock.SUPER_LINE + "\n");
		sb.append(REFVALUE_FIELD + "\n");
		sb.append(CodeBlock.END_METHOD + "\n");
		return sb.toString();
	}
	
}

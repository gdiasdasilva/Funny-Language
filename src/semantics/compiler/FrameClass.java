package semantics.compiler;

import java.util.List;

class FrameClass {
	
	static final String STATIC_LINK_FIELD =   ".field                   public sl L";
	static final String ID_FIELD =            ".field                   public ";
	
	final String frameId;
	final String ancestorFrameId;
	final List<Id> identifiers;

	FrameClass(String frameId, String ancestorFrameId, List<Id> ids) {
		this.frameId = frameId;
		this.ancestorFrameId = ancestorFrameId;
		identifiers = ids;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(CodeBlock.CLASS_NAME_LINE + frameId + "\n");
		sb.append(CodeBlock.SUPER_LINE + "\n");
		for (Id id : identifiers)
			sb.append(ID_FIELD + id.id + " " + id.type + "\n");
		if (ancestorFrameId.length() > 0)
			sb.append(STATIC_LINK_FIELD + ancestorFrameId + ";\n");
		sb.append(CodeBlock.INIT + "\n");
		sb.append(CodeBlock.ALOAD_0 + "\n");
		sb.append(CodeBlock.INVOKE_OBJECT_INIT + "\n");
		sb.append(CodeBlock.RETURN + "\n");
		sb.append(CodeBlock.END_METHOD + "\n");
		return sb.toString();
	}
}

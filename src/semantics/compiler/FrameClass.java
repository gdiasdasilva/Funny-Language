package semantics.compiler;

import java.util.List;

class FrameClass {
	
	static final String STATIC_LINK_FIELD =   ".field                   public sl L";
	static final String ID_FIELD =            ".field                   public ";
	
	final int frameId;
	final int ancestorFrameId;
	final List<Id> identifiers;

	FrameClass(int frameId, int ancestorFrameId, List<Id> ids) {
		this.frameId = frameId;
		this.ancestorFrameId = ancestorFrameId;
		identifiers = ids;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(CodeBlock.CLASS_NAME_LINE + frameId);
		sb.append(CodeBlock.SUPER_LINE + "\n");
		for (Id id : identifiers)
			sb.append(ID_FIELD + id.id + " " + id.type);
		if (frameId > 0)
			sb.append(STATIC_LINK_FIELD + CodeBlock.FRAME + ancestorFrameId);
		sb.append('\n');
		sb.append(CodeBlock.INIT);
		sb.append(CodeBlock.ALOAD_0);
		sb.append(CodeBlock.INVOKE_OBJECT_INIT);
		sb.append(CodeBlock.RETURN);
		sb.append(CodeBlock.END_METHOD);
		return sb.toString();
	}
}

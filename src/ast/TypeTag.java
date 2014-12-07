package ast;

import java.util.List;

public class TypeTag {
	
	private static TypeTag intTypeTag = null;
	private static TypeTag booleanTypeTag = null;
	private static TypeTag stringTypeTag = null;
	private static TypeTag commandTypeTag = null;
	
	protected TypeT typeTagId;
	
	public enum TypeT {
		INTEGER, BOOLEAN, REFERENCE, STRING, FUNCTION, COMMAND
	}
	
	public TypeT getTypeTagId() {
		return typeTagId;
	}
	
	private static final class IntTypeTag extends TypeTag {
		IntTypeTag() {
			typeTagId = TypeT.INTEGER;
		}
	}
	
	private static final class StringTypeTag extends TypeTag {
		StringTypeTag() {
			typeTagId = TypeT.STRING;
		}
	}
	
	private static final class BooleanTypeTag extends TypeTag {
		BooleanTypeTag() {
			typeTagId = TypeT.BOOLEAN;
		}
	}
	
	private static final class CommandTypeTag extends TypeTag {
		CommandTypeTag() {
			typeTagId = TypeT.COMMAND;
		}
	}
	
	public static final class RefTypeTag extends TypeTag {
		public final TypeTag referenceToTypeTag;
		public RefTypeTag(TypeTag t) {
			typeTagId = TypeT.REFERENCE;
			referenceToTypeTag = t;
		}
	}
	
	public static final class FunTypeTag extends TypeTag {
		public final List<TypeTag> paramTypeTags;
		public final TypeTag returnTypeTag;
		public FunTypeTag(List<TypeTag> paramTypeTags, TypeTag returnTypeTag) {
			this.paramTypeTags = paramTypeTags;
			this.returnTypeTag = returnTypeTag;
		}
	}
	
	public static TypeTag getIntTypeTag() {
		if (intTypeTag == null)
			intTypeTag = new IntTypeTag();
		return intTypeTag;
	}
	
	public static TypeTag getBooleanTypeTag() {
		if (booleanTypeTag == null)
			booleanTypeTag = new BooleanTypeTag();
		return booleanTypeTag;
	}
	
	public static TypeTag getStringTypeTag() {
		if (stringTypeTag == null)
			stringTypeTag = new StringTypeTag();
		return stringTypeTag;
	}
	
	public static TypeTag getCommandTypeTag() {
		if (commandTypeTag == null)
			commandTypeTag = new CommandTypeTag();
		return commandTypeTag;
	}
}

package semantics.typeSystem;

import java.util.Map;

import semantics.InvalidFieldNameException;

public class RecType implements Type {
	
	private final Map<String, Type> record;

	public RecType(Map<String, Type> record) {
		this.record = record;
	}
	
	public Type getTypeForField(String f) throws InvalidFieldNameException {
		Type v = record.get(f);
		if (v != null)
			return v;
		throw new InvalidFieldNameException("The record contains no such field " + f);
	}

	@Override
	public VType getType() {
		return VType.RECORD;
	}
	
	public String toString() {
		return "Record";
	}
}

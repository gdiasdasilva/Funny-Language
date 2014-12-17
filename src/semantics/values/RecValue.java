package semantics.values;

import java.util.Map;

import semantics.InvalidFieldNameException;
import semantics.typeSystem.Type.VType;

public class RecValue implements IValue {
	
	private final Map<String, IValue> record;

	public RecValue(Map<String, IValue> record) {
		this.record = record;
	}
	
	public IValue getValueForField(String f) throws InvalidFieldNameException {
		IValue v = record.get(f);
		if (v != null)
			return v;
		throw new InvalidFieldNameException("The record contains no such field " + f);
	}

	@Override
	public VType getType() {
		return VType.RECORD;
	}

}

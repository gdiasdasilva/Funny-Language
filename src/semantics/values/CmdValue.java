package semantics.values;

import semantics.typeSystem.Type.VType;

public class CmdValue implements IValue {

	@Override
	public String toString() {
		return "Command";
	}

	@Override
	public VType getType() {
		return VType.CMD;
	}
}

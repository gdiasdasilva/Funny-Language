package semantics.typeSystem;

public class CmdType implements IType {

	@Override
	public VType getType() {
		return VType.CMD;
	}
	
	public String toString() {
		return "cmd";
	}

}

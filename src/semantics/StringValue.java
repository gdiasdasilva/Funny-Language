package semantics;

public class StringValue extends IValue {

	String val;
	
	public StringValue(String val)
	{
		this.val = val;
	}
	
	@Override
	public VType typeOf() {
		return VType.STRING;
	}
	
	public String getVal() {
		return val;
	}

	public String toString() {
		return val;
	}

}

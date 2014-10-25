package semantics;

public class RefValue extends IValue {

	private IValue val = null;

	public VType typeOf() {
		return VType.REFERENCE;
	}

	public String toString() {
//		return "reference";
		return val.toString();
	}
	
	public IValue getVal()
	{
		return val;
	}
	
	public void setVal(IValue v)
	{
		val = v;
	}
	
}



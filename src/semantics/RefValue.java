package semantics;

public class RefValue implements IValue {

	IValue val;

	public RefValue()
	{
		
	}

	public VType typeOf() {
		return VType.REFERENCE;
	}

	public String toString() {
		return "referencia";
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



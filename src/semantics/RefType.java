package semantics;

public class RefType implements IType {

	public final IType type;
	
	public RefType(IType type)
	{
		this.type = type;
	}
	
	@Override
	public VType getType() {
		return VType.REFERENCE;
	}
	
	public IType getRefType()
	{
		return type;
	}
	
	public String toString() {
		return "Ref";
	}

}

package semantics.typeSystem;


public class RefType implements Type {

	final Type type;
	
	public RefType(Type type)
	{
		this.type = type;
	}
	
	@Override
	public VType getType() {
		return VType.REFERENCE;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RefType)
			if (type == null || ((RefType) obj).type == null)
				return true;
			else
				return (type.equals(((RefType) obj).type));
		return false;
	}

	public String toString() {
		return "Ref";
	}

}

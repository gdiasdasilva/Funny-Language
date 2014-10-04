package semantics;


public class BValue implements Value<Boolean> {
	
	public final Boolean b;

	public BValue(Boolean b) {
		this.b = b;
	}
	
	public static BValue fromBoolean(Boolean b) {
		return new BValue(b);
	}
	
	@Override
	public String toString() {
		return b.toString();
	}
}

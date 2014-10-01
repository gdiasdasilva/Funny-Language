package semantics;


public class IValue implements Value<Integer> {
	
	public final Integer i;

	public IValue(Integer i) {
		this.i = i;
	}
	
	public static IValue fromInteger(Integer i) {
		return new IValue(i);
	}
	
	@Override
	public String toString() {
		return i.toString();
	}

}

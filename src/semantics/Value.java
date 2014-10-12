package semantics;

public class Value<T> {
	
	public final T value;
	
	public Value(T value) {
		this.value = value;
	}
	
	public String toString() {
		return value.toString();
	}
}

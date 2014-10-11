package semantics;

public interface Env<T>
{
	void beginScope();
	void endScope();
	void assoc(String id, Value<T> val);
	Value<T> find(String id);
}
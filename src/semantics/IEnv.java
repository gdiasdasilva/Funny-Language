package semantics;

public interface IEnv
{
	void beginScope();
	void endScope();
	void assoc(String id, Value val);
	Value find(String id);
}
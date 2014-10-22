package semantics;

public interface IEnv
{
	void beginScope();
	void endScope();
	void assoc(String id, IValue val);
	IValue find(String id) throws UndefinedIdException;
}
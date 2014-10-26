package semantics;

public interface IEnv
{
	void beginScope();
	void endScope();
	void assoc(String id, IValue val) throws IdentiferDeclaredTwiceException;
	IValue find(String id) throws UndefinedIdException;
}
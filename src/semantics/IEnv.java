package semantics;

public interface IEnv
{
	IEnv beginScope();
	IEnv endScope();
	void assoc(String id, IValue val) throws IdentiferDeclaredTwiceException;
	IValue find(String id) throws UndefinedIdException;
}
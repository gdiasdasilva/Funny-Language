package semantics;

public interface Environment<T>
{
	Environment<T> beginScope();
	Environment<T> endScope();
	void assoc(String id, T val) throws IdentiferDeclaredTwiceException;
	T find(String id) throws UndefinedIdException;
}
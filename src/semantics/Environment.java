package semantics;

public interface Environment<T>
{
	Environment<T> beginScope();
	Environment<T> endScope();
}
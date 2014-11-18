package semantics;

public interface IEnv
{
	IEnv beginScope();
	IEnv endScope();
}
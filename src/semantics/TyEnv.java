package semantics;

public class TyEnv implements IEnv {

	@Override
	public IEnv beginScope() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEnv endScope() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assoc(String id, IValue val)
			throws IdentiferDeclaredTwiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public IValue find(String id) throws UndefinedIdException {
		// TODO Auto-generated method stub
		return null;
	}

}

package semantics.typeSystem;

import java.util.HashMap;
import java.util.Map;

import semantics.IEnv;
import semantics.IdentiferDeclaredTwiceException;
import semantics.UndefinedIdException;

public class TyEnv implements IEnv {

	private TyEnv upper;
	private Map<String, IType> d;
	
	private TyEnv(TyEnv e)
	{
		upper = e;
		d = new HashMap<String, IType>();
	}
	
	public TyEnv()
	{
		this(null);
	}

	@Override
	public IEnv beginScope()
	{
		TyEnv e = new TyEnv(this);
		return e;
	}

	@Override
	public IEnv endScope()
	{
		if (upper != null)
			upper = upper.upper;
		return this;
	}
	
	public void assoc(String id, IType val) throws IdentiferDeclaredTwiceException
	{
		if (!d.containsKey(id))
			d.put(id, val);
		else
			throw new IdentiferDeclaredTwiceException();
	}
	
	public IType find(String id) throws UndefinedIdException
	{		
		IType v = d.get(id);
		if (v != null)
			return v;
		else if (upper != null)
			return upper.find(id);
		throw new UndefinedIdException("Undefined id '" + id + "' in this scope.");
	}

}
